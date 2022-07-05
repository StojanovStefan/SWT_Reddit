<template>
  <li class="grid">
    <div class="flex flex-col row-start-1 text-center">
      <button
        :disabled="reaction?.type == ReactionType.LIKE"
        class="blue"
        @click="react(ReactionType.LIKE)"
      >
        up
      </button>
      <p class="p-2 font-semibold text-lg">{{ postKarma }}</p>
      <button
        :disabled="reaction?.type == ReactionType.DISLIKE"
        class="red"
        @click="react(ReactionType.DISLIKE)"
      >
        down
      </button>
      <router-link
        class="btn mt-2"
        v-if="
          userStore.currentUser.user_data != null &&
          userStore.currentUser.user_data['id'] == post.user?.id
        "
        :to="{ name: 'ManagePost', params: { id: post.id } }"
        >edit</router-link
      >
    </div>
    <div :to="'/r/' + post.community?.name + '/' + post.id" class="col-start-2">
      <p class="text-sm">
        <em v-if="!isInCommunity">r/{{ post.community?.name }} • </em>Posted by
        u/{{ post.user?.username }}
      </p>
      <h1 class="text-2xl text-slate-700">{{ post.title }}</h1>
      <p class="py-2">{{ post.text }}</p>
      <img v-if="post.imagePath" :src="post.imagePath" />
    </div>
  </li>
  <hr />
</template>

<script lang="ts" setup>
import { onMounted, Ref, ref } from 'vue';
import { SERVER_URI } from '../../constants';
import Post from '../../models/Post';
import { Reaction, ReactionType } from '../../models/Reaction';
import { useCurrentUserStore } from '../../stores/currentUser';

const props = defineProps({
  isInCommunity: {
    type: Boolean,
    default: false
  },
  post: {
    type: Post,
    required: true
  }
});

let postKarma = ref(0);
let postReactions: Ref<Reaction[]> = ref([]);
let reaction: Ref<Reaction | undefined> = ref(new Reaction());

const userStore = useCurrentUserStore();

function calculateKarma() {
  postKarma.value = 0;
  postReactions.value.map((reaction) => {
    postKarma.value += reaction.type == ReactionType.LIKE ? 1 : -1;
  });
}

async function loadReactions() {
  const response = await fetch(`${SERVER_URI}/reaction/post/${props.post.id}`, {
    method: 'GET'
  });
  if (!response.ok) {
    return;
  }
  postReactions.value = await response.json();
  if (response.ok) {
    reaction.value = postReactions.value.find(
      (reaction) =>
        reaction.user != null &&
        userStore.currentUser.user_data != null &&
        reaction.user['id'] == userStore.currentUser.user_data['id']
    );
  }
}

async function react(type: ReactionType) {
  if (userStore.isLogedIn && userStore.currentUser.user_data != null) {
    reaction.value = postReactions.value.find(
      (reaction) =>
        reaction.user != null &&
        userStore.currentUser.user_data != null &&
        reaction.user['id'] == userStore.currentUser.user_data['id']
    );
    if (
      reaction.value != null &&
      reaction.value.type != type &&
      reaction.value.user != null &&
      reaction.value.post != null &&
      reaction.value.user['id'] == userStore.currentUser.user_data['id']
    ) {
      const response = await fetch(
        `${SERVER_URI}/reaction/${reaction.value.id}`,
        {
          method: 'PUT',
          headers: {
            'Content-Type': 'application/json',
            Authorization: `Bearer ${userStore.currentUser.access_token}`
          },
          body: JSON.stringify({
            user: {
              id: reaction.value.user['id']
            },
            post: {
              id: reaction.value.post['id']
            },
            type: type
          })
        }
      );
      if (!response.ok) {
        throw new Error('Failed to create reaction');
      }
      if (response.ok) {
        postReactions.value.find((oldReaction) => {
          if (
            reaction.value != null &&
            reaction.value['id'] == oldReaction['id']
          ) {
            oldReaction.type = type;
            calculateKarma();
          }
        });
      }
    } else if (reaction.value == null || reaction.value['id'] == null) {
      const response = await fetch(`${SERVER_URI}/reaction`, {
        method: 'POST',
        headers: {
          'Content-Type': 'application/json',
          Authorization: `Bearer ${userStore.currentUser.access_token}`
        },
        body: JSON.stringify({
          user: {
            id: userStore.currentUser.user_data['id']
          },
          post: {
            id: props.post.id
          },
          type: type
        })
      });
      if (!response.ok) {
        throw new Error('Failed to create reaction');
      }
      if (response.ok) {
        reaction.value = await response.json();
        if (reaction.value != null) {
          postReactions.value.push(reaction.value);
        }

        calculateKarma();
      }
    }
  }
}
onMounted(async () => {
  await loadReactions();
  calculateKarma();
});
</script>

<style lang="postcss" scoped>
li {
  grid-template-columns: min-content auto;
}
li div:first-child {
  @apply px-4;
}
img {
  object-fit: cover;
  width: 100%;
  height: auto;
  aspect-ratio: 2/1;
}
</style>

<template>
  <section>
    <h1>
      <em>r/{{ community.name }}</em> Recent Posts
    </h1>

    <div v-if="userStore.isLogedIn" class="flex gap-2 mt-8">
      <RouterLink
        class="btn"
        :to="{
          name: 'ManageCommunity',
          params: { id: community.id }
        }"
        v-if="community.id != null"
      >
        Edit Community
      </RouterLink>
      <RouterLink
        class="btn"
        :to="{
          name: 'CreatePost',
          query: {
            communityId: community.id
          }
        }"
        v-if="community.id != null"
      >
        Create Post
      </RouterLink>
    </div>
    <div v-if="posts.length < 1" class="text-center max-w-xl py-12">
      <h1 class="m-auto">Community has no posts!</h1>
    </div>
    <ul v-else class="flex flex-col gap-8 max-w-xl mt-8 mb-28">
      <PostComponent
        v-for="post in posts.reverse()"
        :key="post.id"
        :post="post"
        :isInCommunity="true"
      ></PostComponent>
    </ul>
  </section>
</template>
<script lang="ts" setup>
import { Ref, ref } from '@vue/reactivity';
import { onMounted } from 'vue';
import { useRoute } from 'vue-router';
import { SERVER_URI } from '../../constants';
import Community from '../../models/Community';

import Post from '../../models/Post';
import User from '../../models/User';
import { useCurrentUserStore } from '../../stores/currentUser';
import PostComponent from '../components/PostComponent.vue';

const route = useRoute();

const userStore = useCurrentUserStore();
const currentUserData: User | null | undefined =
  userStore.currentUser.user_data;

let posts: Ref<Post[]> = ref([]);
let community: Ref<Community> = ref(new Community());

async function loadCommunity() {
  const response = await fetch(
    SERVER_URI + '/community/name/' + route.params.community
  );
  if (!response.ok) {
    console.log(response.statusText);
  }
  community.value = (await response.json())[0];
}

async function loadPosts() {
  if (community.value.id == null) {
    return;
  }
  const response = await fetch(
    SERVER_URI + '/post/community/' + community.value.id
  );
  if (!response.ok) {
    console.log(response.status);
  }
  if (response.ok) {
    posts.value = await response.json();
  }
}

onMounted(async () => {
  await loadCommunity();
  await loadPosts();
});
</script>

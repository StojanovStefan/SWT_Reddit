<template>
  <h1 class="text-center my-8" v-if="route.params.id">Edit Post</h1>
  <h1 class="text-center my-8" v-else>Create Post</h1>
  <form @submit.prevent="submit">
    <input
      type="text"
      name="title"
      id="title"
      placeholder="Title"
      v-model="post.title"
      required
    />
    <textarea
      name="text"
      cols="30"
      rows="10"
      id="text"
      placeholder="Text"
      v-model="post.text"
      required
    ></textarea>
    <input
      type="text"
      name="image"
      id="image"
      placeholder="Image URL"
      v-model="post.imagePath"
    />
    <button>
      <span v-if="route.params.id">Update</span>
      <span v-else>Create</span>
      post
    </button>
    <button
      v-if="route.params.id"
      type="button"
      class="red"
      @click="deletePost"
    >
      Delete post
    </button>
  </form>
</template>

<script lang="ts" setup>
import { onMounted, ref, Ref } from 'vue';
import { useRoute, useRouter } from 'vue-router';
import { SERVER_URI } from '../../constants';
import Community from '../../models/Community';
import Post from '../../models/post';
import User from '../../models/User';
import { useCurrentUserStore } from '../../stores/currentUser';

const route = useRoute();
const router = useRouter();

const userStore = useCurrentUserStore();

let post: Ref<Post> = ref(new Post());

async function findPost() {
  if (route.params.id) {
    const response = await fetch(`${SERVER_URI}/post/${route.params.id}`, {
      method: 'GET',
      headers: {
        'Content-Type': 'application/json'
      }
    });
    if (!response.ok) {
      throw new Error(response.statusText);
    }
    post.value = await response.json();
    if (userStore.currentUser.user_data != null) {
      post.value.user = new User();
      post.value.user.id = userStore.currentUser.user_data['id'];
    }
  }
}

async function submit() {
  if (route.params.id) {
    const response = await fetch(`${SERVER_URI}/post/${route.params.id}`, {
      method: 'PUT',
      headers: {
        'Content-Type': 'application/json',
        Authorization: `Bearer ${userStore.currentUser.access_token}`
      },
      body: JSON.stringify(post.value)
    });
    if (!response.ok) {
      throw new Error(response.statusText);
    }
  } else {
    const response = await fetch(`${SERVER_URI}/post`, {
      method: 'POST',
      headers: {
        'Content-Type': 'application/json',
        Authorization: `Bearer ${userStore.currentUser.access_token}`
      },
      body: JSON.stringify(post.value)
    });
    if (!response.ok) {
      throw new Error(response.statusText);
    }
  }
  router.push({ name: 'Home' });
}

async function deletePost() {
  const response = await fetch(`${SERVER_URI}/post/${route.params.id}`, {
    method: 'DELETE',
    headers: {
      Authorization: `Bearer ${userStore.currentUser.access_token}`
    }
  });
  if (!response.ok) {
    throw new Error(response.statusText);
  }
  router.back();
}

onMounted(() => {
  post.value.community = new Community();
  if (route.query.communityId) {
    post.value.community.id = parseInt(route.query.communityId.toString());
  }
  post.value.user = new User();
  if (userStore.currentUser.user_data != undefined) {
    post.value.user.id = userStore.currentUser.user_data['id'];
  }
  if (route.params.id) {
    findPost();
  }
});
</script>

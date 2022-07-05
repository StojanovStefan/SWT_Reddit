<template>
  <h1 class="text-center my-8" v-if="route.params.id">Edit Community</h1>
  <h1 class="text-center my-8" v-else>Create Community</h1>
  <form @submit.prevent="submit">
    <input
      type="text"
      name="name"
      id="name"
      placeholder="Name"
      v-model="community.name"
    />
    <input
      type="text"
      name="description"
      id="description"
      placeholder="description"
      v-model="community.description"
    />
    <p class="text-red-500" v-if="hasErrors">
      <b>Can't cahnge this community</b>
    </p>
    <button>
      <span v-if="route.params.id">Update</span>
      <span v-else>Create</span>
      Community
    </button>
  </form>
</template>

<script lang="ts" setup>
import { onMounted, ref, Ref } from 'vue';
import { useRoute, useRouter } from 'vue-router';
import { SERVER_URI } from '../../constants';
import Community from '../../models/Community';
import { useCurrentUserStore } from '../../stores/currentUser';

const route = useRoute();
const router = useRouter();

let hasErrors = ref(false);

const userStore = useCurrentUserStore();

let community: Ref<Community> = ref(new Community());

async function findCommunity() {
  if (route.params.id) {
    const response = await fetch(`${SERVER_URI}/community/${route.params.id}`, {
      method: 'GET',
      headers: {
        'Content-Type': 'application/json'
      }
    });
    if (!response.ok) {
      hasErrors.value = true;
      throw new Error(response.statusText);
    }
    community.value = await response.json();
  }
}

async function submit() {
  if (route.params.id) {
    const response = await fetch(`${SERVER_URI}/community/${route.params.id}`, {
      method: 'PUT',
      headers: {
        'Content-Type': 'application/json',
        Authorization: `Bearer ${userStore.currentUser.access_token}`
      },
      body: JSON.stringify(community.value)
    });
    if (!response.ok) {
      hasErrors.value = true;
      throw new Error(response.statusText);
    }
  } else {
    const response = await fetch(`${SERVER_URI}/community`, {
      method: 'POST',
      headers: {
        'Content-Type': 'application/json',
        Authorization: `Bearer ${userStore.currentUser.access_token}`
      },
      body: JSON.stringify(community.value)
    });
    if (!response.ok) {
      hasErrors.value = true;
      throw new Error(response.statusText);
    }
  }
  router.push({ name: 'Home' });
}

onMounted(() => {
  if (route.params.id) {
    findCommunity();
  }
});
</script>

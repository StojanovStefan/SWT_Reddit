<template>
  <h1 class="text-center my-8">{{ userData.displayName }}'s Profile</h1>

  <form @submit.prevent="updateUser">
    <input
      type="text"
      v-model="userData.username"
      placeholder="Username"
      required
    /><input
      type="text"
      v-model="userData.displayName"
      placeholder="Display name"
      required
    />
    <input type="text" v-model="userData.email" placeholder="Email" required />
    <input
      type="password"
      v-model="userData.password"
      placeholder="Password"
      required
    />
    <input
      type="password"
      v-model="passwordConfirmation"
      placeholder="Password confirmation"
      required
    />
    <button class="btn" type="submit">Update</button>
    <button type="button" class="red" @click="logout">Log out</button>
  </form>
</template>

<script lang="ts" setup>
import { Ref, ref } from 'vue';
import { useRouter } from 'vue-router';
import { SERVER_URI } from '../../constants';
import User from '../../models/User';
import { useCurrentUserStore } from '../../stores/currentUser';

const router = useRouter();
const currentUser = useCurrentUserStore();
let userData: Ref<User> = ref(currentUser.currentUser.user_data ?? new User());
userData.value.password = '';
let passwordConfirmation: Ref<string> = ref('');

async function updateUser() {
  if (userData.value.password !== passwordConfirmation.value) {
    alert('Passwords do not match');
    passwordConfirmation.value = '';
    userData.value.password = '';
    return;
  }
  const response = await fetch(`${SERVER_URI}/user/${userData.value.id}`, {
    method: 'PUT',
    headers: {
      'Content-Type': 'application/json',
      Authorization: `Bearer ${currentUser.currentUser.access_token}`
    },
    body: JSON.stringify({
      username: userData.value.username,
      displayName: userData.value.displayName,
      email: userData.value.email,
      password: userData.value.password,
      passwordConfirmation: passwordConfirmation.value
    })
  });
  if (!response.ok) {
    throw new Error(response.statusText);
  }
  console.info('User updated');
  passwordConfirmation.value = '';
  userData.value.password = '';
}

function logout() {
  currentUser.logout();
  router.push({ name: 'Home' });
}
</script>

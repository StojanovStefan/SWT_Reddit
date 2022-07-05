<template>
  <main>
    <h1 class="text-center my-8">Please register</h1>
    <form @submit.prevent="register">
      <input
        type="text"
        name="username"
        id="username"
        placeholder="Username"
        v-model="newUser.username"
      />
      <input
        type="text"
        name="displayName"
        id="displayName"
        placeholder="Display name"
        v-model="newUser.displayName"
      />
      <input
        type="text"
        name="email"
        id="email"
        placeholder="Email"
        v-model="newUser.email"
      />

      <input
        type="password"
        name="password"
        id="password"
        placeholder="Password"
        v-model="newUser.password"
      />
      <input
        type="password"
        name="password"
        id="password"
        placeholder="Verify Password"
        v-model="verPassword"
      />
      <p class="text-red-500" v-if="hasErrors">
        <b>Can't register with given credentials</b>
      </p>
      <button class="btn" type="submit">Register</button>
    </form>
  </main>
</template>

<script lang="ts" setup>
import { useRouter } from 'vue-router';
import { ref, Ref } from '@vue/reactivity';
import { useCurrentUserStore } from '../../stores/currentUser';
import User from '../../models/User';
import { SERVER_URI } from '../../constants';

let newUser: Ref<User> = ref(new User());
let verPassword = ref('');
let hasErrors: Ref<boolean> = ref(false);

const router = useRouter();

const currentUser = useCurrentUserStore();

async function register(this: any) {
  try {
    if (newUser.value.password !== verPassword.value) {
      hasErrors.value = true;
      return;
    }
    const response = await fetch(`${SERVER_URI}/user`, {
      method: 'POST',
      headers: {
        'Content-Type': 'application/json'
      },
      body: JSON.stringify({
        username: newUser.value.username,
        displayName: newUser.value.displayName,
        email: newUser.value.email,
        password: newUser.value.password
      })
    });
    if (!response.ok) {
      hasErrors.value = true;
      return;
    }
    router.push({ name: 'Login' });
  } catch (e) {
    hasErrors.value = true;
  }
}
</script>

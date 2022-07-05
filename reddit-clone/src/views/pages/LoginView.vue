<template>
  <main>
    <h1 class="text-center my-8">Please log in</h1>
    <form @submit.prevent="login">
      <input type="text" name="username" id="username" v-model="username" required />
      <input type="password" name="password" id="password" v-model="password" required />
      <p class="text-red-500" v-if="hasErrors">
        <b>Can't login with given credentials</b>
      </p>
      <button class="btn" type="submit">Login</button>
      <RouterLink
        class="underline text-blue-500 text-center"
        :to="{ name: 'Register' }"
        >Register as new user</RouterLink
      >
    </form>
  </main>
</template>

<script lang="ts" setup>
import { useRouter } from 'vue-router';
import { ref, Ref } from '@vue/reactivity';
import { useCurrentUserStore } from '../../stores/currentUser';

let username = ref('');
let password = ref('');
let hasErrors: Ref<boolean> = ref(false);

const router = useRouter();

const currentUser = useCurrentUserStore();

async function login(this: any) {
  try {
    await currentUser.login(username.value, password.value);
    if (currentUser.isLogedIn) {
      hasErrors.value = false;
      router.push({ name: 'Home' });
      return;
    }
  } catch (e) {
    hasErrors.value = true;
  }
}
</script>

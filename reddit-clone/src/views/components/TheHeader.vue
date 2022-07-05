<template>
  <header
    class="h-16 bg-slate-800 text-slate-400 w-full flex justify-between items-center px-8"
  >
    <router-link to="/">Communities</router-link>
    <router-link
      v-if="currentUser.isLogedIn && currentUser.currentUser.user_data != null"
      to="/profile"
      >u/{{ currentUser.currentUser.user_data['username'] }}</router-link
    >
    <router-link v-else to="/login">Login</router-link>
  </header>
</template>

<script lang="ts" setup>
import { useCurrentUserStore } from '../../stores/currentUser';
const currentUser = useCurrentUserStore();
</script>

<style lang="postcss" scoped>
.router-link-exact-active {
  @apply text-slate-200;
}
</style>

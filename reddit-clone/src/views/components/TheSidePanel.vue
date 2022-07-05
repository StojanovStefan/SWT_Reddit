<template>
  <section>
    <ul>
      <h1 class="h2">Dashboard</h1>
      <li>
        <router-link
          :to="{
            name: 'Home'
          }"
          >Home</router-link
        >
      </li>
      <router-link
        :to="{
          name: 'CreateCommunity'
        }"
        >Create Community</router-link
      >
    </ul>
    <br />
    <h1 class="h2">Communities</h1>
    <ul>
      <li
        v-for="community in communities.sort((a, b) => {
          return (a.name || '').localeCompare(b.name || '');
        })"
        :key="community.id"
      >
        <router-link
          :to="{
            name: 'Community',
            params: { community: community.name }
          }"
        >
          r/{{ community.name }}</router-link
        >
      </li>
    </ul>
  </section>
</template>
<script lang="ts" setup>
import Community from '../../models/Community';
import { Ref, ref } from '@vue/reactivity';
import { onMounted } from 'vue';
import { SERVER_URI } from '../../constants';

let communities: Ref<Community[]> = ref([]);

onMounted(async () => {
  const response = await fetch(SERVER_URI + '/community');
  if (!response.ok) {
    throw new Error(response.statusText);
  }
  communities.value = await response.json();
});
</script>

<style lang="postcss" scoped>
section {
  @apply pt-4 pl-4;
}
.router-link-exact-active {
  @apply text-slate-800 font-bold;
}
</style>

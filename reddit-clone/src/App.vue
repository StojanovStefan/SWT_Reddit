<script setup lang="ts">
import TheHeader from './views/components/TheHeader.vue';
</script>

<template>
  <TheHeader></TheHeader>
  <RouterView></RouterView>
</template>

<style>
html,
body,
#app {
  font-family: Roboto, Helvetica, Arial, sans-serif;
  -webkit-font-smoothing: antialiased;
  -moz-osx-font-smoothing: grayscale;
  margin: 0;
  box-sizing: border-box;
  min-height: 100vh;
}
</style>

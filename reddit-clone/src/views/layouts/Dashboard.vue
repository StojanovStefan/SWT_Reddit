<template>
  <section class="grid">
    <TheSidePanel></TheSidePanel>
    <RouterView :key="$route.fullPath" class="pt-4"></RouterView>
  </section>
</template>

<script lang="ts" setup>
import TheSidePanel from '../components/TheSidePanel.vue';
</script>

<style lang="postcss" scoped>
section {
  grid-template-columns: minmax(10rem, 20vw) auto;
}
</style>

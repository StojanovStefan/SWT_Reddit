<template>
  <main>
    <h1>Latest Posts</h1>
    <ul class="flex flex-col gap-8 max-w-xl pt-8 mb-28">
      <PostComponent
        v-for="post in posts.reverse()"
        :key="post.id"
        :post="post"
      ></PostComponent>
    </ul>
  </main>
</template
>
<script lang="ts" setup>
import Post from '../../models/Post';
import PostComponent from '../components/PostComponent.vue';
import { ref, Ref } from '@vue/reactivity';
import { onMounted } from 'vue';
import { SERVER_URI } from '../../constants';

let posts: Ref<Post[]> = ref([]);

onMounted(async () => {
  const postsResponse = await fetch(SERVER_URI + '/post', {
    method: 'GET'
  });
  if (!postsResponse.ok) {
    return;
  }
  posts.value = await postsResponse.json();
});
</script>

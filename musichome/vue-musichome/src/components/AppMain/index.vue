<template>
<div class="main"> 
        <!-- v-show 当路由为 /home 不显示 -->
    <app-link v-show="$route.path " />

   <!-- 子路由渲染出口 -->
    <router-view></router-view>
   
</div>
</template>
<script>
import AppLink from "./Link.vue";
export default {
  components: { AppLink }
};
</script>
<style scoped>

</style>

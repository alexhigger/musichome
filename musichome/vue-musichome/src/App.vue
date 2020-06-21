<template>
  <div id="app">
    <!-- 路由组件渲染出口 -->
    <router-view></router-view>
  </div>
</template>

 <style >
/* 上面不要加 scoped */
body {
  font-family: "微软雅黑";
  /* 满屏显示, 不显示默认边距 margin:8px, */
  margin: 0px auto;
}
</style>
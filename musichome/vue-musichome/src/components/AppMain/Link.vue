
<template>
  <el-breadcrumb separator-class="el-icon-arrow-right" separator="/">
    <el-breadcrumb-item class="line" :to="{ path: $route.path }">{{$route.meta.title}}</el-breadcrumb-item>
  </el-breadcrumb>
</template>
<style scoped>

.el-breadcrumb {
  height: 5px;
  padding: 15px;
  border-radius: 4px;
  background-color: #ddecf8;
  /* 投影 */
 box-shadow: 0 2px 4px rgba(0, 0, 0, .12), 0 0 6px rgba(0, 0, 0, .04)
}
.line {
  border-left: 3px solid #31c17b;
  padding-left: 10px;
}
</style>
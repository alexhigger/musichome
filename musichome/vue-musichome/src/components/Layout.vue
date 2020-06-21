<template>
  <div style="overflow:scrol">
    <!-- 头部区域 -->
    <app-header />
    <!-- 左侧导航区域 -->
    <app-navbar />
    <!-- 右侧主区域 -->
    <app-main />
  </div>
</template>

<script>
// 1. 导入子组件
import AppHeader from "./AppHeader";
import AppNavbar from "./AppNavbar";
import AppMain from "./AppMain";
export default {
  // 2. 注册为子组件
  components: {
    AppHeader,
    AppNavbar,
    AppMain
  }
};
</script>
<style scoped>
/* 头部区域 */
.header {
  position: absolute;
  padding: 0px;
  height: 69px;
  top: 0px;
  left: 220px;
  right: 0px;
 
  box-shadow: 0 2px 4px rgba(0, 0, 0, 0.12), 0 0 6px rgba(0, 0, 0, 0.04);
}
/* 左侧导航栏 */
.navbar {
  position: absolute;
  width: 220px;
  top: 0px;
  left: 0px;
  bottom: 0px;
  overflow-y: auto;
  background-color: #304156;
  
}
/* 右侧主区域 */
.main {
  position: absolute;
  top: 70px;
  left: 220px;
  right: 0px;
  bottom: 0px;
  padding: 3px;
  overflow-y: auto;
  background-color: #f5f5f5;
}

</style>
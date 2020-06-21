<template>
  <div class="navbar">

 <el-menu
      :router="true"
      :default-active="$route.path"
      class="el-menu-vertical-demo"
      background-color="#304156"
      
      text-color="#fff"
      active-text-color="#ffd04b">
          <div class="d1">
      <img class="logo" src="@/assets/logo.png" width="50px" />
      <span class="company">程序员的吉他库</span>
    </div>
      <el-submenu index="1">
        <template slot="title">
          <i class="el-icon-folder-opened"></i>
          <span>吉他谱</span>
        </template>
        <el-menu-item-group>
          <template slot="title">弹唱谱</template>
          <el-menu-item index="/my/" style="">民谣</el-menu-item>
          <el-menu-item index="/lx/">流行</el-menu-item>
          <el-menu-item index="/yg/">摇滚</el-menu-item>
        </el-menu-item-group>
        <el-menu-item-group title="指弹谱">
          <el-menu-item index="/zgf/">中国风</el-menu-item>
          <el-menu-item index="/rh/">日韩</el-menu-item>
          <el-menu-item index="/om/">欧美</el-menu-item>
        </el-menu-item-group>
      </el-submenu>
      <el-menu-item index="2">
        <i class="el-icon-menu"></i>
        <span slot="title">导航二</span>
      </el-menu-item>
      <el-menu-item index="3" disabled>
        <i class="el-icon-document"></i>
        <span slot="title">导航三</span>
      </el-menu-item>
      <el-menu-item index="4">
        <i class="el-icon-setting"></i>
        <span slot="title">导航四</span>
      </el-menu-item>
    </el-menu>
  
  </div>
</template>
<script>
  export default {
    data() {
      return {
       
      };
    },
    methods: {
     
    }
  }
</script>
<style scoped>
.logo {
  vertical-align: middle;
  /* 上 右 下 左 */
  padding: 0px 0px 0 20px;
}
.company {
  position: absolute;
  color: white;
  padding: 12px 10px 0 20px;
}
.d1 {
  background-color: black;
  height: 50px;
  border-radius: 8px;
}
</style>



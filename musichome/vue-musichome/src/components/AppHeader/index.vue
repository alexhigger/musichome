<template>
<div class="header"  >   <!-- 头部左侧Logo和标题 -->
    <!-- 头部右侧下拉菜单 -->
    <!-- <el-dropdown @command="handleCommand"> -->
    <el-dropdown >
      <div class="el-dropdown-link">
        <i class="el-icon-user"></i>:
        <!-- {{user.name}} -->
        admin
        <i class="el-icon-arrow-down el-icon--right"></i>
      </div>
      <el-dropdown-menu slot="dropdown">
        <!-- <el-dropdown-item command="a" icon="el-icon-edit-outline">修改密码</el-dropdown-item>
        <el-dropdown-item command="b" icon="el-icon-switch-button">退出系统</el-dropdown-item> -->
        <el-dropdown-item  icon="el-icon-edit-outline">修改密码</el-dropdown-item>
        <el-dropdown-item  icon="el-icon-switch-button">退出系统</el-dropdown-item>
      </el-dropdown-menu>
    </el-dropdown>
    <!-- 修改密码弹出的部分 ---------------------------------------------------->
    <!-- <el-dialog title="修改密码" :visible.sync="dialogFormVisible" width="400px ">
      <el-form :model="ruleForm" status-icon :rules="rules" ref="ruleForm" label-width="100px">
        <el-form-item label="原密码" prop="oldPass">
          <el-input type="password" v-model="ruleForm.oldPass" autocomplete="off"></el-input>
        </el-form-item>
        <el-form-item label="新密码" prop="newPass" autocomplete="off">
          <el-input type="password" v-model.number="ruleForm.newPass"></el-input>
        </el-form-item>
        <el-form-item label="确认密码" prop="checkPass">
          <el-input type="password" v-model="ruleForm.checkPass" autocomplete="off"></el-input>
        </el-form-item>

        <el-form-item>
          <el-button type="primary" @click="submitForm('ruleForm')">提交</el-button>
          <el-button @click="$refs['ruleForm'].resetFields()">重置</el-button>
        </el-form-item>
      </el-form>
    </el-dialog> -->
    </div>
</template>
<script>
export default {
}
</script>
<style scoped>
.header{
    background-image: url("hdbg.png");
}
/* 下拉菜单 */
.el-dropdown {
  float: right;
  margin-right: 40px;
  margin-top: 20px;
}
.el-dropdown-link {
  cursor: pointer;
  color: black;
}
</style>
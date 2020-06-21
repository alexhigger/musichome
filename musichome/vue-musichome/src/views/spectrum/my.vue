<template>
  <div class="my">
    <!-- 上方区域 -------------------------------------------------------------------------->
    <el-form
      ref="searchForm"
      :inline="true"
      :model="searchMap"
      style="margin-top: 20px;margin-left:20px"
    >
      <el-form-item prop="name">
        曲谱名
        <el-input v-model="searchMap.name" placeholder="" style="width: 200px;"></el-input>
      </el-form-item>
      <el-form-item prop="author">
        作者名
        <el-input v-model="searchMap.author" placeholder="" style="width:200px;"></el-input>
      </el-form-item>
      <el-form-item>
        <el-button type="success" icon="el-icon-search" circle @click="findMys"></el-button>
        <el-button type="info" round @click="resetForm('searchForm')">重置</el-button>
        <el-button type="warning" round icon="el-icon-circle-plus-outline" @click="handleAdd">新增</el-button>
      </el-form-item>
    </el-form>
    <!-- ------------------------------------------------------------------------ -->
    <!-- 列表-->
    <!-- :data 绑定渲染的数据；border: 边框; -->
    <el-table :data="list" stripe border style="width: 100%">
      <!--type="index" 获取索引值，从1开始； label 显示的标题; prop 数据字段名； width 列的宽度 -->
      <el-table-column type="index" label="序号" width="50"></el-table-column>
      <el-table-column prop="name" label="曲谱名称" width="250"></el-table-column>
      <el-table-column prop="author" label="作者"></el-table-column>
      <el-table-column label="操作" width="500">
        <template slot-scope="scope">
          <el-button type="primary" round  icon="el-icon-view" @click="pu(scope.row.id)"></el-button>
          <el-button type="success" plain icon="el-icon-edit" circle @click="handleEdit(scope.row.id)"></el-button>
          <el-button type="danger" plain icon="el-icon-delete" circle @click="deleMyById(scope.row.id)"></el-button>
          <el-upload
          :show-file-list="false"
           multiple
            style="display:inline-flex;margin-left:20px"
            class="upload-demo"         
            :before-upload="handleBeforeUpload"
            :on-success="onSuccess"
            :data="{myid:scope.row.id}"
            action="http://localhost:8181/my/img"            
          >
            <el-button  type="success" plain round="" :icon="importDataBtnIcon">{{importDataBtnText}}</el-button>
          </el-upload>
           <el-upload
          :show-file-list="false"
           multiple
            style="display:inline-flex;margin-left:20px"
            class="upload-demo"    
            :data="{myid:scope.row.id}"
            action="http://localhost:8181/my/updateImg"  
          >
            <el-button  type="primary" plain round >更新图片</el-button>
          </el-upload>
        </template>
      </el-table-column>
    </el-table>
    <!-- 下方页码区域-------------------------------------------------------------------- -->
    <!-- 分页，添加在div里面 -->
    <el-pagination
      @size-change="handleSizeChange"
      @current-change="handleCurrentChange"
      :current-page="currentPage"
      :page-sizes="[1,5, 8, 10]"
      :page-size="pageSize"
      layout="total, sizes, prev, pager, next, jumper"
      :total="total"
    ></el-pagination>
    <!-- ------------点击弹出的页面 ------------------------------------------------------>
    <el-dialog title :visible.sync="dialogFormVisible" width="400px">
      <el-form class="addform" ref="form" :model="form" label-width="100px">
        <el-form-item label="曲谱名" prop="name">
          <el-input v-model="form.name" style="width:230px"></el-input>
        </el-form-item>
        <el-form-item label="作者名" prop="author">
          <el-input v-model="form.author" style="width:230px"></el-input>
        </el-form-item>
        <el-form-item label="曲谱大类型" prop="btype">
          <el-input disabled style="width:230px" placeholder="弹唱谱"></el-input>
        </el-form-item>
        <el-form-item label="曲谱小类型" prop="stype">
          <el-input disabled style="width:230px" placeholder="民谣"></el-input>
        </el-form-item>

        <el-form-item>
          <el-button type="primary" @click="form.id === null ? addData(): updateData()">保存</el-button>
          <!-- <el-button type="primary" @click="addData">保存</el-button> -->
          <el-button @click="dialogFormVisible = false">取消</el-button>
        </el-form-item>
      </el-form>
    </el-dialog>
    <!-- ------------------------------曲谱------------------------------- -->
    <el-dialog :visible.sync="dialogVisible" width="85%">
      <el-image v-for="(u,index) in urls" :key="index+1" :src="u.url" lazy></el-image>
      <!-- <el-image :src="urls"></el-image> -->
      <span slot="footer" class="dialog-footer">
        <el-button @click="dialogVisible = false">取 消</el-button>
      </span>
    </el-dialog>
  </div>
</template>

<script>
import myApi from "@/api/my";

export default {
  data() {
    return {
      list: [], //列表数据
      //----------------------------------------------分页
      total: 0, // 总记录数
      currentPage: 1, // 当前页, 默认第1页
      pageSize: 10, // 每页显示条数， 10条
      //---------------------------------------------搜索框
      searchMap: {
        name: "",
        author: ""
      },
      //--------------------------------------------------控制对话框是否
      dialogFormVisible: false, // 控制新增对话框
      dialogVisible: false,
      //--------------------------------------------------新增对话框的数据绑定源
      form: {
        id: null, //增加id
        name: "",
        author: "",
        btype: 1,
        stype: 1
      },
      // urls: [
      //   "https://fuss10.elemecdn.com/a/3f/3302e58f9a181d2509f3dc0fa68b0jpeg.jpeg",
      //   "https://fuss10.elemecdn.com/1/34/19aa98b1fcb2781c4fba33d850549jpeg.jpeg",
      //   "https://fuss10.elemecdn.com/0/6f/e35ff375812e6b0020b6b4e8f9583jpeg.jpeg",
      //   "https://fuss10.elemecdn.com/9/bb/e27858e973f5d7d3904835f46abbdjpeg.jpeg",
      //   "https://fuss10.elemecdn.com/d/e6/c4d93a3805b3ce3f323f7974e6f78jpeg.jpeg",
      //   "https://fuss10.elemecdn.com/3/28/bbf893f792f03a54408b3b7a7ebf0jpeg.jpeg",
      //   "https://fuss10.elemecdn.com/2/11/6535bcfb26e4c79b48ddde44f4b6fjpeg.jpeg"
      // ],
      urls: [""],
      //上传图片
      importDataBtnText:"上传图片",
      importDataBtnIcon:"el-icon-circle-plus-outline",

    };
  },

  // 钩子函数获取数据
  created() {
    this.getData();
  },
  methods: {

    //点击曲谱的动作
    pu(id){   
     console.log("------当前琴谱的id：",id)
     myApi.getURLById(id).then(response => {
        console.log(response);
        this.urls = response.data;
        console.log(this.urls);
        if(response.data == ""){
          alert("暂无图片，请先上传")
          this.dialogVisible = false;
        }else{
          this.dialogVisible = true;
        }
      })
    },
    // uploadUrl: function(id) {
    //   return "http://http://localhost:8181/my/img?id=" + id;
    // },
    onSuccess(response, file, fileList){
        this.importDataBtnText="上传图片"
        this.importDataBtnIcon="el-icon-circle-plus-outline"
    },
    handleBeforeUpload(file) {
      console.log("文件上传前的方法");
      console.log(file);
      this.importDataBtnText="上传中"
      this.importDataBtnIcon="el-icon-loading"
    },

    //列表获取
    getData() {
      myApi.search(this.currentPage, this.pageSize).then(response => {
        const resp = response.data;
        this.total = resp.totalElements;
        this.list = resp.content;
        // this.urls = resp.content[0].img;
        // console.log("-----------",this.urls);
        console.log(resp);
      });
    },

    //  上方查
    findMys() {
      console.log("xxxxxxxx", this.searchMap);
      myApi.findMys(this.searchMap).then(response => {
        const resp = response.data;
        this.list = resp;
        // // this.name =resp.name;
        // // this.author = resp.author;
        // this.searchMap = resp.content;
        console.log(response);
      });
    },

    //分页改变
    handleSizeChange(val) {
      this.pageSize = val;
      this.getData();
    },
    //分页改变
    handleCurrentChange(val) {
      this.currentPage = val;
      this.getData();
    },
    // 表单重置，
    // 在 el-form-item 标签属性 prop 上, 指定了字段名, 重置才会生效
    resetForm(formName) {
      this.$refs[formName].resetFields();
      this.getData();
    },
    // 打开新增窗口
    handleAdd() {
      console.log("-----打开新增/编辑----");
      this.dialogFormVisible = true;
      this.$nextTick(() => {
        // this.$nextTick()它是一个异步事件，当渲染结束 之后 ，它的回调函数才会被执行
        // 弹出窗口打开之后 ，需要加载Dom, 就需要花费一点时间，我们就应该等待它加载完dom之后，再进行调用resetFields方法，重置表单和清除样式
        this.$refs["form"].resetFields();
      });
    },
    //新增
    addData() {
      // 验证通过，提交添加
      myApi.add(this.form).then(response => {
        // const resp = response.data
        const resp = response;
        console.log(resp);
        // 提示信息
        this.$message({
          message: "新增成功",
          type: "success"
        });
        if (resp) {
          this.getData();
          this.dialogFormVisible = false;
          console.log("--------------新增数据成功------------");
        } else {
          // 验证不通过
          this.$message({
            message: resp.msg,
            type: "warning"
          });
        }
      });
    },
    //删除
    deleMyById(id) {
      console.log("删除", id);
      this.$confirm("确认删除这条记录吗？", "提示", {
        confirmButtonText: "确定",
        cancelButtonText: "取消",
        type: "warning"
      })
        .then(() => {
          // 确认
          console.log("确认");
          myApi.deleteMyById(id).then(response => {
            const resp = response.data;
            //提示信息
            this.$message({
              message: "删除成功",
              type: "success"
            });

            // 删除成功，刷新列表
            this.getData();
          });
        })
        .catch(() => {
          // 取消删除，不理会
          console.log("取消");
        });
    },
    //--------------------------------------------------------------编辑功能（打开窗口+保存）
    // 打开编辑窗口
    handleEdit(id) {
      // 重用打开新增窗口方法, 不要少了 this
      this.handleAdd();
      // 查询数据
      myApi.getMyById(id).then(response => {
        console.log(response);
        const resp = response.data;
        this.form = resp;
        console.log("----------------------", id);
      });
    },
    // 更新数据
    updateData() {
      // 验证通过，提交
      myApi.update(this.form).then(response => {
        //提示信息
        this.$message({
          type: "success",
          message: "更新成功"
        });
        this.getData(); // 刷新列表数据
        this.dialogFormVisible = false; // 关闭窗口
        // if (resp.flag) {
        //   this.fetchData(); // 刷新列表数据
        //   this.dialogFormVisible = false; // 关闭窗口
        // } else {
        //   // 失败, 弹出提示
        //   this.$message({
        //     message: resp.message,
        //     type: "warning"
        //   });
        // }
      });
    }
  }
};
</script>


<style scoped>
</style>
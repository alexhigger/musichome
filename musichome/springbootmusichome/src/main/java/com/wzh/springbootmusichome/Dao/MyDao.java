package com.wzh.springbootmusichome.Dao;

import com.wzh.springbootmusichome.Entity.My;
import com.wzh.springbootmusichome.Entity.MyImg;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.data.jpa.repository.Query;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;
@Mapper
public interface MyDao {
  //搜索框条件查询
  List<My> findMys(@Param("name") String name,@Param("author") String author);

  //用于更新功能，根据id回显具体信息
  My getMyById(Integer id);
  //新增
  int addMys(My my);
  //删除
  int deleteMyById(Integer id);
  //更新
  int updateMy(My my);

  //上传图片
  Integer insertIMG(@Param("url") String url, @Param("myid") Integer myid);
  //获取当前行的图片路径通过当前行的id
  List<MyImg> getURLById(@Param("myid") Integer myid);

  //<!--更新操作1 先把该id的所有图都删除-->
  //<!--更新操作2  上传图片-->
  int deleteUrlByMyId(Integer myid);
}

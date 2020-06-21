package com.wzh.springbootmusichome.service;

import com.wzh.springbootmusichome.Entity.My;
import com.wzh.springbootmusichome.Entity.MyImg;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface myService {
    List<My> findMys(My my);//条件查询

    My getMyById(Integer id);//根据id获取信息
    //新增
    int addMys(My my);
    int deleteMyById(Integer id);
    //更新
    int updateMy(My my);
    //图片
//  Integer updateIMG(String url, Integer id);
    Integer insertIMG(String url,  Integer myid);
    //获取当前行的图片路径通过当前行的id
    List<MyImg> getURLById(Integer myid);

    //<!--更新操作1 先把该id的所有图都删除-->
    int deleteUrlByMyId(Integer myid);
}

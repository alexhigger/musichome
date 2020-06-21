package com.wzh.springbootmusichome.service.serviceImp;

import com.wzh.springbootmusichome.Dao.MyDao;
import com.wzh.springbootmusichome.Entity.My;
import com.wzh.springbootmusichome.Entity.MyImg;
import com.wzh.springbootmusichome.service.myService;
import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class myServiceImp implements myService {
    @Autowired
    MyDao myDao;

    //条件查询
    public List<My> findMys(My my) {
        List<My> mys = null;
        if (my != null) {
            String name = my.getName();
            String author = my.getAuthor();
            mys = myDao.findMys(name, author);
        }
        return mys;
    }

    //根据id获取所有信息
    public My getMyById(Integer id) {
        My my = myDao.getMyById(id);
        return my;
    }

    //新增
    public int addMys(My my) {
        int i = myDao.addMys(my);
        return i;
    }

    //删除
    public int deleteMyById(Integer id) {
        int i = myDao.deleteMyById(id);
        return i;
    }

    //更新
    public int updateMy(My my) {
        int i = myDao.updateMy(my);
        return i;
    }

    //图片
//    public Integer updateIMG(String url, Integer id) {
//        return myDao.updateIMG(url,id);
//    }

   public Integer insertIMG(String url, Integer myid){
        return myDao.insertIMG(url,myid);
   }
    //获取当前行的图片路径通过当前行的id
    public List<MyImg> getURLById(Integer myid){
        return myDao.getURLById(myid);
   }


    //<!--更新操作1 先把该id的所有图都删除-->
    public int deleteUrlByMyId(Integer myid) {
        return myDao.deleteUrlByMyId(myid);
    }
}

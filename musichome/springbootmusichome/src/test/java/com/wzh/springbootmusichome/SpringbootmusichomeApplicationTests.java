package com.wzh.springbootmusichome;

import com.wzh.springbootmusichome.Dao.MyDao;
import com.wzh.springbootmusichome.Entity.My;
import com.wzh.springbootmusichome.repository.MyRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;

import java.util.List;

@SpringBootTest
class SpringbootmusichomeApplicationTests {
    @Autowired
    private MyRepository myRepository;
    @Autowired
    private MyDao myDao;

    @Test
    void contextLoads() {
        PageRequest pageRequest = PageRequest.of(0, 6);
        Page<My> page = myRepository.findAll(pageRequest);
        int i = 0;
    }

    @Test
    public void findStuTest3() {
//        创建My对象 ，封装需要组合查询的条件
        My m1 = new My();
        m1.setName("斑马");
        m1.setAuthor("东");
        //执行SqlSession的查询方法，返回结果集
        List<My> mys = myDao.findMys(m1.getName(), m1.getAuthor());
        System.out.println(mys);

    }

    @Test
    public void addtest(){
        My my = new My();
        my.setName("xxxxx");
        my.setAuthor("xxxxx");
        my.setBtype(1);
        my.setStype(1);
        int i = myDao.addMys(my);
        if (i >0){
            System.out.println("新增成功");
        }
    }

    @Test
    public void deltest(){
        int i = myDao.deleteMyById(45);
        if (i >0){
            System.out.println("删除成功");
        }
    }

    @Test
    public void updatetest(){
        My my = new My();
        my.setId(17);
        my.setAuthor("东东");
        int i = myDao.updateMy(my);
        if (i >0){
            System.out.println("更新成功");
        }
    }

    @Test
    public void test(){
        My my = myDao.getMyById(1);
        System.out.println(my);
    }

}
package com.wzh.springbootmusichome.controller;


import com.wzh.springbootmusichome.Entity.My;
import com.wzh.springbootmusichome.Entity.MyImg;
import com.wzh.springbootmusichome.Entity.RespBean;
import com.wzh.springbootmusichome.config.FastDFSUtils;
import com.wzh.springbootmusichome.repository.MyRepository;
import com.wzh.springbootmusichome.service.myService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;



@RestController
@RequestMapping("/my")
public class MyController {
    @Autowired
    private MyRepository myRepository;


    @Autowired
    private myService myService;
    //分页查询
    @PostMapping("/list/search/{page}/{size}")
    public Page<My> findAll(@PathVariable("page") Integer page, @PathVariable("size") Integer size) {
        PageRequest request = PageRequest.of(page, size);
        return myRepository.findAll(request);
    }
    //条件查询
    @PostMapping("/findMys")
    public List<My> findMys(@RequestBody My my){
     return myService.findMys(my);
    }

    @GetMapping("/getMyById/{id}")
    public  My getMyById(@PathVariable("id") Integer id){
        My my = myService.getMyById(id);
        System.out.println(my);
        return my;
    }
    //增加
    @PostMapping("/addMys")
    public Integer addMys(@RequestBody My my){
        int i = myService.addMys(my);
        if (i>0){
            System.out.println("--------成功插入一条数据-------------");
        }
        return null;
    }

    //删除
    @DeleteMapping("/delteMyById/{id}")
    public Integer deleteMyById(@PathVariable("id") Integer id){
        int i = myService.deleteMyById(id);
        if (i>0){
            System.out.println("--------成功删除一条数据-------------");
        }
        return null;
    }
    //更新

    @PutMapping("/updateMy")
    public Integer updateMy(@RequestBody My my){
        int i = myService.updateMy(my);
        if (i>0){
            System.out.println("--------成功更新一条数据-------------");
        }
        return null;
    }

    @Value("${fastdfs.nginx.host}")
    String nginxHost;

    @PostMapping("/img")
    public RespBean insertIMG(MultipartFile file, Integer myid) {
        String fileId = FastDFSUtils.upload(file);
        String url = nginxHost + fileId;
        if (myService.insertIMG(url, myid) == 1) {
            return RespBean.ok( "上传成功!",url);
        }
        return RespBean.error("上传失败!");
    }



    //获取当前行的图片路径通过当前行的id
    @GetMapping("/img/{myid}")
    public List<MyImg> getURLById(@PathVariable("myid") Integer myid){
        List<MyImg> urlList = myService.getURLById(myid);
        System.out.println(urlList);
        return  urlList;

    }

    //<!--更新操作1 先把该id的所有图都删除-->
    @PostMapping("/updateImg")
    public RespBean updateImg(MultipartFile file, Integer myid){
        int i = myService.deleteUrlByMyId(myid);
        if (i>0){
            System.out.println("--------已删除id为"+myid+"的曲谱图片数据-------------");
        }
        String fileId = FastDFSUtils.upload(file);
        String url = nginxHost + fileId;
        if (myService.insertIMG(url, myid) == 1) {
            return RespBean.ok( "上传成功!",url);
        }
        return RespBean.error("上传失败!");
    }
















//    @GetMapping("/find")
//    public List<My> findByNameAndAuthorLike(String name,String author){
//        List<My> byNameAndAuthorLike = myRepository.findByNameAndAuthorContaining("%" + name + "%", "%" + author + "%");
//        return byNameAndAuthorLike;
//    }

//    @PostMapping("/add")
//    public String add(@RequestBody Book book) {
//        Book result = bookRepository.save(book);
//        if (result != null) {
//            return "success";
//        } else {
//            return "error";
//        }
//    }
//
//    @GetMapping("/findById/{id}")
//    public Book findById(@PathVariable("id") Integer id){
//        return bookRepository.findById(id).get();
//    }
//
//    @PutMapping("/update")
//    public String update(@RequestBody Book book){
//        Book result = bookRepository.save(book);
//        if (result != null) {
//            return "success";
//        } else {
//            return "error";
//        }
//    }
//
//    @DeleteMapping("/deleteById/{id}")
//    public void  deleteById(@PathVariable("id") Integer id){
//        bookRepository.deleteById(id);
//    }
}

package com.wzh.springbootmusichome.Entity;


import java.util.List;

/**
 * 描述：
 *
 * @author: wzh
 * @create: 2020-03-12 21:59
 **/
public class MyImg {
    private String url;

    public MyImg() {
    }

    public MyImg(String url) {
        this.url = url;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    @Override
    public String toString() {
        return "MyImg{" +
                "url='" + url + '\'' +
                '}';
    }
}

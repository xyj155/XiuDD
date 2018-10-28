package com.example.xiudd.sese;

/**
 * Created by Xuyijie on 2018/10/17.
 */

public class test {
    private String url;
    private String title;
    private String tag1;
    private String tag2;
    private String image;

    public test(String url, String title, String tag1, String tag2, String image) {
        this.url = url;
        this.title = title;
        this.tag1 = tag1;
        this.tag2 = tag2;
        this.image = image;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getTag1() {
        return tag1;
    }

    public void setTag1(String tag1) {
        this.tag1 = tag1;
    }

    public String getTag2() {
        return tag2;
    }

    public void setTag2(String tag2) {
        this.tag2 = tag2;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }
}

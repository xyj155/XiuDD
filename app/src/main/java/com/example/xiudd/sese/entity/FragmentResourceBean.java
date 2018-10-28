package com.example.xiudd.sese.entity;

/**
 * Created by Xuyijie on 2018/10/16.
 */

public class FragmentResourceBean {
    private String title;
    private int res;

    public FragmentResourceBean(String title, int res) {
        this.title = title;
        this.res = res;
    }

    public String getTitle() {

        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public int getRes() {
        return res;
    }

    public void setRes(int res) {
        this.res = res;
    }
}

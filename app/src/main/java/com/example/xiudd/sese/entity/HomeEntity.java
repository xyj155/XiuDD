package com.example.xiudd.sese.entity;

import com.example.xiudd.sese.gson.BannerGson;

import com.example.xiudd.sese.gson.MarqueenGson;

import com.example.xiudd.sese.gson.UserGson;
import com.example.xiudd.sese.gson.VipVideoGson;

import java.util.List;

/**
 * Created by 徐易杰 on 2018/10/21.
 */

public class HomeEntity {
    private List<VipVideoGson> vipVideoGson1;
    private List<VipVideoGson> vipVideoGson2;
    private List<BannerGson> bannerGson;
    private List<UserGson> userGsons;
    private List<MarqueenGson> marqueenGsonList;

    public HomeEntity(List<VipVideoGson> vipVideoGson1, List<VipVideoGson> vipVideoGson2, List<BannerGson> bannerGson, List<UserGson> userGsons, List<MarqueenGson> marqueenGsonList) {
        this.vipVideoGson1 = vipVideoGson1;
        this.vipVideoGson2 = vipVideoGson2;
        this.bannerGson = bannerGson;
        this.userGsons = userGsons;
        this.marqueenGsonList = marqueenGsonList;
    }

    public List<VipVideoGson> getVipVideoGson1() {
        return vipVideoGson1;
    }

    public void setVipVideoGson1(List<VipVideoGson> vipVideoGson1) {
        this.vipVideoGson1 = vipVideoGson1;
    }

    public List<VipVideoGson> getVipVideoGson2() {
        return vipVideoGson2;
    }

    public void setVipVideoGson2(List<VipVideoGson> vipVideoGson2) {
        this.vipVideoGson2 = vipVideoGson2;
    }

    public List<BannerGson> getBannerGson() {
        return bannerGson;
    }

    public void setBannerGson(List<BannerGson> bannerGson) {
        this.bannerGson = bannerGson;
    }

    public List<UserGson> getUserGsons() {
        return userGsons;
    }

    public void setUserGsons(List<UserGson> userGsons) {
        this.userGsons = userGsons;
    }

    public List<MarqueenGson> getMarqueenGsonList() {
        return marqueenGsonList;
    }

    public void setMarqueenGsonList(List<MarqueenGson> marqueenGsonList) {
        this.marqueenGsonList = marqueenGsonList;
    }
}

package com.example.xiudd.sese.model;

import com.example.xiudd.sese.base.BaseGson;
import com.example.xiudd.sese.contract.HomeContract;
import com.example.xiudd.sese.gson.BannerGson;

import com.example.xiudd.sese.gson.MarqueenGson;

import com.example.xiudd.sese.gson.UserGson;
import com.example.xiudd.sese.gson.VipVideoGson;
import com.example.xiudd.sese.http.RetrofitUtil;

import rx.Observable;

/**
 * Created by 徐易杰 on 2018/10/21.
 */

public class HomeModel implements HomeContract.Model {
    @Override
    public Observable<BaseGson<VipVideoGson>> getVipVideo(String kind) {
        return RetrofitUtil.getInstance().getServerices().getVipVideo(kind);
    }

    @Override
    public Observable<BaseGson<VipVideoGson>> getAsiaVideo(String kind) {
        return RetrofitUtil.getInstance().getServerices().getVipVideo(kind);
    }

    @Override
    public Observable<BaseGson<BannerGson>> getBanner() {
        return RetrofitUtil.getInstance().getServerices().getBanner();
    }

    @Override

    public Observable<BaseGson<UserGson>> getAroundUserList(String city, int limit) {
        return RetrofitUtil.getInstance().getServerices().getAroundUserList(city, limit);
    }

    @Override
    public Observable<BaseGson<MarqueenGson>> getMarqueen() {
        return RetrofitUtil.getInstance().getServerices().getMarqueen();

    }

}

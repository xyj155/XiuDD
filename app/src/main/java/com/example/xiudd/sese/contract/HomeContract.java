package com.example.xiudd.sese.contract;

import com.example.xiudd.sese.base.BaseGson;
import com.example.xiudd.sese.base.BaseView;
import com.example.xiudd.sese.gson.BannerGson;

import com.example.xiudd.sese.gson.MarqueenGson;

import com.example.xiudd.sese.gson.UserGson;
import com.example.xiudd.sese.gson.VipVideoGson;

import java.util.List;

import rx.Observable;

/**
 * Created by 徐易杰 on 2018/10/21.
 */

public interface HomeContract {
    interface Model {
        Observable<BaseGson<VipVideoGson>> getVipVideo(String kind);

        Observable<BaseGson<VipVideoGson>> getAsiaVideo(String kind);

        Observable<BaseGson<BannerGson>> getBanner();


        Observable<BaseGson<UserGson>> getAroundUserList(String city,int limit);
        Observable<BaseGson<MarqueenGson>> getMarqueen();
    }

    interface View extends BaseView {
        void getVipVideo(List<VipVideoGson> vipVideoGsonList1,List<VipVideoGson> vipVideoGsonList2,List<BannerGson> bannerGsonList,List<UserGson> userGsons,List<MarqueenGson> marqueenGsonList);
    }

    interface Presenter {
        void loadVipVideo(String kind1,String kid2,String location,int limit);

//        Observable<BaseGson<UserGson>> getAroundUserList(String city);
    }

}

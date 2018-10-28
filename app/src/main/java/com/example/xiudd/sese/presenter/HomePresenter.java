package com.example.xiudd.sese.presenter;

import com.example.xiudd.sese.base.BaseGson;
import com.example.xiudd.sese.base.BaseObserver;
import com.example.xiudd.sese.contract.HomeContract;
import com.example.xiudd.sese.entity.HomeEntity;
import com.example.xiudd.sese.gson.BannerGson;

import com.example.xiudd.sese.gson.MarqueenGson;

import com.example.xiudd.sese.gson.UserGson;
import com.example.xiudd.sese.gson.VipVideoGson;
import com.example.xiudd.sese.model.HomeModel;

import rx.Observable;
import rx.android.schedulers.AndroidSchedulers;

import rx.functions.Func5;
import rx.schedulers.Schedulers;

/**
 * Created by 徐易杰 on 2018/10/21.
 */

public class HomePresenter implements HomeContract.Presenter {
    private HomeContract.View view;
    private HomeModel homeModel = new HomeModel();

    public HomePresenter(HomeContract.View view) {
        this.view = view;
    }

    @Override
    public void loadVipVideo(String kind1, String kid2, String location, int limit) {
        view.showLoadingDialog();
        Observable.zip(homeModel.getAsiaVideo(kind1),
                homeModel.getVipVideo(kid2),
                homeModel.getBanner(),
                homeModel.getAroundUserList(location, limit),
                homeModel.getMarqueen(),
                new Func5<BaseGson<VipVideoGson>, BaseGson<VipVideoGson>, BaseGson<BannerGson>, BaseGson<UserGson>, BaseGson<MarqueenGson>, HomeEntity>() {
                    @Override
                    public HomeEntity call(BaseGson<VipVideoGson> vipVideoGsonBaseGson, BaseGson<VipVideoGson> vipVideoGsonBaseGson2, BaseGson<BannerGson> bannerGsonBaseGson, BaseGson<UserGson> userGsonBaseGson, BaseGson<MarqueenGson> marqueenGsonBaseGson) {
                        return new HomeEntity(vipVideoGsonBaseGson.getDataList(),
                                vipVideoGsonBaseGson2.getDataList(),
                                bannerGsonBaseGson.getDataList(),
                                userGsonBaseGson.getDataList(),
                                marqueenGsonBaseGson.getDataList());
                    }
                })
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new BaseObserver<HomeEntity>() {
                    @Override
                    public void onError(String error) {
                        view.hideLoadingDialog();
                    }

                    @Override
                    public void onCompleted() {

                    }

                    @Override
                    public void onNext(HomeEntity homeEntity) {
                        view.hideLoadingDialog();

                        view.getVipVideo(homeEntity.getVipVideoGson1(), homeEntity.getVipVideoGson2(), homeEntity.getBannerGson(), homeEntity.getUserGsons(), homeEntity.getMarqueenGsonList());


                    }
                });
        ;
    }


}

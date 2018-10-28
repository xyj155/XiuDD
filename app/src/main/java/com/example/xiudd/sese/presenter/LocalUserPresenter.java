package com.example.xiudd.sese.presenter;

import com.example.xiudd.sese.base.BaseGson;
import com.example.xiudd.sese.base.BaseObserver;
import com.example.xiudd.sese.contract.LocalUserContract;
import com.example.xiudd.sese.gson.UserGson;
import com.example.xiudd.sese.model.LocalUserModel;

import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * Created by Xuyijie on 2018/10/22.
 */

public class LocalUserPresenter implements LocalUserContract.Presenter {
    private LocalUserModel localUserModel = new LocalUserModel();
    private LocalUserContract.View view;

    public LocalUserPresenter(LocalUserContract.View view) {
        this.view = view;
    }

    @Override
    public void getLocalUserList(String city, int limit) {
        localUserModel.getLocalUserList(city, limit)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new BaseObserver<BaseGson<UserGson>>() {
                    @Override
                    public void onError(String error) {
                        view.failed(error);
                    }

                    @Override
                    public void onCompleted() {

                    }

                    @Override
                    public void onNext(BaseGson<UserGson> userGsonBaseGson) {
                        if (userGsonBaseGson.isSuccess()) {
                            view.showLocalUserList(userGsonBaseGson.getDataList());
                        }else {
                            view.failed(userGsonBaseGson.getError());
                        }
                    }
                });
    }

    @Override
    public void getContactUser(String uid) {
        localUserModel.getContactUser(uid)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new BaseObserver<BaseGson<UserGson>>() {
                    @Override
                    public void onError(String error) {
                        view.failed(error);
                    }

                    @Override
                    public void onCompleted() {

                    }

                    @Override
                    public void onNext(BaseGson<UserGson> userGsonBaseGson) {
                        if (userGsonBaseGson.isSuccess()) {
                            view.showLocalUserList(userGsonBaseGson.getDataList());
                        }else {
                            view.failed(userGsonBaseGson.getError());
                        }
                    }
                });
    }
}

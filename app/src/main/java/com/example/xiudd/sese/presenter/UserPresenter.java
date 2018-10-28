package com.example.xiudd.sese.presenter;

import android.util.Log;

import com.example.xiudd.sese.base.BaseGson;
import com.example.xiudd.sese.base.BaseObserver;
import com.example.xiudd.sese.contract.UserContract;
import com.example.xiudd.sese.gson.UserGson;
import com.example.xiudd.sese.model.UserModel;

import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * Created by Xuyijie on 2018/10/20.
 */

public class UserPresenter implements UserContract.Presenter {
    private UserModel userModel = new UserModel();
    private UserContract.View view;

    public UserPresenter(UserContract.View view) {
        this.view = view;
    }

    @Override
    public void loginUser(String username, String password) {
        view.showLoadingDialog();
        userModel.userLogin(username, password)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new BaseObserver<BaseGson<UserGson>>() {
                    @Override
                    public void onError(String error) {
                        view.hideLoadingDialog();
                        view.loginFailed(error);
                    }

                    @Override
                    public void onCompleted() {
                        view.hideLoadingDialog();
                    }

                    @Override
                    public void onNext(BaseGson<UserGson> userGsonBaseGson) {
                        view.hideLoadingDialog();
                        Log.i(TAG, "onNext: " + userGsonBaseGson.toString());
                        if (userGsonBaseGson.isSuccess()) {
                            view.userLogin(userGsonBaseGson.getPageData());
                        } else {
                            view.loginFailed(userGsonBaseGson.getError());
                        }

                    }
                });
    }

    @Override
    public void QQuserLogin(String openid, final String username, String avatar, String sex, String location, String tel, String sign) {
        view.showLoadingDialog();
        userModel.QQUserLogin(openid, username, avatar, sex, location, tel, sign)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new BaseObserver<BaseGson<UserGson>>() {
                    @Override
                    public void onError(String error) {
                        Log.i(TAG, "onError: "+error);
                        view.hideLoadingDialog();
                        view.loginFailed(error);
                    }

                    @Override
                    public void onCompleted() {
                        view.hideLoadingDialog();
                    }

                    @Override
                    public void onNext(BaseGson<UserGson> userGsonBaseGson) {
                        view.hideLoadingDialog();
                        Log.i(TAG, "onNext: " + userGsonBaseGson.toString());
                        if (userGsonBaseGson.isSuccess()) {
                            if (userGsonBaseGson.getCode() == 201) {
                                view.setSexInfor(userGsonBaseGson.getPageData());//设置信息
                            } else if (userGsonBaseGson.getCode() == 200) {
                                view.qqLogin(userGsonBaseGson.getPageData());//登陆成功
                            } else if (userGsonBaseGson.getCode() == 203) {
                                view.upDateUserInforSuccess(userGsonBaseGson.getPageData());//更新成功
                            } else if (userGsonBaseGson.getCode() == 202) {
                                view.setUserInforFailed(userGsonBaseGson.getPageData());//更新失败
                            }
                        } else {
                            view.loginFailed(userGsonBaseGson.getError());
                        }

                    }
                });
    }

    private static final String TAG = "UserPresenter";
}

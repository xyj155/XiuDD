package com.example.xiudd.sese.presenter;

import android.util.Log;
import android.widget.Toast;

import com.example.xiudd.sese.base.BaseGson;
import com.example.xiudd.sese.base.BaseObserver;
import com.example.xiudd.sese.base.EmptyGson;
import com.example.xiudd.sese.contract.LoginContract;
import com.example.xiudd.sese.gson.UserGson;
import com.example.xiudd.sese.model.LoginModel;
import com.example.xiudd.sese.util.ToastUtils;

import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * Created by Xuyijie on 2018/10/22.
 */

public class LoginPresenter implements LoginContract.Presenter {
    private LoginModel loginModel=new LoginModel();
    private LoginContract.View view;

    public LoginPresenter(LoginContract.View view) {
        this.view = view;
    }

    private static final String TAG = "LoginPresenter";
    @Override
    public void registerWithUser(String username, String password, String tel, String sex, String head, String sign,  String location) {
        view.showLoadingDialog();
        loginModel.UserRegister(username,password,tel,sex,head,sign,location)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new BaseObserver<BaseGson<UserGson>>() {
                    @Override
                    public void onError(String error) {
                        view.hideLoadingDialog();
                        view.registerFailed( error);
                        Log.i(TAG, "onError: "+error);
                    }

                    @Override
                    public void onCompleted() {

                    }

                    @Override
                    public void onNext(BaseGson<UserGson> emptyGsonBaseGson) {
                        view.hideLoadingDialog();
                        if (emptyGsonBaseGson.isSuccess()){
                            view.UserRegister(emptyGsonBaseGson.getPageData());
                        }else {
                            view.registerFailed(emptyGsonBaseGson.getError());
                        }

                    }
                });
    }
}

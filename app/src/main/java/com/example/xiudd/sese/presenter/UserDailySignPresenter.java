package com.example.xiudd.sese.presenter;

import com.example.xiudd.sese.base.BaseGson;
import com.example.xiudd.sese.base.BaseObserver;
import com.example.xiudd.sese.contract.UserDailySignContract;
import com.example.xiudd.sese.gson.SignGson;
import com.example.xiudd.sese.model.UserDailySignModel;

import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * Created by Xuyijie on 2018/10/22.
 */

public class UserDailySignPresenter implements UserDailySignContract.Presenter {
    private UserDailySignModel userDailySignModel = new UserDailySignModel();
    private UserDailySignContract.View view;

    public UserDailySignPresenter(UserDailySignContract.View view) {
        this.view = view;
    }

    @Override
    public void sign(String uid) {
        view.showLoadingDialog();
        userDailySignModel.sign(uid)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new BaseObserver<BaseGson<SignGson>>() {
                    @Override
                    public void onError(String error) {
                        view.failed(error);
                        view.hideLoadingDialog();
                    }

                    @Override
                    public void onCompleted() {

                    }

                    @Override
                    public void onNext(BaseGson<SignGson> signGsonBaseGson) {
                        view.hideLoadingDialog();
                        if (signGsonBaseGson.isSuccess()) {
                            view.getsign(signGsonBaseGson.getPageData());
                        } else {
                            view.failed(signGsonBaseGson.getError());
                        }
                    }
                });
    }
}

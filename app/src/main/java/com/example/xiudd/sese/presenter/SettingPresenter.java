package com.example.xiudd.sese.presenter;

import com.example.xiudd.sese.base.BaseGson;
import com.example.xiudd.sese.base.BaseObserver;
import com.example.xiudd.sese.base.EmptyGson;
import com.example.xiudd.sese.contract.SettingContract;
import com.example.xiudd.sese.model.SettingModel;

import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * Created by Xuyijie on 2018/10/25.
 */

public class SettingPresenter implements SettingContract.Presenter {
    private SettingContract.View view;
    private SettingModel settingModel = new SettingModel();

    public SettingPresenter(SettingContract.View view) {
        this.view = view;
    }

    @Override
    public void feedBack(String uid, String content) {
        settingModel.feedBack(uid, content)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new BaseObserver<BaseGson<EmptyGson>>() {
                    @Override
                    public void onError(String error) {
                        view.failed(error);
                        view.hideLoadingDialog();
                    }

                    @Override
                    public void onCompleted() {

                    }

                    @Override
                    public void onNext(BaseGson<EmptyGson> emptyGsonBaseGson) {
                        if (emptyGsonBaseGson.isSuccess()) {
                            view.success(emptyGsonBaseGson.getDataList());
                        } else {
                            view.failed(emptyGsonBaseGson.getError());
                        }
                    }
                });
    }

    @Override
    public void updatePassword(String password, String password1, String uid) {
        settingModel.updatePassword(password, password1, uid)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new BaseObserver<BaseGson<EmptyGson>>() {
                    @Override
                    public void onError(String error) {
                        view.failed(error);
                        view.hideLoadingDialog();
                    }

                    @Override
                    public void onCompleted() {

                    }

                    @Override
                    public void onNext(BaseGson<EmptyGson> emptyGsonBaseGson) {
                        if (emptyGsonBaseGson.isSuccess()) {
                            view.success(emptyGsonBaseGson.getDataList());
                        } else {
                            view.failed(emptyGsonBaseGson.getError());
                        }
                    }
                });
    }
}

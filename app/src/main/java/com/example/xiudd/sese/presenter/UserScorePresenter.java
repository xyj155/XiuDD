package com.example.xiudd.sese.presenter;

import com.example.xiudd.sese.base.BaseGson;
import com.example.xiudd.sese.base.BaseObserver;
import com.example.xiudd.sese.contract.UserScoreContract;
import com.example.xiudd.sese.gson.ScoreGson;
import com.example.xiudd.sese.gson.SystemPushGson;
import com.example.xiudd.sese.model.UserScoreModel;

import java.util.List;

import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * Created by Xuyijie on 2018/10/24.
 */

public class UserScorePresenter implements UserScoreContract.Presenter {
    private UserScoreContract.View view;
    private UserScoreModel model = new UserScoreModel();

    public UserScorePresenter(UserScoreContract.View view) {
        this.view = view;
    }

    @Override
    public void getUserScore(String uid) {
        view.showLoadingDialog();
        model.getUserScore(uid)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new BaseObserver<BaseGson<ScoreGson>>() {
                    @Override
                    public void onError(String error) {
                        view.failed("获取失败");
                        view.hideLoadingDialog();
                    }

                    @Override
                    public void onCompleted() {

                    }

                    @Override
                    public void onNext(BaseGson<ScoreGson> scoreGsonBaseGson) {
                        view.hideLoadingDialog();
                        if (scoreGsonBaseGson.isSuccess()) {
                            List<ScoreGson> dataList = scoreGsonBaseGson.getDataList();
                            view.getUserScore(dataList);
                        } else {
                            view.failed(scoreGsonBaseGson.getError());
                        }
                    }
                });
    }

    @Override
    public void getSystemPush() {
        view.showLoadingDialog();
        model.getSystemPush()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new BaseObserver<BaseGson<SystemPushGson>>() {
                    @Override
                    public void onError(String error) {
                        view.failed("获取失败");
                        view.hideLoadingDialog();
                    }

                    @Override
                    public void onCompleted() {

                    }

                    @Override
                    public void onNext(BaseGson<SystemPushGson> scoreGsonBaseGson) {
                        view.hideLoadingDialog();
                        if (scoreGsonBaseGson.isSuccess()) {
                            List<SystemPushGson> dataList = scoreGsonBaseGson.getDataList();
                            view.getSystemPush(dataList);
                        } else {
                            view.failed(scoreGsonBaseGson.getError());
                        }
                    }
                });
    }
}

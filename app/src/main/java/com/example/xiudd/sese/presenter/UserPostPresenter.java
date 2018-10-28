package com.example.xiudd.sese.presenter;

import com.example.xiudd.sese.base.BaseGson;
import com.example.xiudd.sese.base.BaseObserver;
import com.example.xiudd.sese.contract.UserPostContract;
import com.example.xiudd.sese.gson.PostListGson;
import com.example.xiudd.sese.gson.ScoreGson;
import com.example.xiudd.sese.model.UserPostModel;

import java.util.List;

import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * Created by Xuyijie on 2018/10/24.
 */

public class UserPostPresenter implements UserPostContract.Presenter {
    private UserPostModel model=new UserPostModel();
    private UserPostContract.View view;

    public UserPostPresenter(UserPostContract.View view) {
        this.view = view;
    }

    @Override
    public void getUserPost(String uid) {
        view.showLoadingDialog();
        model.getUserPost(uid)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new BaseObserver<BaseGson<PostListGson>>() {
                    @Override
                    public void onError(String error) {
                        view.loadFailed("获取失败");
                        view.hideLoadingDialog();
                    }

                    @Override
                    public void onCompleted() {

                    }

                    @Override
                    public void onNext(BaseGson<PostListGson> scoreGsonBaseGson) {
                        view.hideLoadingDialog();
                        if (scoreGsonBaseGson.isSuccess()) {
                            List<PostListGson> dataList = scoreGsonBaseGson.getDataList();
                            view.setUserPost(dataList);
                        } else {
                            view.loadFailed(scoreGsonBaseGson.getError());
                        }
                    }
                });
    }

    @Override
    public void getUserCollectionPost(String uid) {
        view.showLoadingDialog();
        model.getUserCollectionPost(uid)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new BaseObserver<BaseGson<PostListGson>>() {
                    @Override
                    public void onError(String error) {
                        view.loadFailed("获取失败");
                        view.hideLoadingDialog();
                    }

                    @Override
                    public void onCompleted() {

                    }

                    @Override
                    public void onNext(BaseGson<PostListGson> scoreGsonBaseGson) {
                        view.hideLoadingDialog();
                        if (scoreGsonBaseGson.isSuccess()) {
                            List<PostListGson> dataList = scoreGsonBaseGson.getDataList();
                            view.setUserCollectionPost(dataList);
                        } else {
                            view.loadFailed(scoreGsonBaseGson.getError());
                        }
                    }
                });
    }
}

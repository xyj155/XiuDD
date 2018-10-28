package com.example.xiudd.sese.presenter;

import com.example.xiudd.sese.base.BaseGson;
import com.example.xiudd.sese.base.BaseObserver;
import com.example.xiudd.sese.contract.PostListContract;
import com.example.xiudd.sese.gson.PostListGson;
import com.example.xiudd.sese.model.PostListModel;
import com.example.xiudd.sese.util.ToastUtils;

import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * Created by Xuyijie on 2018/10/16.
 */

public class PostListPresenter implements PostListContract.Presenter {
    private PostListContract.View view;
    private PostListModel model = new PostListModel();

    public PostListPresenter(PostListContract.View view) {
        this.view = view;
    }

    @Override
    public void setPostList(String uid) {
        view.showLoadingDialog();
        model.getPostList(uid)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new BaseObserver<BaseGson<PostListGson>>() {
                    @Override
                    public void onError(String error) {
                        view.hideLoadingDialog();
                        ToastUtils.getInstance().showText("请求出错：" + error);
                    }

                    @Override
                    public void onCompleted() {

                    }

                    @Override
                    public void onNext(BaseGson<PostListGson> postListGsonBaseGson) {
                        view.hideLoadingDialog();
                        if (postListGsonBaseGson.getCode() == 200) {
                            view.loadPostList(postListGsonBaseGson.getDataList());
                        } else {
                            ToastUtils.getInstance().showText("请求出错：" + postListGsonBaseGson.getError());
                        }

                    }
                });
    }
}

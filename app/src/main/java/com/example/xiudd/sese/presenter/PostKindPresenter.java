package com.example.xiudd.sese.presenter;

import com.example.xiudd.sese.base.BaseGson;
import com.example.xiudd.sese.base.BaseObserver;
import com.example.xiudd.sese.contract.PostKindContract;
import com.example.xiudd.sese.gson.PostListGson;
import com.example.xiudd.sese.model.PostKindModel;

import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * Created by Xuyijie on 2018/10/27.
 */

public class PostKindPresenter implements PostKindContract.Presenter {
    private PostKindModel postKindModel = new PostKindModel();
    private PostKindContract.View view;

    public PostKindPresenter(PostKindContract.View view) {
        this.view = view;
    }

    @Override
    public void getPostListByKind(String uid, String kind) {
        view.showLoadingDialog();
        postKindModel.getPostListByKind(uid, kind)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new BaseObserver<BaseGson<PostListGson>>() {
                    @Override
                    public void onError(String error) {
                        view.hideLoadingDialog();
                        view.loadFailed(error);
                    }

                    @Override
                    public void onCompleted() {

                    }

                    @Override
                    public void onNext(BaseGson<PostListGson> postListGsonBaseGson) {
                        view.hideLoadingDialog();
                        if (postListGsonBaseGson.isSuccess()) {
                            view.setPostListByKind(postListGsonBaseGson.getDataList());
                        } else {
                            view.loadFailed(postListGsonBaseGson.getError());
                        }
                    }
                });
    }
}

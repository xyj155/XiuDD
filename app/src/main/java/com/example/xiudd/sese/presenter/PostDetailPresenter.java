package com.example.xiudd.sese.presenter;

import com.example.xiudd.sese.base.BaseGson;
import com.example.xiudd.sese.base.BaseObserver;
import com.example.xiudd.sese.base.EmptyGson;
import com.example.xiudd.sese.contract.PostDetailContract;
import com.example.xiudd.sese.gson.SinglePostDetailGson;
import com.example.xiudd.sese.model.PostDetailModel;

import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * Created by 徐易杰 on 2018/10/26.
 */

public class PostDetailPresenter implements PostDetailContract.Presenter {
    private PostDetailModel postDetailModel = new PostDetailModel();
    private PostDetailContract.View view;

    public PostDetailPresenter(PostDetailContract.View view) {
        this.view = view;
    }

    @Override
    public void getSinglePost(String id,String uid) {
        view.showLoadingDialog();
        postDetailModel.getSinglePost(id,uid)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new BaseObserver<BaseGson<SinglePostDetailGson>>() {
                    @Override
                    public void onError(String error) {
                        view.loadFailed(error);
                        view.hideLoadingDialog();
                    }

                    @Override
                    public void onCompleted() {

                    }

                    @Override
                    public void onNext(BaseGson<SinglePostDetailGson> postListGsonBaseGson) {
                        view.hideLoadingDialog();
                        if (postListGsonBaseGson.isSuccess()) {
                            view.setSinglePost(postListGsonBaseGson.getPageData());
                        } else {
                            view.loadFailed(postListGsonBaseGson.getError());
                        }
                    }
                });
    }

    @Override
    public void addLike(String pid, String uid) {
        view.showLoadingDialog();
        postDetailModel.addLike(pid,uid)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new BaseObserver<BaseGson<EmptyGson>>() {
                    @Override
                    public void onError(String error) {
                        view.addLikeFailed(error);
                        view.hideLoadingDialog();
                    }

                    @Override
                    public void onCompleted() {

                    }

                    @Override
                    public void onNext(BaseGson<EmptyGson> postListGsonBaseGson) {
                        view.hideLoadingDialog();
                        if (postListGsonBaseGson.isSuccess()) {
                            view.addLike();
                        } else {
                            view.addLikeFailed(postListGsonBaseGson.getError());
                        }
                    }
                });
    }

    @Override
    public void setComment(String pid, String uid, String comment) {
        view.showLoadingDialog();
        postDetailModel.setComment(pid,uid,comment)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new BaseObserver<BaseGson<EmptyGson>>() {
                    @Override
                    public void onError(String error) {
                        view.loadFailed(error);
                        view.hideLoadingDialog();
                    }

                    @Override
                    public void onCompleted() {

                    }

                    @Override
                    public void onNext(BaseGson<EmptyGson> postListGsonBaseGson) {
                        view.hideLoadingDialog();
                        if (postListGsonBaseGson.isSuccess()) {
                            view.setComment();
                        } else {
                            view.updateCommentFailed(postListGsonBaseGson.getError());
                        }
                    }
                });
    }
}

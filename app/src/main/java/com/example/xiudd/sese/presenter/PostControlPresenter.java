package com.example.xiudd.sese.presenter;

import com.example.xiudd.sese.base.BaseGson;
import com.example.xiudd.sese.base.BaseObserver;
import com.example.xiudd.sese.base.EmptyGson;
import com.example.xiudd.sese.contract.PostControlContract;
import com.example.xiudd.sese.model.PostControlModel;

import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * Created by Xuyijie on 2018/10/26.
 */

public class PostControlPresenter implements PostControlContract.Presenter {
    private PostControlModel postControlModel = new PostControlModel();
    private PostControlContract.View view;

    public PostControlPresenter(PostControlContract.View view) {
        this.view = view;
    }

    @Override
    public void addCollection(String uid, String postId,String  isdelete) {
        postControlModel.addCollection(uid, postId,isdelete)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new BaseObserver<BaseGson<EmptyGson>>() {
                    @Override
                    public void onError(String error) {
                        view.addFailed(error);
                    }

                    @Override
                    public void onCompleted() {

                    }

                    @Override
                    public void onNext(BaseGson<EmptyGson> emptyGsonBaseGson) {
                        if (emptyGsonBaseGson.isSuccess()) {
                            view.addSuccess(emptyGsonBaseGson.getError());
                        } else {
                            view.addFailed(emptyGsonBaseGson.getError());
                        }
                    }
                });
    }
}

package com.example.xiudd.sese.presenter;

import com.example.xiudd.sese.base.BaseGson;
import com.example.xiudd.sese.base.BaseObserver;
import com.example.xiudd.sese.contract.SearchContract;
import com.example.xiudd.sese.gson.PostListGson;
import com.example.xiudd.sese.model.SearchModel;

import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * Created by Xuyijie on 2018/10/27.
 */

public class SearchPresenter implements SearchContract.Presenter {
    private SearchModel searchModel = new SearchModel();
    private SearchContract.View view;

    public SearchPresenter(SearchContract.View view) {
        this.view = view;
    }

    @Override
    public void searchByTitle(String uid, String content) {
        view.showLoadingDialog();
        searchModel.searchByTitle(uid, content)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new BaseObserver<BaseGson<PostListGson>>() {
                    @Override
                    public void onError(String error) {
                        view.loadFailed(error);
                        view.hideLoadingDialog();
                    }

                    @Override
                    public void onCompleted() {

                    }

                    @Override
                    public void onNext(BaseGson<PostListGson> postListGsonBaseGson) {
                        view.hideLoadingDialog();
                        if (postListGsonBaseGson.isSuccess()) {
                            view.loadSearch(postListGsonBaseGson.getDataList());
                        } else {
                            view.loadFailed(postListGsonBaseGson.getError());
                        }
                    }
                });
    }
}

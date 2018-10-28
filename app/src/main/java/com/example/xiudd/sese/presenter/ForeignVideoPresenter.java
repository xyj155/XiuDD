package com.example.xiudd.sese.presenter;

import com.example.xiudd.sese.base.BaseGson;
import com.example.xiudd.sese.base.BaseObserver;
import com.example.xiudd.sese.contract.ForeignVideoContract;
import com.example.xiudd.sese.gson.ForeignGson;
import com.example.xiudd.sese.model.ForeignVideoModel;
import com.example.xiudd.sese.util.ToastUtils;

import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * Created by Xuyijie on 2018/10/17.
 */

public class ForeignVideoPresenter implements ForeignVideoContract.Presenter {
    private ForeignVideoContract.View view;
    private ForeignVideoModel foreignVideoModel = new ForeignVideoModel();

    public ForeignVideoPresenter(ForeignVideoContract.View view) {
        this.view = view;
    }

    @Override
    public void setForeignVideo() {
        view.showLoadingDialog();
        foreignVideoModel.getForeignVideo()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new BaseObserver<BaseGson<ForeignGson>>() {
                    @Override
                    public void onError(String error) {
                        ToastUtils.getInstance().showText("请求错误：" + error);
                    }

                    @Override
                    public void onCompleted() {

                    }

                    @Override
                    public void onNext(BaseGson<ForeignGson> foreignGsonBaseGson) {
                        view.hideLoadingDialog();
                        if (foreignGsonBaseGson.isSuccess()) {
                            view.loadForeignVideo(foreignGsonBaseGson.getDataList());
                        } else {
                            ToastUtils.getInstance().showText("请求错误：" + foreignGsonBaseGson.getError());
                        }
                    }
                });
    }
}

package com.example.xiudd.sese.presenter;

import com.example.xiudd.sese.base.BaseGson;
import com.example.xiudd.sese.base.BaseObserver;
import com.example.xiudd.sese.contract.CartoonContract;
import com.example.xiudd.sese.contract.ForeignVideoContract;
import com.example.xiudd.sese.gson.ForeignGson;
import com.example.xiudd.sese.gson.HotVideoGson;
import com.example.xiudd.sese.model.CartoonModel;
import com.example.xiudd.sese.model.ForeignVideoModel;
import com.example.xiudd.sese.util.ToastUtils;

import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * Created by Xuyijie on 2018/10/18.
 */

public class CartoonPresenter implements CartoonContract.Presenter {
    private CartoonContract.View view;
    private CartoonModel cartoonModel = new CartoonModel();

    public CartoonPresenter(CartoonContract.View view) {
        this.view = view;
    }


    @Override
    public void setCartoonVideo() {
        view.showLoadingDialog();
        cartoonModel.getCartoonVideo()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new BaseObserver<BaseGson<HotVideoGson>>() {
                    @Override
                    public void onError(String error) {
                        ToastUtils.getInstance().showText("请求错误：" + error);
                    }

                    @Override
                    public void onCompleted() {

                    }

                    @Override
                    public void onNext(BaseGson<HotVideoGson> foreignGsonBaseGson) {
                        view.hideLoadingDialog();
                        if (foreignGsonBaseGson.isSuccess()) {
                            view.loadCartoonVideo(foreignGsonBaseGson.getDataList());
                        } else {
                            ToastUtils.getInstance().showText("请求错误：" + foreignGsonBaseGson.getError());
                        }
                    }
                });
    }
}

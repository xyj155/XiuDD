package com.example.xiudd.sese.presenter;

import com.example.xiudd.sese.base.BaseGson;
import com.example.xiudd.sese.base.BaseObserver;
import com.example.xiudd.sese.contract.HotVideoContract;
import com.example.xiudd.sese.gson.HotVideoGson;
import com.example.xiudd.sese.model.HotVideoModel;
import com.example.xiudd.sese.util.ToastUtils;

import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * Created by Xuyijie on 2018/10/17.
 */

public class HotVideoPresenter implements HotVideoContract.Presenter {
    private HotVideoModel hotVideoModel = new HotVideoModel();
    private HotVideoContract.View view;

    public HotVideoPresenter(HotVideoContract.View view) {
        this.view = view;
    }

    @Override
    public void setHotVideo() {
        view.showLoadingDialog();
        hotVideoModel.getHotVideo()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new BaseObserver<BaseGson<HotVideoGson>>() {
                    @Override
                    public void onError(String error) {
                        view.hideLoadingDialog();
                        ToastUtils.getInstance().showText("请求错误：" + error);
                    }

                    @Override
                    public void onCompleted() {

                    }

                    @Override
                    public void onNext(BaseGson<HotVideoGson> hotVideoGsonBaseGson) {
                        view.hideLoadingDialog();
                        if (hotVideoGsonBaseGson.isSuccess()) {
                            view.loadHotVideo(hotVideoGsonBaseGson.getDataList());
                        }else {
                            ToastUtils.getInstance().showText("请求错误：" + hotVideoGsonBaseGson.getError());
                        }
                    }
                });

    }
}

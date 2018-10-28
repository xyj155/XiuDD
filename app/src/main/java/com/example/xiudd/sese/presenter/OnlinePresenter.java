package com.example.xiudd.sese.presenter;

import com.example.xiudd.sese.base.BaseGson;
import com.example.xiudd.sese.base.BaseObserver;
import com.example.xiudd.sese.contract.NovelContract;
import com.example.xiudd.sese.contract.OnlineContract;
import com.example.xiudd.sese.gson.HotVideoGson;
import com.example.xiudd.sese.gson.NovelGson;
import com.example.xiudd.sese.model.NovelModel;
import com.example.xiudd.sese.model.OnlineModel;
import com.example.xiudd.sese.util.ToastUtils;

import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * Created by Xuyijie on 2018/10/18.
 */

public class OnlinePresenter implements OnlineContract.Presenter {
    private OnlineContract.View view;
    private OnlineModel novelModel = new OnlineModel();

    public OnlinePresenter(OnlineContract.View view) {
        this.view = view;
    }
    @Override
    public void setCartoonVideo() {
        novelModel.getCartoonVideo()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new BaseObserver<BaseGson<HotVideoGson>>() {
                    @Override
                    public void onError(String error) {
                        ToastUtils.getInstance().showText("请求错误:" + error);
                    }

                    @Override
                    public void onCompleted() {

                    }

                    @Override
                    public void onNext(BaseGson<HotVideoGson> novelGsonBaseGson) {
                        if (novelGsonBaseGson.isSuccess()) {
                            view.loadCartoonVideo(novelGsonBaseGson.getDataList());
                        } else {
                            ToastUtils.getInstance().showText("请求错误:" + novelGsonBaseGson.getCode());
                        }
                    }
                });
    }
}

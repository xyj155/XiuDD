package com.example.xiudd.sese.presenter;

import com.example.xiudd.sese.base.BaseGson;
import com.example.xiudd.sese.base.BaseObserver;
import com.example.xiudd.sese.contract.TeenContract;
import com.example.xiudd.sese.gson.ForeignGson;
import com.example.xiudd.sese.model.TeenModel;
import com.example.xiudd.sese.util.ToastUtils;

import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * Created by Xuyijie on 2018/10/17.
 */

public class TeenPresenter implements TeenContract.Presenter {
    private TeenContract.View view;
    private TeenModel model = new TeenModel();

    public TeenPresenter(TeenContract.View view) {
        this.view = view;
    }


    @Override
    public void setTeenVideo() {
        view.showLoadingDialog();
        model.getTeenVideo()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new BaseObserver<BaseGson<ForeignGson>>() {
                    @Override
                    public void onError(String error) {
                        ToastUtils.getInstance().showText("请求错误："+error);
                    }

                    @Override
                    public void onCompleted() {

                    }

                    @Override
                    public void onNext(BaseGson<ForeignGson> foreignGsonBaseGson) {
                        view.hideLoadingDialog();
                        if (foreignGsonBaseGson.getCode() == 200) {
                            view.loadTeenVideo(foreignGsonBaseGson.getDataList());
                        } else {
                            ToastUtils.getInstance().showText("请求出错：" + foreignGsonBaseGson.getError());
                        }
                    }
                });
    }
}

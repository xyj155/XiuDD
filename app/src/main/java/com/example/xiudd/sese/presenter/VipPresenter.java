package com.example.xiudd.sese.presenter;

import com.example.xiudd.sese.base.BaseGson;
import com.example.xiudd.sese.base.BaseObserver;
import com.example.xiudd.sese.base.EmptyGson;
import com.example.xiudd.sese.contract.VipContract;
import com.example.xiudd.sese.model.VipModel;

import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * Created by Xuyijie on 2018/10/27.
 */

public class VipPresenter implements VipContract.Presenter {
    private VipContract.View view;
    private VipModel model = new VipModel();

    public VipPresenter(VipContract.View view) {
        this.view = view;
    }

    @Override
    public void vipCharge(String uid, String rank) {
        view.showLoadingDialog();
        model.vipCharge(uid, rank)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new BaseObserver<BaseGson<EmptyGson>>() {
                    @Override
                    public void onError(String error) {
                        view.hideLoadingDialog();
                        view.chargeFailed(error);
                    }

                    @Override
                    public void onCompleted() {

                    }

                    @Override
                    public void onNext(BaseGson<EmptyGson> emptyGsonBaseGson) {
                        view.hideLoadingDialog();
                        if (emptyGsonBaseGson.isSuccess()) {
                            view.chargeSuccess();
                        } else {
                            view.chargeFailed(emptyGsonBaseGson.getError());
                        }
                    }
                });
    }
}

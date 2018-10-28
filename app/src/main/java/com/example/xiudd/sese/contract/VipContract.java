package com.example.xiudd.sese.contract;

import com.example.xiudd.sese.base.BaseGson;
import com.example.xiudd.sese.base.BaseView;
import com.example.xiudd.sese.base.EmptyGson;

import rx.Observable;

/**
 * Created by Xuyijie on 2018/10/27.
 */

public interface VipContract {
    interface Model {
        Observable<BaseGson<EmptyGson>> vipCharge(String uid, String rank);
    }

    interface View extends BaseView{
        void chargeSuccess();

        void chargeFailed(String error);
    }

    interface Presenter {
        void vipCharge(String uid, String rank);
    }
}

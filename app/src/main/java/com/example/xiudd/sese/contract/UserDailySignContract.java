package com.example.xiudd.sese.contract;

import com.example.xiudd.sese.base.BaseGson;
import com.example.xiudd.sese.base.BaseView;
import com.example.xiudd.sese.gson.ForeignGson;
import com.example.xiudd.sese.gson.SignGson;

import java.util.List;

import rx.Observable;

/**
 * Created by Xuyijie on 2018/10/22.
 */

public interface UserDailySignContract {
    interface Model {
        Observable<BaseGson<SignGson>> sign(String uid);
    }

    interface View extends BaseView {
        void getsign(SignGson gsons);

        void failed(String error);
    }

    interface Presenter {
        void sign(String uid);
    }
}

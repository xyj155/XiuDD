package com.example.xiudd.sese.contract;

import com.example.xiudd.sese.base.BaseGson;
import com.example.xiudd.sese.base.BaseView;
import com.example.xiudd.sese.base.EmptyGson;

import java.util.List;

import rx.Observable;

/**
 * Created by Xuyijie on 2018/10/25.
 */

public interface SettingContract {
    interface Model {
        Observable<BaseGson<EmptyGson>> feedBack(String uid, String content);

        Observable<BaseGson<EmptyGson>> updatePassword(String password, String password1,String uid);
    }

    interface View extends BaseView {
        void success(List<EmptyGson> emptyGson);

        void failed(String error);
    }

    interface Presenter {
        void feedBack(String uid, String content);

        void updatePassword(String password, String password1,String uid);
    }
}

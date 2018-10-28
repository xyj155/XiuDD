package com.example.xiudd.sese.contract;

import com.example.xiudd.sese.base.BaseGson;
import com.example.xiudd.sese.base.BaseView;
import com.example.xiudd.sese.gson.UserGson;

import java.util.List;

import rx.Observable;

/**
 * Created by Xuyijie on 2018/10/22.
 */

public interface LocalUserContract {
    interface Model {
        Observable<BaseGson<UserGson>> getLocalUserList(String city, int limit);

        Observable<BaseGson<UserGson>> getContactUser(String uid);
    }

    interface View extends BaseView {
        void failed(String error);

        void showLocalUserList(List<UserGson> list);
    }

    interface Presenter {
        void getLocalUserList(String city, int limit);

        void getContactUser(String uid);
    }
}

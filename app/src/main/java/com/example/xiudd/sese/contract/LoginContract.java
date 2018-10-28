package com.example.xiudd.sese.contract;

import android.content.Context;

import com.example.xiudd.sese.base.BaseGson;
import com.example.xiudd.sese.base.BaseView;
import com.example.xiudd.sese.base.EmptyGson;
import com.example.xiudd.sese.gson.NovelGson;
import com.example.xiudd.sese.gson.UserGson;

import java.util.List;

import rx.Observable;

/**
 * Created by Xuyijie on 2018/10/22.
 */

public interface LoginContract {
    interface Model {
        Observable<BaseGson<UserGson>> UserRegister(String username,
                                                     String password,
                                                     String tel,
                                                     String sex,
                                                     String head,
                                                     String sign,
                                                    String location);

    }

    interface View extends BaseView {
        void UserRegister(UserGson emptyGsonBaseGson);

        void registerFailed(String error);

    }

    interface Presenter {
        void registerWithUser(String username,
                              String password,
                              String tel,
                              String sex,
                              String head,
                              String sign,
                              String location);

    }
}

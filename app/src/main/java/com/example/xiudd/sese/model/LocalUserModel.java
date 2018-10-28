package com.example.xiudd.sese.model;

import com.example.xiudd.sese.base.BaseGson;
import com.example.xiudd.sese.contract.LocalUserContract;
import com.example.xiudd.sese.gson.UserGson;
import com.example.xiudd.sese.http.RetrofitUtil;

import rx.Observable;

/**
 * Created by Xuyijie on 2018/10/22.
 */

public class LocalUserModel implements LocalUserContract.Model {
    @Override
    public Observable<BaseGson<UserGson>> getLocalUserList(String city, int limit) {
        return RetrofitUtil.getInstance().getServerices().getAroundUserList(city, limit);
    }

    @Override
    public Observable<BaseGson<UserGson>> getContactUser(String uid) {
        return RetrofitUtil.getInstance().getServerices().getUserContactList(uid);
    }
}

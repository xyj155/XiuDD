package com.example.xiudd.sese.model;

import com.example.xiudd.sese.base.BaseGson;
import com.example.xiudd.sese.contract.UserDailySignContract;
import com.example.xiudd.sese.gson.SignGson;
import com.example.xiudd.sese.http.RetrofitUtil;

import rx.Observable;

/**
 * Created by Xuyijie on 2018/10/22.
 */

public class UserDailySignModel implements UserDailySignContract.Model {
    @Override
    public Observable<BaseGson<SignGson>> sign(String uid) {
        return RetrofitUtil.getInstance().getServerices().UserSignDaily(uid);
    }
}

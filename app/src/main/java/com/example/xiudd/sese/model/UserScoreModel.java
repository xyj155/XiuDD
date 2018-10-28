package com.example.xiudd.sese.model;

import com.example.xiudd.sese.base.BaseGson;
import com.example.xiudd.sese.contract.UserScoreContract;
import com.example.xiudd.sese.gson.ScoreGson;
import com.example.xiudd.sese.gson.SystemPushGson;
import com.example.xiudd.sese.http.RetrofitUtil;

import rx.Observable;

/**
 * Created by Xuyijie on 2018/10/24.
 */

public class UserScoreModel implements UserScoreContract.Model {
    @Override
    public Observable<BaseGson<ScoreGson>> getUserScore(String uid) {
        return RetrofitUtil.getInstance().getServerices().getUserScore(uid);
    }

    @Override
    public Observable<BaseGson<SystemPushGson>> getSystemPush() {
        return RetrofitUtil.getInstance().getServerices().getSystemPush();
    }
}

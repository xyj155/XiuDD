package com.example.xiudd.sese.model;

import com.example.xiudd.sese.base.BaseGson;
import com.example.xiudd.sese.base.EmptyGson;
import com.example.xiudd.sese.contract.SettingContract;
import com.example.xiudd.sese.http.RetrofitUtil;

import rx.Observable;

/**
 * Created by Xuyijie on 2018/10/25.
 */

public class SettingModel implements SettingContract.Model {
    @Override
    public Observable<BaseGson<EmptyGson>> feedBack(String uid, String content) {
        return RetrofitUtil.getInstance().getServerices().feedBack(uid,content);
    }

    @Override
    public Observable<BaseGson<EmptyGson>> updatePassword(String password, String password1,String uid) {
        return RetrofitUtil.getInstance().getServerices().updatePassword(password,password1, uid);
    }
}

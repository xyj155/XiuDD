package com.example.xiudd.sese.model;

import android.util.Log;

import com.example.xiudd.sese.base.BaseGson;
import com.example.xiudd.sese.contract.UserContract;
import com.example.xiudd.sese.gson.UserGson;
import com.example.xiudd.sese.http.RetrofitUtil;

import rx.Observable;

import static com.shuyu.gsyvideoplayer.GSYPreViewManager.TAG;

/**
 * Created by Xuyijie on 2018/10/20.
 */

public class UserModel implements UserContract.Model {

    @Override
    public Observable<BaseGson<UserGson>> userLogin(String username, String password) {
        return RetrofitUtil.getInstance().getServerices().UserLogin(username,password);
    }

    @Override
    public Observable<BaseGson<UserGson>> QQUserLogin(String openid, String username, String avatar, String sex, String location, String tel, String sign) {
        Log.i(TAG, "QQUserLogin: "+openid+username+avatar+sex);
        return RetrofitUtil.getInstance().getServerices().QQUserLogin(openid,username,avatar,sex,location,tel,sign);
    }
}

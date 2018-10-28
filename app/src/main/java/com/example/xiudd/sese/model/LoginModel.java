package com.example.xiudd.sese.model;

import com.example.xiudd.sese.base.BaseGson;
import com.example.xiudd.sese.base.EmptyGson;
import com.example.xiudd.sese.contract.LoginContract;
import com.example.xiudd.sese.gson.UserGson;
import com.example.xiudd.sese.http.RetrofitUtil;

import rx.Observable;

/**
 * Created by Xuyijie on 2018/10/22.
 */

public class LoginModel implements LoginContract.Model {
    @Override
    public Observable<BaseGson<UserGson>> UserRegister(String username, String password, String tel, String sex, String head, String sign,String location) {
        return RetrofitUtil.getInstance().getServerices().UserRegister(username,password,tel,sex,head,sign,location);
    }
}

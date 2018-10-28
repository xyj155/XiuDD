package com.example.xiudd.sese.contract;

import com.example.xiudd.sese.base.BaseGson;
import com.example.xiudd.sese.base.BaseView;
import com.example.xiudd.sese.gson.UserGson;

import rx.Observable;

/**
 * Created by Xuyijie on 2018/10/20.
 */

public interface UserContract {
    interface Model {
        Observable<BaseGson<UserGson>> userLogin(String  username,String password);
        Observable<BaseGson<UserGson>> QQUserLogin(String openid, String username, String avatar, String sex, String location, String tel, String sign);
    }

    interface View extends BaseView {
        void userLogin(UserGson gsons);
        void loginFailed(String msg);
        void qqLogin(UserGson gsons);
        void setSexInfor(UserGson gsons);
        void upDateUserInforSuccess(UserGson gsons);
        void setUserInforFailed(UserGson userInforFailed);
    }

    interface Presenter {
        void loginUser(String  username,String password);
        void QQuserLogin(String openid,String username,String avatar,String sex,String location,String tel,String sign);
    }
}

package com.example.xiudd.sese.contract;

import com.example.xiudd.sese.base.BaseGson;
import com.example.xiudd.sese.base.BaseView;
import com.example.xiudd.sese.gson.ScoreGson;
import com.example.xiudd.sese.gson.SystemPushGson;

import java.util.List;

import rx.Observable;

/**
 * Created by Xuyijie on 2018/10/24.
 */

public interface UserScoreContract {
    interface Model {
        Observable<BaseGson<ScoreGson>> getUserScore(String uid);

        Observable<BaseGson<SystemPushGson>> getSystemPush();


    }

    interface View extends BaseView {
        void getUserScore(List<ScoreGson> gsons);

        void failed(String error);

        void getSystemPush(List<SystemPushGson> gsons);
    }

    interface Presenter {
        void getUserScore(String uid);

        void getSystemPush();
    }
}

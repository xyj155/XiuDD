package com.example.xiudd.sese.contract;

import com.example.xiudd.sese.base.BaseGson;
import com.example.xiudd.sese.base.BaseView;
import com.example.xiudd.sese.gson.PostListGson;
import com.example.xiudd.sese.gson.ScoreGson;
import com.example.xiudd.sese.gson.SystemPushGson;

import java.util.List;

import rx.Observable;

/**
 * Created by Xuyijie on 2018/10/24.
 */

public interface UserPostContract {
    interface Model {
        Observable<BaseGson<PostListGson>> getUserPost(String uid);

        Observable<BaseGson<PostListGson>> getUserCollectionPost(String uid);
    }

    interface View extends BaseView {
        void setUserPost(List<PostListGson> listGsons);

        void setUserCollectionPost(List<PostListGson> listGsons);
        void loadFailed(String  msg);
    }

    interface Presenter {
        void getUserPost(String uid);

        void getUserCollectionPost(String uid);
    }
}

package com.example.xiudd.sese.contract;

import com.example.xiudd.sese.base.BaseGson;
import com.example.xiudd.sese.base.EmptyGson;

import rx.Observable;

/**
 * Created by Xuyijie on 2018/10/26.
 */

public interface PostControlContract {
    interface Model {
        Observable<BaseGson<EmptyGson>> addCollection(String uid,String postId,String isDelete);
    }

    interface View {
        void addSuccess(String error);
        void addFailed(String error);
    }

    interface Presenter {
       void addCollection(String uid,String postId,String isDelete);
    }
}

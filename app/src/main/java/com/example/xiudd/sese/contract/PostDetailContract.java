package com.example.xiudd.sese.contract;

import com.example.xiudd.sese.base.BaseGson;
import com.example.xiudd.sese.base.BaseView;
import com.example.xiudd.sese.base.EmptyGson;
import com.example.xiudd.sese.gson.SinglePostDetailGson;

import rx.Observable;

/**
 * Created by 徐易杰 on 2018/10/26.
 */

public interface PostDetailContract {
    interface Model {
        Observable<BaseGson<SinglePostDetailGson>> getSinglePost(String id,String uid);

        Observable<BaseGson<EmptyGson>> setComment(String pid, String uid, String comment);

        Observable<BaseGson<EmptyGson>> addLike(String pid, String uid);
    }

    interface View extends BaseView {
        void loadFailed(String msg);

        void setSinglePost(SinglePostDetailGson singlePost);

        void updateCommentFailed(String msg);

        void setComment();

        void addLike();
        void addLikeFailed(String error);
    }

    interface Presenter {
        void getSinglePost(String id,String uid);

        void addLike(String pid, String uid);

        void setComment(String pid, String uid, String comment);
    }
}

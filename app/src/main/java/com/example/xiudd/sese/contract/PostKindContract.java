package com.example.xiudd.sese.contract;

import com.example.xiudd.sese.base.BaseGson;
import com.example.xiudd.sese.base.BaseView;
import com.example.xiudd.sese.gson.PostListGson;

import java.util.List;

import rx.Observable;

/**
 * Created by Xuyijie on 2018/10/27.
 */

public interface PostKindContract {
    interface Model {
        Observable<BaseGson<PostListGson>> getPostListByKind(String uid, String kind);
    }

    interface View extends BaseView {
        void loadFailed(String error);

        void setPostListByKind(List<PostListGson> postListGsons);
    }

    interface Presenter {
        void getPostListByKind(String uid, String kind);
    }
}

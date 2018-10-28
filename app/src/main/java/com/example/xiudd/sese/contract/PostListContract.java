package com.example.xiudd.sese.contract;

import com.example.xiudd.sese.base.BaseGson;
import com.example.xiudd.sese.base.BaseView;
import com.example.xiudd.sese.gson.PostListGson;

import java.util.List;

import rx.Observable;

/**
 * Created by Xuyijie on 2018/10/16.
 */

public interface PostListContract {
    interface Model {
        Observable<BaseGson<PostListGson>> getPostList(String  uid);
    }

    interface View extends BaseView {
        void loadPostList(List<PostListGson> postListGsons);

    }

    interface Presenter {
        void setPostList(String  uid);
    }
}

package com.example.xiudd.sese.model;

import com.example.xiudd.sese.base.BaseGson;
import com.example.xiudd.sese.contract.PostListContract;
import com.example.xiudd.sese.gson.PostListGson;
import com.example.xiudd.sese.http.RetrofitUtil;

import rx.Observable;

/**
 * Created by Xuyijie on 2018/10/16.
 */

public class PostListModel implements PostListContract.Model {
    @Override
    public Observable<BaseGson<PostListGson>> getPostList(String  uid) {
        return RetrofitUtil.getInstance().getServerices().getPostList(uid);
    }
}

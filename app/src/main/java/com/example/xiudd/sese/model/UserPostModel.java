package com.example.xiudd.sese.model;

import com.example.xiudd.sese.base.BaseGson;
import com.example.xiudd.sese.contract.UserPostContract;
import com.example.xiudd.sese.gson.PostListGson;
import com.example.xiudd.sese.http.RetrofitUtil;

import rx.Observable;

/**
 * Created by Xuyijie on 2018/10/24.
 */

public class UserPostModel implements UserPostContract.Model {
    @Override
    public Observable<BaseGson<PostListGson>> getUserPost(String uid) {
        return RetrofitUtil.getInstance().getServerices().getUserPost(uid);
    }

    @Override
    public Observable<BaseGson<PostListGson>> getUserCollectionPost(String uid) {
        return RetrofitUtil.getInstance().getServerices().getUserCollectionPost(uid);
    }
}

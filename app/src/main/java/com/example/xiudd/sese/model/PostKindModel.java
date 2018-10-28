package com.example.xiudd.sese.model;

import com.example.xiudd.sese.base.BaseGson;
import com.example.xiudd.sese.contract.PostKindContract;
import com.example.xiudd.sese.gson.PostListGson;
import com.example.xiudd.sese.http.RetrofitUtil;

import rx.Observable;

/**
 * Created by Xuyijie on 2018/10/27.
 */

public class PostKindModel implements PostKindContract.Model {
    @Override
    public Observable<BaseGson<PostListGson>> getPostListByKind(String uid, String kind) {
        return RetrofitUtil.getInstance().getServerices().getPostListByKind(uid,kind);
    }
}

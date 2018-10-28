package com.example.xiudd.sese.model;

import com.example.xiudd.sese.base.BaseGson;
import com.example.xiudd.sese.base.EmptyGson;
import com.example.xiudd.sese.contract.PostDetailContract;
import com.example.xiudd.sese.gson.SinglePostDetailGson;
import com.example.xiudd.sese.http.RetrofitUtil;

import rx.Observable;

/**
 * Created by 徐易杰 on 2018/10/26.
 */

public class PostDetailModel implements PostDetailContract.Model {
    @Override
    public Observable<BaseGson<SinglePostDetailGson>> getSinglePost(String id,String uid) {
        return RetrofitUtil.getInstance().getServerices().getPostById(id,uid);
    }

    @Override
    public Observable<BaseGson<EmptyGson>> setComment(String pid, String uid, String comment) {
        return RetrofitUtil.getInstance().getServerices().setComment(pid,uid,comment);
    }

    @Override
    public Observable<BaseGson<EmptyGson>> addLike(String pid, String uid) {
        return RetrofitUtil.getInstance().getServerices().addLike(uid,pid);
    }
}

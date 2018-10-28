package com.example.xiudd.sese.model;

import com.example.xiudd.sese.base.BaseGson;
import com.example.xiudd.sese.base.EmptyGson;
import com.example.xiudd.sese.contract.PostControlContract;
import com.example.xiudd.sese.http.RetrofitUtil;

import rx.Observable;

/**
 * Created by Xuyijie on 2018/10/26.
 */

public class PostControlModel implements PostControlContract.Model {
    @Override
    public Observable<BaseGson<EmptyGson>> addCollection(String uid, String postId,String  isdelete) {
        return RetrofitUtil.getInstance().getServerices().addCollection(uid,postId,isdelete);
    }
}

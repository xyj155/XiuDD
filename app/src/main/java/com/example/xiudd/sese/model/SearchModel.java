package com.example.xiudd.sese.model;

import com.example.xiudd.sese.base.BaseGson;
import com.example.xiudd.sese.contract.SearchContract;
import com.example.xiudd.sese.gson.PostListGson;
import com.example.xiudd.sese.http.RetrofitUtil;

import rx.Observable;

/**
 * Created by Xuyijie on 2018/10/27.
 */

public class SearchModel implements SearchContract.Model {
    @Override
    public Observable<BaseGson<PostListGson>> searchByTitle(String uid, String content) {
        return RetrofitUtil.getInstance().getServerices().getPostListBySearch(uid,content);
    }
}

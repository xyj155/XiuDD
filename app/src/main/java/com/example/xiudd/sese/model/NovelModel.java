package com.example.xiudd.sese.model;

import com.example.xiudd.sese.base.BaseGson;
import com.example.xiudd.sese.contract.NovelContract;
import com.example.xiudd.sese.gson.NovelGson;
import com.example.xiudd.sese.http.RetrofitUtil;

import rx.Observable;

/**
 * Created by Xuyijie on 2018/10/17.
 */

public class NovelModel implements NovelContract.Model {


    @Override
    public Observable<BaseGson<NovelGson>> getNovelList(String kind) {
        return RetrofitUtil.getInstance().getServerices().getNovelList(kind);
    }
}

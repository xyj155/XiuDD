package com.example.xiudd.sese.model;

import com.example.xiudd.sese.base.BaseGson;
import com.example.xiudd.sese.contract.ForeignVideoContract;
import com.example.xiudd.sese.gson.ForeignGson;
import com.example.xiudd.sese.http.RetrofitUtil;

import rx.Observable;

/**
 * Created by Xuyijie on 2018/10/17.
 */

public class ForeignVideoModel implements ForeignVideoContract.Model {
    @Override
    public Observable<BaseGson<ForeignGson>> getForeignVideo() {
        return RetrofitUtil.getInstance().getServerices().getForeignVideoList();
    }
}

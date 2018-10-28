package com.example.xiudd.sese.model;

import com.example.xiudd.sese.base.BaseGson;
import com.example.xiudd.sese.contract.TeenContract;
import com.example.xiudd.sese.gson.ForeignGson;
import com.example.xiudd.sese.gson.HotVideoGson;
import com.example.xiudd.sese.http.RetrofitUtil;

import rx.Observable;

/**
 * Created by Xuyijie on 2018/10/17.
 */

public class TeenModel implements TeenContract.Model {
    @Override
    public Observable<BaseGson<ForeignGson>> getTeenVideo() {
        return RetrofitUtil.getInstance().getServerices().getTeenVideoList();
    }
}

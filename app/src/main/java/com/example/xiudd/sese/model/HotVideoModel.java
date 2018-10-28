package com.example.xiudd.sese.model;

import com.example.xiudd.sese.base.BaseGson;
import com.example.xiudd.sese.contract.HotVideoContract;
import com.example.xiudd.sese.gson.HotVideoGson;
import com.example.xiudd.sese.http.RetrofitUtil;

import rx.Observable;

/**
 * Created by Xuyijie on 2018/10/17.
 */

public class HotVideoModel implements HotVideoContract.Model {
    @Override
    public Observable<BaseGson<HotVideoGson>> getHotVideo() {
        return RetrofitUtil.getInstance().getServerices().getHotVideoList();
    }
}

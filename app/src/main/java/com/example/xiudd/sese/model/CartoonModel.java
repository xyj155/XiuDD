package com.example.xiudd.sese.model;

import com.example.xiudd.sese.base.BaseGson;
import com.example.xiudd.sese.contract.CartoonContract;
import com.example.xiudd.sese.gson.HotVideoGson;
import com.example.xiudd.sese.http.RetrofitUtil;

import rx.Observable;

/**
 * Created by Xuyijie on 2018/10/18.
 */

public class CartoonModel implements CartoonContract.Model {
    @Override
    public Observable<BaseGson<HotVideoGson>> getCartoonVideo() {
        return RetrofitUtil.getInstance().getServerices().getCartoonList();
    }
}

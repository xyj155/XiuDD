package com.example.xiudd.sese.model;

import com.example.xiudd.sese.base.BaseGson;
import com.example.xiudd.sese.base.EmptyGson;
import com.example.xiudd.sese.contract.VipContract;
import com.example.xiudd.sese.http.RetrofitUtil;

import rx.Observable;

/**
 * Created by Xuyijie on 2018/10/27.
 */

public class VipModel implements VipContract.Model {
    @Override
    public Observable<BaseGson<EmptyGson>> vipCharge(String uid, String rank) {
        return RetrofitUtil.getInstance().getServerices().vipCharge(uid,rank);
    }
}

package com.example.xiudd.sese.model;

import com.example.xiudd.sese.base.BaseGson;
import com.example.xiudd.sese.contract.OnlineContract;
import com.example.xiudd.sese.gson.HotVideoGson;
import com.example.xiudd.sese.http.RetrofitUtil;

import rx.Observable;

/**
 * Created by Xuyijie on 2018/10/18.
 */

public class OnlineModel implements OnlineContract.Model {
    @Override
    public Observable<BaseGson<HotVideoGson>> getCartoonVideo() {
        return RetrofitUtil.getInstance().getServerices().getOnlineList();
    }
}

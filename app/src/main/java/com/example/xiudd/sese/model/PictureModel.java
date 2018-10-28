package com.example.xiudd.sese.model;

import com.example.xiudd.sese.base.BaseGson;
import com.example.xiudd.sese.contract.PictureContract;
import com.example.xiudd.sese.gson.PictureGson;
import com.example.xiudd.sese.http.RetrofitUtil;

import rx.Observable;

/**
 * Created by Xuyijie on 2018/10/18.
 */

public class PictureModel implements PictureContract.Model {
    @Override
    public Observable<BaseGson<PictureGson>> getPictureList() {
        return RetrofitUtil.getInstance().getServerices().getPictureList();
    }
}

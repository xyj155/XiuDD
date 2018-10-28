package com.example.xiudd.sese.model;

import com.example.xiudd.sese.base.BaseGson;
import com.example.xiudd.sese.contract.PictureFragmentContract;
import com.example.xiudd.sese.gson.BeautifulDataBean;
import com.example.xiudd.sese.gson.BeautifulGson;
import com.example.xiudd.sese.http.RetrofitUtil;

import rx.Observable;

/**
 * Created by 徐易杰 on 2018/10/19.
 */

public class PictureFragmentModel implements PictureFragmentContract.Model {


    @Override
    public Observable<BaseGson<BeautifulGson<BeautifulDataBean>>> getPiclist(String pic) {
        return RetrofitUtil.getInstance().getServerices().getBeauPicList(pic);
    }
}

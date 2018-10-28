package com.example.xiudd.sese.contract;

import com.example.xiudd.sese.base.BaseGson;
import com.example.xiudd.sese.base.BaseView;
import com.example.xiudd.sese.gson.BeautifulDataBean;
import com.example.xiudd.sese.gson.BeautifulGson;

import java.util.List;

import rx.Observable;

/**
 * Created by 徐易杰 on 2018/10/19.
 */

public interface PictureFragmentContract {
    interface Model {
        Observable<BaseGson<BeautifulGson<BeautifulDataBean>>> getPiclist(String pic);
    }

    interface View extends BaseView {
        void loadPicList(List<BeautifulDataBean> postListGsons);

    }

    interface Presenter {
        void setPiclist(String pic);
    }
}

package com.example.xiudd.sese.contract;

import com.example.xiudd.sese.base.BaseGson;
import com.example.xiudd.sese.base.BaseView;
import com.example.xiudd.sese.gson.PictureGson;

import java.util.List;

import rx.Observable;

/**
 * Created by Xuyijie on 2018/10/18.
 */

public interface PictureContract {
    interface Model {
        Observable<BaseGson<PictureGson>> getPictureList();
    }

    interface View extends BaseView {
        void setPictureList(List<PictureGson> list);

        void getPictureDetail(List<String> stringList);
    }

    interface Presenter {
        void getPictureList();

        void getPictureDetail(String url);
    }
}

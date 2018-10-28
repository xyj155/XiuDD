package com.example.xiudd.sese.contract;

import com.example.xiudd.sese.base.BaseGson;
import com.example.xiudd.sese.base.BaseView;
import com.example.xiudd.sese.gson.ForeignGson;
import com.example.xiudd.sese.gson.HotVideoGson;

import java.util.List;

import rx.Observable;

/**
 * Created by Xuyijie on 2018/10/17.
 */

public interface ForeignVideoContract {
    interface Model {
        Observable<BaseGson<ForeignGson>> getForeignVideo();
    }

    interface View extends BaseView {
        void loadForeignVideo(List<ForeignGson> gsons);
    }

    interface Presenter {
        void setForeignVideo();
    }
}

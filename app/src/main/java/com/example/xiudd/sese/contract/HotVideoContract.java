package com.example.xiudd.sese.contract;

import com.example.xiudd.sese.base.BaseGson;
import com.example.xiudd.sese.base.BaseView;
import com.example.xiudd.sese.gson.HotVideoGson;

import java.util.List;

import rx.Observable;

/**
 * Created by Xuyijie on 2018/10/17.
 */

public interface HotVideoContract {
    interface Model {
        Observable<BaseGson<HotVideoGson>> getHotVideo();
    }

    interface View extends BaseView {
        void loadHotVideo(List<HotVideoGson> gsons);
    }

    interface Presenter {
        void setHotVideo();
    }
}

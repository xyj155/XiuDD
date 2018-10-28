package com.example.xiudd.sese.contract;

import com.example.xiudd.sese.base.BaseGson;
import com.example.xiudd.sese.base.BaseView;
import com.example.xiudd.sese.gson.HotVideoGson;

import java.util.List;

import rx.Observable;

/**
 * Created by Xuyijie on 2018/10/18.
 */

public interface CartoonContract {
    interface Model {
        Observable<BaseGson<HotVideoGson>> getCartoonVideo();
    }

    interface View extends BaseView {
        void loadCartoonVideo(List<HotVideoGson> gsons);
    }

    interface Presenter {
        void setCartoonVideo();
    }
}

package com.example.xiudd.sese.contract;

import android.content.Context;

import com.example.xiudd.sese.base.BaseGson;
import com.example.xiudd.sese.base.BaseView;
import com.example.xiudd.sese.gson.NovelGson;

import java.util.List;

import rx.Observable;

/**
 * Created by Xuyijie on 2018/10/17.
 */

public interface NovelContract {
    interface Model {
        Observable<BaseGson<NovelGson>> getNovelList(String kind);

    }

    interface View extends BaseView {
        void setNovelList(List<NovelGson> list);

        void downLoadTxt(String path);
    }

    interface Presenter {
        void getNovelList(String kind);

        void downLoadTxt(Context context,String url);
    }
}

package com.example.xiudd.sese.contract;

import com.example.xiudd.sese.base.BaseGson;
import com.example.xiudd.sese.base.BaseView;
import com.example.xiudd.sese.gson.PostListGson;

import java.util.List;

import rx.Observable;

/**
 * Created by Xuyijie on 2018/10/27.
 */

public interface SearchContract {
    interface Model {
        Observable<BaseGson<PostListGson>> searchByTitle(String uid,String content);
    }

    interface View extends BaseView{
void loadFailed(String msg);
        void loadSearch(List<PostListGson> listGsons);
    }

    interface Presenter {
      void   searchByTitle(String uid,String content);
    }
}

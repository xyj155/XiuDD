package com.example.xiudd.sese.contract;

import com.example.xiudd.sese.base.BaseGson;
import com.example.xiudd.sese.base.EmptyGson;

import java.util.List;
import java.util.Map;

import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import rx.Observable;

/**
 * Created by 徐易杰 on 2018/10/19.
 */

public interface EditPostContract {
    interface Model {
        Observable<BaseGson<EmptyGson>> uploadPost(
                Map<String, RequestBody> partMap,
                List<MultipartBody.Part> file);
    }

    interface View {
        void isUploadSuccess(BaseGson<EmptyGson> baseGson);

        void showLoading(String msg);

        void hideLoading();
    }

    interface Presenter {
        void uploadPost(
                Map<String, RequestBody> partMap,
                List<MultipartBody.Part> file);
    }
}

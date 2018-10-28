package com.example.xiudd.sese.presenter;

import com.example.xiudd.sese.base.BaseGson;
import com.example.xiudd.sese.base.BaseObserver;
import com.example.xiudd.sese.base.EmptyGson;
import com.example.xiudd.sese.contract.EditPostContract;
import com.example.xiudd.sese.model.EditPostModel;

import java.util.List;
import java.util.Map;

import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * Created by 徐易杰 on 2018/10/19.
 */

public class EditPostPresenter implements EditPostContract.Presenter {
    private EditPostModel editPostModel = new EditPostModel();
    private EditPostContract.View view;

    public EditPostPresenter(EditPostContract.View view) {
        this.view = view;
    }

    @Override
    public void uploadPost(Map<String, RequestBody> partMap, List<MultipartBody.Part> file) {
        view.showLoading("提交中...");
        editPostModel.uploadPost(partMap, file)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new BaseObserver<BaseGson<EmptyGson>>() {
                    @Override
                    public void onError(String error) {
                        view.hideLoading();

                    }

                    @Override
                    public void onCompleted() {

                    }

                    @Override
                    public void onNext(BaseGson<EmptyGson> emptyGsonBaseGson) {
                        view.hideLoading();

                        view.isUploadSuccess(emptyGsonBaseGson);

                    }
                });
    }
}

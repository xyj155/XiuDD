package com.example.xiudd.sese.model;

import com.example.xiudd.sese.base.BaseGson;
import com.example.xiudd.sese.base.EmptyGson;
import com.example.xiudd.sese.contract.EditPostContract;
import com.example.xiudd.sese.http.RetrofitUtil;

import java.util.List;
import java.util.Map;

import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import rx.Observable;

/**
 * Created by 徐易杰 on 2018/10/19.
 */

public class EditPostModel implements EditPostContract.Model {
    @Override
    public Observable<BaseGson<EmptyGson>> uploadPost(Map<String, RequestBody> partMap, List<MultipartBody.Part> file) {
        return RetrofitUtil.getInstance().getServerices().setNewPost(partMap, file);
    }
}

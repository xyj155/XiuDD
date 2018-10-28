package com.example.xiudd.sese.presenter;

import android.content.Context;
import android.os.Environment;
import android.support.annotation.RequiresApi;
import android.util.Log;
import android.view.View;
import android.widget.Toast;


import com.bumptech.glide.Glide;
import com.example.xiudd.sese.base.BaseGson;
import com.example.xiudd.sese.base.BaseObserver;
import com.example.xiudd.sese.contract.NovelContract;
import com.example.xiudd.sese.gson.NovelGson;
import com.example.xiudd.sese.http.RetrofitUtil;
import com.example.xiudd.sese.listener.DownloadListener;
import com.example.xiudd.sese.model.NovelModel;
import com.example.xiudd.sese.util.ToastUtils;
import com.example.xiudd.sese.util.rxjavaDownload.DownloadUtil;


import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * Created by Xuyijie on 2018/10/17.
 */

public class NovelPresenter implements NovelContract.Presenter {
    private NovelContract.View view;
    private NovelModel novelModel = new NovelModel();

    public NovelPresenter(NovelContract.View view) {
        this.view = view;
    }

    @Override
    public void getNovelList(String kind) {
        novelModel.getNovelList(kind)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new BaseObserver<BaseGson<NovelGson>>() {
                    @Override
                    public void onError(String error) {
                        ToastUtils.getInstance().showText("请求错误:" + error);
                    }

                    @Override
                    public void onCompleted() {

                    }

                    @Override
                    public void onNext(BaseGson<NovelGson> novelGsonBaseGson) {
                        if (novelGsonBaseGson.isSuccess()) {
                            view.setNovelList(novelGsonBaseGson.getDataList());
                        } else {
                            ToastUtils.getInstance().showText("请求错误:" + novelGsonBaseGson.getCode());
                        }
                    }
                });
    }

    private static final String TAG = "NovelPresenter";
    @Override
    public void downLoadTxt(Context context,String url) {
        //下载相关
        DownloadUtil downloadUtil = new DownloadUtil();
        downloadUtil.downloadFile(url, new DownloadListener() {
            @Override
            public void onStart() {
                Log.e(TAG, "onStart: ");
//                runOnUiThread(new Runnable() {
//                    @Override
//                    public void run() {
//                        mCircleProgressLayout.setVisibility(View.VISIBLE);
//                    }
//                });

            }

            @Override
            public void onProgress(final int currentLength) {
                Log.e(TAG, "onLoading: " + currentLength);
//                runOnUiThread(new Runnable() {
//                    @Override
//                    public void run() {
//                        mCircleProgress.setProgress(currentLength);
//                    }
//                });

            }

            @Override
            public void onFinish(final String localPath) {
                view.downLoadTxt(localPath);
                Log.e(TAG, "onFinish: " + localPath);
//                runOnUiThread(new Runnable() {
//                    @Override
//                    public void run() {
//                        mCircleProgressLayout.setVisibility(View.GONE);
//                        Glide.with(mContext).load(localPath).into(mPicture);
//                    }
//                });
            }

            @Override
            public void onFailure(final String erroInfo) {
                Log.e(TAG, "onFailure: " + erroInfo);
//                runOnUiThread(new Runnable() {
//                    @Override
//                    public void run() {
//                        mCircleProgressLayout.setVisibility(View.GONE);
//                        Toast.makeText(mContext, erroInfo, Toast.LENGTH_SHORT).show();
//                    }
//                });
            }
        });
    }
}

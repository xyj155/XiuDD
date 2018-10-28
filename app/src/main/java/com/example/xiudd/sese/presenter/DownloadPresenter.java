package com.example.xiudd.sese.presenter;

import android.util.Log;

import com.example.xiudd.sese.contract.DownloadContract;
import com.example.xiudd.sese.listener.DownloadListener;
import com.example.xiudd.sese.util.rxjavaDownload.DownloadUtil;

import static com.google.android.exoplayer2.ExoPlayerLibraryInfo.TAG;

/**
 * Created by Xuyijie on 2018/10/19.
 */

public class DownloadPresenter implements DownloadContract.Presenter {
    private DownloadContract.View view;

    public DownloadPresenter(DownloadContract.View view) {
        this.view = view;
    }

    @Override
    public void downLoadTxt(String url) {
        DownloadUtil downloadUtil = new DownloadUtil();
        downloadUtil.downloadFile(url, new DownloadListener() {
            @Override
            public void onStart() {
                view.starDownload();
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
                view.onFinish(localPath);
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

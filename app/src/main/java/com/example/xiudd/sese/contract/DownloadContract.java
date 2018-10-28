package com.example.xiudd.sese.contract;

import android.content.Context;

/**
 * Created by Xuyijie on 2018/10/19.
 */

public interface DownloadContract {
    interface Model {
    }

    interface View {
        void onFinish(String path);

        void starDownload();

void onProgress(int progress);
    }

    interface Presenter {
        void downLoadTxt(String url);
    }
}

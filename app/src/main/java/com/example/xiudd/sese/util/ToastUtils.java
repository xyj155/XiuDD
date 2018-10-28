package com.example.xiudd.sese.util;

import android.content.Context;
import android.widget.Toast;

import com.example.xiudd.sese.MyApp;

public class ToastUtils {
    private static volatile ToastUtils singleton;
    private Toast mToast;
    private Context mContext=MyApp.getInstances().getApplicationContext();

    private ToastUtils() {
    }

    public static ToastUtils getInstance() {
        if (singleton == null) {
            synchronized (ToastUtils.class) {
                if (singleton == null) {
                    singleton = new ToastUtils();
                }
            }
        }
        return singleton;
    }

    public void init(Context context) {
        mContext = MyApp.getInstances().getApplicationContext();
    }

    public void showText(int messageId) {
        showText(mContext.getString(messageId));
    }

    public void showText(CharSequence message) {
        if (mToast != null) {
            mToast.cancel();
        }
        mToast = Toast.makeText(mContext, message, Toast.LENGTH_LONG);
        mToast.show();
    }

    public void cancel() {
        if (mToast != null) {
            mToast.cancel();
        }
    }
}
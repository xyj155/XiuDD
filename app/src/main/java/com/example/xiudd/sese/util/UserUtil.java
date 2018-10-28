package com.example.xiudd.sese.util;

import android.content.Context;
import android.content.SharedPreferences;

import com.example.xiudd.sese.MyApp;

/**
 * Created by Xuyijie on 2018/10/27.
 */

public class UserUtil {
    public static boolean isLogin() {
        SharedPreferences sharedPreferences = MyApp.getInstances().getSharedPreferences("user", Context.MODE_PRIVATE);
        String user = sharedPreferences.getString("user", "");
        if (user.isEmpty()) {
            return false;
        } else {
            return true;
        }
    }
}

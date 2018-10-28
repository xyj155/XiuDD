package com.example.xiudd.sese;

import android.app.Application;
import android.database.sqlite.SQLiteDatabase;
import android.os.Environment;
import android.support.multidex.MultiDexApplication;


import com.example.xiudd.sese.greenDAO.DaoMaster;
import com.example.xiudd.sese.greenDAO.DaoSession;
import com.payelves.sdk.EPay;
import com.tencent.bugly.Bugly;
import com.tencent.bugly.beta.Beta;

import cn.sharesdk.framework.ShareSDK;
import io.rong.imkit.RongIM;


/**
 * Created by Xuyijie on 2018/10/15.
 */

public class MyApp extends MultiDexApplication {
    public static MyApp instances;

    public static MyApp getInstances() {
        return instances;
    }

    private DaoMaster.DevOpenHelper mHelper;
    private SQLiteDatabase db;
    private DaoMaster mDaoMaster;
    private DaoSession mDaoSession;

    @Override
    public void onCreate() {
        super.onCreate();
        RongIM.init(this);
        instances = this;
        setDatabase();
        EPay.getInstance(getApplicationContext()).init("wAwS4BHkB", "1b0ccf51458c4053ae2931772fbbfb97",
                "7115565426999297", "baidu");
        Bugly.init(getApplicationContext(), "5f90694514", false);
        Beta.autoInit = true;
        Beta.autoCheckUpgrade = true;
        Beta.initDelay = 6*1000;
        Beta.defaultBannerId = R.drawable.ic_back;
    }

    /**
     * 设置greenDAO
     */
    private void setDatabase() {
        // 通过 DaoMaster 的内部类 DevOpenHelper，你可以得到一个便利的 SQLiteOpenHelper 对象。
        // 可能你已经注意到了，你并不需要去编写「CREATE TABLE」这样的 SQL 语句，因为 greenDAO已经帮你做了。
        // 注意：默认的 DaoMaster.DevOpenHelper 会在数据库升级时，删除所有的表，意味着这将导致数据的丢失。
        // 所以，在正式的项目中，你还应该做一层封装，来实现数据库的安全升级。
        mHelper = new DaoMaster.DevOpenHelper(this, "down-db", null);
        db = mHelper.getWritableDatabase();
        // 注意：该数据库连接属于 DaoMaster，所以多个 Session 指的是相同的数据库连接。
        mDaoMaster = new DaoMaster(db);
        mDaoSession = mDaoMaster.newSession();
    }

    public DaoSession getDaoSession() {
        return mDaoSession;
    }

    public SQLiteDatabase getDb() {
        return db;
    }


}

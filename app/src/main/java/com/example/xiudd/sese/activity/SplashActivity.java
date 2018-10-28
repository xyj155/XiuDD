package com.example.xiudd.sese.activity;

import android.Manifest;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Toast;

import com.amap.api.location.AMapLocation;
import com.amap.api.location.AMapLocationClient;
import com.amap.api.location.AMapLocationClientOption;
import com.amap.api.location.AMapLocationListener;
import com.example.xiudd.sese.MyApp;
import com.example.xiudd.sese.R;
import com.example.xiudd.sese.base.BaseActivity;
import com.example.xiudd.sese.util.permission.KbPermission;
import com.example.xiudd.sese.util.permission.KbPermissionListener;
import com.example.xiudd.sese.util.permission.KbPermissionUtils;
import com.example.xiudd.sese.view.AppDialog;


public class SplashActivity extends BaseActivity {

    public AMapLocationClient mLocationClient = new AMapLocationClient(MyApp.getInstances().getApplicationContext());
    public AMapLocationClientOption mLocationOption = new AMapLocationClientOption();
    private static final int WRITE_EXTERNAL_STORAGE_REQUEST_CODE = 100;


    @Override
    public int intiLayout() {
        return R.layout.activity_splash;
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        requestWindowFeature(Window.FEATURE_NO_TITLE);// 隐藏标题

        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,

                WindowManager.LayoutParams.FLAG_FULLSCREEN);// 设置全屏
        super.onCreate(savedInstanceState);

        mLocationOption.setLocationMode(AMapLocationClientOption.AMapLocationMode.Hight_Accuracy);
        mLocationOption.setOnceLocation(true);
        mLocationOption.setOnceLocationLatest(true);
        mLocationOption.setNeedAddress(true);



    }

    public void isNetWork() {

        runOnUiThread(new Runnable() {
            @Override
            public void run() {
                if (KbPermissionUtils.needRequestPermission()) { //判断是否需要动态申请权限
                    KbPermission.with(SplashActivity.this)
                            .requestCode(100)
                            .permission(Manifest.permission.INTERNET, Manifest.permission.CAMERA, Manifest.permission.READ_EXTERNAL_STORAGE, Manifest.permission.WRITE_EXTERNAL_STORAGE, Manifest.permission.ACCESS_FINE_LOCATION) //需要申请的权限(支持不定长参数)
                            .callBack(new KbPermissionListener() {
                                @Override
                                public void onPermit(int requestCode, String... permission) { //允许权限的回调
//                            downloadVideo(); //处理具体下载过程
                                    Log.i(TAG, "onPermit: " + "dfjioafa");
                                    intialLocation();

                                }

                                @Override
                                public void onCancel(int requestCode, String... permission) { //拒绝权限的回调
                                    KbPermissionUtils.goSetting(SplashActivity.this); //跳转至当前app的权限设置界面
                                }
                            })
                            .send();
                } else {
                    intialLocation();
                }
            }
        });
    }

    @Override
    public void initView() {
        isNetWork();


    }

    private void intialLocation() {
        mLocationClient.setLocationListener(new AMapLocationListener() {
            @Override
            public void onLocationChanged(AMapLocation aMapLocation) {
                Log.i(TAG, "onLocationChanged: " + aMapLocation.getCity());
                SharedPreferences.Editor editor = getSharedPreferences("user", MODE_PRIVATE).edit();
                editor.putString("location", aMapLocation.getCity());
                editor.apply();
            }
        });
        mLocationOption.setLocationPurpose(AMapLocationClientOption.AMapLocationPurpose.SignIn);
        if (null != mLocationClient) {
            mLocationClient.setLocationOption(mLocationOption);
            //设置场景模式后最好调用一次stop，再调用start以保证场景模式生效
            mLocationClient.stopLocation();
            mLocationClient.startLocation();
        }
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                startActivity(new Intent(SplashActivity.this, MainActivity.class));
                finish();
            }
        }, 2500);
    }

    @Override
    public void initData() {

    }

    @Override
    public void onRequestPermissionsResult(int requestCode, String[] permissions, int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if (requestCode == WRITE_EXTERNAL_STORAGE_REQUEST_CODE) {
            if (grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                intialLocation();
            } else {
                Toast.makeText(this, "应用使用需要开启权限", Toast.LENGTH_SHORT).show();
            }
        }


    }


}

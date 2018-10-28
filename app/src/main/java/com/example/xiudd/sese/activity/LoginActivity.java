package com.example.xiudd.sese.activity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.xiudd.sese.R;
import com.example.xiudd.sese.base.BaseActivity;
import com.example.xiudd.sese.constant.Constant;
import com.example.xiudd.sese.contract.UserContract;
import com.example.xiudd.sese.gson.UserGson;
import com.example.xiudd.sese.presenter.UserPresenter;

import java.util.HashMap;

import butterknife.ButterKnife;
import butterknife.InjectView;
import butterknife.OnClick;
import cn.sharesdk.framework.Platform;
import cn.sharesdk.framework.PlatformActionListener;
import cn.sharesdk.framework.ShareSDK;
import cn.sharesdk.sina.weibo.SinaWeibo;
import cn.sharesdk.tencent.qq.QQ;


public class LoginActivity extends BaseActivity implements UserContract.View {


    @InjectView(R.id.et_username)
    EditText etUsername;
    @InjectView(R.id.et_password)
    EditText etPassword;
    @InjectView(R.id.relativeLayout)
    RelativeLayout relativeLayout;
    @InjectView(R.id.btn_login)
    Button btnLogin;
    @InjectView(R.id.tv_forget)
    TextView tvForget;
    @InjectView(R.id.iv_qq)
    ImageView ivQq;
    @InjectView(R.id.iv_wb)
    ImageView ivWb;
    @InjectView(R.id.iv_phone)
    ImageView ivPhone;
    @InjectView(R.id.tv_register)
    TextView tvRegister;
    public static LoginActivity loginActivity = null;

    private UserPresenter userPresenter = new UserPresenter(this);

    @Override
    public int intiLayout() {
        return R.layout.activity_login;
    }

    @Override
    public void initView() {
        ButterKnife.inject(this);

        etUsername.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if (s.toString().equals("")) {
                    btnLogin.setBackgroundResource(R.drawable.login_btn_bg_c_8_normal);
                    btnLogin.setTextColor(0xff000000);
                } else {
                    btnLogin.setTextColor(0xffffffff);
                    btnLogin.setBackgroundResource(R.drawable.login_btn_bg_c_8_red);
                }
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });
    }

    @Override
    public void initData() {

    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // TODO: add setContentView(...) invocation
        loginActivity = this;
    }


    @OnClick({R.id.btn_login, R.id.tv_forget, R.id.iv_qq, R.id.iv_wb, R.id.iv_phone, R.id.tv_register})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.btn_login:
                userPresenter.loginUser(etUsername.getText().toString(), etPassword.getText().toString());
                break;
            case R.id.tv_forget:
                break;
            case R.id.iv_qq:
                author(QQ.NAME);
                break;
            case R.id.iv_wb:
                author(SinaWeibo.NAME);
                break;
            case R.id.iv_phone:
                break;
            case R.id.tv_register:
                startActivity(new Intent(LoginActivity.this, TelRegisterPhoneActivity.class));
                break;
        }
    }


    private void author(String name) {
        showmDialog("数据获取中...");
        final Platform plat = ShareSDK.getPlatform(name);
        plat.removeAccount(true);
        plat.SSOSetting(false);
        plat.setPlatformActionListener(new PlatformActionListener() {
            @Override
            public void onComplete(final Platform platform, int i, HashMap<String, Object> hashMap) {
                hidemDialog();
                SharedPreferences.Editor editor = getSharedPreferences("qq", MODE_PRIVATE).edit();
                editor.putString("nickname", platform.getDb().getUserName());
                editor.putString("openid", platform.getDb().getUserId());
                editor.putString("avatar", platform.getDb().getUserIcon());
                editor.putString("username", platform.getDb().getUserName());
                editor.apply();
                Log.i(TAG, "onComplete: " + platform.getDb().getUserId() + platform.getDb().getUserName());
                //String openid, final String username, String avatar, String sex, String location, String tel, String sign
                try {
                    runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            userPresenter.QQuserLogin(getSharedPreferences("qq", MODE_PRIVATE).getString("openid", ""),
                                    getSharedPreferences("qq", MODE_PRIVATE).getString("username", ""),
                                    getSharedPreferences("qq", MODE_PRIVATE).getString("avatar", ""),
                                    "",
                                    getSharedPreferences("user", MODE_PRIVATE).getString("location", ""), "", "");
                        }
                    });


                } catch (Exception e) {
                    Log.i(TAG, "onComplete: " + e.getMessage());
                }

//                userPresenter.QQUserLogin(platform.getDb().getUserId(),platform.getDb().getUserName(),platform.getDb().getUserIcon(),);
//                AVUser.loginWithAuthData(map, strplatform, new LogInCallback<AVUser>() {
//                    @Override
//                    public void done(AVUser avUser, AVException e) {
//                        if (e == null) {
//                            Gson gson = new Gson();
//                            String userGson = avUser.toJSONObject().toString();
//                            Log.i(TAG, "done: "+userGson);
//                            AVUserGson avUserGson = gson.fromJson(userGson, AVUserGson.class);
//                            AVUserGson.AuthDataBean authData = avUserGson.getAuthData();
//                            SharedPreferences.Editor editor = getSharedPreferences("user", MODE_PRIVATE).edit();
//                            editor.putBoolean("login", true);
//                            editor.putString("username", authData.getQq().getUsername());
//                            editor.putString("avatar", authData.getQq().getAvatar());
//                            editor.putString("sign", avUserGson.getSign() == null ? "这个用户很懒，什么都没有写！" : avUserGson.getSign());
//                            editor.putString("vip", avUserGson.getVip());
////                            editor.putString("fans", avUser.get("fans").toString());
////                            editor.putString("score", avUserGson.getScore().isEmpty()?"0":avUserGson.getScore());
////                            editor.putString("coin", avUserGson.getCoin().isEmpty()?"0":avUserGson.getCoin());
////                            editor.putString("observe", avUser.get("observe").toString());
//                            editor.apply();
//                            Intent intent = new Intent(LoginActivity.this, MainActivity.class);
////                            intent.putExtra("coin", gsons.getCoin());
//                            intent.putExtra("avatar", authData.getQq().getAvatar());
////                            intent.putExtra("observe", gsons.getObserve());
////                            intent.putExtra("score", gsons.getScore());
////                            intent.putExtra("day", gsons.getDay());
////                            intent.putExtra("fans", gsons.getFans());
//                            intent.putExtra("username", authData.getQq().getUsername());
//                            intent.putExtra("sign",  avUserGson.getSign() == null ? "这个用户很懒，什么都没有写！" : avUserGson.getSign());
//                            setResult(Constant.USER_FRAGMENT_LOGIN, intent);
//                            finish();
//                        } else {
//                            Log.i(TAG, "done: error=" + e.getMessage());
//                        }
//                    }
//                });
                plat.removeAccount(true);
                hideLoadingDialog();
            }

            @Override
            public void onError(Platform platform, int i, Throwable throwable) {
                Toast.makeText(LoginActivity.this, "登录出错", Toast.LENGTH_SHORT).show();
                plat.removeAccount(true);
                hideLoadingDialog();
            }

            @Override
            public void onCancel(Platform platform, int i) {
                Toast.makeText(LoginActivity.this, "登录取消", Toast.LENGTH_SHORT).show();
                plat.removeAccount(true);
                hideLoadingDialog();
            }

        });
        if (!plat.isClientValid()) {
            hideLoadingDialog();
            Toast.makeText(this, "你还没有安装客户端", Toast.LENGTH_SHORT).show();
        }
        if (plat.isAuthValid()) {
            hideLoadingDialog();
            Toast.makeText(this, "已经授权过了", Toast.LENGTH_SHORT).show();
            return;
        }
        plat.showUser(null);
    }

    @Override
    public void showLoadingDialog() {
        showmDialog("登陆中..");
    }

    @Override
    public void hideLoadingDialog() {
        hidemDialog();
    }

    @Override
    public void userLogin(UserGson gsons) {
        Log.i(TAG, "userLogin: " + gsons);
        hidemDialog();
        SharedPreferences.Editor editor = getSharedPreferences("user", MODE_PRIVATE).edit();
        editor.putBoolean("login", true);
        editor.putString("username", gsons.getUsername());
        editor.putString("nickname", gsons.getNickname());
        editor.putString("id", gsons.getId());
        editor.putString("avatar", gsons.getAvatar());
        editor.putString("sign", gsons.getSign().isEmpty() ? "这个用户很懒，什么都没有写！" : gsons.getSign());
        editor.putString("vip", gsons.getVip());
        editor.putString("fans", gsons.getFans());
        editor.putString("score", gsons.getScore());
        editor.putString("password", gsons.getPassword());
        editor.putString("coin", gsons.getCoin());
        editor.putString("day", gsons.getDay());
        editor.putString("token", gsons.getToken());
        editor.putString("vip", gsons.getVip());
        editor.putString("observe", gsons.getObserve());
        editor.apply();
        Intent intent = new Intent(LoginActivity.this, MainActivity.class);
        intent.putExtra("coin", gsons.getCoin());
        intent.putExtra("observe", gsons.getObserve());
        intent.putExtra("score", gsons.getScore());
        intent.putExtra("day", gsons.getDay());
        intent.putExtra("fans", gsons.getFans());
        intent.putExtra("username", gsons.getNickname());
        intent.putExtra("sign", gsons.getSign());
        intent.putExtra("avatar", gsons.getAvatar());
        intent.putExtra("vip", gsons.getVip());
        setResult(Constant.USER_FRAGMENT_LOGIN, intent);
        finish();
    }

    @Override
    public void loginFailed(String msg) {
        hidemDialog();
        Toast.makeText(this, "登陆失败" + msg, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void qqLogin(UserGson gsons) {
        SharedPreferences.Editor editor = getSharedPreferences("user", MODE_PRIVATE).edit();
        editor.putBoolean("login", true);
        editor.putString("username", gsons.getUsername());
        editor.putString("nickname", gsons.getNickname());
        editor.putString("id", gsons.getId());
        editor.putString("avatar", gsons.getAvatar());
        editor.putString("sign", gsons.getSign().isEmpty() ? "这个用户很懒，什么都没有写！" : gsons.getSign());
        editor.putString("vip", gsons.getVip());
        editor.putString("fans", gsons.getFans());
        editor.putString("score", gsons.getScore());
        editor.putString("password", gsons.getPassword());
        editor.putString("coin", gsons.getCoin());
        editor.putString("day", gsons.getDay());
        editor.putString("token", gsons.getToken());
        editor.putString("observe", gsons.getObserve());
        editor.apply();
        Intent intent = new Intent(LoginActivity.this, MainActivity.class);
        intent.putExtra("coin", gsons.getCoin());
        intent.putExtra("observe", gsons.getObserve());
        intent.putExtra("score", gsons.getScore());
        intent.putExtra("day", gsons.getDay());
        intent.putExtra("fans", gsons.getFans());
        intent.putExtra("username", gsons.getNickname());
        intent.putExtra("sign", gsons.getSign());
        setResult(Constant.USER_FRAGMENT_LOGIN, intent);
        finish();
    }

    @Override
    public void setSexInfor(UserGson gsons) {
        startActivity(new Intent(LoginActivity.this, UpdateUserActivity.class));
    }

    @Override
    public void upDateUserInforSuccess(UserGson gsons) {

    }

    @Override
    public void setUserInforFailed(UserGson userInforFailed) {
        Toast.makeText(loginActivity, "个人信息更新失败", Toast.LENGTH_SHORT).show();
    }


}

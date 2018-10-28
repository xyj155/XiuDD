package com.example.xiudd.sese.activity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;


import com.avos.avoscloud.AVException;
import com.avos.avoscloud.AVOSCloud;
import com.avos.avoscloud.AVUser;
import com.avos.avoscloud.LogInCallback;
import com.avos.avoscloud.RequestMobileCodeCallback;
import com.example.xiudd.sese.R;
import com.example.xiudd.sese.base.BaseActivity;
import com.example.xiudd.sese.util.MyCountDownTimer;

import butterknife.ButterKnife;
import butterknife.InjectView;
import butterknife.OnClick;


public class TelRegisterPhoneActivity extends BaseActivity {

    public static final int REQUEST_INFORMATION_CODE = 0XA;
    @InjectView(R.id.toolbar)
    Toolbar toolbar;
    @InjectView(R.id.et_username)
    EditText etUsername;
    @InjectView(R.id.et_password)
    EditText etPassword;
    @InjectView(R.id.tv_send)
    TextView tvSend;
    @InjectView(R.id.btn_login)
    Button btnLogin;
    private MyCountDownTimer myCountDownTimer;
    public static TelRegisterPhoneActivity telActivity = null;

    @Override
    public int intiLayout() {
        return R.layout.activity_telphone;
    }

    @Override
    public void initView() {
        ButterKnife.inject(this);
        initToolBar().setToolbarBackIco().setToolbarTitle("验证码手机号码");
        myCountDownTimer = new MyCountDownTimer(tvSend, 60000, 1000);
        etPassword.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if (s.toString().isEmpty()) {
                    btnLogin.setBackgroundResource(R.drawable.login_btn_bg_c_8_normal);
                } else {
                    btnLogin.setBackgroundResource(R.drawable.login_btn_bg_c_8_normal);
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
        telActivity = this;
    }

    @OnClick({R.id.tv_send, R.id.btn_login})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.tv_send:
                showmDialog("发送中...");
                if (etUsername.getText().toString().isEmpty()) {
                    Toast.makeText(this, "手机号码不可为空", Toast.LENGTH_SHORT).show();
                } else {
                    AVOSCloud.requestSMSCodeInBackground(etUsername.getText().toString(), new RequestMobileCodeCallback() {
                        @Override
                        public void done(AVException e) {
                            if (e == null) {
                                hidemDialog();
                                Toast.makeText(TelRegisterPhoneActivity.this, "验证码发送成功", Toast.LENGTH_SHORT).show();
                                myCountDownTimer.start();
                            } else {
                                hidemDialog();
                                Log.i(TAG, "done: " + e);
                                Toast.makeText(TelRegisterPhoneActivity.this, "验证码发送失败！" + e.getMessage(), Toast.LENGTH_SHORT).show();
                            }
                            // 发送失败可以查看 e 里面提供的信息
                        }
                    });
                }
                break;
            case R.id.btn_login:
                showmDialog("数据获取中...");
                AVUser.signUpOrLoginByMobilePhoneInBackground(etUsername.getText().toString(), etPassword.getText().toString(), new LogInCallback<AVUser>() {
                    @Override
                    public void done(AVUser avUser, AVException e) {
                        if (e == null) {
                            SharedPreferences.Editor editor = getSharedPreferences("user", MODE_PRIVATE).edit();
                            editor.putString("tel", etUsername.getText().toString());
                            editor.apply();
                            startActivityForResult(new Intent(TelRegisterPhoneActivity.this, UserRegisterInformationActivity.class), REQUEST_INFORMATION_CODE);
                    hidemDialog();
                        } else {
                            hidemDialog();
                            Toast.makeText(TelRegisterPhoneActivity.this, "验证码失败" + e.getMessage(), Toast.LENGTH_SHORT).show();
                        }
                        // 如果 e 为空就可以表示登录成功了，并且 user 是一个全新的用户
                    }
                });
                break;
        }
    }
}

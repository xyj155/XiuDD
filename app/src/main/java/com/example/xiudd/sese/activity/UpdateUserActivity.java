package com.example.xiudd.sese.activity;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

import com.example.xiudd.sese.R;
import com.example.xiudd.sese.base.BaseActivity;
import com.example.xiudd.sese.contract.UserContract;
import com.example.xiudd.sese.gson.UserGson;
import com.example.xiudd.sese.presenter.UserPresenter;
import com.example.xiudd.sese.util.ToastUtils;

import butterknife.ButterKnife;
import butterknife.InjectView;
import butterknife.OnClick;

public class UpdateUserActivity extends BaseActivity implements UserContract.View {


    @InjectView(R.id.toolbar)
    Toolbar toolbar;
    @InjectView(R.id.et_tel)
    EditText etTel;
    @InjectView(R.id.et_sign)
    EditText etSign;
    @InjectView(R.id.rb_boy)
    RadioButton rbBoy;
    @InjectView(R.id.rb_girl)
    RadioButton rbGirl;
    @InjectView(R.id.rg_sex)
    RadioGroup rgSex;
    @InjectView(R.id.btn_commit)
    Button btnCommit;
    private String sex;
    private UserPresenter userPresenter = new UserPresenter(this);

    @Override
    public int intiLayout() {
        return R.layout.activity_update_user;
    }

    @Override
    public void initView() {
        ButterKnife.inject(this);
        initToolBar().setToolbarBackIco().setToolbarTitle("完善信息");

    }

    @Override
    public void initData() {

    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // TODO: add setContentView(...) invocation

    }

    @OnClick(R.id.btn_commit)
    public void onViewClicked() {
        //,String location,String tel,String sign)
        if (etSign.getText().toString().isEmpty()) {
            Toast.makeText(this, "介绍不可为空！", Toast.LENGTH_SHORT).show();
        } else if (etTel.getText().toString().isEmpty()) {
            ToastUtils.getInstance().showText("联系方式不可为空");
        } else {
            userPresenter.QQuserLogin(getSharedPreferences("qq", MODE_PRIVATE).getString("openid",""),
                    getSharedPreferences("qq", MODE_PRIVATE).getString("username",""),
                    getSharedPreferences("qq", MODE_PRIVATE).getString("avatar",""), rbBoy.isChecked() ? "男" : "女",
                    getSharedPreferences("user", MODE_PRIVATE).getString("location", "")
                    , etTel.getText().toString(),
                    etSign.getText().toString());
        }

    }

    @Override
    public void showLoadingDialog() {
        showmDialog("添加...");
    }

    @Override
    public void hideLoadingDialog() {
        hidemDialog();
    }

    @Override
    public void userLogin(UserGson gsons) {

    }

    @Override
    public void loginFailed(String msg) {
        ToastUtils.getInstance().showText("提交失败" + msg);
    }

    @Override
    public void qqLogin(UserGson gsons) {

    }

    @Override
    public void setSexInfor(UserGson gsons) {

    }

    @Override
    public void upDateUserInforSuccess(UserGson gsons) {
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
        finish();
        LoginActivity.loginActivity.finish();
        Toast.makeText(this, "更新成功", Toast.LENGTH_SHORT).show();
    }

    @Override
    public void setUserInforFailed(UserGson gsons) {
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
        finish();
        LoginActivity.loginActivity.finish();
        Toast.makeText(this, "个人信息更新失败", Toast.LENGTH_SHORT).show();
    }
}

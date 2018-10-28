package com.example.xiudd.sese.activity;

import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.xiudd.sese.R;
import com.example.xiudd.sese.base.BaseActivity;
import com.example.xiudd.sese.base.EmptyGson;
import com.example.xiudd.sese.contract.SettingContract;
import com.example.xiudd.sese.presenter.SettingPresenter;

import java.util.List;

import butterknife.ButterKnife;
import butterknife.InjectView;
import butterknife.OnClick;

public class UpdatePasswordActivity extends BaseActivity implements SettingContract.View {


    @InjectView(R.id.toolbar)
    Toolbar toolbar;
    @InjectView(R.id.et_username)
    EditText etUsername;
    @InjectView(R.id.et_password)
    EditText etPassword;
    @InjectView(R.id.et_password2)
    EditText etPassword2;
    @InjectView(R.id.btn_submit)
    Button btnSubmit;
    private SettingPresenter settingPresenter = new SettingPresenter(this);

    @Override
    public int intiLayout() {
        return R.layout.activity_update_password;
    }

    @Override
    public void initView() {
        ButterKnife.inject(this);
        initToolBar().setToolbarBackIco().setToolbarTitle("修改密码");
    }

    @Override
    public void initData() {

    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // TODO: add setContentView(...) invocation

    }

    @OnClick(R.id.btn_submit)
    public void onViewClicked() {
        if (etUsername.getText().toString().isEmpty()) {
            Toast.makeText(this, "输入不可为空", Toast.LENGTH_SHORT).show();
        } else if (etPassword.getText().toString().isEmpty() || etPassword2.getText().toString().isEmpty()) {
            Toast.makeText(this, "输入不可为空", Toast.LENGTH_SHORT).show();
        } else if (!etPassword.getText().toString().equals(etPassword2.getText().toString())) {
            Toast.makeText(this, "前后密码输入不正确", Toast.LENGTH_SHORT).show();
        } else {
            Log.i(TAG, "onViewClicked: "+etUsername.getText().toString()+
                    etPassword.getText().toString()+
                    getSharedPreferences("user", MODE_PRIVATE).getString("id", ""));
            settingPresenter.updatePassword(etUsername.getText().toString(),
                    etPassword.getText().toString(),
                    getSharedPreferences("user", MODE_PRIVATE).getString("id", ""));
        }
    }

    @Override
    public void showLoadingDialog() {
        showmDialog("提交中..");
    }

    @Override
    public void hideLoadingDialog() {
        hidemDialog();
    }

    @Override
    public void success(List<EmptyGson> emptyGson) {
        finish();
        Toast.makeText(this, "更改密码成功", Toast.LENGTH_SHORT).show();
    }

    @Override
    public void failed(String error) {
        Toast.makeText(this, "更改密码失败"+error, Toast.LENGTH_SHORT).show();
    }
}

package com.example.xiudd.sese.activity;

import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.xiudd.sese.R;
import com.example.xiudd.sese.base.BaseActivity;
import com.example.xiudd.sese.base.EmptyGson;
import com.example.xiudd.sese.contract.SettingContract;
import com.example.xiudd.sese.presenter.SettingPresenter;
import com.example.xiudd.sese.util.ToastUtils;

import java.util.List;

import butterknife.ButterKnife;
import butterknife.InjectView;
import butterknife.OnClick;



/**
 * Created by Xuyijie on 2018/10/25.
 */

public class FeedbackActivity extends BaseActivity implements SettingContract.View {
    @InjectView(R.id.toolbar)
    Toolbar toolbar;
    @InjectView(R.id.rt_content)
    EditText rtContent;
    @InjectView(R.id.btn_submit)
    Button btnSubmit;

    @Override
    public int intiLayout() {
        return R.layout.activity_feedback;
    }

    @Override
    public void initView() {
        ButterKnife.inject(this);
        initToolBar().setToolbarBackIco().setToolbarTitle("反馈");
    }

    @Override
    public void initData() {

    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // TODO: add setContentView(...) invocation

    }

    private SettingPresenter settingPresenter = new SettingPresenter(this);

    @OnClick(R.id.btn_submit)
    public void onViewClicked() {
        settingPresenter.feedBack(getSharedPreferences("user", MODE_PRIVATE).getString("id", ""), rtContent.getText().toString());
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
        Toast.makeText(this, "提交成功", Toast.LENGTH_SHORT).show();
    }

    @Override
    public void failed(String error) {
        Toast.makeText(this, "提交失败" + error, Toast.LENGTH_SHORT).show();
    }
}

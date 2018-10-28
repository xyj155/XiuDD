package com.example.xiudd.sese.activity;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.example.xiudd.sese.R;
import com.example.xiudd.sese.base.BaseActivity;
import com.example.xiudd.sese.contract.UserDailySignContract;
import com.example.xiudd.sese.gson.SignGson;
import com.example.xiudd.sese.presenter.UserDailySignPresenter;
import com.example.xiudd.sese.view.CircleNumberProgressBar;

import butterknife.ButterKnife;
import butterknife.InjectView;
import butterknife.OnClick;

public class UserSignActivity extends BaseActivity implements UserDailySignContract.View {

    @InjectView(R.id.toolbar)
    Toolbar toolbar;
    @InjectView(R.id.pg_score)
    CircleNumberProgressBar pgScore;
    @InjectView(R.id.pg_day)
    CircleNumberProgressBar pgDay;
    @InjectView(R.id.tv_sign)
    TextView tvSign;
    private UserDailySignPresenter userDailySignPresenter = new UserDailySignPresenter(this);

    @Override
    public int intiLayout() {
        return R.layout.activity_user_sign;
    }

    @Override
    public void initView() {
        ButterKnife.inject(this);
        initToolBar().setToolbarBackIco().setToolbarTitle("签到");
        pgDay.setUnit("Day");
        pgScore.setMax(5000);
        pgScore.setUnit("分");
        pgDay.setProgress(Integer.valueOf(getSharedPreferences("user",MODE_PRIVATE).getString("day","")));
        pgScore.setProgress(Integer.valueOf(getSharedPreferences("user",MODE_PRIVATE).getString("score","")));


    }

    @Override
    public void initData() {

    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // TODO: add setContentView(...) invocation

    }

    @OnClick({R.id.tv_sign})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.tv_sign:
                userDailySignPresenter.sign(getSharedPreferences("user",MODE_PRIVATE).getString("id",""));
                break;
        }
    }

    @Override
    public void showLoadingDialog() {
        showmDialog("请求中..");
    }

    @Override
    public void hideLoadingDialog() {
        hidemDialog();
    }

    @Override
    public void getsign(SignGson gsons) {
        SharedPreferences.Editor editor = getSharedPreferences("user", MODE_PRIVATE).edit();
        editor.putString("score", gsons.getScore());
        editor.putString("day", String.valueOf(gsons.getDay()));
        editor.putString("coin", String.valueOf(gsons.getCoin()));
        editor.apply();

        tvSign.setClickable(false);
        tvSign.setBackgroundResource(R.drawable.btn_sign_normal);
        pgDay.setProgress(gsons.getDay());
        pgScore.setProgress(Integer.valueOf(gsons.getScore()));
        tvSign.setText("已签到");
    }

    @Override
    public void failed(String error) {
        tvSign.setClickable(true);
        tvSign.setBackgroundResource(R.drawable.btn_sign_checked);
        Toast.makeText(this, "签到失败", Toast.LENGTH_SHORT).show();
    }
}

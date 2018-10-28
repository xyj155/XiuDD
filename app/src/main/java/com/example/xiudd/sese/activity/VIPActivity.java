package com.example.xiudd.sese.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.xiudd.sese.R;
import com.example.xiudd.sese.base.BaseActivity;
import com.example.xiudd.sese.view.CircleImageView;

import butterknife.ButterKnife;
import butterknife.InjectView;
import butterknife.OnClick;


public class VIPActivity extends BaseActivity {


    @InjectView(R.id.toolbar)
    Toolbar toolbar;
    @InjectView(R.id.circleImageView)
    CircleImageView circleImageView;
    @InjectView(R.id.tv_username)
    TextView tvUsername;
    @InjectView(R.id.tv_is_vip)
    TextView tvIsVip;
    @InjectView(R.id.tv_open_vip)
    TextView tvOpenVip;
    @InjectView(R.id.iv_rank1)
    ImageView ivRank1;
    @InjectView(R.id.textView4)
    TextView textView4;
    @InjectView(R.id.iv_rank2)
    ImageView ivRank2;
    @InjectView(R.id.textView3)
    TextView textView3;
    @InjectView(R.id.iv_rank3)
    ImageView ivRank3;
    @InjectView(R.id.textView6)
    TextView textView6;
    @InjectView(R.id.iv_rank4)
    ImageView ivRank4;
    @InjectView(R.id.textView5)
    TextView textView5;

    @Override
    public int intiLayout() {
        return R.layout.activity_vip;
    }

    @Override
    public void initView() {
        ButterKnife.inject(this);
        initToolBar().setToolbarBackIco().setToolbarTitle("会员权益");

        Glide.with(VIPActivity.this).load(getSharedPreferences("user", MODE_PRIVATE).getString("avatar", "")).into(circleImageView);
        tvUsername.setText(getSharedPreferences("user", MODE_PRIVATE).getString("nickname", ""));
    }

    @Override
    public void initData() {

    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // TODO: add setContentView(...) invocation

    }

    @OnClick(R.id.tv_open_vip)
    public void onViewClicked() {
        startActivity(new Intent(VIPActivity.this, RechargeActivity.class));
    }
}

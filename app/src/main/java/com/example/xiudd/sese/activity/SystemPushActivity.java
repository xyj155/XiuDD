package com.example.xiudd.sese.activity;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.widget.ImageView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.example.xiudd.sese.R;
import com.example.xiudd.sese.base.BaseActivity;
import com.example.xiudd.sese.contract.UserScoreContract;
import com.example.xiudd.sese.gson.ScoreGson;
import com.example.xiudd.sese.gson.SystemPushGson;
import com.example.xiudd.sese.presenter.UserScorePresenter;
import com.scwang.smartrefresh.layout.SmartRefreshLayout;
import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.scwang.smartrefresh.layout.listener.OnRefreshListener;

import java.util.List;

import butterknife.ButterKnife;
import butterknife.InjectView;

import static android.R.attr.data;

public class SystemPushActivity extends BaseActivity implements UserScoreContract.View {
    @InjectView(R.id.toolbar)
    Toolbar toolbar;
    @InjectView(R.id.ry_push)
    RecyclerView ryPush;
    @InjectView(R.id.sl_sys)
    SmartRefreshLayout slSys;
    private UserScorePresenter userScorePresenter = new UserScorePresenter(this);


    @Override
    public int intiLayout() {
        return R.layout.activity_system_push;
    }

    @Override
    public void initView() {
        ButterKnife.inject(this);
        slSys.autoRefresh();
        initToolBar().setToolbarBackIco().setToolbarTitle("系统通知");
        ryPush.setLayoutManager(new LinearLayoutManager(SystemPushActivity.this));
        slSys.setOnRefreshListener(new OnRefreshListener() {
            @Override
            public void onRefresh(RefreshLayout refreshLayout) {
                userScorePresenter.getSystemPush();
            }
        });

    }

    @Override
    public void initData() {

    }

    @Override
    public void showLoadingDialog() {
        showmDialog("数据加载中...");
    }

    @Override
    public void hideLoadingDialog() {
        hidemDialog();
    }

    @Override
    public void getUserScore(List<ScoreGson> gsons) {

    }

    @Override
    public void failed(String error) {
        slSys.finishRefresh();
        Toast.makeText(this, "数据加载错误", Toast.LENGTH_SHORT).show();
    }

    @Override
    public void getSystemPush(List<SystemPushGson> gsons) {
        PushAdapter adapter = new PushAdapter(gsons);
        ryPush.setAdapter(adapter);
        slSys.finishRefresh();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // TODO: add setContentView(...) invocation

    }

    private class PushAdapter extends BaseQuickAdapter<SystemPushGson, BaseViewHolder> {

        public PushAdapter(@Nullable List<SystemPushGson> data) {
            super(R.layout.ry_user_push_item, data);
        }

        @Override
        protected void convert(BaseViewHolder helper, SystemPushGson item) {
            helper.setText(R.id.tv_title, item.getTitle())
                    .setText(R.id.tv_content, "积分：+" + item.getContent());
            Glide.with(SystemPushActivity.this).load(item.getImage()).into((ImageView) helper.getView(R.id.iv_image));
        }
    }
}

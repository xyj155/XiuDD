package com.example.xiudd.sese.activity;

import android.os.Bundle;
import android.support.annotation.LayoutRes;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.widget.Toast;

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

public class UserScoreHistoryActivity extends BaseActivity implements UserScoreContract.View {
    @InjectView(R.id.toolbar)
    Toolbar toolbar;
    @InjectView(R.id.ry_user_score)
    RecyclerView ryUserScore;
    @InjectView(R.id.sl_score)
    SmartRefreshLayout slScore;
    private UserScorePresenter userScorePresenter = new UserScorePresenter(this);

    @Override
    public int intiLayout() {
        return R.layout.activity_user_score_history;
    }

    @Override
    public void initView() {
        ButterKnife.inject(this);
        initToolBar().setToolbarBackIco().setToolbarTitle("积分通知");
        slScore.autoRefresh();
        slScore.setOnRefreshListener(new OnRefreshListener() {
            @Override
            public void onRefresh(RefreshLayout refreshLayout) {
                userScorePresenter.getUserScore(getSharedPreferences("user", MODE_PRIVATE).getString("id", ""));
            }
        });
        ryUserScore.setLayoutManager(new LinearLayoutManager(UserScoreHistoryActivity.this));

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
        slScore.finishRefresh();
        ScoreAdapter adapter = new ScoreAdapter(gsons);
        ryUserScore.setAdapter(adapter);
    }

    @Override
    public void failed(String error) {
        slScore.finishRefresh();


    }

    @Override
    public void getSystemPush(List<SystemPushGson> gsons) {

    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // TODO: add setContentView(...) invocation

    }

    private class ScoreAdapter extends BaseQuickAdapter<ScoreGson, BaseViewHolder> {

        public ScoreAdapter(@Nullable List<ScoreGson> data) {
            super(R.layout.ry_user_score_item, data);
        }

        @Override
        protected void convert(BaseViewHolder helper, ScoreGson item) {
            helper.setText(R.id.tv_count, "金币：" + item.getCoin())
                    .setText(R.id.tv_score,"积分：+"+item.getScore())
                    .setText(R.id.tv_date, item.getTime().substring(0,10));
        }
    }
}

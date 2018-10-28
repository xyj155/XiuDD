package com.example.xiudd.sese.activity;

import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;

import com.example.xiudd.sese.R;
import com.example.xiudd.sese.adapter.PostListAdapter;
import com.example.xiudd.sese.base.BaseActivity;
import com.example.xiudd.sese.contract.UserPostContract;
import com.example.xiudd.sese.gson.PostListGson;
import com.example.xiudd.sese.presenter.UserPostPresenter;
import com.scwang.smartrefresh.layout.SmartRefreshLayout;
import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.scwang.smartrefresh.layout.listener.OnRefreshListener;

import java.util.List;

import butterknife.ButterKnife;
import butterknife.InjectView;

public class UserPostHistoryActivity extends BaseActivity implements UserPostContract.View {
    @InjectView(R.id.toolbar)
    Toolbar toolbar;
    @InjectView(R.id.ry_collection)
    RecyclerView ryCollection;
    @InjectView(R.id.sl_collection)
    SmartRefreshLayout slCollection;
    private UserPostPresenter userPostPresenter = new UserPostPresenter(this);


    @Override
    public int intiLayout() {
        return R.layout.activity_user_post_history;
    }

    @Override
    public void initView() {
        ButterKnife.inject(this);
        initToolBar().setToolbarBackIco().setToolbarTitle("发表历史");
        ryCollection.setLayoutManager(new LinearLayoutManager(UserPostHistoryActivity.this));
        slCollection.autoRefresh();
        slCollection.setOnRefreshListener(new OnRefreshListener() {
            @Override
            public void onRefresh(RefreshLayout refreshLayout) {
                userPostPresenter.getUserPost(getSharedPreferences("user", MODE_PRIVATE).getString("id", ""));
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
    public void setUserPost(List<PostListGson> listGsons) {
        slCollection.finishRefresh();
        Log.i(TAG, "setUserPost: "+listGsons.toString());
        PostListAdapter adapter = new PostListAdapter(UserPostHistoryActivity.this, listGsons);
        ryCollection.setAdapter(adapter);
    }

    @Override
    public void setUserCollectionPost(List<PostListGson> listGsons) {

    }

    @Override
    public void loadFailed(String msg) {
        slCollection.finishRefresh();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // TODO: add setContentView(...) invocation

    }
}

package com.example.xiudd.sese.activity;

import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import com.example.xiudd.sese.R;
import com.example.xiudd.sese.adapter.PostListAdapter;
import com.example.xiudd.sese.base.BaseActivity;
import com.example.xiudd.sese.contract.PostKindContract;
import com.example.xiudd.sese.gson.PostListGson;
import com.example.xiudd.sese.presenter.PostKindPresenter;
import com.scwang.smartrefresh.layout.SmartRefreshLayout;
import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.scwang.smartrefresh.layout.listener.OnRefreshListener;

import java.util.List;

import butterknife.ButterKnife;
import butterknife.InjectView;

public class PostKindDetailActivity extends BaseActivity implements PostKindContract.View {

    @InjectView(R.id.toolbar)
    Toolbar toolbar;
    @InjectView(R.id.ry_kind)
    RecyclerView ryKind;
    @InjectView(R.id.sl_kind)
    SmartRefreshLayout slKind;
    @InjectView(R.id.tv_notopic)
    TextView tvNotopic;
    private PostKindPresenter postKindPresenter = new PostKindPresenter(this);

    @Override
    public int intiLayout() {
        return R.layout.activity_post_kind_detail;
    }

    @Override
    public void initView() {
        ButterKnife.inject(this);
        initToolBar().setToolbarBackIco().setToolbarTitle(getIntent().getStringExtra("kind"));
        ryKind.setFocusableInTouchMode(false);
        ryKind.setLayoutManager(new LinearLayoutManager(PostKindDetailActivity.this));
    }

    @Override
    public void initData() {
        slKind.autoRefresh();
        slKind.setOnRefreshListener(new OnRefreshListener() {
            @Override
            public void onRefresh(RefreshLayout refreshLayout) {
                postKindPresenter.getPostListByKind(getSharedPreferences("user", MODE_PRIVATE).getString("id", ""), getIntent().getStringExtra("kind"));
            }
        });
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // TODO: add setContentView(...) invocation

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
    public void loadFailed(String error) {
        Log.i(TAG, "loadFailed: " + error);

    }

    @Override
    public void setPostListByKind(List<PostListGson> postListGsons) {
        slKind.finishRefresh();
        if (postListGsons.size()==0){
            tvNotopic.setVisibility(View.VISIBLE);
            ryKind.setVisibility(View.GONE);
        }else {
            tvNotopic.setVisibility(View.GONE);
            ryKind.setVisibility(View.VISIBLE);
            ryKind.setLayoutManager(new LinearLayoutManager(PostKindDetailActivity.this));
            PostListAdapter postListAdapter = new PostListAdapter(PostKindDetailActivity.this, postListGsons);
            postListAdapter.notifyDataSetChanged();
            ryKind.setAdapter(postListAdapter);
        }

    }
}

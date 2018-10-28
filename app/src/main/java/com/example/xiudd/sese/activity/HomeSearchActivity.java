package com.example.xiudd.sese.activity;

import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.xiudd.sese.R;
import com.example.xiudd.sese.adapter.PostListAdapter;
import com.example.xiudd.sese.base.BaseActivity;
import com.example.xiudd.sese.contract.PostKindContract;
import com.example.xiudd.sese.contract.SearchContract;
import com.example.xiudd.sese.gson.PostListGson;
import com.example.xiudd.sese.presenter.PostKindPresenter;
import com.example.xiudd.sese.presenter.SearchPresenter;
import com.scwang.smartrefresh.layout.SmartRefreshLayout;
import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.scwang.smartrefresh.layout.listener.OnRefreshListener;

import java.util.List;

import butterknife.ButterKnife;
import butterknife.InjectView;
import butterknife.OnClick;


public class HomeSearchActivity extends BaseActivity implements SearchContract.View {

    //

    @InjectView(R.id.et_content)
    EditText etContent;
    @InjectView(R.id.ry_search)
    RecyclerView rySearch;
    @InjectView(R.id.sl_search)
    SmartRefreshLayout slSearch;
    @InjectView(R.id.toolbar)
    Toolbar toolbar;
    @InjectView(R.id.tv_search)
    TextView tvSearch;
    private SearchPresenter postKindPresenter = new SearchPresenter(this);

    @Override
    public int intiLayout() {
        return R.layout.activity_home_search;
    }

    @Override
    public void initView() {
        ButterKnife.inject(this);
        initToolBar().setToolbarBackIco().setToolbarTitle("搜索");
        slSearch.setOnRefreshListener(new OnRefreshListener() {
            @Override
            public void onRefresh(RefreshLayout refreshLayout) {
                postKindPresenter.searchByTitle(getSharedPreferences("user", MODE_PRIVATE).getString("id", ""), etContent.getText().toString());
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

    }

    @OnClick(R.id.tv_search)
    public void onViewClicked() {
        postKindPresenter.searchByTitle(getSharedPreferences("user", MODE_PRIVATE).getString("id", ""), etContent.getText().toString());
    }

    @Override
    public void showLoadingDialog() {
        showmDialog("搜索中...");
    }

    @Override
    public void hideLoadingDialog() {
        hidemDialog();
    }

    @Override
    public void loadFailed(String error) {

    }

    @Override
    public void loadSearch(List<PostListGson> listGsons) {
        rySearch.setLayoutManager(new LinearLayoutManager(HomeSearchActivity.this));
        rySearch.setFocusableInTouchMode(false);
        slSearch.finishRefresh();
        PostListAdapter adapter = new PostListAdapter(HomeSearchActivity.this, listGsons);
        rySearch.setAdapter(adapter);
    }

}

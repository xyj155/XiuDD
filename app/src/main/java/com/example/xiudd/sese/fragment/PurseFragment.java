package com.example.xiudd.sese.fragment;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.example.xiudd.sese.R;
import com.example.xiudd.sese.activity.PostKindListActivity;
import com.example.xiudd.sese.adapter.PostListAdapter;
import com.example.xiudd.sese.base.BaseFragment;
import com.example.xiudd.sese.contract.PostListContract;
import com.example.xiudd.sese.gson.PostListGson;
import com.example.xiudd.sese.presenter.PostListPresenter;
import com.scwang.smartrefresh.layout.SmartRefreshLayout;
import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.scwang.smartrefresh.layout.listener.OnRefreshListener;

import java.util.List;

import butterknife.ButterKnife;
import butterknife.InjectView;
import butterknife.OnClick;

/**
 * Created by Xuyijie on 2018/10/20.
 */

public class PurseFragment extends BaseFragment implements PostListContract.View {
    @InjectView(R.id.home_ry)
    RecyclerView homeRy;
    @InjectView(R.id.ll_square)
    LinearLayout llSquare;
    @InjectView(R.id.ll_shop)
    LinearLayout llShop;
    private PostListPresenter postListPresenter;
    private PostListAdapter adapter;

    @InjectView(R.id.sr_home)
    SmartRefreshLayout srHome;

    @Override
    protected int setView() {
        return R.layout.fragment_purse;
    }

    @Override
    protected void init(View view) {
        ButterKnife.inject(this, view);
        adapter = new PostListAdapter(getActivity(), null);
        homeRy.setNestedScrollingEnabled(false);
        homeRy.setFocusableInTouchMode(false);
        adapter.notifyDataSetChanged();
        homeRy.setLayoutManager(new LinearLayoutManager(getContext()));
    }

    @Override
    public void onHiddenChanged(boolean hidden) {
        super.onHiddenChanged(hidden);
        if (!hidden) {
            if (adapter != null) {
                postListPresenter.setPostList(getActivity().getSharedPreferences("user", Context.MODE_PRIVATE).getString("id", "").isEmpty() ? "" : getActivity().getSharedPreferences("user", Context.MODE_PRIVATE).getString("id", ""));
            }
        }
    }

    @Override
    protected void initData(Bundle savedInstanceState) {
        postListPresenter = new PostListPresenter(this);
        postListPresenter.setPostList(getActivity().getSharedPreferences("user", Context.MODE_PRIVATE).getString("id", "").isEmpty() ? "" : getActivity().getSharedPreferences("user", Context.MODE_PRIVATE).getString("id", ""));
        homeRy.setNestedScrollingEnabled(false);
        srHome.setOnRefreshListener(new OnRefreshListener() {
            @Override
            public void onRefresh(RefreshLayout refreshLayout) {
                postListPresenter.setPostList(getActivity().getSharedPreferences("user", Context.MODE_PRIVATE).getString("id", "").isEmpty() ? "" : getActivity().getSharedPreferences("user", Context.MODE_PRIVATE).getString("id", ""));

            }
        });
    }

    @Override
    public void showLoadingDialog() {

    }

    @Override
    public void hideLoadingDialog() {

    }

    @Override
    public void loadPostList(List<PostListGson> postListGsons) {
        srHome.finishRefresh();
        adapter.replaceData(postListGsons);
        homeRy.setAdapter(adapter);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // TODO: inflate a fragment view
        View rootView = super.onCreateView(inflater, container, savedInstanceState);
        ButterKnife.inject(this, rootView);
        return rootView;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        ButterKnife.reset(this);
    }

    @OnClick({R.id.ll_square, R.id.ll_shop})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.ll_square:
                startActivity(new Intent(getContext(), PostKindListActivity.class));
                break;
            case R.id.ll_shop:
                Toast.makeText(mActivity, "改模块正在开发", Toast.LENGTH_SHORT).show();
                break;
        }
    }
}

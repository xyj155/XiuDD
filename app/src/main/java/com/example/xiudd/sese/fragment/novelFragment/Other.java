package com.example.xiudd.sese.fragment.novelFragment;

import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.xiudd.sese.R;
import com.example.xiudd.sese.activity.NovelActivity;
import com.example.xiudd.sese.base.BaseFragment;
import com.example.xiudd.sese.contract.NovelContract;
import com.example.xiudd.sese.gson.NovelGson;
import com.example.xiudd.sese.presenter.NovelPresenter;
import com.scwang.smartrefresh.layout.SmartRefreshLayout;
import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.scwang.smartrefresh.layout.listener.OnRefreshListener;

import java.util.ArrayList;
import java.util.List;

import butterknife.ButterKnife;
import butterknife.InjectView;

/**
 * Created by 徐易杰 on 2018/10/17.
 */

public class Other extends BaseFragment implements NovelContract.View {
    @InjectView(R.id.ty_novel)
    RecyclerView tyNovel;
    @InjectView(R.id.sl_novel)
    SmartRefreshLayout slNovel;
    private NovelPresenter novelPresenter;
    NovelActivity.NovelAdapter adapter;
    List<NovelGson> novelGsons = new ArrayList<>();

    @Override
    protected int setView() {
        return R.layout.novel_fragment_include;
    }

    @Override
    protected void init(View view) {
        ButterKnife.inject(this, view);
        adapter = new NovelActivity.NovelAdapter(getContext(),null);
        novelPresenter = new NovelPresenter(this);
        tyNovel.setLayoutManager(new LinearLayoutManager(getActivity()));

        slNovel.autoRefresh();
        slNovel.setOnRefreshListener(new OnRefreshListener() {
            @Override
            public void onRefresh(RefreshLayout refreshLayout) {
                slNovel.finishRefresh(200);
            }
        });
    }

    @Override
    protected void initData(Bundle savedInstanceState) {
        novelPresenter.getNovelList("3");
    }


    @Override
    public void showLoadingDialog() {
        showDialog();
    }

    @Override
    public void hideLoadingDialog() {
        hideDialog();
    }

    @Override
    public void setNovelList(List<NovelGson> list) {
        novelGsons.addAll(list);
        adapter.addData(novelGsons);
        tyNovel.setAdapter(adapter);
    }

    @Override
    public void downLoadTxt(String path) {

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // TODO: inflate a fragment view
        View rootView = super.onCreateView(inflater, container, savedInstanceState);

        return rootView;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        ButterKnife.reset(this);
    }
}

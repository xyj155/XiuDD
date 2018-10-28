package com.example.xiudd.sese.activity;


import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.View;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.example.xiudd.sese.R;
import com.example.xiudd.sese.base.BaseActivity;
import com.example.xiudd.sese.contract.PictureContract;
import com.example.xiudd.sese.gson.PictureGson;
import com.example.xiudd.sese.presenter.PicturePresenter;
import com.scwang.smartrefresh.layout.SmartRefreshLayout;
import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.scwang.smartrefresh.layout.listener.OnRefreshListener;

import java.util.List;

import butterknife.ButterKnife;
import butterknife.InjectView;


public class PictureListActivity extends BaseActivity implements PictureContract.View {

    @InjectView(R.id.toolbar)
    Toolbar toolbar;
    @InjectView(R.id.ry_picture)
    RecyclerView ryPicture;
    @InjectView(R.id.sl_picture)
    SmartRefreshLayout slPicture;
    private PictureAdapter pictureAdapter;
    private PicturePresenter picturePresenter;

    @Override
    public int intiLayout() {
        return R.layout.activity_picture_list;
    }

    @Override
    public void initView() {
        ButterKnife.inject(this);
        initToolBar().setToolbarBackIco().setToolbarTitle("美图");
        pictureAdapter = new PictureAdapter(PictureListActivity.this, null);
        picturePresenter = new PicturePresenter(this);
        ryPicture.setLayoutManager(new LinearLayoutManager(PictureListActivity.this));
        slPicture.autoRefresh();
        ryPicture.setAdapter(pictureAdapter);
        slPicture.setOnRefreshListener(new OnRefreshListener() {
            @Override
            public void onRefresh(RefreshLayout refreshLayout) {
                picturePresenter.getPictureList();

            }
        });
    }

    @Override
    public void initData() {
        picturePresenter.getPictureList();
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
    public void setPictureList(List<PictureGson> list) {
        pictureAdapter.replaceData(list);
        slPicture.finishRefresh();
    }

    @Override
    public void getPictureDetail(List<String> stringList) {

    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // TODO: add setContentView(...) invocation

    }

    public class PictureAdapter extends BaseQuickAdapter<PictureGson, BaseViewHolder> {

        private Context context;

        public PictureAdapter(Context context, List<PictureGson> data) {
            super(R.layout.ry_picture_item, data);
            this.context = context;
        }

        @Override
        protected void convert(BaseViewHolder helper, final PictureGson item) {
            helper.setText(R.id.tv_title, item.getTitle())
                    .setOnClickListener(R.id.ll_txt, new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            Intent intent = new Intent(context, CelebrityActivity.class);
                            intent.putExtra("url", item.getUrl());
                            startActivity(intent);
                        }
                    });
        }

    }
}

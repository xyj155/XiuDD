package com.example.xiudd.sese.fragment.download;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.example.xiudd.sese.MyApp;
import com.example.xiudd.sese.R;
import com.example.xiudd.sese.activity.VideoPlayerActivity;
import com.example.xiudd.sese.base.BaseFragment;
import com.example.xiudd.sese.greenDAO.DaoSession;
import com.example.xiudd.sese.greenDAO.DownloadDAO;
import com.example.xiudd.sese.greenDAO.DownloadDAODao;
import com.scwang.smartrefresh.layout.SmartRefreshLayout;
import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.scwang.smartrefresh.layout.listener.OnRefreshListener;

import java.util.ArrayList;
import java.util.List;

import butterknife.ButterKnife;
import butterknife.InjectView;

import static android.R.id.list;


/**
 * Created by Xuyijie on 2018/10/19.
 */

public class DownloadedFragment extends BaseFragment {
    @InjectView(R.id.ry_download_history)
    RecyclerView ryDownloadHistory;
    @InjectView(R.id.sl_downloading)
    SmartRefreshLayout slDownloading;
    private DownloadHistoryAdapter  downloadHistoryAdapter = new DownloadHistoryAdapter(null);
    private DaoSession daoSession;
    List<DownloadDAO> downloadDAOs = new ArrayList<>();
    @Override
    protected int setView() {
        return R.layout.fragment_downloaded;
    }

    private static final String TAG = "DownloadedFragment";

    @Override
    protected void init(View view) {
        ButterKnife.inject(this, view);
        ryDownloadHistory.setLayoutManager(new LinearLayoutManager(getContext()));
        daoSession = MyApp.getInstances().getDaoSession();
        ryDownloadHistory.setAdapter(downloadHistoryAdapter);
        slDownloading.autoRefresh();
        slDownloading.setOnRefreshListener(new OnRefreshListener() {
            @Override
            public void onRefresh(RefreshLayout refreshLayout) {
                List<DownloadDAO> list = daoSession.getDownloadDAODao().queryBuilder().where(DownloadDAODao.Properties.Down.eq("0")).list();
                downloadDAOs.addAll(list);
                slDownloading.finishRefresh(500);
                downloadHistoryAdapter.replaceData(downloadDAOs);
            }
        });
    }

    @Override
    protected void initData(Bundle savedInstanceState) {

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

    private class DownloadHistoryAdapter extends BaseQuickAdapter<DownloadDAO, BaseViewHolder> {

        public DownloadHistoryAdapter(@Nullable List<DownloadDAO> data) {
            super(R.layout.ry_download_history_item, data);
        }

        @Override
        protected void convert(final BaseViewHolder helper, final DownloadDAO item) {
            helper.setText(R.id.tv_title, item.getName())
                    .setOnClickListener(R.id.ll_video, new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            Log.i(TAG, "onClick:path= " + item.getPath());
                            Log.i(TAG, "run: id="+item.getId());
                            if (item.getPath().isEmpty()) {

                                Toast.makeText(mContext, "改视频不存在或移动位置", Toast.LENGTH_SHORT).show();
                                downloadHistoryAdapter.remove(helper.getPosition());
                                DownloadDAODao downloadDAODao = MyApp.getInstances().getDaoSession().getDownloadDAODao();
                                downloadDAODao.deleteByKey(item.getId());
                            } else {
                                Intent intent = new Intent(getContext(), VideoPlayerActivity.class);
                                intent.putExtra("url", "file://" + item.getPath());
                                intent.putExtra("cover", item.getImage());
                                intent.putExtra("title", item.getName());
                                startActivity(intent);
                            }

                        }
                    });
            Glide.with(getContext()).load(item.getImage()).into((ImageView) helper.getView(R.id.tv_cover));
        }
    }
}

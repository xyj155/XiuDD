package com.example.xiudd.sese.fragment.download;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.example.xiudd.sese.MyApp;
import com.example.xiudd.sese.R;
import com.example.xiudd.sese.base.BaseFragment;
import com.example.xiudd.sese.greenDAO.DaoSession;
import com.example.xiudd.sese.greenDAO.DownloadDAO;
import com.example.xiudd.sese.greenDAO.DownloadDAODao;
import com.example.xiudd.sese.listener.DownloadListener;
import com.example.xiudd.sese.util.GlideRoundTransform;
import com.example.xiudd.sese.util.rxjavaDownload.DownloadUtil;
import com.example.xiudd.sese.view.CircleNumberProgressBar;
import com.scwang.smartrefresh.layout.SmartRefreshLayout;
import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.scwang.smartrefresh.layout.listener.OnRefreshListener;

import java.util.ArrayList;
import java.util.List;

import butterknife.ButterKnife;
import butterknife.InjectView;




/**
 * Created by Xuyijie on 2018/10/19.
 */

public class DownloadingFragment extends BaseFragment {
    @InjectView(R.id.ry_download)
    RecyclerView ryDownload;
    @InjectView(R.id.sl_downloading)
    SmartRefreshLayout slDownloading;
    private DownloadAdapter downloadAdapter = new DownloadAdapter(null);
    DownloadDAODao downloadDAODao = MyApp.getInstances().getDaoSession().getDownloadDAODao();
    private DaoSession daoSession;
    List<DownloadDAO> downloadDAOs = new ArrayList<>();

    @Override
    protected int setView() {
        return R.layout.fragment_downloading;
    }

    @Override
    protected void init(View view) {
        ButterKnife.inject(this, view);
        daoSession = MyApp.getInstances().getDaoSession();

        ryDownload.setLayoutManager(new LinearLayoutManager(getContext()));
        ryDownload.setAdapter(downloadAdapter);
        slDownloading.autoRefresh();
        slDownloading.setOnRefreshListener(new OnRefreshListener() {
            @Override
            public void onRefresh(RefreshLayout refreshLayout) {
                List<DownloadDAO> list = daoSession.getDownloadDAODao().queryBuilder().where(DownloadDAODao.Properties.Down.eq("1")).list();
                downloadDAOs.addAll(list);
                slDownloading.finishRefresh(500);
                downloadAdapter.replaceData(downloadDAOs);
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


    private class DownloadAdapter extends BaseQuickAdapter<DownloadDAO, BaseViewHolder> {


        public DownloadAdapter(@Nullable List<DownloadDAO> data) {
            super(R.layout.ry_download_item, data);
        }

        @Override
        protected void convert(final BaseViewHolder helper, final DownloadDAO item) {

            final CircleNumberProgressBar imageView = helper.getView(R.id.iv_download);
            final DownloadUtil downloadUtil = new DownloadUtil();
            helper.setOnLongClickListener(R.id.ll_downloading, new View.OnLongClickListener() {
                @Override
                public boolean onLongClick(View v) {
                    downloadAdapter.remove(helper.getPosition());
                    downloadDAODao.deleteByKey(item.getId());
                    return true;
                }
            });
            Glide.with(getContext()).load(item.getImage()).apply(new RequestOptions().error(R.drawable.error_pic).transform(new GlideRoundTransform(getContext(), 5))).into((ImageView) helper.getView(R.id.tv_cover));
            helper.setText(R.id.tv_title, item.getName())
                    .setOnClickListener(R.id.iv_download, new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            Log.i(TAG, "onClick: " + item.getUrl());
                            downloadUtil.downloadFile(item.getUrl().replace("https", "http"), new DownloadListener() {
                                @Override
                                public void onStart() {

                                }

                                @Override
                                public void onProgress(final int currentLength) {
                                    Log.e(TAG, "onLoading: " + currentLength);
                                    if (getActivity() == null)
                                        return;
                                    getActivity().runOnUiThread(new Runnable() {
                                        @Override
                                        public void run() {
                                            imageView.setProgress( currentLength);
                                        }
                                    });
                                }

                                @Override
                                public void onFinish(final String localPath) {
                                    if (getActivity() == null)
                                        return;
                                    getActivity().runOnUiThread(new Runnable() {
                                        @Override
                                        public void run() {
                                            Log.i(TAG, "run: " + localPath);
                                            Log.i(TAG, "run: id="+item.getId());
                                            DownloadDAO downloadDAO = new DownloadDAO(item.getId(), item.getName(), localPath, item.getUrl(), item.getImage(), 0);
                                            Log.i(TAG, "run: " + downloadDAO.toString());
                                            downloadDAODao.update(downloadDAO);
                                            downloadAdapter.remove(helper.getPosition());
                                        }
                                    });
                                }

                                @Override
                                public void onFailure(final String erroInfo) {
                                    if (getActivity() == null)
                                        return;
                                    getActivity().runOnUiThread(new Runnable() {
                                        @Override
                                        public void run() {
                                            Log.i(TAG, "run: " + erroInfo);
                                            imageView.setProgress(0);
                                        }
                                    });

                                }
                            });
                        }
                    });


        }

    }

    @Override
    public void onDestroy() {
        super.onDestroy();
    }
}

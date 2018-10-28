package com.example.xiudd.sese.activity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.example.xiudd.sese.MyApp;
import com.example.xiudd.sese.R;
import com.example.xiudd.sese.base.BaseActivity;
import com.example.xiudd.sese.contract.ForeignVideoContract;
import com.example.xiudd.sese.fragment.HomeFragment;
import com.example.xiudd.sese.fragment.MainPageFragment;
import com.example.xiudd.sese.greenDAO.DownloadDAO;
import com.example.xiudd.sese.greenDAO.DownloadDAODao;
import com.example.xiudd.sese.gson.ForeignGson;
import com.example.xiudd.sese.presenter.ForeignVideoPresenter;
import com.example.xiudd.sese.util.GlideRoundTransform;
import com.scwang.smartrefresh.layout.SmartRefreshLayout;
import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.scwang.smartrefresh.layout.listener.OnRefreshListener;
import com.zhouwei.mzbanner.MZBannerView;
import com.zhouwei.mzbanner.holder.MZHolderCreator;
import com.zhouwei.mzbanner.holder.MZViewHolder;

import java.util.ArrayList;
import java.util.List;

import butterknife.ButterKnife;
import butterknife.InjectView;

public class ForeignActivity extends BaseActivity implements ForeignVideoContract.View {


    @InjectView(R.id.toolbar)
    Toolbar toolbar;
    @InjectView(R.id.banner)
    MZBannerView banner;
    @InjectView(R.id.ry_foreign)
    RecyclerView ryForeign;
    @InjectView(R.id.sl_foreign)
    SmartRefreshLayout slForeign;
    private ForeignVideoPresenter presenter = new ForeignVideoPresenter(this);

    @Override
    public int intiLayout() {
        return R.layout.activity_foreign;
    }

    @Override
    public void initView() {
        ButterKnife.inject(this);
        StaggeredGridLayoutManager staggeredGridLayoutManager = new StaggeredGridLayoutManager(2, StaggeredGridLayoutManager.VERTICAL);
        //通过布局管理器控制条目排列的顺序  true:反向显示 false:正向显示
        staggeredGridLayoutManager.setReverseLayout(false);
        //设置布局管理器，参数StaggeredGridLayoutManager，可以是RecyclerView实现和StaggeredGridView一样的效果
        ryForeign.setLayoutManager(new LinearLayoutManager(ForeignActivity.this));
        initToolBar().setToolbarBackIco().setToolbarTitle("外国风情");
    }

    @Override
    public void initData() {
        presenter.setForeignVideo();
        List<String> list = new ArrayList<>();
        list.add("https://timgsa.baidu.com/timg?image&quality=80&size=b9999_10000&sec=1539755425221&di=c5ab59282e280ba0d658fa8247a27a55&imgtype=0&src=http%3A%2F%2Fimg.zcool.cn%2Fcommunity%2F01106257b673380000018c1bdeb3b6.jpg%40900w_1l_2o_100sh.jpg");
        list.add("https://timgsa.baidu.com/timg?image&quality=80&size=b9999_10000&sec=1539755425221&di=c5ab59282e280ba0d658fa8247a27a55&imgtype=0&src=http%3A%2F%2Fimg.zcool.cn%2Fcommunity%2F01106257b673380000018c1bdeb3b6.jpg%40900w_1l_2o_100sh.jpg");
        list.add("https://timgsa.baidu.com/timg?image&quality=80&size=b9999_10000&sec=1539755425221&di=c5ab59282e280ba0d658fa8247a27a55&imgtype=0&src=http%3A%2F%2Fimg.zcool.cn%2Fcommunity%2F01106257b673380000018c1bdeb3b6.jpg%40900w_1l_2o_100sh.jpg");
        list.add("https://timgsa.baidu.com/timg?image&quality=80&size=b9999_10000&sec=1539755425221&di=c5ab59282e280ba0d658fa8247a27a55&imgtype=0&src=http%3A%2F%2Fimg.zcool.cn%2Fcommunity%2F01106257b673380000018c1bdeb3b6.jpg%40900w_1l_2o_100sh.jpg");
        banner.setDelayedTime(5000);
        banner.setIndicatorVisible(true);
        banner.setPages(list, new MZHolderCreator() {
            @Override
            public MZViewHolder createViewHolder() {
                return new HomeFragment.BannerViewHolder();
            }
        });
        banner.start();
        slForeign.autoRefresh();
        slForeign.setOnRefreshListener(new OnRefreshListener() {
            @Override
            public void onRefresh(RefreshLayout refreshLayout) {
                presenter.setForeignVideo();
                slForeign.finishRefresh(200);
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
        showmDialog("数据加载中");
    }

    @Override
    public void hideLoadingDialog() {
        hidemDialog();
    }

    @Override
    public void loadForeignVideo(List<ForeignGson> gsons) {
        ryForeign.setNestedScrollingEnabled(false);
        ForeignVideoAdapter adapter = new ForeignVideoAdapter(ForeignActivity.this, gsons);
        ryForeign.setAdapter(adapter);
    }

    private class ForeignVideoAdapter extends BaseQuickAdapter<ForeignGson, BaseViewHolder> {
        private Context context;

        public ForeignVideoAdapter(Context context, @Nullable List<ForeignGson> data) {
            super(R.layout.ry_hot_video_item, data);
            this.context = context;
        }

        @Override
        protected void convert(BaseViewHolder helper, final ForeignGson item) {
            helper.setText(R.id.tv_title, item.getVideo_name())
                    .setOnClickListener(R.id.ll_video, new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            Intent intent = new Intent(context, VideoPlayerActivity.class);
                            intent.putExtra("url", item.getVideo_url());
                            intent.putExtra("cover", item.getCover());
                            intent.putExtra("title", item.getVideo_name());
                            context.startActivity(intent);
                        }
                    })
                    .setOnClickListener(R.id.tv_download, new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            DownloadDAODao downloadDAODao = MyApp.getInstances().getDaoSession().getDownloadDAODao();
                            long time = System.currentTimeMillis() / 1000;
                            DownloadDAO downloadDAO = new DownloadDAO(time, item.getVideo_name(), "", item.getVideo_url(),item.getCover(), 1);
                            downloadDAODao.insert(downloadDAO);//添加一个
                            startActivity(new Intent(context,UserDownLoadActivity.class));
                        }
                    });
            RequestOptions requestOptions = new RequestOptions()
                    .error(R.drawable.error_pic)
                    .transform(new GlideRoundTransform(context, 5));
            Glide.with(context).load(item.getCover()).apply(requestOptions).into((ImageView) helper.getView(R.id.iv_cover));
        }
    }
}

package com.example.xiudd.sese.fragment;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.widget.NestedScrollView;
import android.support.v7.widget.GridLayoutManager;
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
import com.example.xiudd.sese.R;
import com.example.xiudd.sese.activity.AroundUsersActivity;
import com.example.xiudd.sese.activity.PostWriteActivity;
import com.example.xiudd.sese.activity.RechargeActivity;
import com.example.xiudd.sese.activity.UserSignActivity;
import com.example.xiudd.sese.base.BaseFragment;
import com.example.xiudd.sese.contract.HomeContract;
import com.example.xiudd.sese.gson.BannerGson;

import com.example.xiudd.sese.gson.MarqueenGson;

import com.example.xiudd.sese.gson.UserGson;
import com.example.xiudd.sese.gson.VipVideoGson;
import com.example.xiudd.sese.presenter.HomePresenter;
import com.example.xiudd.sese.util.GlideRoundTransform;
import com.example.xiudd.sese.view.avatarpie.CircleImageView;
import com.example.xiudd.sese.view.avatarpie.PileLayout;
import com.example.xiudd.sese.view.marqueen.MarqueeView;
import com.example.xiudd.sese.view.marqueen.SimpleMF;
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
import butterknife.OnClick;
import io.rong.imkit.RongIM;
import io.rong.imlib.RongIMClient;
import io.rong.imlib.model.CSCustomServiceInfo;

import static android.content.Context.MODE_PRIVATE;


/**
 * Created by Xuyijie on 2018/10/20.
 */

public class HomeFragment extends BaseFragment implements HomeContract.View{
    @InjectView(R.id.home_bn)
    MZBannerView<String> homeBn;
    @InjectView(R.id.home_mv)
    MarqueeView homeMv;

    @InjectView(R.id.sr_home)
    SmartRefreshLayout srHome;
    @InjectView(R.id.tv_recharge)
    TextView tvRecharge;
    @InjectView(R.id.tv_sign)
    TextView tvSign;
    @InjectView(R.id.tv_service)
    TextView tvService;

    @InjectView(R.id.sl_home)
    NestedScrollView slHome;
    @InjectView(R.id.iv_addpost)
    ImageView ivAddpost;
    @InjectView(R.id.ry_home_vip)
    RecyclerView ryHomeVip;
    @InjectView(R.id.ry_asia)
    RecyclerView ryAsia;
    @InjectView(R.id.pile_layout)
    PileLayout pileLayout;
    @InjectView(R.id.tv_more)
    TextView tvMore;
    private HomePresenter homePresenter = new HomePresenter(this);
    private List<UserGson> list1 = new ArrayList<>();


    @Override
    protected int setView() {
        return R.layout.fragment_main;
    }

    @Override
    protected void init(View view) {
        ButterKnife.inject(this, view);
        Glide.with(getActivity()).load(R.drawable.cartoon_recommend_push_note_img).into(ivAddpost);
        srHome.autoRefresh();
        ryAsia.requestFocus();
        ryAsia.setFocusable(false);
        slHome.scrollBy(0, 0);
        slHome.smoothScrollTo(0, 20);
        slHome.scrollTo(0, 0);
        ryHomeVip.setLayoutManager(new GridLayoutManager(getActivity(), 3));
        ryHomeVip.setNestedScrollingEnabled(false);
        ryAsia.setLayoutManager(new GridLayoutManager(getActivity(), 3));
        ryAsia.setNestedScrollingEnabled(false);
        srHome.autoRefresh();
        srHome.setOnRefreshListener(new OnRefreshListener() {
            @Override
            public void onRefresh(RefreshLayout refreshLayout) {
                homePresenter.loadVipVideo("0", "1", getActivity().getSharedPreferences("user", MODE_PRIVATE).getString("location", ""), 8);
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


    @OnClick({R.id.tv_recharge, R.id.tv_sign, R.id.tv_service, R.id.iv_addpost,R.id.tv_more})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.tv_more:
                startActivity(new Intent(getContext(), AroundUsersActivity.class));
                break;
            case R.id.tv_recharge:
                startActivity(new Intent(getContext(), RechargeActivity.class));
                break;
            case R.id.tv_sign:
                startActivity(new Intent(getContext(), UserSignActivity.class));
                break;
            case R.id.tv_service:
                showDialog();
                RongIM.connect(getActivity().getSharedPreferences("user", MODE_PRIVATE).getString("token", ""), new RongIMClient.ConnectCallback() {
                    @Override
                    public void onTokenIncorrect() {

                    }

                    @Override
                    public void onSuccess(String s) {
                        hideDialog();
                        CSCustomServiceInfo.Builder csBuilder = new CSCustomServiceInfo.Builder();
                        CSCustomServiceInfo csInfo = csBuilder.nickName("客服").build();
                        RongIM.getInstance().startCustomerServiceChat(getContext(), getActivity().getSharedPreferences("user", MODE_PRIVATE).getString("username", ""), "在线客服",csInfo);
                    }

                    @Override
                    public void onError(RongIMClient.ErrorCode errorCode) {
                        hideDialog();
                    }
                });
//                startActivity(new Intent(getContext(), VipServiceActivity.class));
                break;
            case R.id.iv_addpost:
                startActivity(new Intent(getContext(), PostWriteActivity.class));
                break;
        }
    }

    @Override
    public void showLoadingDialog() {
        showDialog();
    }

    @Override
    public void hideLoadingDialog() {
        hideDialog();
    }

    private static final String TAG = "HomeFragment";


    @Override
    public void getVipVideo(List<VipVideoGson> vipVideoGsonList1, List<VipVideoGson> vipVideoGsonList2, List<BannerGson> bannerGsonList, List<UserGson> list, List<MarqueenGson> marqueenGsonList) {
        VipAdapter vipAdapter = new VipAdapter(vipVideoGsonList1);
        ryHomeVip.setAdapter(vipAdapter);
        VipAdapter vipAdapter1 = new VipAdapter(vipVideoGsonList2);
        ryAsia.setAdapter(vipAdapter1);
        srHome.finishRefresh();
        List<String> banner = new ArrayList<>();
        for (int i = 0; i < bannerGsonList.size(); i++) {
            banner.add(bannerGsonList.get(i).getImage());
        }
        homeBn.setDelayedTime(5000);
        homeBn.setIndicatorVisible(true);
        homeBn.setPages(banner, new MZHolderCreator() {
            @Override
            public MZViewHolder createViewHolder() {
                return new BannerViewHolder();
            }
        });
        homeBn.start();
        LayoutInflater inflater = LayoutInflater.from(getContext());
        list1.addAll(list);
        pileLayout.removeAllViews();
        for (int i = 0; i < list1.size(); i++) {
            CircleImageView imageView = (CircleImageView) inflater.inflate(R.layout.item_praise, pileLayout, false);
            Glide.with(this).load(list1.get(i).getAvatar()).into(imageView);
            Log.i(TAG, "getVipVideo: " + list1.get(i).getAvatar());
            pileLayout.addView(imageView);
        }
        list1.clear();
        List<String> datas = new ArrayList<>();
        for (int i = 0; i < marqueenGsonList.size(); i++) {
            datas.add(marqueenGsonList.get(i).getTxt());
        }
        SimpleMF<String> marqueeFactory = new SimpleMF(getContext());
        marqueeFactory.setData(datas);
        homeMv.setMarqueeFactory(marqueeFactory);
        homeMv.startFlipping();
        list1.clear();
    }



    public static class BannerViewHolder implements MZViewHolder<String> {
        private ImageView mImageView;

        @Override
        public View createView(Context context) {
            View view = LayoutInflater.from(context).inflate(R.layout.banner_item, null);
            mImageView = (ImageView) view.findViewById(R.id.banner_image);
            return view;
        }

        @Override
        public void onBind(Context context, int position, String data) {
            Glide.with(context).load(data).apply(new RequestOptions().error(R.drawable.error_pic).transform(new GlideRoundTransform(context, 7))).into(mImageView);
//            Glide.with(context).load(data).into(mImageView);
        }
    }

    @Override
    public void onPause() {
        super.onPause();
        homeBn.pause();//暂停轮播
    }

    @Override
    public void onResume() {
        super.onResume();
        homeBn.start();//开始轮播
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        ButterKnife.reset(this);
    }

    private class VipAdapter extends BaseQuickAdapter<VipVideoGson, BaseViewHolder> {

        public VipAdapter(@Nullable List<VipVideoGson> data) {
            super(R.layout.ry_home_vip_item, data);
        }

        @Override
        protected void convert(BaseViewHolder helper, VipVideoGson item) {
            helper.setText(R.id.tv_title, item.getName())
                    .setText(R.id.tv_tag1, item.getTag1())
                    .setText(R.id.tv_tag2, item.getTag2());
            Glide.with(getActivity()).load(item.getCover()).apply(new RequestOptions().transform(new GlideRoundTransform(getActivity(), 7))).into((ImageView) helper.getView(R.id.iv_cover));
        }
    }

}

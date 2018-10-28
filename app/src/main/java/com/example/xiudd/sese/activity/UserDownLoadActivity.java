package com.example.xiudd.sese.activity;

import android.content.Context;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.widget.Toolbar;
import android.view.View;

import com.example.xiudd.sese.R;
import com.example.xiudd.sese.adapter.DownloadPagerAdapter;
import com.example.xiudd.sese.base.BaseActivity;
import com.example.xiudd.sese.fragment.download.DownloadedFragment;
import com.example.xiudd.sese.fragment.download.DownloadingFragment;
import com.example.xiudd.sese.indicator.ScaleTransitionPagerTitleView;


import net.lucode.hackware.magicindicator.MagicIndicator;
import net.lucode.hackware.magicindicator.ViewPagerHelper;
import net.lucode.hackware.magicindicator.buildins.commonnavigator.CommonNavigator;
import net.lucode.hackware.magicindicator.buildins.commonnavigator.abs.CommonNavigatorAdapter;
import net.lucode.hackware.magicindicator.buildins.commonnavigator.abs.IPagerIndicator;
import net.lucode.hackware.magicindicator.buildins.commonnavigator.abs.IPagerTitleView;
import net.lucode.hackware.magicindicator.buildins.commonnavigator.indicators.BezierPagerIndicator;
import net.lucode.hackware.magicindicator.buildins.commonnavigator.indicators.LinePagerIndicator;
import net.lucode.hackware.magicindicator.buildins.commonnavigator.titles.ClipPagerTitleView;
import net.lucode.hackware.magicindicator.buildins.commonnavigator.titles.DummyPagerTitleView;
import net.lucode.hackware.magicindicator.buildins.commonnavigator.titles.SimplePagerTitleView;

import java.util.ArrayList;
import java.util.List;

import butterknife.ButterKnife;
import butterknife.InjectView;




public class UserDownLoadActivity extends BaseActivity {


    @InjectView(R.id.toolbar)
    Toolbar toolbar;

    @InjectView(R.id.pt_download)
    MagicIndicator magicIndicator;
    @InjectView(R.id.vp_download)
    ViewPager vpDownload;
    String[] title = {"下载中", "下载完成"};

    @Override
    public int intiLayout() {
        return R.layout.activity_user_down_load;
    }

    @Override
    public void initView() {
        initToolBar().setToolbarBackIco().setToolbarTitle("下载");
        ButterKnife.inject(this);
        List<Fragment> fragments = new ArrayList<>();
        fragments.add(new DownloadingFragment());
        fragments.add(new DownloadedFragment());

        DownloadPagerAdapter downloadPagerAdater = new DownloadPagerAdapter(getSupportFragmentManager(), fragments, title);
        vpDownload.setAdapter(downloadPagerAdater);
        vpDownload.setCurrentItem(0);
        magicIndicator.setBackgroundColor(Color.WHITE);
        CommonNavigator commonNavigator = new CommonNavigator(this);
        commonNavigator.setAdapter(new CommonNavigatorAdapter() {
            @Override
            public int getCount() {
                return title == null ? 0 : title.length;
            }

            @Override
            public IPagerTitleView getTitleView(Context context, final int index) {
                SimplePagerTitleView simplePagerTitleView = new ScaleTransitionPagerTitleView(context);
                simplePagerTitleView.setText(title[index]);
                simplePagerTitleView.setTextSize(18);
                simplePagerTitleView.setNormalColor(Color.GRAY);
                simplePagerTitleView.setSelectedColor(Color.BLACK);
                simplePagerTitleView.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        vpDownload.setCurrentItem(index);
                    }
                });
                return simplePagerTitleView;
            }

            @Override
            public IPagerIndicator getIndicator(Context context) {
                BezierPagerIndicator indicator = new BezierPagerIndicator(context);
                indicator.setColors(Color.parseColor("#1E90FF"), Color.parseColor("#1E90FF"), Color.parseColor("#1E90FF"), Color.parseColor("#1E90FF"), Color.parseColor("#1E90FF"));
                return indicator;
            }
        });
        magicIndicator.setNavigator(commonNavigator);
        ViewPagerHelper.bind(magicIndicator, vpDownload);
    }
//        final CommonNavigator commonNavigator = new CommonNavigator(this);
//        commonNavigator.setAdapter(new CommonNavigatorAdapter() {
//
//            @Override
//            public int getCount() {
//                return title == null ? 0 : title.length;
//            }
//
//            @Override
//            public IPagerTitleView getTitleView(Context context, final int i) {
//                ClipPagerTitleView clipPagerTitleView = new ClipPagerTitleView(context);
//                clipPagerTitleView.setText(title[i]);
//                clipPagerTitleView.setTextColor(Color.parseColor("#1E90FF"));
//                clipPagerTitleView.setClipColor(Color.BLACK);
//                clipPagerTitleView.setOnClickListener(new View.OnClickListener() {
//                    @Override
//                    public void onClick(View v) {
//                        vpDownload.setCurrentItem(i);
//                    }
//                });
//
//                return clipPagerTitleView;
//            }
//
//
//            @Override
//            public IPagerIndicator getIndicator(Context context) {
//                return null;    // 没有指示器，因为title的指示作用已经很明显了
//            }
//        });
//        ptDownload.setNavigator(commonNavigator);
//        vpDownload.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
//
//            @Override
//            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
//                ptDownload.onPageScrolled(position, positionOffset, positionOffsetPixels);
//            }
//
//            @Override
//            public void onPageSelected(int position) {
//                ptDownload.onPageSelected(position);
//            }
//
//            @Override
//            public void onPageScrollStateChanged(int state) {
//                ptDownload.onPageScrollStateChanged(state);
//            }
//        });
//
//        vpDownload.setCurrentItem(0);


//        ptDownload.setFooterLineHeight(5);
//        ptDownload.setClipPadding(20);
//        ptDownload.setTextColor(0xff000000);
//        ptDownload.setTitlePadding(10);
//        ptDownload.setLinePosition(TitlePageIndicator.LinePosition.Bottom);
//        ptDownload.setFooterIndicatorStyle(TitlePageIndicator.IndicatorStyle.None);
//        ptDownload.setFooterColor(0xff1E90FF);
//        ptDownload.setViewPager(vpDownload);
//}

    @Override
    public void initData() {

    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // TODO: add setContentView(...) invocation

    }


}

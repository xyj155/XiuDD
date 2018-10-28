package com.example.xiudd.sese.fragment;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.os.Handler;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.xiudd.sese.R;
import com.example.xiudd.sese.activity.HomeSearchActivity;
import com.example.xiudd.sese.adapter.DownloadPagerAdapter;
import com.example.xiudd.sese.base.BaseFragment;
import com.example.xiudd.sese.indicator.ScaleTransitionPagerTitleView;
import com.zaaach.citypicker.CityPicker;
import com.zaaach.citypicker.adapter.OnPickListener;
import com.zaaach.citypicker.model.City;
import com.zaaach.citypicker.model.HotCity;
import com.zaaach.citypicker.model.LocateState;
import com.zaaach.citypicker.model.LocatedCity;

import net.lucode.hackware.magicindicator.MagicIndicator;
import net.lucode.hackware.magicindicator.ViewPagerHelper;
import net.lucode.hackware.magicindicator.buildins.commonnavigator.CommonNavigator;
import net.lucode.hackware.magicindicator.buildins.commonnavigator.abs.CommonNavigatorAdapter;
import net.lucode.hackware.magicindicator.buildins.commonnavigator.abs.IPagerIndicator;
import net.lucode.hackware.magicindicator.buildins.commonnavigator.abs.IPagerTitleView;
import net.lucode.hackware.magicindicator.buildins.commonnavigator.indicators.BezierPagerIndicator;
import net.lucode.hackware.magicindicator.buildins.commonnavigator.titles.SimplePagerTitleView;

import java.util.ArrayList;
import java.util.List;

import butterknife.ButterKnife;
import butterknife.InjectView;
import butterknife.OnClick;




/**
 * Created by Xuyijie on 2018/10/15.
 */

public class MainPageFragment extends BaseFragment {

    @InjectView(R.id.toolbar)
    RelativeLayout toolbar;
    @InjectView(R.id.vp_home)
    ViewPager vpHome;
    @InjectView(R.id.tv_location)
    TextView tvLocation;
    @InjectView(R.id.mi_home)
    MagicIndicator miHome;
    @InjectView(R.id.iv_search)
    ImageView ivSearch;
    private String[] title = {"推荐", "社区"};
    List<HotCity> hotCities = new ArrayList<>();

    @Override
    protected int setView() {
        return R.layout.fragment_home;
    }

    @Override
    protected void init(View view) {
        ButterKnife.inject(this, view);
        tvLocation.setText(getActivity().getSharedPreferences("user", Context.MODE_PRIVATE).getString("location", ""));
        List<Fragment> fragments = new ArrayList<>();
        fragments.add(new HomeFragment());
        fragments.add(new PurseFragment());
        DownloadPagerAdapter downloadPagerAdater = new DownloadPagerAdapter(getChildFragmentManager(), fragments, title);
        vpHome.setAdapter(downloadPagerAdater);
        vpHome.setCurrentItem(0);
        miHome.setBackgroundColor(Color.WHITE);
        CommonNavigator commonNavigator = new CommonNavigator(getContext());
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
                        vpHome.setCurrentItem(index);
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
        miHome.setNavigator(commonNavigator);
        ViewPagerHelper.bind(miHome, vpHome);

        hotCities.add(new HotCity("北京", "北京", "101010100"));
        hotCities.add(new HotCity("上海", "上海", "101020100"));
        hotCities.add(new HotCity("广州", "广东", "101280101"));
        hotCities.add(new HotCity("深圳", "广东", "101280601"));
        hotCities.add(new HotCity("杭州", "浙江", "101210101"));
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

    @OnClick({ R.id.tv_location, R.id.iv_search})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.iv_search:
                startActivity(new Intent(getContext(), HomeSearchActivity.class));
                break;
            case R.id.tv_location:
                CityPicker.getInstance()
                        .setFragmentManager(getChildFragmentManager())    //此方法必须调用
                        .enableAnimation(true)    //启用动画效果
                        .setLocatedCity(new LocatedCity("杭州", "浙江", "101210101"))  //APP自身已定位的城市，默认为null（定位失败）
                        .setHotCities(hotCities)    //指定热门城市
                        .setOnPickListener(new OnPickListener() {
                            @Override
                            public void onPick(int position, City data) {
                                if (data != null) {
                                    Toast.makeText(getContext(), data.getName(), Toast.LENGTH_SHORT).show();
                                }

                            }

                            @Override
                            public void onLocate() {
                                //开始定位，这里模拟一下定位
                                new Handler().postDelayed(new Runnable() {
                                    @Override
                                    public void run() {
                                        //定位完成之后更新数据
                                        CityPicker.getInstance()
                                                .locateComplete(new LocatedCity("深圳", "广东", "101280601"), LocateState.SUCCESS);
                                    }
                                }, 2000);
                            }
                        })
                        .show();

                break;
        }
    }


}

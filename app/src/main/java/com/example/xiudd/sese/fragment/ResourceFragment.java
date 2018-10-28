package com.example.xiudd.sese.fragment;

import android.os.Bundle;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.example.xiudd.sese.R;
import com.example.xiudd.sese.adapter.FragmentResourceAdapter;
import com.example.xiudd.sese.base.BaseFragment;
import com.example.xiudd.sese.entity.FragmentResourceBean;

import java.util.ArrayList;
import java.util.List;

import butterknife.ButterKnife;
import butterknife.InjectView;

/**
 * Created by Xuyijie on 2018/10/15.
 */

public class ResourceFragment extends BaseFragment {
    @InjectView(R.id.ry_resource)
    RecyclerView ryResource;
    private FragmentResourceAdapter fragmentPagerAdapter;

    @Override
    protected int setView() {
        return R.layout.fragment_resource;
    }

    @Override
    protected void init(View view) {
        ButterKnife.inject(this, view);
        ryResource.setLayoutManager(new LinearLayoutManager(getContext()));

    }

    @Override
    protected void initData(Bundle savedInstanceState) {
        List<FragmentResourceBean> fragmentResourceBeanList = new ArrayList<>();
        fragmentResourceBeanList.add(new FragmentResourceBean("会员演播室", R.mipmap.vip));
        fragmentResourceBeanList.add(new FragmentResourceBean("热门视频", R.mipmap.hot));
        fragmentResourceBeanList.add(new FragmentResourceBean("外国情趣", R.mipmap.wgqq));
        fragmentResourceBeanList.add(new FragmentResourceBean("未成年", R.mipmap.kid));
        fragmentResourceBeanList.add(new FragmentResourceBean("在线视频", R.mipmap.net));
        fragmentResourceBeanList.add(new FragmentResourceBean("卡通动漫", R.mipmap.kt));
        fragmentResourceBeanList.add(new FragmentResourceBean("网红自拍", R.mipmap.camera));
        fragmentResourceBeanList.add(new FragmentResourceBean("有声小说", R.mipmap.txt));
        fragmentResourceBeanList.add(new FragmentResourceBean("文字阅读", R.mipmap.book));
        fragmentPagerAdapter = new FragmentResourceAdapter(getContext(), fragmentResourceBeanList);
        ryResource.setAdapter(fragmentPagerAdapter);
    }


    @Override
    public void onDestroyView() {
        super.onDestroyView();
        ButterKnife.reset(this);
    }
}

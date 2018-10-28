package com.example.xiudd.sese.activity;

import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.os.Environment;
import android.support.annotation.RequiresApi;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.util.Log;
import android.view.View;

import com.bifan.txtreaderlib.ui.HwTxtPlayActivity;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.example.xiudd.sese.R;
import com.example.xiudd.sese.base.BaseActivity;
import com.example.xiudd.sese.contract.NovelContract;
import com.example.xiudd.sese.fragment.novelFragment.CityExpress;
import com.example.xiudd.sese.fragment.novelFragment.FamilySex;
import com.example.xiudd.sese.fragment.novelFragment.Other;
import com.example.xiudd.sese.fragment.novelFragment.RemQiExchange;
import com.example.xiudd.sese.fragment.novelFragment.SchoolSe;
import com.example.xiudd.sese.fragment.novelFragment.SexIntellinge;
import com.example.xiudd.sese.fragment.novelFragment.SexJoke;
import com.example.xiudd.sese.gson.NovelGson;
import com.example.xiudd.sese.presenter.NovelPresenter;


import java.util.ArrayList;
import java.util.List;

import butterknife.ButterKnife;
import butterknife.InjectView;



public class NovelActivity extends BaseActivity {


//    @InjectView(R.id.pt_novel)
//    TitlePageIndicator ptNovel;
    @InjectView(R.id.vp_novel)
    ViewPager vpNovel;


    @Override
    public int intiLayout() {
        return R.layout.activity_novel;
    }

    @Override
    public void initView() {
        // TODO: add setContentView(...) invocation
        ButterKnife.inject(this);
        initToolBar().setToolbarBackIco().setToolbarTitle("文字小说");
//        ptNovel.setFooterColor(0xffffffff);
//        ptNovel.setBackgroundColor(0xffffffff);
//        ptNovel.setFooterIndicatorHeight(9);
//        ptNovel.setInd(0xff1E90FF);
    }

    @Override
    public void initData() {
        vpNovel.setOffscreenPageLimit(9);
        List<Fragment> fragments = new ArrayList<>();
        fragments.add(new CityExpress());
        fragments.add(new RemQiExchange());
        fragments.add(new SchoolSe());
        fragments.add(new FamilySex());
        fragments.add(new SexIntellinge());
        fragments.add(new SexJoke());
        fragments.add(new Other());
        String[] title = {"城市激情", "人妻交换", "校园春色", "家庭乱伦", "性爱技巧", "色情笑话", "其他"};
        NovelFragmentAdapter fragmentAdapter = new NovelFragmentAdapter(getSupportFragmentManager(), fragments, title);
        vpNovel.setAdapter(fragmentAdapter);
//        ptNovel.setViewPager(vpNovel);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    private class NovelFragmentAdapter extends FragmentPagerAdapter {
        private List<Fragment> fragmentList;
        private String[] title;

        public NovelFragmentAdapter(FragmentManager fm, List<Fragment> fragmentList, String[] title) {
            super(fm);
            this.fragmentList = fragmentList;
            this.title = title;
        }

        @Override
        public Fragment getItem(int position) {
            return fragmentList.get(position);
        }

        @Override
        public int getCount() {
            return title.length;
        }

        @Override
        public CharSequence getPageTitle(int position) {
            return title[position];
        }
    }

    public static class NovelAdapter extends BaseQuickAdapter<NovelGson, BaseViewHolder> implements NovelContract.View {
        private NovelPresenter novelPresenter = new NovelPresenter(this);
        private Context context;

        public NovelAdapter(Context context, List<NovelGson> data) {
            super(R.layout.ry_novel_item, data);
            this.context = context;
        }

        @Override
        protected void convert(BaseViewHolder helper, final NovelGson item) {
            helper.setText(R.id.tv_title, item.getTitle())
                    .setOnClickListener(R.id.ll_txt, new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            Log.i(TAG, "onClick: " + "http://122.152.231.185/" + item.getContent());
                            novelPresenter.downLoadTxt(context, "http://122.152.231.185/" + item.getContent());

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
        public void setNovelList(List<NovelGson> list) {

        }

        @Override
        public void downLoadTxt(String path) {
            HwTxtPlayActivity.loadTxtFile(context, path);
        }
    }
}

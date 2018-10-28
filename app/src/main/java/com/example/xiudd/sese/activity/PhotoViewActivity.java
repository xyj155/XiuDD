package com.example.xiudd.sese.activity;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.support.v4.view.ViewPager;
import android.widget.FrameLayout;
import android.widget.TextView;

import com.example.xiudd.sese.R;
import com.example.xiudd.sese.base.BaseActivity;
import com.example.xiudd.sese.fragment.PictureSlideFragment;

import java.util.ArrayList;
import java.util.List;

import butterknife.ButterKnife;
import butterknife.InjectView;

public class PhotoViewActivity extends BaseActivity {


    @InjectView(R.id.vp_photo)
    ViewPager vpPhoto;
    @InjectView(R.id.tv_count)
    TextView tvCount;
    @InjectView(R.id.iv_image)
    FrameLayout ivImage;


    @Override
    public int intiLayout() {
        return R.layout.activity_photo_view;
    }

    @Override
    public void initView() {
        ButterKnife.inject(this);
        final ArrayList<String> url = getIntent().getStringArrayListExtra("url");
        vpPhoto.setAdapter(new PictureSlidePagerAdapter(getSupportFragmentManager(), url));
        vpPhoto.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
                tvCount.setText(String.valueOf(position + 1) + "/" + url.size());
            }

            @Override
            public void onPageSelected(int position) {
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });
    }

    @Override
    public void initData() {

    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // TODO: add setContentView(...) invocation

    }

    private class PictureSlidePagerAdapter extends FragmentStatePagerAdapter {
        List<String> stringList = new ArrayList<>();

        public PictureSlidePagerAdapter(FragmentManager fm, List<String> stringList) {
            super(fm);
            this.stringList = stringList;
        }


        @Override
        public Fragment getItem(int position) {
            return PictureSlideFragment.newInstance(stringList.get(position));
        }

        @Override
        public int getCount() {
            return stringList.size();
        }
    }
}

package com.example.xiudd.sese.activity;

import android.content.DialogInterface;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AlertDialog;
import android.view.Window;
import android.view.WindowManager;
import android.widget.TextView;

import com.example.xiudd.sese.R;
import com.example.xiudd.sese.base.BaseActivity;
import com.example.xiudd.sese.contract.PictureContract;
import com.example.xiudd.sese.fragment.PictureSlideFragment;
import com.example.xiudd.sese.gson.PictureGson;
import com.example.xiudd.sese.presenter.PicturePresenter;

import java.util.ArrayList;
import java.util.List;

import butterknife.ButterKnife;
import butterknife.InjectView;

public class CelebrityActivity extends BaseActivity implements PictureContract.View {

    @InjectView(R.id.vp_pic)
    ViewPager vpPic;
    @InjectView(R.id.tv_count)
    TextView tvCount;
    private PicturePresenter picturePresenter;


    @Override
    public int intiLayout() {
        return R.layout.activity_celebrity;
    }

    @Override
    public void initView() {
        ButterKnife.inject(this);
        picturePresenter = new PicturePresenter(this);

    }

    @Override
    public void initData() {
        new Thread(new Runnable() {
            @Override
            public void run() {
                picturePresenter.getPictureDetail("https://www.267ww.com" + getIntent().getStringExtra("url"));
            }
        }).start();

    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        AlertDialog alertDialog = new AlertDialog.Builder(this).setTitle("提示")
                .setMessage("图片加载需要一点时间，请耐心等待！")
                .setIcon(R.mipmap.ico)
                .setPositiveButton("确定", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {

                    }
                })
                .setNegativeButton("取消", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        finish();
                    }
                })
                .create();
        alertDialog.show();
        // TODO: add setContentView(...) invocation

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

    }

    @Override
    public void getPictureDetail(final List<String> stringList) {
        runOnUiThread(new Runnable() {
            @Override
            public void run() {
                vpPic.setAdapter(new PictureSlidePagerAdapter(getSupportFragmentManager(), stringList));
                vpPic.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
                    @Override
                    public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
                        tvCount.setText(String.valueOf(position + 1) + "/" + stringList.size());
                    }

                    @Override
                    public void onPageSelected(int position) {
                    }

                    @Override
                    public void onPageScrollStateChanged(int state) {

                    }
                });
            }
        });

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

package com.example.xiudd.sese.activity;


import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.IdRes;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.view.KeyEvent;
import android.view.WindowManager;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

import com.example.xiudd.sese.R;
import com.example.xiudd.sese.base.BaseActivity;
import com.example.xiudd.sese.fragment.ConversationFragment;
import com.example.xiudd.sese.fragment.MainPageFragment;
import com.example.xiudd.sese.fragment.PicturesFragment;
import com.example.xiudd.sese.fragment.ResourceFragment;
import com.example.xiudd.sese.fragment.UserFragment;
import com.example.xiudd.sese.view.AppDialog;

import java.io.File;

import butterknife.ButterKnife;
import butterknife.InjectView;
import butterknife.OnClick;


public class MainActivity extends BaseActivity  {


    @InjectView(R.id.contentContainer)
    FrameLayout contentContainer;
    @InjectView(R.id.rb_home)
    RadioButton rbHome;
    @InjectView(R.id.rb_resource)
    RadioButton rbResource;
    @InjectView(R.id.rb_chat)
    RadioButton rbChat;
    @InjectView(R.id.rb_user)
    RadioButton rbUser;
    @InjectView(R.id.bottomBar)
    RadioGroup bottomBar;
    @InjectView(R.id.iv_add)
    ImageView ivAdd;

    private FragmentManager supportFragmentManager;
    private MainPageFragment homeFragment;
    private ResourceFragment resourceFragment;
    private ConversationFragment converFragment;
    private PicturesFragment picturesFragment;
    private UserFragment userFragment;

    @Override
    public int intiLayout() {
        return R.layout.activity_main;
    }

    @Override
    public void initView() {
        // TODO: add setContentView(...) invocation
        ButterKnife.inject(this);
        bottomBar.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, @IdRes int checkedId) {
                supportFragmentManager = getSupportFragmentManager();
                FragmentTransaction transaction = supportFragmentManager.beginTransaction();
                hideAllFragment(transaction);
                switch (checkedId) {
                    case R.id.rb_home:
                        if (homeFragment == null) {
                            homeFragment = new MainPageFragment();
                            transaction.add(R.id.contentContainer, homeFragment);
                        } else {
                            transaction.show(homeFragment);
                        }
                        break;
                    case R.id.rb_resource:
                        if (resourceFragment == null) {
                            resourceFragment = new ResourceFragment();
                            transaction.add(R.id.contentContainer, resourceFragment);
                        } else {
                            transaction.show(resourceFragment);
                        }
                        break;
                    case R.id.rb_chat:
                        if (converFragment == null) {
                            converFragment = new ConversationFragment();
                            transaction.add(R.id.contentContainer, converFragment);
                        } else {
                            transaction.show(converFragment);
                        }
                        break;
                    case R.id.rb_user:
                        if (userFragment == null) {
                            userFragment = new UserFragment();
                            transaction.add(R.id.contentContainer, userFragment);
                        } else {
                            transaction.show(userFragment);
                        }
                        break;
                }
                transaction.commit();
            }
        });
        showFirstPosition();
    }

    @Override
    public void initData() {
        File file = new File("/mnt/sdcard/羞涩");
//判断文件夹是否存在，如果不存在就创建，否则不创建
        if (!file.exists()) {
            //通过file的mkdirs()方法创建目录中包含却不存在的文件夹
            file.mkdirs();
        }

    }
    @Override
    public boolean onKeyDown(int keyCode,KeyEvent event){
        if(keyCode==KeyEvent.KEYCODE_BACK){
            new AppDialog(this)
                    .setTitle("提示")
                    .setMessage("是否退出当前应用")
                    .setPositiveButton("确定", new AppDialog.OnMyDialogButtonClickListener() {
                        @Override
                        public void onClick() {
                            finish();
                            System.exit(0);
                        }
                    })
                    .setNegativeButton("取消", new AppDialog.OnMyDialogButtonClickListener() {
                        @Override
                        public void onClick() {

                        }
                    })
                    .show();
            return false;
        }
        return super.onKeyDown(keyCode,event);
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_HIDDEN);

    }




    private void showFirstPosition() {
        supportFragmentManager = getSupportFragmentManager();
        final FragmentTransaction transaction = supportFragmentManager.beginTransaction();
        homeFragment = new MainPageFragment();
        transaction.add(R.id.contentContainer, homeFragment);
        transaction.commit();
    }


    public void hideAllFragment(FragmentTransaction transaction) {
        if (homeFragment != null) {
            transaction.hide(homeFragment);
        }
        if (resourceFragment != null) {
            transaction.hide(resourceFragment);
        }
        if (converFragment != null) {
            transaction.hide(converFragment);
        }
        if (userFragment != null) {
            transaction.hide(userFragment);
        }
    }

    @OnClick(R.id.iv_add)
    public void onViewClicked() {
        startActivity(new Intent(MainActivity.this,PostWriteActivity.class));
    }
}

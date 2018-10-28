package com.example.xiudd.sese.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.TextView;


import com.example.xiudd.sese.R;
import com.example.xiudd.sese.base.BaseActivity;
import com.tencent.bugly.beta.Beta;

import butterknife.ButterKnife;
import butterknife.InjectView;
import butterknife.OnClick;
import cn.sharesdk.onekeyshare.OnekeyShare;


public class SettingActivity extends BaseActivity {


    @InjectView(R.id.toolbar)
    Toolbar toolbar;
    @InjectView(R.id.tv_share)
    TextView tvShare;
    //    @InjectView(R.id.tv_update_password)
//    TextView tvUpdatePassword;
    @InjectView(R.id.tv_check_update)
    TextView tvCheckUpdate;
    @InjectView(R.id.tv_about)
    TextView tvAbout;
    @InjectView(R.id.tv_reback)
    TextView tvReback;
    @InjectView(R.id.tv_login_out)
    TextView tvLoginOut;
    @InjectView(R.id.tv_reset_password)
    TextView tvResetPassword;

    @Override
    public int intiLayout() {
        return R.layout.activity_setting;
    }

    @Override
    public void initView() {
        initToolBar().setToolbarBackIco().setToolbarTitle("设置");
        ButterKnife.inject(this);
    }

    @Override
    public void initData() {

    }


    @OnClick({R.id.tv_reset_password, R.id.tv_share, R.id.tv_check_update, R.id.tv_about, R.id.tv_reback, R.id.tv_login_out})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.tv_share:
                showShare();
                break;
            case R.id.tv_reset_password:
                startActivity(new Intent(SettingActivity.this, UpdatePasswordActivity.class));
                break;
            case R.id.tv_check_update:
                Beta.checkUpgrade(true, false);
                break;
            case R.id.tv_about:
                break;
            case R.id.tv_reback:
                startActivity(new Intent(SettingActivity.this, FeedbackActivity.class));
                break;
            case R.id.tv_login_out:
                break;
        }
    }

    private void showShare() {
        OnekeyShare oks = new OnekeyShare();
        oks.disableSSOWhenAuthorize();
        oks.setTitle("羞涩分享");
        oks.setTitleUrl("http://sharesdk.cn");
        oks.setText("我在使用羞涩APP，小伙伴，我们一起吧！！点击连接下载");
        oks.setUrl("http://sharesdk.cn");
        oks.setComment("我是测试评论文本");
        oks.show(this);
    }

}

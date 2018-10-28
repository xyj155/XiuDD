package com.example.xiudd.sese.activity;

import com.example.xiudd.sese.R;
import com.example.xiudd.sese.base.BaseActivity;

public class ConversationActivity extends BaseActivity {


    @Override
    public int intiLayout() {
        return R.layout.activity_conversation;
    }

    @Override
    public void initView() {
        initToolBar().setToolbarBackIco().setToolbarTitle(getIntent().getData().getQueryParameter("title"));
    }

    @Override
    public void initData() {

    }
}

package com.example.xiudd.sese.activity;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.example.xiudd.sese.R;
import com.example.xiudd.sese.base.BaseActivity;
import com.example.xiudd.sese.contract.LocalUserContract;
import com.example.xiudd.sese.gson.UserGson;
import com.example.xiudd.sese.presenter.LocalUserPresenter;
import com.scwang.smartrefresh.layout.SmartRefreshLayout;
import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.scwang.smartrefresh.layout.listener.OnRefreshListener;

import java.util.List;

import butterknife.ButterKnife;
import butterknife.InjectView;
import io.rong.imkit.RongIM;
import io.rong.imlib.RongIMClient;

public class UserContractActivity extends BaseActivity implements LocalUserContract.View {


    @InjectView(R.id.toolbar)
    Toolbar toolbar;
    @InjectView(R.id.ry_contact)
    RecyclerView ryContact;
    @InjectView(R.id.sl_contact)
    SmartRefreshLayout slContact;
    private LocalUserPresenter localUserPresenter = new LocalUserPresenter(this);

    @Override
    public int intiLayout() {
        return R.layout.activity_user_contract;

    }

    @Override
    public void initView() {
        ButterKnife.inject(this);
        initToolBar().setToolbarBackIco().setToolbarTitle("联系人");
        ryContact.setLayoutManager(new LinearLayoutManager(UserContractActivity.this));
        slContact.setOnRefreshListener(new OnRefreshListener() {
            @Override
            public void onRefresh(RefreshLayout refreshLayout) {
                localUserPresenter.getContactUser(getSharedPreferences("user", MODE_PRIVATE).getString("id", ""));
            }
        });

    }

    @Override
    public void initData() {
        localUserPresenter.getContactUser(getSharedPreferences("user", MODE_PRIVATE).getString("id", ""));
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // TODO: add setContentView(...) invocation

    }

    @Override
    public void showLoadingDialog() {
        showmDialog("获取中...");
    }

    @Override
    public void hideLoadingDialog() {
        hidemDialog();
    }

    @Override
    public void failed(String error) {

    }

    @Override
    public void showLocalUserList(List<UserGson> list) {
        UserContactAdapter adapter = new UserContactAdapter(list);
        ryContact.setAdapter(adapter);
    }

    private class UserContactAdapter extends BaseQuickAdapter<UserGson, BaseViewHolder> {

        public UserContactAdapter(@Nullable List<UserGson> data) {
            super(R.layout.ry_localaround_user_item, data);
        }

        @Override
        protected void convert(BaseViewHolder helper, final UserGson item) {
            helper.setText(R.id.tv_username, item.getNickname())
                    .setVisible(R.id.btn_chat, false)
                    .setOnClickListener(R.id.ll_chat, new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            showmDialog("请求中...");
                            RongIM.connect(getSharedPreferences("user", MODE_PRIVATE).getString("token", ""), new RongIMClient.ConnectCallback() {
                                @Override
                                public void onTokenIncorrect() {

                                }

                                @Override
                                public void onSuccess(String s) {
                                    hidemDialog();
                                    Log.i(TAG, "onSuccess: " + s);
                                    Log.i(TAG, "onSuccess:getUsername " + item.getUsername());
                                    Log.i(TAG, "onSuccess:getNickname " + item.getNickname());
                                    RongIM.getInstance().startPrivateChat(UserContractActivity.this, item.getUsername(), item.getNickname());
                                }

                                @Override
                                public void onError(RongIMClient.ErrorCode errorCode) {
                                    hidemDialog();
                                    Toast.makeText(mContext, "连接服务器失败，请稍后重试", Toast.LENGTH_SHORT).show();
                                }
                            });

                        }
                    });
            Glide.with(UserContractActivity.this).load(item.getAvatar()).into((ImageView) helper.getView(R.id.iv_avatar));
        }
    }
}


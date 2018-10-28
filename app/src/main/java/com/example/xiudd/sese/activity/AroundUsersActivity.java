package com.example.xiudd.sese.activity;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
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

public class AroundUsersActivity extends BaseActivity implements LocalUserContract.View {
    @InjectView(R.id.toolbar)
    Toolbar toolbar;
    @InjectView(R.id.ry_local_user)
    RecyclerView ryLocalUser;
    @InjectView(R.id.sl_local)
    SmartRefreshLayout slLocal;
    private LocalUserPresenter localUserPresenter = new LocalUserPresenter(this);

    @Override
    public int intiLayout() {
        return R.layout.activity_around_users;
    }

    @Override
    public void initView() {
        ButterKnife.inject(this);
        initToolBar().setToolbarBackIco().setToolbarTitle("附近的人");
        ryLocalUser.setLayoutManager(new LinearLayoutManager(AroundUsersActivity.this));
        slLocal.autoRefresh();
        slLocal.setOnRefreshListener(new OnRefreshListener() {
            @Override
            public void onRefresh(RefreshLayout refreshLayout) {
                localUserPresenter.getLocalUserList(getSharedPreferences("user", MODE_PRIVATE).getString("location", ""), 80);
            }
        });
    }

    @Override
    public void initData() {

    }

    @Override
    public void showLoadingDialog() {
        showmDialog("数据加载中...");

    }

    @Override
    public void hideLoadingDialog() {
        hidemDialog();
        slLocal.finishRefresh();
    }

    @Override
    public void failed(String error) {
        Toast.makeText(this, "加载错误：" + error, Toast.LENGTH_SHORT).show();
        slLocal.finishRefresh();

    }

    @Override
    public void showLocalUserList(List<UserGson> list) {
        AroundUserAdapter aroundUserAdapter = new AroundUserAdapter(list);
        ryLocalUser.setAdapter(aroundUserAdapter);
        slLocal.finishRefresh();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // TODO: add setContentView(...) invocation

    }

    private class AroundUserAdapter extends BaseQuickAdapter<UserGson, BaseViewHolder> {

        public AroundUserAdapter(@Nullable List<UserGson> data) {
            super(R.layout.ry_localaround_user_item, data);
        }

        @Override
        protected void convert(BaseViewHolder helper, final UserGson item) {

            helper.setText(R.id.tv_username, item.getNickname())
                    .setOnClickListener(R.id.btn_chat, new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            showmDialog("加载中...");
                            RongIM.connect(getSharedPreferences("user", MODE_PRIVATE).getString("token", ""), new RongIMClient.ConnectCallback() {
                                @Override
                                public void onTokenIncorrect() {

                                }

                                @Override
                                public void onSuccess(String s) {
                                    hidemDialog();
                                    RongIM.getInstance().startPrivateChat(AroundUsersActivity.this, item.getUsername(), item.getNickname());
                                }

                                @Override
                                public void onError(RongIMClient.ErrorCode errorCode) {
                                    hidemDialog();
                                    Toast.makeText(mContext, "连接服务器失败，请稍后重试", Toast.LENGTH_SHORT).show();
                                }
                            });

                        }
                    });
            Glide.with(AroundUsersActivity.this).load(item.getAvatar()).into((ImageView) helper.getView(R.id.iv_avatar));
        }
    }
}

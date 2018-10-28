package com.example.xiudd.sese.fragment;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.example.xiudd.sese.R;
import com.example.xiudd.sese.activity.ConversationListActivity;
import com.example.xiudd.sese.activity.LoginActivity;
import com.example.xiudd.sese.activity.SettingActivity;
import com.example.xiudd.sese.activity.UserCollectionActivity;
import com.example.xiudd.sese.activity.UserDownLoadActivity;
import com.example.xiudd.sese.activity.UserPostHistoryActivity;
import com.example.xiudd.sese.activity.UserSignActivity;
import com.example.xiudd.sese.activity.VIPActivity;
import com.example.xiudd.sese.base.BaseFragment;
import com.example.xiudd.sese.constant.Constant;
import com.example.xiudd.sese.contract.UserContract;
import com.example.xiudd.sese.gson.UserGson;
import com.example.xiudd.sese.presenter.UserPresenter;
import com.example.xiudd.sese.util.DataCleanManager;
import com.example.xiudd.sese.view.CircleImageView;

import java.io.File;

import butterknife.ButterKnife;
import butterknife.InjectView;
import butterknife.OnClick;


/**
 * Created by Xuyijie on 2018/10/15.
 */

public class UserFragment extends BaseFragment implements UserContract.View {
    @InjectView(R.id.tv_sign)
    TextView tvSign;
    @InjectView(R.id.tv_username)
    TextView tvUsername;
    @InjectView(R.id.tv_clean)
    TextView tvClean;
    @InjectView(R.id.user_head_iv)
    CircleImageView userHeadIv;
    @InjectView(R.id.tv_vip)
    LinearLayout tvVip;
    @InjectView(R.id.ll_sign)
    LinearLayout llSign;
    @InjectView(R.id.tv_post)
    LinearLayout tvPost;
    @InjectView(R.id.tv_collection)
    LinearLayout tvCollection;
    @InjectView(R.id.ll_clean)
    LinearLayout llClean;
    @InjectView(R.id.tv_msg)
    LinearLayout tvMsg;
    @InjectView(R.id.tv_download)
    LinearLayout tvDownload;
    @InjectView(R.id.tv_fans)
    TextView tvFans;
    @InjectView(R.id.tv_score)
    TextView tvScore;
    @InjectView(R.id.tv_coin)
    TextView tvCoin;
    @InjectView(R.id.tv_observe)
    TextView tvObserve;
    @InjectView(R.id.user_ll)
    RelativeLayout userLl;
    @InjectView(R.id.tv_setting)
    TextView tvSetting;
    @InjectView(R.id.tv_login)
    ImageView tvLogin;
    @InjectView(R.id.iv_rank)
    ImageView ivRank;
    @InjectView(R.id.tv_rank)
    TextView tvRank;
    private UserPresenter userPresenter = new UserPresenter(this);

    @Override
    protected int setView() {
        return R.layout.fragment_user;
    }

    @Override
    protected void init(View view) {
        ButterKnife.inject(this, view);
        initAppCache();
    }

    @Override
    protected void initData(Bundle savedInstanceState) {
        SharedPreferences sp = getActivity().getSharedPreferences("user", Context.MODE_PRIVATE);
        if (!sp.getString("username", "").isEmpty()) {
            tvLogin.setVisibility(View.GONE);
            tvUsername.setText(sp.getString("username", ""));
            tvSign.setText(sp.getString("sign", ""));
            tvObserve.setText(sp.getString("observe", "").isEmpty() ? "0" : sp.getString("observe", ""));
            tvFans.setText(sp.getString("fans", "").isEmpty() ? "0" : sp.getString("fans", ""));
            tvScore.setText(sp.getString("score", "").isEmpty() ? "0" : sp.getString("score", ""));
            tvCoin.setText(sp.getString("coin", "").isEmpty() ? "0" : sp.getString("coin", ""));
            if (sp.getString("vip", "").equals("1")) {
                tvRank.setText("普通会员");
                Glide.with(getContext()).load(R.mipmap.vip).into(ivRank);
            } else if (sp.getString("vip", "").equals("2")) {
                tvRank.setText("白银会员");
                Glide.with(getContext()).load(R.mipmap.cp_rank_third).into(ivRank);
            } else if (sp.getString("vip", "").equals("3")) {
                tvRank.setText("黄金会员");
                Glide.with(getContext()).load(R.mipmap.cp_rank_second).into(ivRank);
            } else if (sp.getString("vip", "").equals("4")) {
                tvRank.setText("超级会员");
                Glide.with(getContext()).load(R.mipmap.cp_rank_first).into(ivRank);
            }
            Glide.with(getActivity()).load(sp.getString("avatar", "")).into(userHeadIv);
        } else {
            tvLogin.setVisibility(View.VISIBLE);
            Glide.with(getActivity()).load(R.mipmap.head_normal).into(userHeadIv);
        }

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // TODO: inflate a fragment view
        View rootView = super.onCreateView(inflater, container, savedInstanceState);

        ButterKnife.inject(this, rootView);
        return rootView;
    }

    @Override
    public void onStart() {
        super.onStart();
        SharedPreferences sp = getActivity().getSharedPreferences("user", Context.MODE_PRIVATE);
        if (!sp.getString("username", "").isEmpty()) {
            tvLogin.setVisibility(View.GONE);
            tvUsername.setText(sp.getString("username", ""));
            tvSign.setText(sp.getString("sign", ""));
            tvObserve.setText(sp.getString("observe", "").isEmpty() ? "0" : sp.getString("observe", ""));
            tvFans.setText(sp.getString("fans", "").isEmpty() ? "0" : sp.getString("fans", ""));
            tvScore.setText(sp.getString("score", "").isEmpty() ? "0" : sp.getString("score", ""));
            tvCoin.setText(sp.getString("coin", "").isEmpty() ? "0" : sp.getString("coin", ""));
            Glide.with(getActivity()).load(sp.getString("avatar", "")).into(userHeadIv);
            if (sp.getString("vip", "").equals("1")) {
                tvRank.setText("普通会员");
                Glide.with(getContext()).load(R.mipmap.vip).into(ivRank);
            } else if (sp.getString("vip", "").equals("2")) {
                Glide.with(getContext()).load(R.mipmap.cp_rank_third).into(ivRank);
                tvRank.setText("白银会员");
            } else if (sp.getString("vip", "").equals("3")) {
                Glide.with(getContext()).load(R.mipmap.cp_rank_second).into(ivRank);
            } else if (sp.getString("vip", "").equals("4")) {
                tvRank.setText("超级会员");
                Glide.with(getContext()).load(R.mipmap.cp_rank_first).into(ivRank);
            }
        } else {
            tvLogin.setVisibility(View.VISIBLE);
            Glide.with(getActivity()).load(R.mipmap.head_normal).into(userHeadIv);
        }
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Log.i(TAG, "onCreate: ");
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        ButterKnife.reset(this);
    }

    @OnClick({R.id.tv_login, R.id.tv_setting, R.id.user_head_iv, R.id.user_ll, R.id.tv_vip, R.id.ll_sign, R.id.tv_post, R.id.tv_collection, R.id.ll_clean, R.id.tv_msg, R.id.tv_download})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.tv_login:
                startActivityForResult(new Intent(getContext(), LoginActivity.class), Constant.USER_FRAGMENT_LOGIN);
                break;
            case R.id.tv_setting:
                startActivity(new Intent(getContext(), SettingActivity.class));
                break;
            case R.id.user_head_iv:
                break;
            case R.id.user_ll:

                break;
            case R.id.tv_vip:
                startActivity(new Intent(getContext(), VIPActivity.class));
                break;
            case R.id.ll_sign:
                startActivity(new Intent(getContext(), UserSignActivity.class));
                break;
            case R.id.tv_post:
                startActivity(new Intent(getContext(), UserPostHistoryActivity.class));
                break;
            case R.id.tv_collection:
                startActivity(new Intent(getContext(), UserCollectionActivity.class));
                break;
            case R.id.ll_clean:
                try {
                    DataCleanManager.cleanApplicationData(getActivity().getApplicationContext());
                    tvClean.setText("清理缓存   0.00 KB");
                    Toast.makeText(mActivity, "清理缓存成功！", Toast.LENGTH_SHORT).show();
                } catch (Exception e) {
                    Log.i(TAG, "onViewClicked: " + e.getMessage());
                }
                break;
            case R.id.tv_msg:
                startActivity(new Intent(getContext(), ConversationListActivity.class));
                break;
            case R.id.tv_download:
                startActivity(new Intent(getContext(), UserDownLoadActivity.class));
                break;
        }
    }

    @Override
    public void onResume() {
        super.onResume();
    }

    private void initAppCache() {
        File file = new File(getActivity().getCacheDir().getPath());
        try {
            tvClean.setText("清理缓存 :  " + DataCleanManager.getCacheSize(file));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private static final String TAG = "UserFragment";

    @Override
    public void onHiddenChanged(boolean hidden) {
        super.onHiddenChanged(hidden);
        if (hidden) {
            Log.i(TAG, "1onHiddenChanged: ");
        } else {
            if (!getActivity().getSharedPreferences("user", Context.MODE_PRIVATE).getString("username", "").isEmpty()) {
                userPresenter.loginUser(getActivity().getSharedPreferences("user", Context.MODE_PRIVATE).getString("username", ""),
                        getActivity().getSharedPreferences("user", Context.MODE_PRIVATE).getString("password", ""));
            }

            Log.i(TAG, "2onHiddenChanged: ");
        }
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        Log.i(TAG, "onActivityResult: " + requestCode + resultCode + data);
        if (resultCode == Constant.USER_FRAGMENT_LOGIN) {
            SharedPreferences sp = getActivity().getSharedPreferences("user", Context.MODE_PRIVATE);
            tvUsername.setText(data.getStringExtra("username"));
            tvSign.setText(data.getStringExtra("sign"));
            tvObserve.setText(data.getStringExtra("observe"));
            tvFans.setText(data.getStringExtra("fans"));
            tvScore.setText(data.getStringExtra("score"));
            tvCoin.setText(data.getStringExtra("coin"));
            if (sp.getString("vip", "").equals("1")) {
                tvRank.setText("普通会员");
                Glide.with(getContext()).load(R.mipmap.vip).into(ivRank);
            } else if (sp.getString("vip", "").equals("2")) {
                tvRank.setText("白银会员");
                Glide.with(getContext()).load(R.mipmap.cp_rank_third).into(ivRank);
            } else if (sp.getString("vip", "").equals("3")) {
                tvRank.setText("黄金会员");
                Glide.with(getContext()).load(R.mipmap.cp_rank_second).into(ivRank);
            } else if (sp.getString("vip", "").equals("4")) {
                tvRank.setText("超级会员");
                Glide.with(getContext()).load(R.mipmap.cp_rank_first).into(ivRank);
            }
            Glide.with(getActivity()).load(data.getStringExtra("avatar")).into(userHeadIv);
        }
    }

    @Override
    public void showLoadingDialog() {

    }

    @Override
    public void hideLoadingDialog() {

    }

    @Override
    public void userLogin(UserGson gsons) {
        tvCoin.setText(gsons.getCoin());
        tvScore.setText(gsons.getScore());
        tvFans.setText(gsons.getFans());
        tvObserve.setText(gsons.getObserve());
        SharedPreferences.Editor editor = getActivity().getSharedPreferences("user", Context.MODE_PRIVATE).edit();
        editor.putString("fans", gsons.getFans());
        editor.putString("score", gsons.getScore());
        editor.putString("coin", gsons.getCoin());
        editor.putString("day", gsons.getDay());
        editor.putString("observe", gsons.getObserve());
        if (gsons.getVip().equals("1")) {
            tvRank.setText("普通会员");
            Glide.with(getContext()).load(R.mipmap.vip).into(ivRank);
        } else if (gsons.getVip().equals("2")) {
            tvRank.setText("白银会员");
            Glide.with(getContext()).load(R.mipmap.cp_rank_third).into(ivRank);
        } else if (gsons.getVip().equals("3")) {
            tvRank.setText("黄金会员");
            Glide.with(getContext()).load(R.mipmap.cp_rank_second).into(ivRank);
        } else if (gsons.getVip().equals("4")) {
            tvRank.setText("超级会员");
            Glide.with(getContext()).load(R.mipmap.cp_rank_first).into(ivRank);
        }
        editor.apply();
    }

    @Override
    public void loginFailed(String msg) {
        Toast.makeText(mActivity, "获取信息出错", Toast.LENGTH_SHORT).show();
    }

    @Override
    public void qqLogin(UserGson gsons) {

    }

    @Override
    public void setSexInfor(UserGson gsons) {

    }

    @Override
    public void upDateUserInforSuccess(UserGson gsons) {

    }

    @Override
    public void setUserInforFailed(UserGson userInforFailed) {

    }
}

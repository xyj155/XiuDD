package com.example.xiudd.sese.fragment;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.widget.NestedScrollView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.example.xiudd.sese.R;
import com.example.xiudd.sese.activity.SystemPushActivity;
import com.example.xiudd.sese.activity.UserContractActivity;
import com.example.xiudd.sese.activity.UserScoreHistoryActivity;
import com.example.xiudd.sese.base.BaseFragment;

import butterknife.ButterKnife;
import butterknife.InjectView;
import butterknife.OnClick;
import io.rong.imkit.RongIM;
import io.rong.imkit.fragment.ConversationListFragment;
import io.rong.imlib.RongIMClient;
import io.rong.imlib.model.Conversation;

/**
 * Created by Xuyijie on 2018/10/20.
 */

public class ConversationFragment extends BaseFragment {
    private static final String TAG = "ConversationFragment";
    @InjectView(R.id.linearLayout)
    RelativeLayout linearLayout;
    @InjectView(R.id.sl_message)
    NestedScrollView slMessage;
    @InjectView(R.id.tv_coin)
    TextView tvCoin;
    @InjectView(R.id.tv_message)
    TextView tvMessage;
    @InjectView(R.id.tv_system)
    TextView tvSystem;
    @InjectView(R.id.iv_contact)
    TextView ivContact;


    @Override
    protected int setView() {
        return R.layout.fragment_conversation;
    }

    @Override
    protected void init(View view) {
        slMessage.scrollTo(0, 0);
        slMessage.scrollBy(0, 0);
        RongIM.connect("80u2tPccBaJlW1OvjfvfZkxF5RIP1B6fPduAE+Gne5KaBpFDZ9X++tO7NGgXKHE4El5pbkEnp0g=", new RongIMClient.ConnectCallback() {
            @Override
            public void onTokenIncorrect() {

            }

            @Override
            public void onSuccess(String s) {
                Log.i(TAG, "onSuccess: " + s);
                /**
                 * 启动单聊
                 * context - 应用上下文。
                 * targetUserId - 要与之聊天的用户 Id。
                 * title - 聊天的标题，如果传入空值，则默认显示与之聊天的用户名称。
                 */
                ConversationListFragment listFragment = (ConversationListFragment) ConversationListFragment.instantiate(getContext(), ConversationListFragment.class.getName());
                Uri uri = Uri.parse("rong://" + getActivity().getApplicationInfo().packageName).buildUpon()
                        .appendPath("conversationlist")
                        .appendQueryParameter(Conversation.ConversationType.PRIVATE.getName(), "false")
                        .appendQueryParameter(Conversation.ConversationType.GROUP.getName(), "false")
                        .appendQueryParameter(Conversation.ConversationType.DISCUSSION.getName(), "false")
                        .appendQueryParameter(Conversation.ConversationType.PUBLIC_SERVICE.getName(), "false")
                        .appendQueryParameter(Conversation.ConversationType.SYSTEM.getName(), "false")
                        .build();
                listFragment.setUri(uri);
                FragmentTransaction transaction = getActivity().getSupportFragmentManager().beginTransaction();
                //将融云的Fragment界面加入到我们的页面。
                transaction.add(R.id.subconversationlist, listFragment);
                transaction.commitAllowingStateLoss();
                //为了更加直观，服务器建立连接后进入此界面，直接调用如下代码，执行单人聊天，第二个参数代表对方用户ID，第三个参数代表聊天窗口标题，为了方便测试聊天，需要两个手机测试，所以登陆第一个token的用户与第二个用户"chao"建立聊天，在运行第二个手机之前，记得改"chao"的token登录，然后聊天这里改为第一个的ID"text"。
//                RongIM.getInstance().startPrivateChat(getContext(), "10086", "聊天中");
//                RongIM.getInstance().startPrivateChat(getContext(), "10286", "聊天中");
//                RongIM.getInstance().startPrivateChat(getContext(), "1036", "聊天中");
//                RongIM.getInstance().startPrivateChat(getContext(), "10486", "聊天中");
//                RongIM.getInstance().startPrivateChat(getContext(), "10386", "聊天中");
//                RongIM.getInstance().startPrivateChat(getContext(), "10586", "聊天中");
//                RongIM.getInstance().startPrivateChat(getContext(), "15086", "聊天中");
//                RongIM.getInstance().startPrivateChat(getContext(), "16086", "聊天中");
//                if (RongIM.getInstance() != null) {
//                    RongIM.getInstance().startPrivateChat(ConversationListActivity.this, "123", "");
//                }
//                RongIM.getInstance().startPrivateChat(ConversationListActivity.this, "9527", "标题");
            }

            @Override
            public void onError(RongIMClient.ErrorCode errorCode) {
                Log.i(TAG, "onError: " + errorCode.getMessage());

            }
        });
    }

    @Override
    protected void initData(Bundle savedInstanceState) {

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // TODO: inflate a fragment view
        View rootView = super.onCreateView(inflater, container, savedInstanceState);
        ButterKnife.inject(this, rootView);
        return rootView;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        ButterKnife.reset(this);
    }

    @OnClick({R.id.tv_coin, R.id.tv_message, R.id.tv_system, R.id.iv_contact})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.tv_coin:
                startActivity(new Intent(getContext(), UserScoreHistoryActivity.class));
                break;
            case R.id.tv_message:
                startActivity(new Intent(getContext(), UserContractActivity.class));
                break;
            case R.id.tv_system:
                startActivity(new Intent(getContext(), SystemPushActivity.class));
                break;
        }
    }
}

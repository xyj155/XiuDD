package com.example.xiudd.sese.activity;

import android.net.Uri;
import android.support.v4.app.FragmentTransaction;
import android.util.Log;

import com.example.xiudd.sese.R;
import com.example.xiudd.sese.base.BaseActivity;

import io.rong.imkit.RongIM;
import io.rong.imkit.fragment.ConversationListFragment;
import io.rong.imlib.RongIMClient;
import io.rong.imlib.model.Conversation;

public class ConversationListActivity extends BaseActivity {

    @Override
    public int intiLayout() {
        return R.layout.activity_conversation_list;
    }

    @Override
    public void initView() {
        initToolBar().setToolbarBackIco().setToolbarTitle("会话列表");
        RongIM.connect(getSharedPreferences("user",MODE_PRIVATE).getString("token",""), new RongIMClient.ConnectCallback() {
            @Override
            public void onTokenIncorrect() {

            }

            @Override
            public void onSuccess(String s) {
                Log.i(TAG, "onSuccess: "+s);
                /**
                 * 启动单聊
                 * context - 应用上下文。
                 * targetUserId - 要与之聊天的用户 Id。
                 * title - 聊天的标题，如果传入空值，则默认显示与之聊天的用户名称。
                 */
                ConversationListFragment listFragment = (ConversationListFragment) ConversationListFragment.instantiate(ConversationListActivity.this, ConversationListFragment.class.getName());
                Uri uri = Uri.parse("rong://" + getApplicationInfo().packageName).buildUpon()
                        .appendPath("conversationlist")
                        .appendQueryParameter(Conversation.ConversationType.PRIVATE.getName(), "false")
                        .appendQueryParameter(Conversation.ConversationType.GROUP.getName(), "false")
                        .appendQueryParameter(Conversation.ConversationType.DISCUSSION.getName(), "false")
                        .appendQueryParameter(Conversation.ConversationType.PUBLIC_SERVICE.getName(), "false")
                        .appendQueryParameter(Conversation.ConversationType.SYSTEM.getName(), "false")
                        .build();
                listFragment.setUri(uri);
                FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
                //将融云的Fragment界面加入到我们的页面。
                transaction.add(R.id.subconversationlist, listFragment);
                transaction.commitAllowingStateLoss();
            }

            @Override
            public void onError(RongIMClient.ErrorCode errorCode) {
                Log.i(TAG, "onError: "+errorCode.getMessage());

            }
        });

    }

    @Override
    public void initData() {

    }
}

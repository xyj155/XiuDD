package com.example.xiudd.sese.adapter;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.support.annotation.Nullable;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.ImageView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.example.xiudd.sese.R;
import com.example.xiudd.sese.activity.PhotoViewActivity;
import com.example.xiudd.sese.activity.PostDetailActivity;
import com.example.xiudd.sese.contract.PostControlContract;
import com.example.xiudd.sese.gson.PostListGson;
import com.example.xiudd.sese.presenter.PostControlPresenter;
import com.example.xiudd.sese.util.GlideRoundTransform;

import java.util.ArrayList;
import java.util.List;

import cn.sharesdk.onekeyshare.OnekeyShare;


/**
 * Created by Xuyijie on 2018/10/16.
 */

public class PostListAdapter extends BaseQuickAdapter<PostListGson, BaseViewHolder> implements PostControlContract.View {
    private Activity context;
    private PostControlPresenter postControlPresenter = new PostControlPresenter(this);

    public PostListAdapter(Activity context, @Nullable List<PostListGson> data) {
        super(R.layout.ry_home_social_item, data);
        this.context = context;
    }

    @Override
    protected void convert(final BaseViewHolder helper, final PostListGson item) {
        Log.i(TAG, "convert: " + item);
        final CheckBox checkBox = (CheckBox) helper.getView(R.id.rb_thumb);
        if (item.getIscollection()) {
            checkBox.setChecked(true);
        } else {
            checkBox.setChecked(false);
        }
        helper.setText(R.id.tv_username, "用户名：" + item.getUser().getUsername())
                .setText(R.id.tv_location, "地址：" + item.getUser().getLocation())
                .setText(R.id.tv_title, item.getTitle())
                .setText(R.id.tv_comment, String.valueOf(item.getComment()))
                .setText(R.id.tv_content, item.getContent())
                .setText(R.id.tv_tag, "# " + item.getKind())
                .setText(R.id.rb_thumb, String.valueOf(item.getThumb()))
                .setOnCheckedChangeListener(R.id.rb_thumb, new CompoundButton.OnCheckedChangeListener() {
                    @Override
                    public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                        if (isChecked) {
                            helper.setText(R.id.rb_thumb, String.valueOf(Integer.valueOf(checkBox.getText().toString()) + 1));
                            postControlPresenter.addCollection(context.getSharedPreferences("user", Context.MODE_PRIVATE).getString("id", ""),
                                    item.getId(), "1");
                        } else {
                            helper.setText(R.id.rb_thumb, String.valueOf(Integer.valueOf(checkBox.getText().toString()) - 1));
                            postControlPresenter.addCollection(
                                    context.getSharedPreferences("user", Context.MODE_PRIVATE).getString("id", ""),
                                    item.getId(), "0");
                        }
                    }
                })
                .setOnClickListener(R.id.ll_item, new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Intent intent = new Intent(context, PostDetailActivity.class);
                        intent.putExtra("title", item.getTitle());
                        intent.putExtra("id", item.getId());
                        context.startActivity(intent);
                        context.overridePendingTransition(R.anim.activity_in, R.anim.activity_out);
                    }
                })
                .setOnClickListener(R.id.tv_share, new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        OnekeyShare oks = new OnekeyShare();
                        oks.disableSSOWhenAuthorize();
                        oks.setTitle("羞涩分享");
                        oks.setText(item.getTitle());
                        oks.setComment(item.getContent());
                        oks.show(context);
                    }
                });
        Log.i(TAG, "convert: sex=" + item.getUser().getSex());
        if (item.getUser().getSex().equals("1")) {
            Glide.with(context).load(R.mipmap.boy).into((ImageView) helper.getView(R.id.iv_sex));
        } else {
            Glide.with(context).load(R.mipmap.girl).into((ImageView) helper.getView(R.id.iv_sex));
        }
        if (item.getPic().size() > 0) {
            PicListAdapter adapter = new PicListAdapter(item.getPic());
            RecyclerView recyclerView = helper.getView(R.id.ry_pic);
            GridLayoutManager gridLayoutManager;
            if (item.getPic().size() < 5) {
                gridLayoutManager = new GridLayoutManager(context, 2);
            } else {
                gridLayoutManager = new GridLayoutManager(context, 3);
            }
            recyclerView.setLayoutManager(gridLayoutManager);
            recyclerView.setAdapter(adapter);
        }
        Glide.with(context).load(item.getUser().getAvatar()).apply(new RequestOptions().error(R.mipmap.user_head)).into((ImageView) helper.getView(R.id.iv_head));

    }

    @Override
    public void addSuccess(String message) {

    }

    @Override
    public void addFailed(String error) {

    }

    private class PicListAdapter extends BaseQuickAdapter<String, BaseViewHolder> {
        private List<String> data;

        public PicListAdapter(@Nullable List<String> data) {
            super(R.layout.home_piclist_item, data);
            this.data = data;
        }

        @Override
        protected void convert(BaseViewHolder helper, String item) {
            helper.setOnClickListener(R.id.iv_pic, new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(context, PhotoViewActivity.class);
                    intent.putStringArrayListExtra("url", (ArrayList<String>) data);
                    context.startActivity(intent);
                }
            });
            Glide.with(context).load(item).apply(new RequestOptions().error(R.drawable.error_pic).transform(new GlideRoundTransform(context, 5))).into((ImageView) helper.getView(R.id.iv_pic));
        }
    }
}

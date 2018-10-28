package com.example.xiudd.sese.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.widget.NestedScrollView;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.example.xiudd.sese.R;
import com.example.xiudd.sese.base.BaseActivity;
import com.example.xiudd.sese.contract.PostDetailContract;
import com.example.xiudd.sese.gson.SinglePostDetailGson;
import com.example.xiudd.sese.presenter.PostDetailPresenter;
import com.example.xiudd.sese.util.GlideRoundTransform;
import com.example.xiudd.sese.view.CircleImageView;

import java.util.ArrayList;
import java.util.List;

import butterknife.ButterKnife;
import butterknife.InjectView;
import butterknife.OnClick;

import static com.tencent.bugly.crashreport.inner.InnerApi.context;

public class PostDetailActivity extends BaseActivity implements PostDetailContract.View, CompoundButton.OnCheckedChangeListener, View.OnClickListener {

    @InjectView(R.id.toolbar)
    Toolbar toolbar;
    @InjectView(R.id.circleImageView2)
    CircleImageView circleImageView2;
    @InjectView(R.id.tv_username)
    TextView tvUsername;
    @InjectView(R.id.tv_sign)
    TextView tvSign;
    @InjectView(R.id.tv_content)
    TextView tvContent;
    @InjectView(R.id.ry_pic)
    RecyclerView ryPic;
    @InjectView(R.id.ry_comment)
    RecyclerView ryComment;
    @InjectView(R.id.et_comment)
    EditText etComment;
    @InjectView(R.id.btn_comment)
    TextView btnComment;
    @InjectView(R.id.tv_title)
    TextView tvTitle;
    @InjectView(R.id.nestedScrollView)
    NestedScrollView nestedScrollView;
    @InjectView(R.id.cb_collection)
    CheckBox cbCollection;
    private PostDetailPresenter postDetailPresenter = new PostDetailPresenter(this);
    private CommentAdapter commentAdapte;

    @Override
    public int intiLayout() {
        return R.layout.activity_post_detail;
    }

    @Override
    public void initView() {
        ButterKnife.inject(this);
        initToolBar().setToolbarBackIco().setToolbarTitle("详情");
        cbCollection.setOnClickListener(this);
        ryPic.setNestedScrollingEnabled(false);
        ryComment.setNestedScrollingEnabled(false);

        ryComment.setLayoutManager(new LinearLayoutManager(PostDetailActivity.this));

    }

    @Override
    public void initData() {
        postDetailPresenter.getSinglePost(getIntent().getStringExtra("id"), getSharedPreferences("user", MODE_PRIVATE).getString("id", ""));
    }

    @Override
    public void showLoadingDialog() {
        showmDialog("数据获取中...");
    }

    @Override
    public void hideLoadingDialog() {
        hidemDialog();
    }

    @Override
    public void loadFailed(String msg) {
        Toast.makeText(this, "获取失败", Toast.LENGTH_SHORT).show();
    }

    @Override
    public void setSinglePost(SinglePostDetailGson singlePost) {
        Glide.with(PostDetailActivity.this).load(singlePost.getUser().getAvatar()).into(circleImageView2);
        tvUsername.setText(singlePost.getUser().getUsername());
        tvSign.setText(singlePost.getUser().getSign());
        tvTitle.setText(singlePost.getTitle());
        tvContent.setText(singlePost.getContent());
        PicListAdapter picAdapter = new PicListAdapter(singlePost.getPic());
        if (singlePost.getPic().size()==1){
            ryPic.setLayoutManager(new LinearLayoutManager(PostDetailActivity.this));
        }else {
            ryPic.setLayoutManager(new GridLayoutManager(PostDetailActivity.this,2));
        }
        ryPic.setAdapter(picAdapter);
        commentAdapte = new CommentAdapter(singlePost.getCommentData());
        ryComment.setAdapter(commentAdapte);
        if (singlePost.isLike()) {
            cbCollection.setChecked(true);
        } else {
            cbCollection.setChecked(false);
        }

    }

    @Override
    public void updateCommentFailed(String msg) {
        Toast.makeText(this, "发表评论失败！", Toast.LENGTH_SHORT).show();
    }

    @Override
    public void setComment() {
        SinglePostDetailGson.CommentDataBean comment = new SinglePostDetailGson.CommentDataBean();
        comment.setComment(etComment.getText().toString());
        comment.setAvatar(getSharedPreferences("user", MODE_PRIVATE).getString("avatar", ""));
        comment.setUsername(getSharedPreferences("user", MODE_PRIVATE).getString("username", ""));
        commentAdapte.addData(comment);
        etComment.setText("");
        etComment.setFocusable(true);
    }

    @Override
    public void addLike() {
        Toast.makeText(this, "收藏成功", Toast.LENGTH_SHORT).show();
    }

    @Override
    public void addLikeFailed(String error) {
        Toast.makeText(this, "收藏失败", Toast.LENGTH_SHORT).show();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // TODO: add setContentView(...) invocation

    }

    @OnClick({R.id.circleImageView2, R.id.btn_comment})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.circleImageView2:
                break;
            case R.id.btn_comment:
                postDetailPresenter.setComment(getIntent().getStringExtra("id"), getSharedPreferences("user", MODE_PRIVATE).getString("id", ""), etComment.getText().toString());
                break;
        }
    }

    @Override
    public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {

    }

    @Override
    public void onClick(View v) {
        if (cbCollection.isChecked()) {
            postDetailPresenter.addLike(getIntent().getStringExtra("id"), getSharedPreferences("user", MODE_PRIVATE).getString("id", ""));
        }
    }

    private class PicListAdapter extends BaseQuickAdapter<String, BaseViewHolder> {
        private List<String> data;

        public PicListAdapter(@Nullable List<String> data) {
            super(R.layout.ry_post_piclist_item, data);
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

    private class CommentAdapter extends BaseQuickAdapter<SinglePostDetailGson.CommentDataBean, BaseViewHolder> {

        public CommentAdapter(@Nullable List<SinglePostDetailGson.CommentDataBean> data) {
            super(R.layout.ry_comment_item, data);
        }

        @Override
        protected void convert(BaseViewHolder helper, SinglePostDetailGson.CommentDataBean item) {
            helper.setText(R.id.tv_username, "用户：" + item.getUsername())
                    .setText(R.id.tv_comment, "评论；" + item.getComment());
            Glide.with(PostDetailActivity.this).load(item.getAvatar()).apply(new RequestOptions().error(R.mipmap.head_normal)).into((ImageView) helper.getView(R.id.iv_head));
        }
    }
}

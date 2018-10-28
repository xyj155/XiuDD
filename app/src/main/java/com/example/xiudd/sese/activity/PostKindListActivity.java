package com.example.xiudd.sese.activity;


import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.CompoundButton;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.example.xiudd.sese.R;
import com.example.xiudd.sese.base.BaseActivity;
import com.example.xiudd.sese.contract.PostKindContract;
import com.example.xiudd.sese.gson.PostListGson;
import com.example.xiudd.sese.presenter.PostKindPresenter;
import com.example.xiudd.sese.util.ToastUtils;

import java.util.ArrayList;
import java.util.List;

import butterknife.ButterKnife;
import butterknife.InjectView;

public class PostKindListActivity extends BaseActivity  {

    @InjectView(R.id.toolbar)
    Toolbar toolbar;
    @InjectView(R.id.ry_kind)
    RecyclerView ryKind;

    @Override
    public int intiLayout() {
        return R.layout.activity_post_kind_list;
    }

    @Override
    public void initView() {
        ButterKnife.inject(this);
        initToolBar().setToolbarBackIco().setToolbarTitle("分类");
    }

    @Override
    public void initData() {
        List<Kind> kinds = new ArrayList<>();
        kinds.add(new Kind("同城互约", R.mipmap.kind_yue));
        kinds.add(new Kind("Less女同", R.mipmap.kind_less));
        kinds.add(new Kind("Gay男男", R.mipmap.kind_gay));
        kinds.add(new Kind("色情美图", R.mipmap.kind_beau));
        kinds.add(new Kind("激情自拍", R.mipmap.kind_jiqi));
        kinds.add(new Kind("爱妻美拍", R.mipmap.kind_wife));
        kinds.add(new Kind("激情写真", R.mipmap.kind_true));
        kinds.add(new Kind("闲聊话题", R.mipmap.kind_topic));
        ClassKindAdapter classKindAdapter = new ClassKindAdapter(kinds);
        ryKind.setLayoutManager(new LinearLayoutManager(PostKindListActivity.this));
        ryKind.setAdapter(classKindAdapter);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // TODO: add setContentView(...) invocation

    }



    private class Kind {
        private String name;
        private int icon;

        public Kind(String name, int icon) {
            this.name = name;
            this.icon = icon;
        }

        public int getIcon() {

            return icon;
        }

        public void setIcon(int icon) {
            this.icon = icon;
        }

        public String getName() {

            return name;
        }

        public void setName(String name) {
            this.name = name;
        }
    }

    private class ClassKindAdapter extends BaseQuickAdapter<Kind, BaseViewHolder> {

        public ClassKindAdapter(@Nullable List<Kind> data) {
            super(R.layout.ry_kind_item, data);
        }

        @Override
        protected void convert(BaseViewHolder helper, final Kind item) {
            helper.setText(R.id.tv_title, item.getName())
                    .setOnClickListener(R.id.ll_hotvideo, new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            Intent intent = new Intent(PostKindListActivity.this, PostKindDetailActivity.class);
                            intent.putExtra("kind", item.getName());
                            startActivity(intent);
                        }
                    });
            Glide.with(PostKindListActivity.this).load(item.getIcon()).into((ImageView) helper.getView(R.id.iv_res));
        }
    }
}

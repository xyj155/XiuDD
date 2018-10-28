package com.example.xiudd.sese.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Toast;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.example.xiudd.sese.R;
import com.example.xiudd.sese.base.BaseActivity;

import java.util.ArrayList;
import java.util.List;

import butterknife.ButterKnife;
import butterknife.InjectView;

public class PostChooseKindActivity extends BaseActivity {


    @InjectView(R.id.toolbar)
    Toolbar toolbar;
    @InjectView(R.id.ry_kind)
    RecyclerView ryKind;

    @Override
    public int intiLayout() {
        return R.layout.activity_post_kind;
    }

    @Override
    public void initView() {
        ButterKnife.inject(this);
        initToolBar().setToolbarTitle("请选择分类");
    }

    @Override
    public void initData() {
        ryKind.setLayoutManager(new LinearLayoutManager(PostChooseKindActivity.this));
        List<String> list = new ArrayList<>();
        list.add("同城互约");
        list.add("Less女同");
        list.add("Gay男男");
        list.add("色情美图");
        list.add("激情自拍");
        list.add("爱妻美拍");
        list.add("激情写真");
        list.add("闲聊话题");
        ClassifyAdapter classifyAdapter = new ClassifyAdapter(list);
        ryKind.setAdapter(classifyAdapter);
    }

    private class ClassifyAdapter extends BaseQuickAdapter<String, BaseViewHolder> {

        public ClassifyAdapter(@Nullable List<String> data) {
            super(R.layout.ry_kind_post_item, data);
        }

        @Override
        protected void convert(final BaseViewHolder helper, final String item) {
            helper.setText(R.id.tv_title, item)
                    .setOnClickListener(R.id.ll_txt, new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            if (helper.getPosition()==0){
                                Toast.makeText(PostChooseKindActivity.this, "此分类仅限黄金会员以上可使用！", Toast.LENGTH_SHORT).show();
                            }else {
                                Intent intent = new Intent(PostChooseKindActivity.this, PostWriteActivity.class);
                                intent.putExtra("kind", item);
                                setResult(12, intent);
                                finish();
                            }

                        }
                    });
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // TODO: add setContentView(...) invocation

    }
}

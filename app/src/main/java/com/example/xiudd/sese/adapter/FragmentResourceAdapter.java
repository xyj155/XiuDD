package com.example.xiudd.sese.adapter;

import android.content.Context;
import android.content.Intent;
import android.view.View;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.example.xiudd.sese.R;
import com.example.xiudd.sese.activity.CartoonActivity;
import com.example.xiudd.sese.activity.ForeignActivity;
import com.example.xiudd.sese.activity.HotVideoActivity;
import com.example.xiudd.sese.activity.NovelActivity;
import com.example.xiudd.sese.activity.OnLineActivity;
import com.example.xiudd.sese.activity.PictureListActivity;
import com.example.xiudd.sese.activity.TeenVideoActivity;
import com.example.xiudd.sese.activity.VIPActivity;
import com.example.xiudd.sese.activity.VoiceTxtActivity;
import com.example.xiudd.sese.entity.FragmentResourceBean;

import java.util.List;

/**
 * Created by Xuyijie on 2018/10/16.
 */

public class FragmentResourceAdapter extends BaseQuickAdapter<FragmentResourceBean, BaseViewHolder> {
    private Context context;

    public FragmentResourceAdapter(Context context, List<FragmentResourceBean> data) {
        super(R.layout.ry_resource_1th_item, data);
        this.context = context;
    }

    @Override
    protected void convert(final BaseViewHolder helper, FragmentResourceBean item) {
        helper.setText(R.id.tv_title, item.getTitle())
                .setOnClickListener(R.id.ll_hotvideo, new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        switch (helper.getPosition()) {
                            case 0:
                                context.startActivity(new Intent(context, VIPActivity.class));//热门视频
                                break;
                            case 1:
                                context.startActivity(new Intent(context, HotVideoActivity.class));//热门视频
                                break;
                            case 2:
                                context.startActivity(new Intent(context, ForeignActivity.class));//外国人
                                break;
                            case 3:
                                context.startActivity(new Intent(context, TeenVideoActivity.class));//未成年
                                break;
                            case 4:
                                context.startActivity(new Intent(context, OnLineActivity.class));//未成年
                                break;
                            case 5:
                                context.startActivity(new Intent(context, CartoonActivity.class));//未成年
                                break;
                            case 6:
                                context.startActivity(new Intent(context, PictureListActivity.class));//美图
                                break;
                            case 7:
                                context.startActivity(new Intent(context, VoiceTxtActivity.class));//小说
                                break;
                            case 8:
                                context.startActivity(new Intent(context, NovelActivity.class));//小说
                                break;

                        }
                    }
                });
        Glide.with(context).load(item.getRes()).into((ImageView) helper.getView(R.id.iv_res));

    }
}

package com.example.xiudd.sese.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.example.xiudd.sese.R;
import com.example.xiudd.sese.activity.PhotoViewActivity;
import com.example.xiudd.sese.base.BaseFragment;
import com.example.xiudd.sese.contract.PictureFragmentContract;
import com.example.xiudd.sese.gson.BeautifulDataBean;
import com.example.xiudd.sese.presenter.PictureFragmentPresenter;
import com.example.xiudd.sese.util.GlideRoundTransform;
import com.scwang.smartrefresh.layout.SmartRefreshLayout;
import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.scwang.smartrefresh.layout.listener.OnLoadMoreListener;
import com.scwang.smartrefresh.layout.listener.OnRefreshListener;

import java.util.ArrayList;
import java.util.List;

import butterknife.ButterKnife;
import butterknife.InjectView;



/**
 * Created by Xuyijie on 2018/10/15.
 */

public class PicturesFragment extends BaseFragment implements PictureFragmentContract.View {
    @InjectView(R.id.ry_pic)
    RecyclerView ryPic;
    @InjectView(R.id.sl_picture)
    SmartRefreshLayout slPicture;
private PictureFragmentPresenter pictureFragmentPresenter;
    private PictureAdapter pictureAdapter=new PictureAdapter(null);
   private List<BeautifulDataBean> piclist=new ArrayList<>();
    private int page=1;
    @Override
    protected int setView() {
        return R.layout.fragment_picture;
    }

    @Override
    protected void init(View view) {
        ButterKnife.inject(this, view);

        ryPic.setLayoutManager(new GridLayoutManager(getActivity(),2));
pictureFragmentPresenter=new PictureFragmentPresenter(this);
        ryPic.setAdapter(pictureAdapter);
        slPicture.autoRefresh();
        slPicture.setOnRefreshListener(new OnRefreshListener() {
            @Override
            public void onRefresh(RefreshLayout refreshLayout) {
                pictureFragmentPresenter.setPiclist("1");
            }
        });
        slPicture.setOnLoadMoreListener(new OnLoadMoreListener() {
            @Override
            public void onLoadMore(RefreshLayout refreshLayout) {
                page++;
                pictureFragmentPresenter.setPiclist(String .valueOf(page));
            }
        });

    }

    @Override
    protected void initData(Bundle savedInstanceState) {

    }


    @Override
    public void onDestroyView() {
        super.onDestroyView();
        ButterKnife.reset(this);
    }

    @Override
    public void showLoadingDialog() {

    }

    @Override
    public void hideLoadingDialog() {

    }



    @Override
    public void loadPicList(List<BeautifulDataBean> postListGsons) {
        pictureAdapter.addData(postListGsons);
        slPicture.finishRefresh();
        slPicture.finishLoadMore();
    }

  public class PictureAdapter extends BaseQuickAdapter<BeautifulDataBean ,BaseViewHolder>{

      public PictureAdapter( @Nullable List<BeautifulDataBean> data) {
          super(R.layout.item_picture_item, data);
      }

      @Override
      protected void convert(BaseViewHolder helper, final BeautifulDataBean item) {
          helper.setOnClickListener(R.id.iv_pic, new View.OnClickListener() {
              @Override
              public void onClick(View v) {
                  Intent intent = new Intent(getContext(), PhotoViewActivity.class);
                  ArrayList<String> list=new ArrayList<String>();
                  list.add(item.getPic_url());
                  intent.putStringArrayListExtra("url",list);
                  startActivity(intent);
              }
          });
          Glide.with(getActivity()).load(item.getPic_url()).apply(new RequestOptions().centerCrop().transform(new GlideRoundTransform(getActivity(), 5))).into((ImageView) helper.getView(R.id.iv_pic));
          Log.i(TAG, "convert:pic= "+item.getPic_url());
      }
  }
}

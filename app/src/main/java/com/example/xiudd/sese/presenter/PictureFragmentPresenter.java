package com.example.xiudd.sese.presenter;

import com.example.xiudd.sese.base.BaseGson;
import com.example.xiudd.sese.base.BaseObserver;
import com.example.xiudd.sese.contract.PictureFragmentContract;
import com.example.xiudd.sese.gson.BeautifulDataBean;
import com.example.xiudd.sese.gson.BeautifulGson;
import com.example.xiudd.sese.model.PictureFragmentModel;

import java.util.List;

import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * Created by 徐易杰 on 2018/10/19.
 */

public class PictureFragmentPresenter implements PictureFragmentContract.Presenter {
    private PictureFragmentContract.View view;
    private PictureFragmentModel novelModel = new PictureFragmentModel();

    public PictureFragmentPresenter(PictureFragmentContract.View view) {
        this.view = view;
    }

    @Override
    public void setPiclist(String page) {
        novelModel.getPiclist(page)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new BaseObserver<BaseGson<BeautifulGson<BeautifulDataBean>>>() {
                    @Override
                    public void onError(String error) {

                    }

                    @Override
                    public void onCompleted() {

                    }

                    @Override
                    public void onNext(BaseGson<BeautifulGson<BeautifulDataBean>> beautifulGsonBaseGson) {
                        List<BeautifulDataBean> data = beautifulGsonBaseGson.getPageData().getData();
                        view.loadPicList(data);
                    }
                });
    }
}

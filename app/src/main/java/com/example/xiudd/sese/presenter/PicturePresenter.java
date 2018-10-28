package com.example.xiudd.sese.presenter;

import com.example.xiudd.sese.base.BaseGson;
import com.example.xiudd.sese.base.BaseObserver;
import com.example.xiudd.sese.contract.PictureContract;
import com.example.xiudd.sese.gson.PictureGson;
import com.example.xiudd.sese.model.PictureModel;
import com.example.xiudd.sese.util.ToastUtils;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * Created by Xuyijie on 2018/10/18.
 */

public class PicturePresenter implements PictureContract.Presenter {
    private PictureContract.View view;
    private PictureModel pictureModel = new PictureModel();

    public PicturePresenter(PictureContract.View view) {
        this.view = view;
    }

    @Override
    public void getPictureList() {
        view.showLoadingDialog();
        pictureModel.getPictureList()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new BaseObserver<BaseGson<PictureGson>>() {
                    @Override
                    public void onError(String error) {
                        view.hideLoadingDialog();
                        ToastUtils.getInstance().showText("请求错误：" + error);
                    }

                    @Override
                    public void onCompleted() {

                    }

                    @Override
                    public void onNext(BaseGson<PictureGson> pictureGsonBaseGson) {
                        view.hideLoadingDialog();
                        if (pictureGsonBaseGson.isSuccess()) {
                            view.setPictureList(pictureGsonBaseGson.getDataList());
                        } else {
                            ToastUtils.getInstance().showText("请求错误：" + pictureGsonBaseGson.getError());
                        }
                    }
                });
    }

    @Override
    public void getPictureDetail(String url) {
        try {
            List<String> list = new ArrayList<>();
            Document document = Jsoup.connect(url).get();
            Elements select = document.select("div.content");
            Elements img = select.select("img");
            for (Element element : img) {
                list.add(element.attr("src"));
            }
            view.getPictureDetail(list);

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}

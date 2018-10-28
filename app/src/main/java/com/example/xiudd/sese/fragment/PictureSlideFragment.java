package com.example.xiudd.sese.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.example.xiudd.sese.R;
import com.example.xiudd.sese.view.photoview.PhotoViewAttacher;


/**
 * Created by Administrator on 2016/1/3.
 */
public class PictureSlideFragment extends Fragment {
    private String url;
    private PhotoViewAttacher mAttacher;
    private ImageView imageView;

    public static PictureSlideFragment newInstance(String url) {
        PictureSlideFragment f = new PictureSlideFragment();

        Bundle args = new Bundle();
        args.putString("url", url);
        f.setArguments(args);
        return f;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        url = getArguments() != null ? getArguments().getString("url") : "http://www.zhagame.com/wp-content/uploads/2016/01/JarvanIV_6.jpg";

    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View v=inflater.inflate(R.layout.fragment_picture_slide,container,false);

        imageView= (ImageView) v.findViewById(R.id.iv_main_pic);
        mAttacher = new PhotoViewAttacher(imageView);
Glide.with(getContext()).load(url).apply(new RequestOptions().fitCenter()).into(imageView);
//        Glide.with(getActivity()).load(url).into(new ImageViewTarget<Drawable>(imageView) {
//            @Override
//            protected void setResource(@Nullable Drawable resource) {
//                mAttacher.update();
//            }
//        });
        return v;
    }



}

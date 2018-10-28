package com.example.xiudd.sese.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.xiudd.sese.R;
import com.example.xiudd.sese.adapter.GridImageAdapter;
import com.example.xiudd.sese.base.BaseActivity;
import com.example.xiudd.sese.base.BaseGson;
import com.example.xiudd.sese.base.EmptyGson;
import com.example.xiudd.sese.contract.EditPostContract;
import com.example.xiudd.sese.presenter.EditPostPresenter;
import com.example.xiudd.sese.util.FullyGridLayoutManager;
import com.luck.picture.lib.PictureSelector;
import com.luck.picture.lib.config.PictureConfig;
import com.luck.picture.lib.entity.LocalMedia;

import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import butterknife.ButterKnife;
import butterknife.InjectView;
import butterknife.OnClick;
import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;

import static com.luck.picture.lib.config.PictureMimeType.ofImage;


public class PostWriteActivity extends BaseActivity implements EditPostContract.View {

    @InjectView(R.id.tv_kind)
    TextView tvKind;
    private List<LocalMedia> selectList = new ArrayList<>();
    @InjectView(R.id.toolbar)
    Toolbar toolbar;
    @InjectView(R.id.et_title)
    EditText etTitle;
    @InjectView(R.id.et_content)
    EditText etContent;
    @InjectView(R.id.btn_submit)
    Button btnSubmit;
    @InjectView(R.id.ry_gallary)
    RecyclerView ryGallary;
    GridImageAdapter gridImageAdapter;
    private EditPostPresenter editPostPresenter;

    @Override
    public int intiLayout() {
        return R.layout.activity_post_write;
    }

    @Override
    public void initView() {
        ButterKnife.inject(this);
        initToolBar().setToolbarBackIco().setToolbarTitle("发布");
        FullyGridLayoutManager manager = new FullyGridLayoutManager(PostWriteActivity.this, 3, GridLayoutManager.VERTICAL, false);
        ryGallary.setLayoutManager(manager);
        gridImageAdapter = new GridImageAdapter(PostWriteActivity.this, new GridImageAdapter.onAddPicClickListener() {
            @Override
            public void onAddPicClick() {
                // 进入相册 以下是例子：不需要的api可以不写
                PictureSelector.create(PostWriteActivity.this)
                        .openGallery(ofImage())// 全部.PictureMimeType.ofAll()、图片.ofImage()、vip_video.ofVideo()、音频.ofAudio()
//                        .theme(themeId)// 主题样式设置 具体参考 values/styles   用法：R.style.picture.white.style
                        .maxSelectNum(9)// 最大图片选择数量
                        .minSelectNum(1)// 最小选择数量
                        .imageSpanCount(4)// 每行显示个数
                        .selectionMode(PictureConfig.MULTIPLE)// 多选 or 单选
                        .previewImage(true)// 是否可预览图片
                        .previewVideo(false)// 是否可预览视频
                        .enablePreviewAudio(false) // 是否可播放音频
                        .isCamera(true)// 是否显示拍照按钮
                        .isZoomAnim(true)// 图片列表点击 缩放效果 默认true
                        //.imageFormat(PictureMimeType.PNG)// 拍照保存图片格式后缀,默认jpeg
                        //.setOutputCameraPath("/CustomPath")// 自定义拍照保存路径
                        .enableCrop(false)// 是否裁剪
                        .compress(true)// 是否压缩
                        .synOrAsy(true)//同步true或异步false 压缩 默认同步
                        //.compressSavePath(getPath())//压缩图片保存地址
                        //.sizeMultiplier(0.5f)// glide 加载图片大小 0~1之间 如设置 .glideOverride()无效
                        .glideOverride(160, 160)// glide 加载宽高，越小图片列表越流畅，但会影响列表图片浏览的清晰度
                        .withAspectRatio(3, 2)// 裁剪比例 如16:9 3:2 3:4 1:1 可自定义
                        .freeStyleCropEnabled(true)// 裁剪框是否可拖拽
                        .showCropGrid(false)// 是否显示裁剪矩形网格 圆形裁剪时建议设为false
                        .openClickSound(true)// 是否开启点击声音
                        .selectionMedia(selectList)// 是否传入已选图片
                        //.isDragFrame(false)// 是否可拖动裁剪框(固定)
//                        .videoMaxSecond(15)
//                        .videoMinSecond(10)
                        //.previewEggs(false)// 预览图片时 是否增强左右滑动图片体验(图片滑动一半即可看到上一张是否选中)
                        //.cropCompressQuality(90)// 裁剪压缩质量 默认100
                        .minimumCompressSize(100)// 小于100kb的图片不压缩
                        //.cropWH()// 裁剪宽高比，设置如果大于图片本身宽高则无效
                        //.rotateEnabled(true) // 裁剪是否可旋转图片
                        //.scaleEnabled(true)// 裁剪是否可放大缩小图片
                        //.videoQuality()// 视频录制质量 0 or 1
                        //.videoSecond()//显示多少秒以内的视频or音频也可适用
                        //.recordVideoSecond()//录制视频秒数 默认60s
                        .forResult(PictureConfig.CHOOSE_REQUEST);//结果回调onActivityResult code
            }
        });
        gridImageAdapter.setList(selectList);
        gridImageAdapter.setSelectMax(9);
        ryGallary.setAdapter(gridImageAdapter);
    }

    @Override
    public void initData() {
        editPostPresenter = new EditPostPresenter(this);


    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // TODO: add setContentView(...) invocation

    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == 12) {
            if (data != null) {
                tvKind.setText(data.getStringExtra("kind"));
            }

        }
        if (resultCode == RESULT_OK) {
            switch (requestCode) {
                case PictureConfig.CHOOSE_REQUEST:
                    // 图片选择结果回调
                    selectList = PictureSelector.obtainMultipleResult(data);
                    // 例如 LocalMedia 里面返回三种path
                    // 1.media.getPath(); 为原图path
                    // 2.media.getCutPath();为裁剪后path，需判断media.isCut();是否为true
                    // 3.media.getCompressPath();为压缩后path，需判断media.isCompressed();是否为true
                    // 如果裁剪并压缩了，已取压缩路径为准，因为是先裁剪后压缩的
                    for (LocalMedia media : selectList) {
                        Log.i("图片-----》", media.getPath());
                    }
                    gridImageAdapter.setList(selectList);
                    gridImageAdapter.notifyDataSetChanged();
                    break;
            }
        }
    }

    @Override
    public void isUploadSuccess(BaseGson<EmptyGson> baseGson) {
        if (baseGson.isSuccess()){
            finish();
            Toast.makeText(this, "提交成功！等待审核", Toast.LENGTH_SHORT).show();
        }else {
            Toast.makeText(this, "提交失败！错误"+baseGson.getError(), Toast.LENGTH_SHORT).show();
        }

    }

    @Override
    public void showLoading(String msg) {
        showmDialog(msg);
    }

    @Override
    public void hideLoading() {
        hidemDialog();
    }

    public RequestBody toRequestBody(String value) {
        RequestBody requestBody = RequestBody.create(MediaType.parse("text/plain"), value);
        return requestBody;
    }

    @OnClick({R.id.btn_submit, R.id.tv_kind})
    public void onViewClicked(View v) {
        switch (v.getId()) {
            case R.id.btn_submit:
                if (etTitle.getText().toString().isEmpty()) {
                    Toast.makeText(this, "请输入标题", Toast.LENGTH_SHORT).show();
                } else if (etContent.getText().toString().isEmpty()) {
                    Toast.makeText(this, "请输入内容", Toast.LENGTH_SHORT).show();
                } else if (tvKind.getText().toString() .equals("请选择发布类别") ) {
                    Toast.makeText(this, "请选择分类", Toast.LENGTH_SHORT).show();
                } else {
                    final Map<String, RequestBody> partMap = new HashMap<>();
                    partMap.put("uid", toRequestBody("1"));
                    partMap.put("location", toRequestBody("嘉兴学院"));
                    partMap.put("title", toRequestBody(etTitle.getText().toString()));
                    partMap.put("content", toRequestBody(etContent.getText().toString()));
                    partMap.put("kind", toRequestBody(tvKind.getText().toString()));
                    final List<MultipartBody.Part> list = new ArrayList<>();
                    for (LocalMedia media : selectList) {
                        File file = new File(media.getPath());
                        RequestBody requestBody = RequestBody.create(MediaType.parse("image/*"), file);
                        MultipartBody.Part formData = MultipartBody.Part.createFormData("image[]", file.getName(), requestBody);
                        list.add(formData);
                    }
                    editPostPresenter.uploadPost(partMap, list);
                }

                break;
            case R.id.tv_kind:
                startActivityForResult(new Intent(PostWriteActivity.this, PostChooseKindActivity.class), 12);
                break;
        }
    }


}

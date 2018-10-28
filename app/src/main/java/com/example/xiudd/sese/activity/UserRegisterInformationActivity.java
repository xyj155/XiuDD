package com.example.xiudd.sese.activity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

import com.avos.avoscloud.AVException;
import com.avos.avoscloud.AVFile;
import com.avos.avoscloud.AVUser;
import com.avos.avoscloud.SaveCallback;
import com.bumptech.glide.Glide;
import com.example.xiudd.sese.R;
import com.example.xiudd.sese.base.BaseActivity;
import com.example.xiudd.sese.contract.LoginContract;
import com.example.xiudd.sese.gson.UserGson;
import com.example.xiudd.sese.presenter.LoginPresenter;
import com.example.xiudd.sese.view.CircleImageView;
import com.luck.picture.lib.PictureSelector;
import com.luck.picture.lib.config.PictureConfig;
import com.luck.picture.lib.config.PictureMimeType;
import com.luck.picture.lib.entity.LocalMedia;

import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;

import butterknife.ButterKnife;
import butterknife.InjectView;
import butterknife.OnClick;

import static com.luck.picture.lib.config.PictureMimeType.ofImage;


public class UserRegisterInformationActivity extends BaseActivity implements LoginContract.View {


    @InjectView(R.id.toolbar)
    Toolbar toolbar;
    @InjectView(R.id.iv_head)
    CircleImageView ivHead;
    @InjectView(R.id.et_username)
    EditText etUsername;
    @InjectView(R.id.et_password)
    EditText etPassword;
    @InjectView(R.id.et_sign)
    EditText etSign;
    @InjectView(R.id.rb_boy)
    RadioButton rbBoy;
    @InjectView(R.id.rb_girl)
    RadioButton rbGirl;
    @InjectView(R.id.rg_sex)
    RadioGroup rgSex;
    @InjectView(R.id.btn_login)
    Button btnLogin;
    List<LocalMedia> selectList = new ArrayList<>();
    private LoginPresenter loginPresent = new LoginPresenter(this);

    @Override
    public int intiLayout() {
        return R.layout.activity_user_register_information;
    }

    @Override
    public void initView() {
        ButterKnife.inject(this);
        initToolBar().setToolbarBackIco().setToolbarTitle("完善信息");
    }

    @Override
    public void initData() {

    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // TODO: add setContentView(...) invocation

    }

    @OnClick({R.id.iv_head, R.id.btn_login})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.iv_head:
                PictureSelector.create(UserRegisterInformationActivity.this)
                        .openGallery(ofImage())// 全部.PictureMimeType.ofAll()、图片.ofImage()、vip_video.ofVideo()、音频.ofAudio()
//                        .theme(themeId)// 主题样式设置 具体参考 values/styles   用法：R.style.picture.white.style
                        .maxSelectNum(1)// 最大图片选择数量
                        .minSelectNum(1)// 最小选择数量
                        .imageSpanCount(4)// 每行显示个数
                        .selectionMode(PictureConfig.MULTIPLE)// 多选 or 单选
                        .previewImage(true)// 是否可预览图片
                        .previewVideo(false)// 是否可预览视频
                        .enablePreviewAudio(false) // 是否可播放音频
                        .isCamera(true)// 是否显示拍照按钮
                        .isZoomAnim(true)// 图片列表点击 缩放效果 默认true
                        .imageFormat(PictureMimeType.PNG)
                        .setOutputCameraPath("/羞涩")// 自定义拍照保存路径
                        .enableCrop(false)// 是否裁剪
                        .compress(true)// 是否压缩
                        .synOrAsy(true)//同步true或异步false 压缩 默认同步
                        .glideOverride(160, 160)// glide 加载宽高，越小图片列表越流畅，但会影响列表图片浏览的清晰度
                        .withAspectRatio(3, 2)// 裁剪比例 如16:9 3:2 3:4 1:1 可自定义
                        .freeStyleCropEnabled(true)// 裁剪框是否可拖拽
                        .showCropGrid(false)// 是否显示裁剪矩形网格 圆形裁剪时建议设为false
                        .openClickSound(true)// 是否开启点击声音
                        .selectionMedia(selectList)// 是否传入已选图片
                        .minimumCompressSize(100)// 小于100kb的图片不压缩
                        .forResult(PictureConfig.CHOOSE_REQUEST);//结果回调onActivityResult code
                break;
            case R.id.btn_login:
                if (etUsername.getText().toString().isEmpty()) {
                    Toast.makeText(this, "请输入用户名", Toast.LENGTH_SHORT).show();
                } else if (etPassword.getText().toString().isEmpty()) {
                    Toast.makeText(this, "请输入密码", Toast.LENGTH_SHORT).show();
                } else if (!rbBoy.isChecked() && !rbGirl.isChecked()) {
                    Toast.makeText(this, "请选择性别", Toast.LENGTH_SHORT).show();
                } else if (selectList.size() == 0) {
                    Toast.makeText(this, "请选择头像", Toast.LENGTH_SHORT).show();
                } else {
                    try {
                        final AVFile file = AVFile.withAbsoluteLocalPath("head.png", selectList.get(0).getCompressPath());
                        file.saveInBackground(new SaveCallback() {
                            @Override
                            public void done(AVException e) {
                                if (e == null) {
                                    AVUser.getCurrentUser().put("nickname", etUsername.getText().toString());
                                    AVUser.getCurrentUser().put("password", etPassword.getText().toString());
                                    AVUser.getCurrentUser().put("avatar", file.getUrl());
                                    AVUser.getCurrentUser().put("sign", etSign.getText().toString().isEmpty() ? "这个人很懒，什么都没有写" : etSign.getText().toString());
                                    AVUser.getCurrentUser().put("sex", rbBoy.isChecked() ? "男" : "女");
                                    AVUser.getCurrentUser().saveInBackground();
                                    SharedPreferences.Editor editor = getSharedPreferences("user", MODE_PRIVATE).edit();
                                    editor.putBoolean("login", true);
                                    editor.putString("username", etUsername.getText().toString());
                                    editor.putString("avatar", file.getUrl());
                                    editor.putString("sign", etSign.getText().toString().isEmpty() ? "这个用户很懒，什么都没有写！" : etSign.getText().toString());
                                    editor.putString("vip", "0");
                                    editor.putString("fans", "0");
                                    editor.putString("score", "0");
                                    editor.putString("coin", "0");
                                    editor.putString("observe", "0");
                                    editor.apply();
                                    loginPresent.registerWithUser(
                                            etUsername.getText().toString(),
                                            etPassword.getText().toString(),
                                            getSharedPreferences("user", MODE_PRIVATE).getString("tel", ""),
                                            rbBoy.isChecked() ? "男" : "女",
                                            file.getUrl(),
                                            etSign.getText().toString().isEmpty() ? "这个用户很懒，什么都没有写！" : etSign.getText().toString(),
                                            getSharedPreferences("user", Context.MODE_PRIVATE).getString("location", ""));
                                } else {
                                    Toast.makeText(UserRegisterInformationActivity.this, "注册失败" + e.getMessage(), Toast.LENGTH_SHORT).show();
                                }
                            }
                        });
                    } catch (FileNotFoundException e) {
                        e.printStackTrace();
                    }
                }
                break;
        }

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == RESULT_OK) {
            switch (requestCode) {
                case PictureConfig.CHOOSE_REQUEST:
                    selectList = PictureSelector.obtainMultipleResult(data);
                    Glide.with(UserRegisterInformationActivity.this).load(selectList.get(0).getCompressPath()).into(ivHead);
                    break;
            }
        }
    }

    @Override
    public void showLoadingDialog() {
        showmDialog("注册中");
    }

    @Override
    public void hideLoadingDialog() {
        hidemDialog();
    }

    @Override
    public void UserRegister(UserGson emptyGsonBaseGson) {
        SharedPreferences.Editor editor = getSharedPreferences("user", MODE_PRIVATE).edit();
        editor.putBoolean("login", true);
        editor.putString("username", emptyGsonBaseGson.getNickname());
        editor.putString("avatar", emptyGsonBaseGson.getAvatar());
        editor.putString("sign", etSign.getText().toString().isEmpty() ? "这个用户很懒，什么都没有写！" : etSign.getText().toString());
        editor.putString("vip", "0");
        editor.putString("fans", "0");
        editor.putString("score", "0");
        editor.putString("coin", "0");
        editor.putString("observe", "0");
        editor.putString("token",emptyGsonBaseGson.getToken());
        editor.apply();
        AVUser currentUser = AVUser.getCurrentUser();
        currentUser.put("location", getSharedPreferences("user", MODE_PRIVATE).getString("location", ""));
        currentUser.put("token", emptyGsonBaseGson.getToken());
        currentUser.put("password", etPassword.getText().toString());
        currentUser.saveInBackground(new SaveCallback() {
            @Override
            public void done(AVException e) {
                if (e == null) {
                    startActivity(new Intent(UserRegisterInformationActivity.this, MainActivity.class));
                    TelRegisterPhoneActivity.telActivity.finish();
                    LoginActivity.loginActivity.finish();
                    finish();
                } else {
                    Toast.makeText(UserRegisterInformationActivity.this, "提交信息失败！" + e.getMessage(), Toast.LENGTH_SHORT).show();
                }
            }
        });


    }

    @Override
    public void registerFailed(String error) {
        Toast.makeText(this, "注册失败！" + error, Toast.LENGTH_SHORT).show();
    }
}

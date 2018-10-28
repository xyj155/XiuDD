package com.example.xiudd.sese.view;

import android.app.Dialog;
import android.content.Context;
import android.view.View;
import android.widget.TextView;

import com.example.xiudd.sese.R;

import butterknife.ButterKnife;
import butterknife.InjectView;


/**
 * Created by 林 on 2017/5/2.
 */

public class AppDialog extends Dialog {


    @InjectView(R.id.tv_title)
    TextView mTvTitle;
    @InjectView(R.id.tv_message)
    TextView mTvMessage;
    @InjectView(R.id.tv_negativeButton)
    TextView mTvNegativeButton;
    @InjectView(R.id.tv_positiveButton)
    TextView mTvPositiveButton;
    private Context mContext;

    //在构造方法里预加载我们的样式，这样就不用每次创建都指定样式了
    public AppDialog(Context context) {
        this(context, R.style.MyDialog);

    }

    public AppDialog(Context context, int themeResId) {
        super(context, themeResId);

        mContext = context;
        setContentView(R.layout.apple_dialog);
        ButterKnife.inject(this);
    }


    /**
     * 设置标题栏
     */
    public AppDialog setTitle(String title) {
        if (title != null) {
            mTvTitle.setText(title);
        }
        return this;
    }

    /**
     * 设置内容
     */
    public AppDialog setMessage(String msg) {
        if (msg != null) {
            mTvMessage.setText(msg);
            mTvMessage.setVisibility(View.VISIBLE);
        }
        return this;
    }

    /**
     * 设置内容,当内容比较多时，需要设置gravity为Gravity.START属性
     */
    public AppDialog setMessage(String msg, int gravity) {
        if (msg != null) {
            mTvMessage.setText(msg);
            mTvMessage.setGravity(gravity);
            mTvMessage.setVisibility(View.VISIBLE);
        }
        return this;
    }

    /**
     * 设置右边确定点击按钮
     *
     * @param text     按钮上的显示字
     * @param listener 点击事件监听
     */
    public AppDialog setPositiveButton(final String text, final OnMyDialogButtonClickListener listener) {
        if (text != null) {
            mTvPositiveButton.setText(text);
        }
        mTvPositiveButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (listener != null) {
                    listener.onClick();
                }
                dismiss();
            }
        });
        return this;
    }

    public interface OnMyDialogButtonClickListener {
        void onClick();
    }


    /**
     * 设置右边确定点击按钮
     * 默认 点击 字体 为 确定
     *
     * @param listener 点击事件监听
     */
    public AppDialog setPositiveButton(final OnMyDialogButtonClickListener listener) {

        return setPositiveButton(null, listener);
    }

    /**
     * 设置左边取消点击按钮
     *
     * @param text     按钮上的显示字
     * @param listener 点击事件监听
     */
    public AppDialog setNegativeButton(String text, final OnMyDialogButtonClickListener listener) {
        if (text != null) {
            mTvNegativeButton.setText(text);
        }
        mTvNegativeButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (listener != null) {
                    listener.onClick();
                }
                dismiss();
            }
        });
        return this;
    }

    /**
     * 设置右边取消点击按钮
     * 默认 点击 字体 为 取消
     *
     * @param listener 点击事件监听
     */
    public AppDialog setNegativeButton(final OnMyDialogButtonClickListener listener) {
        return setNegativeButton(null, listener);
    }

    /**
     * 设置是否可以取消dialog，由于直接使用setCancelable返回的是Dialog，所以自定义方法
     */
    public AppDialog setDialogCancelable(boolean flag) {
        setCancelable(flag);
        return this;
    }


}

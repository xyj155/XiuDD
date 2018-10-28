package com.example.xiudd.sese.activity;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.Toast;

import com.example.xiudd.sese.R;
import com.example.xiudd.sese.base.BaseActivity;
import com.example.xiudd.sese.contract.VipContract;
import com.example.xiudd.sese.presenter.VipPresenter;
import com.payelves.sdk.EPay;
import com.payelves.sdk.enums.EPayResult;
import com.payelves.sdk.listener.PayResultListener;

import butterknife.ButterKnife;
import butterknife.InjectView;
import butterknife.OnClick;

public class RechargeActivity extends BaseActivity implements CompoundButton.OnCheckedChangeListener, VipContract.View {


    @InjectView(R.id.toolbar)
    Toolbar toolbar;
    @InjectView(R.id.cb_sv)
    CheckBox cbSv;
    @InjectView(R.id.cb_pv)
    CheckBox cbPv;
    @InjectView(R.id.cb_wv)
    CheckBox cbWv;
    @InjectView(R.id.cb_gv)
    CheckBox cbGv;
    @InjectView(R.id.btn_pay)
    Button btnPay;
    private int payCount = 88 * 100;
    private String goodsName = "超级会员";
    private VipPresenter vipPresenter = new VipPresenter(this);
    private String rank = "4";

    @Override
    public int intiLayout() {
        return R.layout.activity_recharge;
    }

    @Override
    public void initView() {
        ButterKnife.inject(this);
        initToolBar().setToolbarBackIco().setToolbarTitle("会员充值");
        cbSv.setOnCheckedChangeListener(this);
        cbGv.setOnCheckedChangeListener(this);
        cbWv.setOnCheckedChangeListener(this);
        cbPv.setOnCheckedChangeListener(this);

    }

    @Override
    public void initData() {

    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // TODO: add setContentView(...) invocation

    }

    @OnClick(R.id.btn_pay)
    public void onViewClicked() {
        if (payCount != 0) {
            EPay.getInstance(this).pay(goodsName, "羞涩" + goodsName + "VIP", payCount,
                    "5822", "1", "", new PayResultListener() {
                        /**
                         * @param context
                         * @param payId   支付精灵支付id
                         * @param orderId   商户系统订单id
                         * @param payUserId 商户系统用户ID
                         * @param payResult
                         * @param payType   支付类型:1 支付宝，2 微信 3 银联
                         * @param amount    支付金额
                         * @see EPayResult#FAIL_CODE
                         * @see EPayResult#SUCCESS_CODE
                         * 1支付成功，2支付失败
                         */
                  @Override
                        public void onFinish(Context context, Long payId, String orderId, String payUserId,
                                             EPayResult payResult, int payType, Integer amount) {
                            EPay.getInstance(context).closePayView();//关闭快捷支付页面
                            if (payResult.getCode() == EPayResult.SUCCESS_CODE.getCode()) {
                                //支付成功逻辑处理
                                Log.i(TAG, "onFinish: " + getSharedPreferences("user", MODE_PRIVATE).getString("id", "")
                                        + "========" + String.valueOf(rank));
                                vipPresenter.vipCharge(
                                        getSharedPreferences("user", MODE_PRIVATE).getString("id", ""), rank);
                            } else if (payResult.getCode() == EPayResult.FAIL_CODE.getCode()) {
                                //支付失败逻辑处理
                                Toast.makeText(RechargeActivity.this, payResult.getMsg(), Toast.LENGTH_LONG).show();
                            }
                        }
                    });
        }

    }

    @Override
    public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
        switch (buttonView.getId()) {
            case R.id.cb_sv:
                if (cbSv.isChecked()) {
                    cbGv.setChecked(false);
                    cbWv.setChecked(false);
                    cbPv.setChecked(false);
                    btnPay.setText("支付88元");
                    payCount = 88 * 100;
                    goodsName = "超级会员";
                    rank = "4";
                }
                break;
            case R.id.cb_pv:
                if (cbPv.isChecked()) {
                    cbSv.setChecked(false);
                    cbWv.setChecked(false);
                    cbGv.setChecked(false);
                    btnPay.setText("支付60元");
                    payCount = 60 * 100;
                    goodsName = "黄金会员";
                    rank = "3";
                }
                break;
            case R.id.cb_wv:
                if (cbWv.isChecked()) {
                    cbGv.setChecked(false);
                    cbSv.setChecked(false);
                    cbPv.setChecked(false);
                    btnPay.setText("支付40元");
                    payCount = 40 * 100;
                    rank = "2";
                    goodsName = "白银会员";
                }
                break;
            case R.id.cb_gv:
                if (cbGv.isChecked()) {
                    cbSv.setChecked(false);
                    cbWv.setChecked(false);
                    cbPv.setChecked(false);
                    rank = "1";
                    btnPay.setText("支付20元");
                    payCount = 20 * 100;
                    goodsName = "普通会员";
                }
                break;
        }
    }

    @Override
    public void showLoadingDialog() {
        showmDialog("回调中...");
    }

    @Override
    public void hideLoadingDialog() {
        hidemDialog();
    }

    @Override
    public void chargeSuccess() {
        Toast.makeText(this, "支付成功", Toast.LENGTH_SHORT).show();
        SharedPreferences.Editor editor = getSharedPreferences("user", MODE_PRIVATE).edit();
        editor.putString("vip", rank);
        editor.apply();
    }

    @Override
    public void chargeFailed(String error) {
        Toast.makeText(this, "支付失败" + error, Toast.LENGTH_SHORT).show();
    }
//
//    @OnClick({R.id.btn_alipay, R.id.btn_wxpay})
//    public void onViewClicked(View view) {
//        switch (view.getId()) {
//            case R.id.btn_alipay:
//                final CenterDialog centerDialog = new CenterDialog(RechargeActivity.this, R.layout.alipayrecharge_dialog_layout, new int[]{R.id.iv_money,R.id.dialog_cancel, R.id.dialog_sure});
//                final View layoutImage = centerDialog.getLayoutImage();
//                centerDialog.setOnCenterItemClickListener(new CenterDialog.OnCenterItemClickListener() {
//                    @Override
//                    public void OnCenterItemClick(CenterDialog dialog, View view) {
//                        switch (view.getId()) {
//                            case R.id.dialog_cancel:
//                                centerDialog.dismiss();
//                                break;
//                        }
//                    }
//
//                    @Override
//                    public void LongItemClickListener(CenterDialog dialog, View view) {
//                        FileUtils.saveBitmap((ImageView) view.findViewById(R.id.iv_money), Environment.getExternalStorageDirectory() + "/羞涩/支付宝二维码.png");
//                        Toast.makeText(RechargeActivity.this, "二维码保存在文件夹羞涩目录下，，请使用支付宝扫码充值！", Toast.LENGTH_SHORT).show();
//                    }
//                });
//                centerDialog.show();
//                break;
//            case R.id.btn_wxpay:
//                final CenterDialog wxDialog = new CenterDialog(RechargeActivity.this, R.layout.wxpay_recharge_dialog_layout, new int[]{R.id.iv_money,R.id.dialog_cancel, R.id.dialog_sure});
//
//                wxDialog.setOnCenterItemClickListener(new CenterDialog.OnCenterItemClickListener() {
//                    @Override
//                    public void OnCenterItemClick(CenterDialog dialog, View view) {
//                        switch (view.getId()) {
//                            case R.id.dialog_cancel:
//                                wxDialog.dismiss();
//                                break;
//                        }
//                    }
//
//                    @Override
//                    public void LongItemClickListener(CenterDialog dialog, View view) {
//                        FileUtils.saveBitmap((ImageView) view.findViewById(R.id.iv_money), Environment.getExternalStorageDirectory() + "/羞涩/微信二维码.png");
//                        Toast.makeText(RechargeActivity.this, "二维码保存在文件夹羞涩目录下，请使用微信扫码充值！", Toast.LENGTH_SHORT).show();
//
//                    }
//                });
//                wxDialog.show();
//                break;
//        }
//    }
}

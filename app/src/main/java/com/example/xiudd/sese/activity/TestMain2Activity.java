package com.example.xiudd.sese.activity;

import android.content.Context;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.Button;
import android.widget.Toast;

import com.example.xiudd.sese.R;
import com.huawei.android.hms.agent.common.BaseApiAgent;
import com.payelves.sdk.EPay;
import com.payelves.sdk.enums.EPayResult;
import com.payelves.sdk.listener.PayResultListener;

import butterknife.ButterKnife;
import butterknife.InjectView;
import butterknife.OnClick;

public class TestMain2Activity extends AppCompatActivity {
    @InjectView(R.id.btn_alipay)
    Button btnAlipay;

//    @InjectView(R.id.btn_pay)
//    Button btnPay;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_test_main2);
        ButterKnife.inject(this);
    }

    @OnClick(R.id.btn_alipay)
    public void onViewClicked() {
        EPay.getInstance(this).pay("黄金VIP", "羞涩黄金会员", 5000,
                "5822", "1","", new PayResultListener() {
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
                                         EPayResult payResult , int payType, Integer amount) {
                        EPay.getInstance(context).closePayView();//关闭快捷支付页面
                        if(payResult.getCode() == EPayResult.SUCCESS_CODE.getCode()){
                            //支付成功逻辑处理
                            Toast.makeText(TestMain2Activity.this, payResult.getMsg(), Toast.LENGTH_LONG).show();
                        }else if(payResult.getCode() == EPayResult.FAIL_CODE.getCode()){
                            //支付失败逻辑处理
                            Toast.makeText(TestMain2Activity.this, payResult.getMsg(), Toast.LENGTH_LONG).show();
                        }
                    }
                });
    }

//    @OnClick(R.id.btn_pay)
//    public void onViewClicked() {
//    }
}

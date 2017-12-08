package com.bjxiyang.shuzianfang.myapplication.activity;

import android.os.Bundle;
import android.view.View;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.baisi.myapplication.okhttp.listener.DisposeDataListener;
import com.bjxiyang.shuzianfang.R;
import com.bjxiyang.shuzianfang.myapplication.manager.SPManager;
import com.bjxiyang.shuzianfang.myapplication.model.JiaoFeiLiShiXiangQing;
import com.bjxiyang.shuzianfang.myapplication.response_xy.XY_Response;
import com.bjxiyang.shuzianfang.myapplication.update.network.RequestCenter;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by gll on 2017/10/23.
 */

public class JiaoFeiLiShiXiangQingActivity extends MySwipeBackActivity {

    @BindView(R.id.tv_lishijiaofei_dizhi)
    TextView tv_lishijiaofei_dizhi;
    @BindView(R.id.tv_lishijiaofei_money)
    TextView tv_lishijiaofei_money;
    @BindView(R.id.tv_lishijiaofei_dingdanhao)
    TextView tv_lishijiaofei_dingdanhao;
    @BindView(R.id.tv_lishijiaofei_jiaofeishijian)
    TextView tv_lishijiaofei_jiaofeishijian;
    @BindView(R.id.tv_lishijiaofei_zhifufangshi)
    TextView tv_lishijiaofei_zhifufangshi;
    @BindView(R.id.tv_huoqujifen)
    TextView tv_huoqujifen;
    @BindView(R.id.tv_lishijiaofei_lishigongsiname)
    TextView tv_lishijiaofei_lishigongsiname;
    @BindView(R.id.iv_lishijiaofei_fanhui)
    RelativeLayout iv_lishijiaofei_fanhui;

    private int propertyId;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lishijiaofei_xiangqing);
        ButterKnife.bind(this);
        iv_lishijiaofei_fanhui.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
        propertyId = getIntent().getIntExtra("propertyId",0);
        getData();

    }
    private void setData(JiaoFeiLiShiXiangQing.ObjBean objBean){
        tv_lishijiaofei_dizhi.setText(objBean.getCommunityName()
                +"\t\t" +objBean.getNperName()
                +"\t\t"+objBean.getFloorName()
                +"\t\t"+objBean.getUnitName()
                +"\t\t"+objBean.getDoorName());
        tv_lishijiaofei_money.setText(objBean.getFee()+"");
        tv_lishijiaofei_dingdanhao.setText(objBean.getOrderNo());
        tv_lishijiaofei_jiaofeishijian.setText(objBean.getPaymentTime());

        if (objBean.getPaymentType()==0) {
            tv_lishijiaofei_zhifufangshi.setText("微信");
        }  else {
            tv_lishijiaofei_zhifufangshi.setText("支付宝");
        }
        tv_lishijiaofei_lishigongsiname.setText(objBean.getPropertyName());


    }

    private void getData(){
        String url= XY_Response.URL_GETPAYDETAIL
                +"cmemberId="+ SPManager.getInstance().getString("c_memberId","0")
                +"&propertyId="+propertyId;
        RequestCenter.all(url, JiaoFeiLiShiXiangQing.class, new DisposeDataListener() {
            @Override
            public void onSuccess(Object responseObj) {
                JiaoFeiLiShiXiangQing jiaoFeiLiShiXiangQing= (JiaoFeiLiShiXiangQing) responseObj;
                if (jiaoFeiLiShiXiangQing.getCode()==1000){
                    setData(jiaoFeiLiShiXiangQing.getObj());
                }

            }

            @Override
            public void onFailure(Object reasonObj) {

            }
        });


    }

}

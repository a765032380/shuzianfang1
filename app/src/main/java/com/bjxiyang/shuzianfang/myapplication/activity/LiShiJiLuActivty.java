package com.bjxiyang.shuzianfang.myapplication.activity;

import android.os.Bundle;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;

import com.bjxiyang.shuzianfang.R;
import com.bjxiyang.shuzianfang.myapplication.ui.activity.MySwipeBackActivity;
import com.bjxiyang.shuzianfang.myapplication.until.MyUntil;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by Administrator on 2017/10/18 0018.
 */

public class LiShiJiLuActivty extends MySwipeBackActivity implements View.OnClickListener {
    @BindView(R.id.ib_lishijilu_fanghui)
    RelativeLayout ib_lishijilu_fanghui;
    @BindView(R.id.ll_wuyejiaofeijilu)
    LinearLayout ll_wuyejiaofeijilu;
    @BindView(R.id.ll_weixiushenqing)
    LinearLayout ll_weixiushenqing;
    @BindView(R.id.ll_tousujianyi)
    LinearLayout ll_tousujianyi;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lishijilu1);
        ButterKnife.bind(this);
        initUI();
    }

    private void initUI() {
        ib_lishijilu_fanghui.setOnClickListener(this);
        ll_wuyejiaofeijilu.setOnClickListener(this);
        ll_weixiushenqing.setOnClickListener(this);
        ll_tousujianyi.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            //返回
            case R.id.ib_lishijilu_fanghui:
                finish();
                break;
            //物业缴费记录
            case R.id.ll_wuyejiaofeijilu:
                MyUntil.mStartActivity(LiShiJiLuActivty.this,JiaoFeiLiShiActivity.class);
                break;
            //维修申请记录
            case R.id.ll_weixiushenqing:
                MyUntil.mStartActivity(LiShiJiLuActivty.this,BaoXiuLiShiActivity.class);
                break;
            //投诉建议记录
            case R.id.ll_tousujianyi:
                MyUntil.mStartActivity(LiShiJiLuActivty.this,FanKuiLiShiActivtiy.class);

                break;
        }
    }
}

package com.bjxiyang.shuzianfang.myapplication.activity;

import android.os.Bundle;
import android.support.v4.widget.SwipeRefreshLayout;
import android.view.View;
import android.widget.ListView;
import android.widget.RelativeLayout;

import com.baisi.myapplication.okhttp.listener.DisposeDataListener;
import com.bjxiyang.shuzianfang.R;
import com.bjxiyang.shuzianfang.myapplication.adapter.JiLuAdapter;
import com.bjxiyang.shuzianfang.myapplication.manager.SPManager;
import com.bjxiyang.shuzianfang.myapplication.model.BaoXiuLiShi;
import com.bjxiyang.shuzianfang.myapplication.response_xy.XY_Response;
import com.bjxiyang.shuzianfang.myapplication.update.network.RequestCenter;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by gll on 2017/10/20 0020.
 *
 * 北大还行撒贝宁
 * 不知美妻刘强东
 * 普通家庭马化腾
 * 一无所有王健林
 * 对钱无感是马云
 * 神秘消失李大牙
 * 刻苦奋斗高二愣
 * 倔强任性是老王
 * 毫无情商贡磊磊
 *
 */



public class BaoXiuLiShiActivity extends MySwipeBackActivity {
    @BindView(R.id.iv_lishibaoxiu_fanhui)
    RelativeLayout iv_lishibaoxiu_fanhui;
    @BindView(R.id.list_view)
    ListView list_view;
    @BindView(R.id.swipeRefreshLayout)
    SwipeRefreshLayout swipeRefreshLayout;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lishibaoxiu);
        ButterKnife.bind(this);
        initUI();
        initData();

    }

    private void initData() {
        String url= XY_Response.URL_USERCENTER_GETPROPERTYREPAIR
                +"cmemberId="+ SPManager.getInstance().getString("c_memberId","");
        RequestCenter.all(url, BaoXiuLiShi.class, new DisposeDataListener() {
            @Override
            public void onSuccess(Object responseObj) {
                BaoXiuLiShi baoXiuLiShi= (BaoXiuLiShi) responseObj;
                if (baoXiuLiShi.getCode()==1000){
                    JiLuAdapter adapter=new JiLuAdapter(BaoXiuLiShiActivity.this,baoXiuLiShi.getObj());
                    list_view.setAdapter(adapter);
                }

            }

            @Override
            public void onFailure(Object reasonObj) {

            }
        });



    }

    private void initUI() {
        iv_lishibaoxiu_fanhui.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        swipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                swipeRefreshLayout.setRefreshing(false);
                initData();
            }
        });


    }
}

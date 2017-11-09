package com.bjxiyang.shuzianfang.myapplication.activity;

import android.os.Bundle;
import android.support.v4.widget.SwipeRefreshLayout;
import android.view.View;
import android.widget.ListView;
import android.widget.RelativeLayout;

import com.baisi.myapplication.okhttp.listener.DisposeDataListener;
import com.bjxiyang.shuzianfang.R;
import com.bjxiyang.shuzianfang.myapplication.adapter.FanKuiLiShiAdapter;
import com.bjxiyang.shuzianfang.myapplication.manager.SPManager;
import com.bjxiyang.shuzianfang.myapplication.model.FanKuiLiShi;
import com.bjxiyang.shuzianfang.myapplication.response_xy.XY_Response;
import com.bjxiyang.shuzianfang.myapplication.ui.activity.MySwipeBackActivity;
import com.bjxiyang.shuzianfang.myapplication.update.network.RequestCenter;

/**
 * Created by Administrator on 2017/10/21 0021.
 */

public class FanKuiLiShiActivtiy extends MySwipeBackActivity {


    private RelativeLayout iv_lishifankui_fanhui;
    private ListView list_view;
    private SwipeRefreshLayout swipeRefreshLayout;

    private FanKuiLiShiAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lishifankui);
        initUI();
        initData();
    }

    private void initData() {
        String url= XY_Response.URL_USERCENTER_GETCOMPLAINTLIST
                +"cmemberId="+ SPManager.getInstance().getString("c_memberId","");
        RequestCenter.all(url, FanKuiLiShi.class, new DisposeDataListener() {
            @Override
            public void onSuccess(Object responseObj) {
                FanKuiLiShi fanKuiLiShi= (FanKuiLiShi) responseObj;
                if (fanKuiLiShi.getCode()==1000){
                    adapter=new FanKuiLiShiAdapter(FanKuiLiShiActivtiy.this,fanKuiLiShi.getObj());
                    list_view.setAdapter(adapter);
                }
            }

            @Override
            public void onFailure(Object reasonObj) {

            }
        });



    }

    private void initUI() {
        iv_lishifankui_fanhui= (RelativeLayout) findViewById(R.id.iv_lishifankui_fanhui);
        iv_lishifankui_fanhui.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        list_view= (ListView) findViewById(R.id.lv_lishifankui);
        swipeRefreshLayout= (SwipeRefreshLayout) findViewById(R.id.swipeRefreshLayout);
        swipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                swipeRefreshLayout.setRefreshing(false);
                initData();
            }
        });


    }
}

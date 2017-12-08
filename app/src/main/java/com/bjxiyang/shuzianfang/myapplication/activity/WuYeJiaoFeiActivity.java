package com.bjxiyang.shuzianfang.myapplication.activity;

import android.os.Bundle;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.AlertDialog;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.RelativeLayout;

import com.baisi.myapplication.okhttp.listener.DisposeDataListener;
import com.bjxiyang.shuzianfang.myapplication.model.WuYeJiaoFei;
import com.bjxiyang.shuzianfang.myapplication.response_xy.XY_Response;
import com.bjxiyang.shuzianfang.R;
import com.bjxiyang.shuzianfang.myapplication.adapter.WuYeJiaoFeiAdapter;
import com.bjxiyang.shuzianfang.myapplication.manager.SPManager;
import com.bjxiyang.shuzianfang.myapplication.until.DialogUntil;
import com.bjxiyang.shuzianfang.myapplication.until.MyUntil;
import com.bjxiyang.shuzianfang.myapplication.update.network.RequestCenter;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2017/6/9 0009.
 */

public class WuYeJiaoFeiActivity extends MySwipeBackActivity
        implements View.OnClickListener,
        SwipeRefreshLayout.OnRefreshListener{

    private RelativeLayout iv_wuyejiaofei_fanhui,rl_lishijiaofei;
    private ListView lv_wuyejiaofei;
    private LinearLayout ll_activity_wuxiangmu;
    private LinearLayout ll_wuyejiaofei_listview;
    private LinearLayout ll_activity_wuwangluo;
    private WuYeJiaoFeiAdapter adapter;
    private List<WuYeJiaoFei.ObjBean> mList;
    private SwipeRefreshLayout swipeRefreshLayout;
    private SwipeRefreshLayout swipeRefreshLayout1;
    private SwipeRefreshLayout swipeRefreshLayout2;
    private LinearLayout ll_activity_banbentishi;


    private static AlertDialog.Builder builder;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_wuyejiaofei);
        initUI();
        }

    @Override
    protected void onResume() {
        super.onResume();
        getData();
    }

    private void initUI() {
        rl_lishijiaofei= (RelativeLayout) findViewById(R.id.rl_lishijiaofei);
        rl_lishijiaofei.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //跳转到历史缴费页面
                MyUntil.mStartActivity(WuYeJiaoFeiActivity.this,JiaoFeiLiShiActivity.class);
            }
        });
        ll_activity_wuwangluo= (LinearLayout) findViewById(R.id.ll_activity_wuwangluo);
        iv_wuyejiaofei_fanhui= (RelativeLayout) findViewById(R.id.iv_wuyejiaofei_fanhui);
        iv_wuyejiaofei_fanhui.setOnClickListener(this);
        lv_wuyejiaofei= (ListView) findViewById(R.id.lv_wuyejiaofei);
        swipeRefreshLayout= (SwipeRefreshLayout) findViewById(R.id.swipeRefreshLayout);
        swipeRefreshLayout.setOnRefreshListener(this);
        swipeRefreshLayout.setColorSchemeResources(R.color.color_898787);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.iv_wuyejiaofei_fanhui:
                finish();
                break;
        }
    }

    private List<WuYeJiaoFei.ObjBean> getData(){
        mList=new ArrayList<>();
        DialogUntil.showLoadingDialog(this,"正在加载",true);
        String url= XY_Response.URL_GETPROPERTYLIST+"cmemberId="+
                SPManager.getInstance().getString("c_memberId","")
                +"&paystate="+0
                +"&feeType="+0;
        RequestCenter.all(url,WuYeJiaoFei.class,new DisposeDataListener() {
            @Override
            public void onSuccess(Object responseObj) {
                DialogUntil.closeLoadingDialog();
                WuYeJiaoFei wuYeJiaoFei= (WuYeJiaoFei) responseObj;
                if (wuYeJiaoFei.getCode()==1000){
                    mList=wuYeJiaoFei.getObj();
                    if (mList.size()>0){

                        adapter=new WuYeJiaoFeiAdapter(WuYeJiaoFeiActivity.this,mList);
                        lv_wuyejiaofei.setAdapter(adapter);
                        showListView();
                    }else {
//                        showWuJiLu();
                    }
                }else {
//                    showWuJiLu();
                    MyUntil.show(WuYeJiaoFeiActivity.this,wuYeJiaoFei.getMsg());
                }

            }

            @Override
            public void onFailure(Object reasonObj) {
                DialogUntil.closeLoadingDialog();
//                showWuWangLuo();
//                MyDialog.showDialog(WuYeJiaoFeiActivity.this);

            }
        });
        return mList;
    }
    private void showListView(){
//        ll_activity_banbentishi.setVisibility(View.GONE);
//        swipeRefreshLayout1.setVisibility(View.GONE);
//        swipeRefreshLayout2.setVisibility(View.GONE);
        swipeRefreshLayout.setVisibility(View.VISIBLE);
    }
    private void showWuWangLuo(){
//        ll_activity_banbentishi.setVisibility(View.GONE);
//        swipeRefreshLayout2.setVisibility(View.VISIBLE);
//        swipeRefreshLayout1.setVisibility(View.GONE);
        swipeRefreshLayout.setVisibility(View.GONE);
    }
    private void showWuJiLu(){
//        ll_activity_banbentishi.setVisibility(View.GONE);
//        swipeRefreshLayout2.setVisibility(View.GONE);
//        swipeRefreshLayout1.setVisibility(View.VISIBLE);
        swipeRefreshLayout.setVisibility(View.GONE);
    }
    private void showBanBen(){
//        ll_activity_banbentishi.setVisibility(View.VISIBLE);
//        swipeRefreshLayout2.setVisibility(View.GONE);
//        swipeRefreshLayout1.setVisibility(View.GONE);
        swipeRefreshLayout.setVisibility(View.GONE);
    }
    @Override
    public void onRefresh() {
        getData();
        swipeRefreshLayout.setRefreshing(false);
//        swipeRefreshLayout1.setRefreshing(false);
//        swipeRefreshLayout2.setRefreshing(false);
    }

}

package com.bjxiyang.shuzianfang.myapplication.activity;

import android.os.Bundle;
import android.support.v4.widget.SwipeRefreshLayout;
import android.view.View;
import android.widget.ListView;
import android.widget.RelativeLayout;

import com.baisi.myapplication.okhttp.listener.DisposeDataListener;
import com.bjxiyang.shuzianfang.R;
import com.bjxiyang.shuzianfang.myapplication.adapter.WuYeJiaoFeiLiShiAdapter;
import com.bjxiyang.shuzianfang.myapplication.manager.SPManager;
import com.bjxiyang.shuzianfang.myapplication.model.WuYeJiaoFeiLiShi;
import com.bjxiyang.shuzianfang.myapplication.response_xy.XY_Response;
import com.bjxiyang.shuzianfang.myapplication.ui.activity.MySwipeBackActivity;
import com.bjxiyang.shuzianfang.myapplication.until.DialogUntil;
import com.bjxiyang.shuzianfang.myapplication.until.MyUntil;
import com.bjxiyang.shuzianfang.myapplication.update.network.RequestCenter;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2017/10/23 0023.
 */

public class JiaoFeiLiShiActivity extends MySwipeBackActivity{
    private RelativeLayout iv_lishijiaofei_fanhui;
    private ListView lv_lishijiaofei;
    private List<WuYeJiaoFeiLiShi.ObjBean> mList;
    private WuYeJiaoFeiLiShiAdapter adapter;
    private SwipeRefreshLayout sw_lishijiaofei;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lishijiaofei);
        initUI();
        getData();
    }


    private void initUI() {
        sw_lishijiaofei= (SwipeRefreshLayout) findViewById(R.id.sw_lishijiaofei);
        sw_lishijiaofei.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                getData();
                sw_lishijiaofei.setRefreshing(false);
            }
        });
        iv_lishijiaofei_fanhui= (RelativeLayout) findViewById(R.id.iv_lishijiaofei_fanhui);
        iv_lishijiaofei_fanhui.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
        lv_lishijiaofei= (ListView) findViewById(R.id.lv_lishijiaofei);
//        lv_lishijiaofei.setOnItemClickListener(new AdapterView.OnItemClickListener() {
//            @Override
//            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
//                Intent intent=new Intent(JiaoFeiLiShiActivity.this,JiaoFeiLiShiXiangQingActivity.class);
//                intent.putExtra("propertyId",mList.get(i).getMonthpaylist().get(i).getPropertyId());
//                startActivity(intent);
//
//            }
//        });



    }
    private void getData(){
        mList=new ArrayList<>();
        DialogUntil.showLoadingDialog(this,"正在加载",true);
        String url= XY_Response.URL_GETPAYLIST+"cmemberId="+
                SPManager.getInstance().getString("c_memberId","")
                +"&feeType="+0;
        RequestCenter.getPropertyList(url, new DisposeDataListener() {
            @Override
            public void onSuccess(Object responseObj) {
                DialogUntil.closeLoadingDialog();
                WuYeJiaoFeiLiShi wuYeJiaoFei= (WuYeJiaoFeiLiShi) responseObj;
                if (wuYeJiaoFei.getCode()==1000){
                    mList=wuYeJiaoFei.getObj();
                    if (mList.size()>0){
                        adapter=new WuYeJiaoFeiLiShiAdapter(JiaoFeiLiShiActivity.this,mList);
                        lv_lishijiaofei.setAdapter(adapter);
                    }else {
//                        showWuJiLu();
                    }
                }else {
//                    showWuJiLu();
                    MyUntil.show(JiaoFeiLiShiActivity.this,wuYeJiaoFei.getMsg());
                }

            }

            @Override
            public void onFailure(Object reasonObj) {
                DialogUntil.closeLoadingDialog();

            }
        });
    }



}

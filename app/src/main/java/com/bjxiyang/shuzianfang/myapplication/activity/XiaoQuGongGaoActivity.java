package com.bjxiyang.shuzianfang.myapplication.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.widget.SwipeRefreshLayout;
import android.view.View;
import android.widget.AdapterView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.RelativeLayout;

import com.baisi.myapplication.okhttp.listener.DisposeDataListener;
import com.bjxiyang.shuzianfang.R;
import com.bjxiyang.shuzianfang.myapplication.adapter.XiaoQuGongGaoAdapter;
import com.bjxiyang.shuzianfang.myapplication.manager.SPManager;
import com.bjxiyang.shuzianfang.myapplication.model.GongGao;
import com.bjxiyang.shuzianfang.myapplication.response_xy.XY_Response;
import com.bjxiyang.shuzianfang.myapplication.ui.activity.MySwipeBackActivity;
import com.bjxiyang.shuzianfang.myapplication.until.DialogUntil;
import com.bjxiyang.shuzianfang.myapplication.until.MyUntil;
import com.bjxiyang.shuzianfang.myapplication.update.network.RequestCenter;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2017/6/9 0009.
 */

public class XiaoQuGongGaoActivity extends MySwipeBackActivity
        implements View.OnClickListener
        ,AdapterView.OnItemClickListener
        ,SwipeRefreshLayout.OnRefreshListener{
    private RelativeLayout rl_xiaoqugonggao_fanhui;
    private ListView lv_xiaoqugonggao;
    private List<GongGao.Obj> mList;
    private XiaoQuGongGaoAdapter adapter;
    private LinearLayout ll_xiaoqugonggao_wuxiaoxi;
    private LinearLayout ll_xiaoqugonggao_listview;
    private LinearLayout ll_xiaoqugonggao_wuwangluo;
    private SwipeRefreshLayout swipeRefreshLayout;
    private SwipeRefreshLayout swipeRefreshLayout1;
    private SwipeRefreshLayout swipeRefreshLayout2;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_xiaoqugonggao3);
        initUI();
        getData();

    }

    private void initUI() {
        ll_xiaoqugonggao_wuxiaoxi= (LinearLayout) findViewById(R.id.ll_xiaoqugonggao_wuxiaoxi);
        ll_xiaoqugonggao_wuwangluo= (LinearLayout) findViewById(R.id.ll_xiaoqugonggao_wuwangluo);
        rl_xiaoqugonggao_fanhui= (RelativeLayout) findViewById(R.id.rl_xiaoqugonggao_fanhui);
        rl_xiaoqugonggao_fanhui.setOnClickListener(this);
        lv_xiaoqugonggao= (ListView) findViewById(R.id.lv_xiaoqugonggao);
        lv_xiaoqugonggao.setOnItemClickListener(this);
        swipeRefreshLayout= (SwipeRefreshLayout) findViewById(R.id.swipeRefreshLayout);
        swipeRefreshLayout.setOnRefreshListener(this);
        swipeRefreshLayout.setColorSchemeResources(R.color.color_898787);
        swipeRefreshLayout1= (SwipeRefreshLayout) findViewById(R.id.swipeRefreshLayout1);
        swipeRefreshLayout1.setOnRefreshListener(this);
        swipeRefreshLayout1.setColorSchemeResources(R.color.color_898787);
        swipeRefreshLayout2= (SwipeRefreshLayout) findViewById(R.id.swipeRefreshLayout2);
        swipeRefreshLayout2.setOnRefreshListener(this);
        swipeRefreshLayout2.setColorSchemeResources(R.color.color_898787);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.rl_xiaoqugonggao_fanhui:
                finish();
                break;
        }
    }
    private List<GongGao.Obj> getData(){
        mList=new ArrayList<>();
        DialogUntil.showLoadingDialog(this,"正在加载",true);
        String url= XY_Response.URL_GETNOTICELIST+"cmemberId="+
                SPManager.getInstance().getString("c_memberId",null);

        RequestCenter.getNoticeList(url, new DisposeDataListener() {
            @Override
            public void onSuccess(Object responseObj) {
                DialogUntil.closeLoadingDialog();
                GongGao gongGao= (GongGao) responseObj;
                if (gongGao.getCode().equals("1000")){
                    mList=gongGao.getObj();
                    if (mList.size()>0){
                        adapter=new XiaoQuGongGaoAdapter(XiaoQuGongGaoActivity.this,mList);
                        lv_xiaoqugonggao.setAdapter(adapter);
                        showListView();
                    }else {
                        showWuJiLu();
                    }
                }else {
                    showWuJiLu();
                    MyUntil.show(XiaoQuGongGaoActivity.this,gongGao.getMsg());
                }
            }

            @Override
            public void onFailure(Object reasonObj) {
                DialogUntil.closeLoadingDialog();
                showWuWangLuo();
            }
        });
        return mList;
    }


    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

        Bundle bundle=new Bundle();
        bundle.putSerializable("data",mList.get(position));
        Intent intent=new Intent(XiaoQuGongGaoActivity.this,XiaoQuGongGaoXiangQingActivity.class);
        intent.putExtra("type",1);
        intent.putExtras(bundle);

        startActivity(intent);

    }
    //展示ListView

    private void showListView(){
        swipeRefreshLayout1.setVisibility(View.GONE);
        swipeRefreshLayout2.setVisibility(View.GONE);
        swipeRefreshLayout.setVisibility(View.VISIBLE);
    }
    //展示无网络
    private void showWuWangLuo(){
        swipeRefreshLayout2.setVisibility(View.VISIBLE);
        swipeRefreshLayout1.setVisibility(View.GONE);
        swipeRefreshLayout.setVisibility(View.GONE);
    }
    //展示无数据
    private void showWuJiLu(){
        swipeRefreshLayout2.setVisibility(View.GONE);
        swipeRefreshLayout1.setVisibility(View.VISIBLE);
        swipeRefreshLayout.setVisibility(View.GONE);
    }
    //下拉刷新的回掉
    @Override
    public void onRefresh() {
        getData();
        swipeRefreshLayout.setRefreshing(false);
        swipeRefreshLayout1.setRefreshing(false);
        swipeRefreshLayout2.setRefreshing(false);
    }

}

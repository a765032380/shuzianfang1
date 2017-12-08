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
import com.bjxiyang.shuzianfang.myapplication.until.DialogUntil;
import com.bjxiyang.shuzianfang.myapplication.until.MyUntil;
import com.bjxiyang.shuzianfang.myapplication.update.network.RequestCenter;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by Administrator on 2017/6/9 0009.
 */

public class XiaoQuGongGaoActivity extends MySwipeBackActivity implements View.OnClickListener
        ,AdapterView.OnItemClickListener,SwipeRefreshLayout.OnRefreshListener{
    @BindView(R.id.rl_xiaoqugonggao_fanhui)
    RelativeLayout rl_xiaoqugonggao_fanhui;
    @BindView(R.id.lv_xiaoqugonggao)
    ListView lv_xiaoqugonggao;
    @BindView(R.id.ll_xiaoqugonggao_wuxiaoxi)
    LinearLayout ll_xiaoqugonggao_wuxiaoxi;
    @BindView(R.id.ll_xiaoqugonggao_wuwangluo)
    LinearLayout ll_xiaoqugonggao_wuwangluo;
    @BindView(R.id.swipeRefreshLayout)
    SwipeRefreshLayout swipeRefreshLayout;
    @BindView(R.id.swipeRefreshLayout1)
    SwipeRefreshLayout swipeRefreshLayout1;
    @BindView(R.id.swipeRefreshLayout2)
    SwipeRefreshLayout swipeRefreshLayout2;

    private List<GongGao.Obj> mList;
    private XiaoQuGongGaoAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_xiaoqugonggao3);
        ButterKnife.bind(this);
        initUI();
        getData();

    }

    private void initUI() {
        rl_xiaoqugonggao_fanhui.setOnClickListener(this);
        lv_xiaoqugonggao.setOnItemClickListener(this);
        swipeRefreshLayout.setOnRefreshListener(this);
        swipeRefreshLayout.setColorSchemeResources(R.color.color_898787);
        swipeRefreshLayout1.setOnRefreshListener(this);
        swipeRefreshLayout1.setColorSchemeResources(R.color.color_898787);
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

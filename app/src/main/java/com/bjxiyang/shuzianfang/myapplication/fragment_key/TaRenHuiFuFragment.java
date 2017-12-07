package com.bjxiyang.shuzianfang.myapplication.fragment_key;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.widget.SwipeRefreshLayout;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;

import com.baisi.myapplication.okhttp.listener.DisposeDataListener;
import com.bjxiyang.shuzianfang.R;
import com.bjxiyang.shuzianfang.myapplication.activity.XiaoQuGongGaoXiangQingActivity;
import com.bjxiyang.shuzianfang.myapplication.adapter.TaRenHuiFuAdapter;
import com.bjxiyang.shuzianfang.myapplication.manager.SPManager;
import com.bjxiyang.shuzianfang.myapplication.model.GongGao;
import com.bjxiyang.shuzianfang.myapplication.model.MsgList;
import com.bjxiyang.shuzianfang.myapplication.model.XiTongXiaoXi;
import com.bjxiyang.shuzianfang.myapplication.response_xy.Response_AF;
import com.bjxiyang.shuzianfang.myapplication.until.MyUntil;
import com.bjxiyang.shuzianfang.myapplication.update.network.RequestCenter;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2017/8/21 0021.
 */

public class TaRenHuiFuFragment extends Fragment implements SwipeRefreshLayout.OnRefreshListener ,AdapterView.OnItemClickListener{
    private int type;
    private View view;
    private ListView list_view;
    private SwipeRefreshLayout swipeRefreshLayout;
    private TaRenHuiFuAdapter adapter;
    private List<MsgList.ObjBean> mList2;
    @SuppressLint({"NewApi", "ValidFragment"})
    public TaRenHuiFuFragment(int type){
        this.type=type;
    }
    @SuppressLint({"NewApi", "ValidFragment"})
    public TaRenHuiFuFragment(){
    }

    private int pageCount_sys=1;
    private int pageSize_sys=5;


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view=inflater.inflate(R.layout.fragment_tarenhuifu,null);
        list_view= (ListView) view.findViewById(R.id.list_view);
        swipeRefreshLayout= (SwipeRefreshLayout) view.findViewById(R.id.swipeRefreshLayout);
        swipeRefreshLayout.setOnRefreshListener(this);
        list_view.setOnItemClickListener(this);

        getData2();

        return view;

    }
    private void getData2(){
        mList2=new ArrayList<>();
        String url= Response_AF.URL_INIT_MSGLIST
                +"cmemberId="+ SPManager.getInstance().getString("c_memberId",null)
                +"&msgType="+type;
//                +"&pageCount="+pageCount_sys
//                +"&pageSize="+pageSize_sys;
        Log.i("LLLL",url);
        RequestCenter.usercenter_getSysMsg(url, new DisposeDataListener() {
            @Override
            public void onSuccess(Object responseObj) {
                MsgList xiTongXiaoXi= (MsgList) responseObj;
                if (xiTongXiaoXi.getCode()==1000){
                    mList2=xiTongXiaoXi.getObj();
                    adapter=new TaRenHuiFuAdapter(getContext(),mList2,type);
                    list_view.setAdapter(adapter);
                }else {
                    MyUntil.show(getContext(),xiTongXiaoXi.getMsg());
                }
            }

            @Override
            public void onFailure(Object reasonObj) {

            }
        });
    }

    @Override
    public void onRefresh() {
        getData2();
        swipeRefreshLayout.setRefreshing(false);
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        SPManager.getInstance().putInt("xiaoxi",(SPManager.getInstance().getInt("xiaoxi",0)-1));

        Intent intent=new Intent(getContext(), XiaoQuGongGaoXiangQingActivity.class);
        Bundle bundle=new Bundle();
        bundle.putSerializable("data", mList2.get(position));
        intent.putExtras(bundle);
        intent.putExtra("type",1);
        startActivity(intent);


    }
}

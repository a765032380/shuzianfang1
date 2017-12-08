package com.bjxiyang.shuzianfang.myapplication.activity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.baisi.myapplication.okhttp.listener.DisposeDataListener;
import com.bjxiyang.shuzianfang.R;
import com.bjxiyang.shuzianfang.myapplication.manager.SPManager;
import com.bjxiyang.shuzianfang.myapplication.model.MsgList;
import com.bjxiyang.shuzianfang.myapplication.response_xy.Response_AF;
import com.bjxiyang.shuzianfang.myapplication.update.network.RequestCenter;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by Administrator on 2017/6/10 0010.
 */

public class XiaoQuGongGaoXiangQingActivity extends MySwipeBackActivity {
    /**
     * UI
     */
    @BindView(R.id.rl_xiaoqugonggao_xiangqing_fanhui)
    RelativeLayout rl_xiaoqugonggao_xiangqing_fanhui;
    @BindView(R.id.tv_xiaoqugonggao_xiangqing_title)
    TextView tv_xiaoqugonggao_xiangqing_title;
    @BindView(R.id.iv_xiaoqugonggao_xiangqing_jinji)
    ImageView iv_xiaoqugonggao_xiangqing_jinji;
    @BindView(R.id.tv_xiaoqugonggao_xiangqing_date)
    TextView tv_xiaoqugonggao_xiangqing_date;
    @BindView(R.id.tv_xiaoqugonggao_xiangqing_time)
    TextView tv_xiaoqugonggao_xiangqing_time;
    @BindView(R.id.tv_xiaoqugonggao_xiangqing_wuyeguanlichu)
    TextView tv_xiaoqugonggao_xiangqing_wuyeguanlichu;
    @BindView(R.id.tv_xiaoqugonggao_xiangqing_gonggaoxiangqing)
    TextView tv_xiaoqugonggao_xiangqing_gonggaoxiangqing;
    @BindView(R.id.tv_jiaofeitongdao)
    TextView tv_jiaofeitongdao;
    /**
     * Data
     */

    private MsgList.ObjBean obj;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_xiaoqugonggao_xiangqing3);
        ButterKnife.bind(this);
        initUI();
        initData();


    }

    private void sendData() {
        if (obj.getMsgType().equals("物业费缴纳")){
            tv_jiaofeitongdao.setVisibility(View.VISIBLE);
        }else {
            tv_jiaofeitongdao.setVisibility(View.INVISIBLE);
        }
        tv_xiaoqugonggao_xiangqing_title.setText(obj.getMsgType());
        tv_xiaoqugonggao_xiangqing_date.setText(obj.getAddTime());
//        if (obj.getType()==0){
//            iv_xiaoqugonggao_xiangqing_jinji.setVisibility(View.GONE);
//        }
//        tv_xiaoqugonggao_xiangqing_wuyeguanlichu.setText(obj.getMsgType()+"");
        tv_xiaoqugonggao_xiangqing_gonggaoxiangqing.setText(obj.getMsgContent());
    }
    private void initUI() {
        tv_jiaofeitongdao.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(XiaoQuGongGaoXiangQingActivity.this,WuYeJiaoFeiActivity.class));
            }
        });
        rl_xiaoqugonggao_xiangqing_fanhui.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }
    private void initData(){
        Intent intent=getIntent();
        if (intent.getIntExtra("type",0)==0){
            getData2();
        }else {
            obj = (MsgList.ObjBean) intent.getSerializableExtra("data");
            sendData();
        }
    }


    private void getData2(){
        String url= Response_AF.URL_INIT_MSGLIST
                +"cmemberId="+ SPManager.getInstance().getString("c_memberId",null)
                +"&msgType="+0;
//                +"&pageCount="+pageCount_sys
//                +"&pageSize="+pageSize_sys;
        Log.i("LLLL",url);
        RequestCenter.usercenter_getSysMsg(url, new DisposeDataListener() {
            @Override
            public void onSuccess(Object responseObj) {
                MsgList xiTongXiaoXi= (MsgList) responseObj;
                if (xiTongXiaoXi.getCode()==1000){
                    if (xiTongXiaoXi.getObj().size()>0) {
                        obj = xiTongXiaoXi.getObj().get(0);
                        if (obj.getMsgType().equals("物业费缴纳")) {
                            tv_jiaofeitongdao.setVisibility(View.VISIBLE);
                        } else {
                            tv_jiaofeitongdao.setVisibility(View.GONE);
                        }
                        sendData();
                    }
                }
            }

            @Override
            public void onFailure(Object reasonObj) {

            }
        });
    }



//    private void getData(){
//        DialogUntil.showLoadingDialog(this,"正在加载",true);
//        String url= XY_Response.URL_GETNOTICELIST+"cmemberId="+
//                SPManager.getInstance().getString("c_memberId",null);
//
//        RequestCenter.getNoticeList(url, new DisposeDataListener() {
//            @Override
//            public void onSuccess(Object responseObj) {
//                DialogUntil.closeLoadingDialog();
//                GongGao gongGao= (GongGao) responseObj;
//                if (gongGao.getCode().equals("1000")){
//                    if (gongGao.getObj().size()>0) {
//                        obj = gongGao.getObj().get(0);
//                        if (obj.getTitle().equals("物业费缴纳")) {
//                            tv_jiaofeitongdao.setVisibility(View.VISIBLE);
//                        } else {
//                            tv_jiaofeitongdao.setVisibility(View.GONE);
//                        }
//                        sendData();
//                    }
//
//                }
//            }
//
//            @Override
//            public void onFailure(Object reasonObj) {
//                DialogUntil.closeLoadingDialog();
//            }
//        });
//    }
}

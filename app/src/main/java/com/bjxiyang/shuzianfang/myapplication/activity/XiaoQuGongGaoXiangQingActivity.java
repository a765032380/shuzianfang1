package com.bjxiyang.shuzianfang.myapplication.activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.bjxiyang.shuzianfang.R;
import com.bjxiyang.shuzianfang.myapplication.model.GongGao;
import com.bjxiyang.shuzianfang.myapplication.model.Text;
import com.bjxiyang.shuzianfang.myapplication.ui.activity.MySwipeBackActivity;
import com.bjxiyang.shuzianfang.myapplication.until.MyUntil;

/**
 * Created by Administrator on 2017/6/10 0010.
 */

public class XiaoQuGongGaoXiangQingActivity extends MySwipeBackActivity {
    /**
     * UI
     */
    private RelativeLayout rl_xiaoqugonggao_xiangqing_fanhui;
    private TextView tv_xiaoqugonggao_xiangqing_title;
    private ImageView iv_xiaoqugonggao_xiangqing_jinji;
    private TextView tv_xiaoqugonggao_xiangqing_date;
    private TextView tv_xiaoqugonggao_xiangqing_time;
    private TextView tv_xiaoqugonggao_xiangqing_wuyeguanlichu;
    private TextView tv_xiaoqugonggao_xiangqing_gonggaoxiangqing;
    private TextView tv_jiaofeitongdao;
    /**
     * Data
     */

    private GongGao.Obj obj;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_xiaoqugonggao_xiangqing3);
        initUI();
        initData();
        sendData();

    }

    private void sendData() {
        tv_xiaoqugonggao_xiangqing_title.setText(obj.getMsgType());
        tv_xiaoqugonggao_xiangqing_date.setText(obj.getAddTime());
//        if (obj.getType()==0){
//            iv_xiaoqugonggao_xiangqing_jinji.setVisibility(View.GONE);
//        }
//        tv_xiaoqugonggao_xiangqing_wuyeguanlichu.setText(obj.+"");
        tv_xiaoqugonggao_xiangqing_gonggaoxiangqing.setText(obj.getMsgContent());
    }
    private void initUI() {
        rl_xiaoqugonggao_xiangqing_fanhui= (RelativeLayout) findViewById(R.id.rl_xiaoqugonggao_xiangqing_fanhui);
        rl_xiaoqugonggao_xiangqing_fanhui.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        tv_xiaoqugonggao_xiangqing_title= (TextView) findViewById(R.id.tv_xiaoqugonggao_xiangqing_title);
        iv_xiaoqugonggao_xiangqing_jinji= (ImageView) findViewById(R.id.iv_xiaoqugonggao_xiangqing_jinji);
        tv_xiaoqugonggao_xiangqing_date= (TextView) findViewById(R.id.tv_xiaoqugonggao_xiangqing_date);
        tv_xiaoqugonggao_xiangqing_wuyeguanlichu= (TextView) findViewById(R.id.tv_xiaoqugonggao_xiangqing_wuyeguanlichu);
        tv_xiaoqugonggao_xiangqing_gonggaoxiangqing= (TextView) findViewById(R.id.tv_xiaoqugonggao_xiangqing_gonggaoxiangqing);
        tv_jiaofeitongdao= (TextView) findViewById(R.id.tv_jiaofeitongdao);
        tv_jiaofeitongdao.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                MyUntil.mStartActivity(XiaoQuGongGaoXiangQingActivity.this,WuYeJiaoFeiActivity.class);
            }
        });
    }
    private void initData(){
        Intent intent=getIntent();
        obj= (GongGao.Obj) intent.getSerializableExtra("data");
        isWuYeJiaoFei();
//        gonggao= (GongGao) bundle.get("data");
    }
    private void isWuYeJiaoFei(){
        if (obj.getMsgType().equals("物业缴费")){
            tv_jiaofeitongdao.setVisibility(View.VISIBLE);
        }else {
            tv_jiaofeitongdao.setVisibility(View.GONE);
        }


    }



}

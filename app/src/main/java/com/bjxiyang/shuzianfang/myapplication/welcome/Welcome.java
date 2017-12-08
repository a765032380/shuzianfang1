package com.bjxiyang.shuzianfang.myapplication.welcome;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.bjxiyang.shuzianfang.R;
import com.bjxiyang.shuzianfang.myapplication.activity.BeasActivity;
import com.bjxiyang.shuzianfang.myapplication.activity.SDLoginActivity;

import java.util.Timer;
import java.util.TimerTask;

/**
 * Created by Cl on 2016/10/10.
 */
public class Welcome extends BeasActivity {

    private long timeCount = 1;
    private Timer mtimer;
    private ImageView iv_qidong;
    private LinearLayout lv_tiaoguo;
    private TextView tv_time;

    private boolean isFirstIn = false;
    private boolean isStart = true;

    private final static int GO_HOME = 0;
    private final static int GO_GUIDE = 1;

    private final static int TIME = 0;

    private Handler handler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            switch (msg.what) {
                case GO_HOME:
                    goHome();
                    break;
                case GO_GUIDE:
                    goGuide();
                    break;
            }
            super.handleMessage(msg);
        }
    };
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.welcome);
        iv_qidong= (ImageView) findViewById(R.id.iv_qidong);
        lv_tiaoguo= (LinearLayout) findViewById(R.id.lv_tiaoguo);
        tv_time= (TextView) findViewById(R.id.tv_time);
//        goHome();


//        lv_tiaoguo.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                if (mtimer!=null) {
//                    mtimer.cancel();
//                }
//                init();
//            }
//        });
        int pageSize=1;
//        String url= XY_Response.URL_STARTADPAGE+"pageSize="+pageSize;
//        RequestCenter.startadpage(url, new DisposeDataListener() {
//            @Override
//            public void onSuccess(Object responseObj) {
//                final QiDongYe qiDongYe= (QiDongYe) responseObj;
//
//                    if (qiDongYe.getCode().equals("1000")) {
//                        ImageLoaderManager.getInstance(Welcome.this)
//                                .displayImage(iv_qidong, qiDongYe.getObj().getAdUrl());
//                        timeCount = 0;
//
//                        iv_qidong.setOnClickListener(new View.OnClickListener() {
//                            @Override
//                            public void onClick(View v) {
//                                Intent intent = new Intent(Welcome.this, MyWebViewActivity.class);
//                                intent.putExtra("url", qiDongYe.getObj().getAdRedirectUrl());
//                                startActivity(intent);
//                                mtimer.cancel();
//                                timeCount = 0;
//                            }
//                        });
                        setTimerTask();
//                    }else {
//                        lv_tiaoguo.setVisibility(View.GONE);
//                        tv_time.setVisibility(View.GONE);
//
//                        init();
//                    }
//
//            }
//
//            @Override
//            public void onFailure(Object reasonObj) {
//                init();
//            }
//        });
    }

    @Override
    protected void onResume() {
        super.onResume();
    }

    private void init() {
        SharedPreferences sp = getSharedPreferences("my", MODE_PRIVATE);
        SharedPreferences.Editor edit = sp.edit();
        isFirstIn = sp.getBoolean("isFirstIn", false);
        if (!isFirstIn) {
            handler.sendEmptyMessageDelayed(GO_GUIDE, TIME);
        } else {
            handler.sendEmptyMessageDelayed(GO_HOME, TIME);
        }
    }
    private void goHome() {
        startActivity(new Intent(Welcome.this, SDLoginActivity.class));
        finish();
    }
    private void goGuide() {
        startActivity(new Intent(Welcome.this, Guide.class));
        finish();
    }

    private Handler timerHandler = new Handler(){
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            if (msg.what == 1){
                timeCount --;
                if (timeCount > 0){
                changeSmsButton();
                }else{
                    mtimer.cancel();
                    init();
                }
            }
        }
    };
    //倒计时的方法
    public void setTimerTask(){
        mtimer = new Timer();
        mtimer.schedule(new TimerTask() {
            @Override
            public void run() {
                Message message = new Message();
                message.what = 1;
                timerHandler.sendMessage(message);

            }
        },1000,1000);
    }
    //在倒计时的时候,对按钮上的字进行赋值
    public void changeSmsButton() {
        tv_time.setText(timeCount+"");
    }
}
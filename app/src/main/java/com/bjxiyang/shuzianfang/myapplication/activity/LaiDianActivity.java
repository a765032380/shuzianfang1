package com.bjxiyang.shuzianfang.myapplication.activity;

import android.app.Activity;
import android.graphics.drawable.AnimationDrawable;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.bjxiyang.shuzianfang.R;

/**
 * Created by Administrator on 2017/10/18 0018.
 */

public class LaiDianActivity extends Activity{
//    @BindView(R.id.iv_camera)
    ImageView iv_camera;
//    @BindView(R.id.tv_guaduan)
    TextView tv_guaduan;
//    @BindView(R.id.tv_jieting)
    TextView tv_jieting;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_laidian);
//        ButterKnife.bind(this);
        initUI();

    }

    private void initUI() {

        iv_camera= (ImageView) findViewById(R.id.iv_camera);
        tv_guaduan= (TextView) findViewById(R.id.tv_guaduan);
        tv_jieting= (TextView) findViewById(R.id.tv_jieting);
        //挂断按键
        tv_guaduan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });
        //接听按键
        tv_jieting.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });
        iv_camera.setBackgroundResource(R.drawable.laidiananim);
        AnimationDrawable anim = (AnimationDrawable) iv_camera.getBackground();
        anim.start();
    }
}

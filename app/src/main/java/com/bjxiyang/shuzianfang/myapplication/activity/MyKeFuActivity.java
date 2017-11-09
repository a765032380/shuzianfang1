package com.bjxiyang.shuzianfang.myapplication.activity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;

import com.bjxiyang.shuzianfang.R;
import com.bjxiyang.shuzianfang.myapplication.ui.activity.MySwipeBackActivity;
import com.bjxiyang.shuzianfang.myapplication.ui.activity.YiJianFanKuiActivity;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by Administrator on 2017/9/7 0007.
 */

public class MyKeFuActivity extends MySwipeBackActivity implements View.OnClickListener{
    @BindView(R.id.ib_wodekefu_fanghui)
    RelativeLayout ib_wodekefu_fanghui;
    @BindView(R.id.ll_jinjiqiuzhu)
    LinearLayout ll_jinjiqiuzhu;
    @BindView(R.id.ll_yijianfankui)
    LinearLayout ll_yijianfankui;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_wodekefu);
        ButterKnife.bind(this);
        initUI();
    }

    private void initUI() {
        ib_wodekefu_fanghui.setOnClickListener(this);
        ll_jinjiqiuzhu.setOnClickListener(this);
        ll_yijianfankui.setOnClickListener(this);


    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            //返回的按键
            case R.id.ib_wodekefu_fanghui:
                finish();
                break;
            //紧急求助
            case R.id.ll_jinjiqiuzhu:
                Intent j = new Intent();
                j.setAction(Intent.ACTION_DIAL);
                j.setData(Uri.parse("tel:4000080828"));
                startActivity(j);
                break;
            //意见反馈
            case R.id.ll_yijianfankui:
                startIntent(YiJianFanKuiActivity.class);
                break;
        }
    }
    private void startIntent(Class c){
        Intent intent = new Intent(this, c);
        startActivity(intent);
    }
}

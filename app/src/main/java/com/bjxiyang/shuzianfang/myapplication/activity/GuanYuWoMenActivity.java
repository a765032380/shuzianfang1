package com.bjxiyang.shuzianfang.myapplication.activity;

import android.os.Bundle;
import android.view.View;
import android.widget.LinearLayout;

import com.bjxiyang.shuzianfang.R;
import com.bjxiyang.shuzianfang.myapplication.ui.activity.MySwipeBackActivity;

/**
 * Created by Administrator on 2017/8/21 0021.
 */

public class GuanYuWoMenActivity extends MySwipeBackActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_guanyuwomen);
        LinearLayout lv_fanhui= (LinearLayout) findViewById(R.id.lv_fanhui);
        lv_fanhui.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
    }
}

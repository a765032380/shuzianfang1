package com.bjxiyang.shuzianfang.myapplication.activity;

import android.os.Bundle;
import android.view.View;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.bjxiyang.shuzianfang.R;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by Administrator on 2017/12/13 0013.
 */

public class ChengZhangZhiActivity extends MySwipeBackActivity {

    @BindView(R.id.tv_big_chengzhangzhi)
    TextView tv_big_chengzhangzhi;
    @BindView(R.id.lv_chengzhangzhi)
    ListView lv_chengzhangzhi;
    @BindView(R.id.iv_chengzhangzhi_fanhui)
    RelativeLayout iv_chengzhangzhi_fanhui;




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chengzhangzhi);
        ButterKnife.bind(this);
        iv_chengzhangzhi_fanhui.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });

    }
}

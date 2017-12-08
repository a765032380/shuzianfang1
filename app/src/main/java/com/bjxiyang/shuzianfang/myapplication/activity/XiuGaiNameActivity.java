package com.bjxiyang.shuzianfang.myapplication.activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.baisi.myapplication.okhttp.listener.DisposeDataListener;
import com.bjxiyang.shuzianfang.R;
import com.bjxiyang.shuzianfang.myapplication.manager.SPManager;
import com.bjxiyang.shuzianfang.myapplication.model.FanHui2;
import com.bjxiyang.shuzianfang.myapplication.response_xy.XY_Response2;
import com.bjxiyang.shuzianfang.myapplication.until.DialogUntil;
import com.bjxiyang.shuzianfang.myapplication.until.MyUntil;
import com.bjxiyang.shuzianfang.myapplication.update.network.RequestCenter;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by Administrator on 2017/8/21 0021.
 */

public class XiuGaiNameActivity extends BeasActivity implements View.OnClickListener {

    @BindView(R.id.rl_username_fanghui)
    RelativeLayout rl_username_fanghui;
    @BindView(R.id.tv_baocun)
    TextView tv_baocun;
    @BindView(R.id.et_name)
    EditText et_name;
    @BindView(R.id.ll_username_delete)
    LinearLayout ll_username_delete;
    @BindView(R.id.tv_title)
    TextView tv_title;

    private String name;
    private String realname;
    private int type;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_username);
        ButterKnife.bind(this);
        Intent intent=getIntent();
        name=intent.getStringExtra("name");
        type=intent.getIntExtra("type",0);
        realname=intent.getStringExtra("realname");
        if (type==0){
            et_name.setText(name+"");
            tv_title.setText("用户名");
        }else {
            et_name.setText(realname+"");
            tv_title.setText("真实姓名");
        }

        rl_username_fanghui.setOnClickListener(this);
        tv_baocun.setOnClickListener(this);
        ll_username_delete.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            //返回的按键
            case R.id.rl_username_fanghui:
                finish();
                break;
            //保存的按键
            case R.id.tv_baocun:
                name= String.valueOf(et_name.getText());
                if (name==null||name.equals("")){
                    MyUntil.show(XiuGaiNameActivity.this,"请输入用户名");
                    break;
                }
                DialogUntil.showLoadingDialog(this,"正在提交",false);
                String url= XY_Response2.URL_UESRCENTER_UPDATEUSERINFO1
                        +"cmemberId="+ SPManager.getInstance().getString("c_memberId",null)
                        +"&name="+name
                        +"&type="+type;
                RequestCenter.usercenter_updateUserInfo(url, new DisposeDataListener() {
                    @Override
                    public void onSuccess(Object responseObj) {
                        DialogUntil.closeLoadingDialog();
                        FanHui2 fanHui2= (FanHui2) responseObj;
                        if (fanHui2.getCode()==1000){
                            MyUntil.show(XiuGaiNameActivity.this,"修改成功");

                            Intent mIntent = new Intent();
                            if (type==0) {
                                mIntent.putExtra("name", name);
                                SPManager.getInstance().putString("nickName", name);
                            }else {
                                mIntent.putExtra("realname", name);
                                SPManager.getInstance().putString("realname", name);
                            }
                            // 设置结果，并进行传送
                            setResult(RESULT_OK, mIntent);
                            finish();
                        }else {
                            DialogUntil.closeLoadingDialog();
                            MyUntil.show(XiuGaiNameActivity.this,fanHui2.getMsg());
                        }
                    }

                    @Override
                    public void onFailure(Object reasonObj) {
                        DialogUntil.closeLoadingDialog();
                    }
                });



                break;
            //清空名字的按键
            case R.id.ll_username_delete:
                et_name.setText("");
                break;



        }
    }
}

package com.bjxiyang.shuzianfang.myapplication.activity;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.util.Log;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;

import com.baisi.myapplication.okhttp.listener.DisposeDataListener;
import com.bjxiyang.shuzianfang.R;
import com.bjxiyang.shuzianfang.myapplication.manager.SPManager;
import com.bjxiyang.shuzianfang.myapplication.manager.UserManager;
import com.bjxiyang.shuzianfang.myapplication.model.UpdateVersion;
import com.bjxiyang.shuzianfang.myapplication.ui.activity.FuWuTiaoKuanActivity;
import com.bjxiyang.shuzianfang.myapplication.ui.activity.MySwipeBackActivity;
import com.bjxiyang.shuzianfang.myapplication.ui.activity.SDLoginActivity;
import com.bjxiyang.shuzianfang.myapplication.until.DialogUntil;
import com.bjxiyang.shuzianfang.myapplication.until.MyUntil;
import com.bjxiyang.shuzianfang.myapplication.update.CommonDialog;
import com.bjxiyang.shuzianfang.myapplication.update.network.RequestCenter;
import com.bjxiyang.shuzianfang.myapplication.update.service.UpdateService;
import com.bjxiyang.shuzianfang.myapplication.update.util.Util;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by Administrator on 2017/8/21 0021.
 */

public class SheZhiActivity extends MySwipeBackActivity implements View.OnClickListener {
    @BindView(R.id.rl_setting_fanghui)
    RelativeLayout rl_setting_fanghui;
    @BindView(R.id.ll_setting_clear)
    LinearLayout ll_setting_clear;
    @BindView(R.id.ll_setting_yonghuxieyi)
    LinearLayout ll_setting_yonghuxieyi;
    @BindView(R.id.jianchagengxin)
    LinearLayout jianchagengxin;
    @BindView(R.id.ll_setting_guanyuwomen)
    LinearLayout ll_setting_guanyuwomen;
    @BindView(R.id.ll_setting_pingfen)
    LinearLayout ll_setting_pingfen;
    @BindView(R.id.siginoutbutton)
    LinearLayout siginoutbutton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_setting);
        ButterKnife.bind(this);
        rl_setting_fanghui.setOnClickListener(this);
        ll_setting_clear.setOnClickListener(this);
        ll_setting_yonghuxieyi.setOnClickListener(this);
        jianchagengxin.setOnClickListener(this);
        ll_setting_guanyuwomen.setOnClickListener(this);
        ll_setting_pingfen.setOnClickListener(this);
        siginoutbutton.setOnClickListener(this);



    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            //返回的按键
            case R.id.rl_setting_fanghui:
                finish();
                break;
            //清空缓存
            case R.id.ll_setting_clear:

                break;
            //用户协议
            case R.id.ll_setting_yonghuxieyi:
                Intent intent=new Intent(this,FuWuTiaoKuanActivity.class);
                startActivity(intent);
                break;
            //检查更新
            case R.id.jianchagengxin:
                checkVersion();
                break;
            //关于我们
            case R.id.ll_setting_guanyuwomen:
                MyUntil.mStartActivity(this,GuanYuWoMenActivity.class);
                break;
            //用户评分
            case R.id.ll_setting_pingfen:

                break;
            //退出账号
            case R.id.siginoutbutton:

                final AlertDialog.Builder builder = new AlertDialog.Builder(SheZhiActivity.this);
                builder.setTitle("您确定要退出吗？");
                builder.setIcon(R.mipmap.app_logo);
                builder.setPositiveButton("确定", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        SPManager.getInstance().remove("mobilePhone");
                        SPManager.getInstance().remove("communityId_one");
                        SPManager.getInstance().remove("c_memberId");
                        SPManager.getInstance().remove("test_men");
                        SPManager.getInstance().putBoolean("isOne",false);
                        UserManager.getInstance().removeUser();
                        MyUntil.mStartActivity(SheZhiActivity.this,SDLoginActivity.class);

                        finish();
                    }
                });
                builder.setNegativeButton("取消", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        builder.create();
                    }
                });
                builder.show();



                break;

        }
    }
    //检查更新代码
    private void checkVersion() {
        DialogUntil.showLoadingDialog(SheZhiActivity.this,"正在检查更新",true);
        RequestCenter.checkVersion(new DisposeDataListener() {
            @Override
            public void onSuccess(Object responseObj) {
                DialogUntil.closeLoadingDialog();

                final UpdateVersion updateModel = (UpdateVersion) responseObj;

                if (updateModel.getCode().equals("1000")) {
                    UpdateVersion.ObjBean obj=updateModel.getObj();


                    Log.i("lllll",Util.getVersionCode(SheZhiActivity.this)+"---"+obj.getVerNo());
                    Log.i("lllll",Double.valueOf(Util.getVersionCode(SheZhiActivity.this))
                            +"---"+Double.valueOf(obj.getVerNo()));
                    if (Double.valueOf(Util.getVersionCode(SheZhiActivity.this))< Double.valueOf(obj.getVerNo())) {
                        //说明有新版本,开始下载
                        CommonDialog dialog = new CommonDialog(SheZhiActivity.this,
                                getString(R.string.update_new_version),
                                obj.getVerDescript(),
                                getString(R.string.cancel),
                                getString(R.string.update_install),
                                new CommonDialog.DialogClickListener() {
                                    @Override
                                    public void onDialogClick() {
                                        Intent intent = new Intent(SheZhiActivity.this, UpdateService.class);
                                        intent.putExtra("APPURL", updateModel.getObj().getVerUrl());
                                        startService(intent);
                                    }
                                });
                        dialog.setCancelable(false);
                        dialog.show();
                    } else {
                        MyUntil.show(SheZhiActivity.this,"该版本已是最新版本");
                    }
                }else {
                    MyUntil.show(SheZhiActivity.this,updateModel.getMsg());
                }
            }

            @Override
            public void onFailure(Object reasonObj) {
                DialogUntil.closeLoadingDialog();

                AlertDialog.Builder builder=new AlertDialog.Builder(SheZhiActivity.this);
                AlertDialog dialog=builder
                        .setIcon(R.mipmap.app_logo)
                        .setTitle("检查更新")
                        .setMessage("网络连接失败")
                        .setPositiveButton("确定", null)
                        .show();

            }
        });
    }
}

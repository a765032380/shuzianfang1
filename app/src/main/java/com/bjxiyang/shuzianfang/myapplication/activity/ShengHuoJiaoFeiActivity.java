package com.bjxiyang.shuzianfang.myapplication.activity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.baisi.myapplication.okhttp.listener.DisposeDataListener;
import com.bjxiyang.shuzianfang.myapplication.model.SelectPlot;
import com.bjxiyang.shuzianfang.myapplication.response_xy.XY_Response;
import com.bjxiyang.shuzianfang.myapplication.ui.activity.MySwipeBackActivity;
import com.bjxiyang.shuzianfang.R;
import com.bjxiyang.shuzianfang.myapplication.manager.SPManager;
import com.bjxiyang.shuzianfang.myapplication.ui.activity.YiJianFanKuiActivity;
import com.bjxiyang.shuzianfang.myapplication.until.DialogUntil;
import com.bjxiyang.shuzianfang.myapplication.until.MyUntil;
import com.bjxiyang.shuzianfang.myapplication.update.network.RequestCenter;
import com.bjxiyang.shuzianfang.myapplication.widgets.CommonActionSheetDialog;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by Administrator on 2017/7/18 0018.
 */

public class ShengHuoJiaoFeiActivity extends MySwipeBackActivity implements View.OnClickListener{

    public static final int SHUIFEI=1;
    public static final int DIANFEI=2;
    public static final int RANQIFEI=3;
    public static final int KUANDAIFEI=4;
    public static final int WUYEFEI=5;


    /***
     * UI
     */
    private LinearLayout ll_address;
    private LinearLayout ll_shuifei;
    private LinearLayout ll_dianfei;
    private LinearLayout ll_ranqifei;
    private LinearLayout ll_kuandaifei;
    private LinearLayout ll_wuyefei;
    private LinearLayout ll_nuanqifei;
    private LinearLayout ll_youxiandianshi;
    private TextView tv_shopname;
    private RelativeLayout iv_shenghuojiaofei_fanhui;
    @BindView(R.id.ll_weixiufuwu)
    LinearLayout ll_weixiufuwu;
    @BindView(R.id.ll_tousujianyi)
    LinearLayout ll_tousujianyi;
    @BindView(R.id.rl_jilu)
    RelativeLayout rl_jilu;

    public CommonActionSheetDialog commonActionSheetDialog;
    private List<SelectPlot.Obj> mList_plot;
    private int communityId;
    private String name;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_shenghuojiaofei);
        ButterKnife.bind(this);
        initUI();
    }

    private void initUI() {
        rl_jilu.setOnClickListener(this);
        ll_tousujianyi.setOnClickListener(this);
        ll_weixiufuwu.setOnClickListener(this);
        iv_shenghuojiaofei_fanhui= (RelativeLayout) findViewById(R.id.iv_shenghuojiaofei_fanhui);
//        tv_shopname= (TextView) findViewById(R.id.tv_shopname);
        ll_address= (LinearLayout) findViewById(R.id.ll_address);
        ll_shuifei= (LinearLayout) findViewById(R.id.ll_shuifei);
        ll_dianfei= (LinearLayout) findViewById(R.id.ll_dianfei);
        ll_ranqifei= (LinearLayout) findViewById(R.id.ll_ranqifei);
        ll_kuandaifei= (LinearLayout) findViewById(R.id.ll_kuandaifei);
        ll_wuyefei= (LinearLayout) findViewById(R.id.ll_wuyefei);
        ll_nuanqifei= (LinearLayout) findViewById(R.id.ll_nuanqifei);
        ll_youxiandianshi= (LinearLayout) findViewById(R.id.ll_youxiandianshi);
        ll_youxiandianshi.setOnClickListener(this);
        ll_nuanqifei.setOnClickListener(this);
        iv_shenghuojiaofei_fanhui.setOnClickListener(this);
//        ll_address.setOnClickListener(this);
        ll_shuifei.setOnClickListener(this);
        ll_dianfei.setOnClickListener(this);
        ll_ranqifei.setOnClickListener(this);
        ll_kuandaifei.setOnClickListener(this);
        ll_wuyefei.setOnClickListener(this);

    }

    private void getData(){
        mList_plot=new ArrayList<>();
        DialogUntil.showLoadingDialog(ShengHuoJiaoFeiActivity.this,"正在加载",true);
        String url= XY_Response.URL_FINDCOMMUNITYBYPER+"mobilePhone="+
                SPManager.getInstance().getString("mobilePhone","");

        RequestCenter.findCommunityByPer(url, new DisposeDataListener() {
            @Override
            public void onSuccess(Object responseObj) {
                SelectPlot selectPlot= (SelectPlot) responseObj;
                DialogUntil.closeLoadingDialog();
                if (selectPlot.getCode().equals("1000")){
                    mList_plot = selectPlot.getObj();
                    if (mList_plot==null){
                        MyUntil.show(ShengHuoJiaoFeiActivity.this,"请选择小区");
//                        Intent intent = new Intent(getContext(),XYXuanZeXiaoQuActivity.class);
//                        startActivity(intent);
                    }else {
                        showActionSheet(mList_plot);
                    }

                }else {
//                    Toast.makeText(getActivity(),selectPlot.getMsg(),Toast.LENGTH_LONG).show();
                }
            }

            @Override
            public void onFailure(Object reasonObj) {
                DialogUntil.closeLoadingDialog();
            }
        });
    }
    public void showActionSheet(final List<SelectPlot.Obj> mList)
    {
        if (mList.size()>0){
            commonActionSheetDialog = new CommonActionSheetDialog(ShengHuoJiaoFeiActivity.this);





            for (int i=0;i<mList.size();i++) {
//                    for (int j=0;j<i;j++){
//                        if (!(mList.get(i).getCommunityName()+mList.get(i).getNperName())
//                                .equals((mList.get(j).getCommunityName()+mList.get(j).getNperName())))
//                        {
//                            commonActionSheetDialog.addMenuItem(mList.get(i).getCommunityName()
//                                    + mList.get(i).getNperName());
//                        }
//                        else {
//                            commonActionSheetDialog.addMenuItem(mList.get(i).getCommunityName()
//                                    + mList.get(i).getNperName());
//                        }
//
//                    }


                commonActionSheetDialog.addMenuItem(mList.get(i).getCommunityName()
                        + mList.get(i).getNperName() +
                        mList.get(i).getUnitName() +
                        mList.get(i).getDoorName());
//                if (i>0){
//                    if (!(mList.get(i).getCommunityName()+mList.get(i).getNperName())
//                            .equals((mList.get(i-1).getCommunityName()+mList.get(i-1).getNperName())))
//                    {
//                        commonActionSheetDialog.addMenuItem(mList.get(i).getCommunityName()
//                                + mList.get(i).getNperName());
//                    }
//                }else {
//                    commonActionSheetDialog.addMenuItem(mList.get(i).getCommunityName()
//                            + mList.get(i).getNperName());
//                }
            }
//                for (SelectPlot.Obj item:mList){
//
//                    commonActionSheetDialog.addMenuItem(item.getCommunityName()
//                            +item.getNperName());
//                }
            commonActionSheetDialog.setMenuListener(new CommonActionSheetDialog.MenuListener() {
                @Override
                public void onItemSelected(int position, String item) {
                    communityId=mList.get(position).getCommunityId();
                    name=mList.get(position).getCommunityName()
                            + mList.get(position).getNperName() +
                            mList.get(position).getUnitName() +
                            mList.get(position).getDoorName();

//                    tv_shopname.setText(name);
                }
                @Override
                public void onCancel() {
                }
            });
            commonActionSheetDialog.show();
        }else {
            Toast.makeText(ShengHuoJiaoFeiActivity.this,"当前数据为空，请添加小区", Toast.LENGTH_LONG).show();
        }
    }


    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.iv_shenghuojiaofei_fanhui:
                finish();
                break;
//            case R.id.ll_address:
//                getData();
//                break;
            case R.id.ll_shuifei:
                startAli();
//                startActivity(SHUIFEI);
                break;
            case R.id.ll_dianfei:
                startAli();
//                startActivity(DIANFEI);
                break;
            case R.id.ll_ranqifei:
                startAli();
//                startActivity(RANQIFEI);
                break;
            case R.id.ll_kuandaifei:
                startAli();
//                startActivity(KUANDAIFEI);
                break;
            case R.id.ll_wuyefei:
                MyUntil.mStartActivity(ShengHuoJiaoFeiActivity.this,WuYeJiaoFeiActivity.class);
//                startActivity(WUYEFEI);
                break;
            //物业保修
            case R.id.ll_weixiufuwu:

                MyUntil.mStartActivity(ShengHuoJiaoFeiActivity.this,WuYeBaoXiuActivity.class);
                break;
            //投诉建议
            case R.id.ll_tousujianyi:
                Intent intent=new Intent(ShengHuoJiaoFeiActivity.this,YiJianFanKuiActivity.class);
                intent.putExtra("type",0);
                startActivity(intent);


//                MyUntil.mStartActivity(ShengHuoJiaoFeiActivity.this,YiJianFanKuiActivity.class);
                break;
            //记录
            case R.id.rl_jilu:
                MyUntil.mStartActivity(ShengHuoJiaoFeiActivity.this,LiShiJiLuActivty.class);

                break;
            case R.id.ll_nuanqifei:
                startAli();
                break;
            case R.id.ll_youxiandianshi:
                startAli();
                break;




        }
    }
    private void startActivity(int type){
//        Intent intent=new Intent(ShengHuoJiaoFeiActivity.this,ShengHuoPayActivity.class);
//        intent.putExtra("type",type);
//        startActivity(intent);
    }
    private void startAli(){
        String appPackageName="com.eg.android.AlipayGphone";
        try{
            Intent intent =getPackageManager().getLaunchIntentForPackage(appPackageName);
            intent.setData(Uri.parse("alipays://platformapi/startapp?appId=20000193"));
            startActivity(intent);
        }catch(Exception e){
            Toast.makeText(this, "没有安装支付宝", Toast.LENGTH_LONG).show();
        }
//        Intent i = new Intent();
////        i.setAction(Intent.);
//        i.setData(Uri.parse("alipays://platformapi/startapp?appId=49"));
//        startActivity(i);
    }
}

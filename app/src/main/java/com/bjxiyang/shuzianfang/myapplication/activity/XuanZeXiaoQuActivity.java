package com.bjxiyang.shuzianfang.myapplication.activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewParent;
import android.widget.AdapterView;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;

import com.baisi.myapplication.okhttp.listener.DisposeDataListener;
import com.bjxiyang.shuzianfang.R;
import com.bjxiyang.shuzianfang.myapplication.adapter.XYXuanZeXiaoQuAdapter;
import com.bjxiyang.shuzianfang.myapplication.manager.MyPreferences;
import com.bjxiyang.shuzianfang.myapplication.manager.SPManager;
import com.bjxiyang.shuzianfang.myapplication.model.SelectPlot;
import com.bjxiyang.shuzianfang.myapplication.response_xy.XY_Response;
import com.bjxiyang.shuzianfang.myapplication.ui.activity.MySwipeBackActivity;
import com.bjxiyang.shuzianfang.myapplication.until.DialogUntil;
import com.bjxiyang.shuzianfang.myapplication.until.MyUntil;
import com.bjxiyang.shuzianfang.myapplication.update.network.RequestCenter;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by Administrator on 2017/8/8 0008.
 */

public class XuanZeXiaoQuActivity extends MySwipeBackActivity
        implements View.OnClickListener,AdapterView.OnItemClickListener{
    /***
     * UI
     */
    @BindView(R.id.lv_fanhui)
    LinearLayout lv_fanhui;
    @BindView(R.id.ll_xuanzexiaoqu1_add)
    LinearLayout ll_xuanzexiaoqu1_add;
    @BindView(R.id.tv_dingweidizhi)
    TextView tv_dingweidizhi;
    @BindView(R.id.lv_qitashequ)
    ListView lv_qitashequ;
    @BindView(R.id.tv_qitashequ)
    TextView tv_qitashequ;
    @BindView(R.id.ll_youdizhi)
    LinearLayout ll_youdizhi;
    @BindView(R.id.tv_dingweiname)
    TextView tv_dingweiname;
    @BindView(R.id.tv_dingweiyezhu)
    TextView tv_dingweiyezhu;
    @BindView(R.id.ll_dizhi)
    LinearLayout ll_dizhi;
    /***
     * Date
     */
    private List<String> list;
    private List<SelectPlot.Obj> mList;
    private XYXuanZeXiaoQuAdapter adapter;
    public static XuanZeXiaoQuActivity xuanzexiaoquactivity;
    private int communityId=-1;
    private int nperId;
    private int floorId;
    private int unitId;


    private int guideResourceId = 0;//引导页图片资源id
    private View view;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_xuanzexiaoqu3);


        setGuideResId(R.drawable.tianjiaxiaoqu2);//添加引导页
        addGuideImage();//添加引导页
        xuanzexiaoquactivity=this;
        ButterKnife.bind(this);
        initUI();

        if (!SPManager.getInstance().getString("test_men","").equals("")){
            ll_youdizhi.setVisibility(View.VISIBLE);
            tv_dingweiname.setText(SPManager.getInstance().getString("realName","姓名"));
            tv_dingweidizhi.setText(SPManager.getInstance().getString("test_men",""));
            if (SPManager.getInstance().getInt("RoleType",1)==1){
                tv_dingweiyezhu.setText("租户");
            }else {
                tv_dingweiyezhu.setText("业主");
            }
        }
        getData();
    }

    private void initUI() {
        ll_dizhi.setOnClickListener(this);
        lv_fanhui.setOnClickListener(this);
        ll_xuanzexiaoqu1_add.setOnClickListener(this);
        tv_dingweidizhi.setOnClickListener(this);
        lv_qitashequ.setOnItemClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            //返回的按键
            case R.id.lv_fanhui:
                finish();
                break;
            //添加小区的按键
            case R.id.ll_xuanzexiaoqu1_add:
                MyUntil.mStartActivity(XuanZeXiaoQuActivity.this,XYXuanZeXiaoQuActivity.class);
                break;
            case R.id.tv_dingweidizhi:
                break;
            //选择以选择小区的按键
            case R.id.ll_dizhi:
                finish();
                break;
        }
    }
    private List<SelectPlot.Obj> getData(){
        mList=new ArrayList<>();
        DialogUntil.showLoadingDialog(XuanZeXiaoQuActivity.this,"正在加载",true);
        String url= XY_Response.URL_FINDCOMMUNITYBYPER+"mobilePhone="+
                SPManager.getInstance().getString("mobilePhone",null);

        RequestCenter.findCommunityByPer(url, new DisposeDataListener() {
            @Override
            public void onSuccess(Object responseObj) {
                SelectPlot selectPlot= (SelectPlot) responseObj;
                DialogUntil.closeLoadingDialog();
                if (selectPlot.getCode().equals("1000")){
                    mList = selectPlot.getObj();
                    if (mList==null){
                        Intent intent = new Intent(XuanZeXiaoQuActivity.this,XYXuanZeXiaoQuActivity.class);
                        startActivity(intent);
                    }else {
                        if (mList.size()<0){
                            SPManager.getInstance().putBoolean("isOne",false);
                        }else {
                            lv_qitashequ.setVisibility(View.VISIBLE);
                            tv_qitashequ.setVisibility(View.GONE);
//                            list=new ArrayList<String>();
//                            for (int i=0;i<mList.size();i++){
//                                list.add(mList.get(i).getCommunityName()+mList.get(i).getNperName()
//                                +mList.get(i).getFloorName()+mList.get(i).getUnitName()+mList.get(i).getDoorName());
//                            }
//                            for (int i=0;i<mList.size();i++) {
//                                for (int j=0;j<i;j++){
//                                    if ((mList.get(i).getCommunityName()+mList.get(i).getNperName())
//                                            .equals((mList.get(j).getCommunityName()+mList.get(j).getNperName())))
//                                    {
//                                        mList.remove(i);
//                                    }
//                                }
//                                if (mList.get(i).getCommunityId()==SPManager.getInstance().getInt("communityId",0)
//                                        &&mList.get(i).getNperId()==SPManager.getInstance().getInt("nperId_one",0)){
//                                    mList.remove(i);
//                                }
//                            }
                            adapter=new XYXuanZeXiaoQuAdapter(XuanZeXiaoQuActivity.this,mList);
                            lv_qitashequ.setAdapter(adapter);
                        }
                    }

                }
            }

            @Override
            public void onFailure(Object reasonObj) {
                DialogUntil.closeLoadingDialog();
            }
        });
        return mList;

    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        SPManager.getInstance().putString("communityName",mList.get(position).getCommunityName());
        nperId=mList.get(position).getNperId();
        communityId=mList.get(position).getCommunityId();
        floorId=mList.get(position).getFloorId();
        unitId=mList.get(position).getUnitId();

        SPManager.getInstance().putBoolean("isOne",true);
        SPManager.getInstance().putInt("RoleType",mList.get(position).getRoleType());
        SPManager.getInstance().putInt("communityId_one",communityId);
        SPManager.getInstance().putInt("communityId",communityId);
        SPManager.getInstance().putInt("nperId_one",nperId);
        SPManager.getInstance().putInt("floorId_one",floorId);
        SPManager.getInstance().putInt("unitId_one",unitId);
        SPManager.getInstance().putInt("doorId_one",mList.get(position).getDoorId());
        SPManager.getInstance().putString("test_men",mList.get(position).getCommunityName()
                +mList.get(position).getNperName());

        finish();
    }

    public void addGuideImage() {
        // Intent intent = getIntent();
        // String fristload = intent.getStringExtra("fristload");
        //查找通过setContentView上的根布局
        view = getWindow().getDecorView().findViewById(R.id.my_content_view);
        if (view == null) return;
        if (MyPreferences.activityIsGuided(this, this.getClass().getName())) {
            //引导过了
            return;
        }
        ViewParent viewParent = view.getParent();
        if (viewParent instanceof FrameLayout) {
            final FrameLayout frameLayout = (FrameLayout) viewParent;
            if (guideResourceId != 0) {//设置了引导图片
                final ImageView guideImage = new ImageView(this);
                FrameLayout.LayoutParams params = new FrameLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT);
                guideImage.setLayoutParams(params);
                guideImage.setScaleType(ImageView.ScaleType.FIT_XY);
                guideImage.setImageResource(guideResourceId);
                guideImage.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {

                        frameLayout.removeView(guideImage);

                        MyPreferences.setIsGuided(getApplicationContext(), XuanZeXiaoQuActivity.this.getClass().getName());//设为已引导
                    }
                });

                frameLayout.addView(guideImage);//添加引导图片
                SPManager.getInstance().putInt("status",3);

            }
        }
    }

    protected void setGuideResId(int resId) {
        this.guideResourceId = resId;
    }
    
}

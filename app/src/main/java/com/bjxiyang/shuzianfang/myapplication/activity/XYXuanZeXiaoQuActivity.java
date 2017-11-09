package com.bjxiyang.shuzianfang.myapplication.activity;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.annotation.Nullable;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewParent;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.baisi.myapplication.okhttp.listener.DisposeDataListener;
import com.bjxiyang.shuzianfang.R;
import com.bjxiyang.shuzianfang.myapplication.adapter.XYXuanZeXiaoQuAdapter2;
import com.bjxiyang.shuzianfang.myapplication.manager.MyPreferences;
import com.bjxiyang.shuzianfang.myapplication.manager.SPManager;
import com.bjxiyang.shuzianfang.myapplication.manager.UserManager;
import com.bjxiyang.shuzianfang.myapplication.model.AddFangWu;
import com.bjxiyang.shuzianfang.myapplication.model.Door;
import com.bjxiyang.shuzianfang.myapplication.model.Floor;
import com.bjxiyang.shuzianfang.myapplication.model.OpenDoor;
import com.bjxiyang.shuzianfang.myapplication.model.Plots;
import com.bjxiyang.shuzianfang.myapplication.model.SelectPlot;
import com.bjxiyang.shuzianfang.myapplication.model.Unit;
import com.bjxiyang.shuzianfang.myapplication.response_xy.BianLiDianResponse;
import com.bjxiyang.shuzianfang.myapplication.response_xy.XY_Response;
import com.bjxiyang.shuzianfang.myapplication.ui.activity.HomeActivity;
import com.bjxiyang.shuzianfang.myapplication.ui.activity.MySwipeBackActivity;
import com.bjxiyang.shuzianfang.myapplication.ui.activity.XYKeyAccredit;
import com.bjxiyang.shuzianfang.myapplication.ui.dialog.KaiMenYouXiDialog;
import com.bjxiyang.shuzianfang.myapplication.until.DialogUntil;
import com.bjxiyang.shuzianfang.myapplication.until.MyUntil;
import com.bjxiyang.shuzianfang.myapplication.until.SelectType;
import com.bjxiyang.shuzianfang.myapplication.until.UserType;
import com.bjxiyang.shuzianfang.myapplication.update.network.RequestCenter;
import com.bjxiyang.shuzianfang.myapplication.view.MyDialog;
import com.bjxiyang.shuzianfang.myapplication.widgets.CommonActionSheetDialog;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by gll on 17-5-23.
 */

public class  XYXuanZeXiaoQuActivity extends MySwipeBackActivity
        implements AdapterView.OnItemClickListener,View.OnClickListener {

    private RelativeLayout ib_menjinjilu_fanghui1;
    private List<String> mList;
    private ListView lv_xuanzexiaoqu;
    private TextView select_xiaoqu_title;
    private int type;
    private TextView add_xiaoqu_dizhi;
    private String xiaoqu_dizhi;
    private RelativeLayout ib_menjinjilu_fanghui;
    private TextView ib_quedingtianjia;
    private ImageView ib_yezhujiashu,ib_zuhu,ib_jiaren;
    private XYXuanZeXiaoQuAdapter2 adapter;
    private String xiaoqu;
    private String louhao;
    private String danyuan;
    private String men;
    private EditText et_name;
    private TextView tv_lianxidianhua;
    private LinearLayout queren;
    private LinearLayout xuanze;
    private LinearLayout select_yezhu,select_zuhu,select_jiaren;

    private Plots plots;
    private Floor floor;
    private Unit unit;
    private Door door;
    
    
    private int guideResourceId = 0;//引导页图片资源id
    private View view;
    
    
    private String name;
    private String uphone;
    public CommonActionSheetDialog commonActionSheetDialog;
    private String phone;
    private int communityId;
    private int nperId;
    private int floorId;
    private int unitId;
    private int doorId;
    private int roleType= UserType.USER_OWNER;
    private boolean isXiaoQu;
    List<SelectPlot.Obj> mList1;
    Handler handler=new Handler(new Handler.Callback() {
        @Override
        public boolean handleMessage(Message msg) {
            switch (msg.what){
                case 1000:
                    DialogUntil.closeLoadingDialog();
                    mList= (List<String>) msg.obj;
                    adapter=new XYXuanZeXiaoQuAdapter2(XYXuanZeXiaoQuActivity.this,mList);
                    lv_xuanzexiaoqu.setAdapter(adapter);
                    lv_xuanzexiaoqu.setOnItemClickListener(XYXuanZeXiaoQuActivity.this);
                    break;
            }
            return false;
        }
    });
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.xy_activity_xuanzexiaoqu);

        setGuideResId(R.drawable.xuanzexiaoqu3);//添加引导页
        addGuideImage();//添加引导页
        
        Intent intent=getIntent();
        isXiaoQu=intent.getBooleanExtra("isXiaoQu",false);
        initUI();
    }
    private void initUI() {
        et_name= (EditText) findViewById(R.id.et_name);
        tv_lianxidianhua= (TextView) findViewById(R.id.tv_lianxidianhua);
        tv_lianxidianhua.setText(SPManager.getInstance().getString("mobilePhone",""));
        select_yezhu= (LinearLayout) findViewById(R.id.select_yezhu);
        select_zuhu= (LinearLayout) findViewById(R.id.select_zuhu);
//        select_jiaren= (LinearLayout) findViewById(R.id.select_jiaren);
        select_yezhu.setOnClickListener(this);
        select_zuhu.setOnClickListener(this);
//        select_jiaren.setOnClickListener(this);
//        ib_jiaren= (ImageButton) findViewById(R.id.ib_jiaren);
        ib_yezhujiashu= (ImageView) findViewById(R.id.ib_yezhujiashu);
//        ib_yezhujiashu.setOnClickListener(this);
        ib_zuhu= (ImageView) findViewById(R.id.ib_zuhu);
//        ib_zuhu.setOnClickListener(this);
        add_xiaoqu_dizhi= (TextView) findViewById(R.id.add_xiaoqu_dizhi);
        add_xiaoqu_dizhi.setOnClickListener(this);
        ib_menjinjilu_fanghui1= (RelativeLayout) findViewById(R.id.ib_menjinjilu_fanghui1);
        ib_menjinjilu_fanghui1.setOnClickListener(this);
        ib_quedingtianjia= (TextView) findViewById(R.id.ib_quedingtianjia);
        ib_quedingtianjia.setOnClickListener(this);
        queren= (LinearLayout) findViewById(R.id.queren);
        xuanze= (LinearLayout) findViewById(R.id.xuanze);
        ib_menjinjilu_fanghui= (RelativeLayout) findViewById(R.id.ib_menjinjilu_fanghui);
        ib_menjinjilu_fanghui.setOnClickListener(this);
        lv_xuanzexiaoqu= (ListView) findViewById(R.id.lv_xuanzexiaoqu);
        select_xiaoqu_title= (TextView) findViewById(R.id.select_xiaoqu_title);
        getData(type);

    }
    //得到模拟数据
    private List<String> getData(int type1){
        DialogUntil.showLoadingDialog(XYXuanZeXiaoQuActivity.this,"正在加载数据",true);
        final int type2=type1;
        mList=new ArrayList<>();
        switch (type1){
            case 0:
                phone= SPManager.getInstance().getString("mobilePhone","");
                String url= XY_Response.URL_FINDCOMMUNITY+"mobilePhone="+phone;
                RequestCenter.findCommunity(url, new DisposeDataListener() {
                    @Override
                    public void onSuccess(Object responseObj) {
                        lv_xuanzexiaoqu.setEnabled(true);
                        plots= (Plots) responseObj;
                        if (plots.getCode().equals("1000")){
                            List<Plots.Obj> list= plots.getObj();
                            for (int i = 0; i< list.size(); i++){
                                mList.add(list.get(i).getNperName());
                                if (list.get(i).getNperName().equals("")){
                                    mList.remove(i);
                                }
                            }
                        }else {
                            mList.add("当前列表为空");
                            lv_xuanzexiaoqu.setEnabled(false);
                           }
                        Message message=new Message();
                        message.obj=mList;
                        message.what=1000;
                        handler.sendMessage(message);
                    }

                    @Override
                    public void onFailure(Object reasonObj){
                        qinqiushibai();
                    }
                });
                break;
            case 1:
                String url1=XY_Response.URL_FINDFLOOR+"mobilePhone="
                                +phone+"&communityId="+communityId+"&nperId="+nperId;
                RequestCenter.findFloor(url1, new DisposeDataListener() {
                    @Override
                    public void onSuccess(Object responseObj) {
                        lv_xuanzexiaoqu.setEnabled(true);
                        mList=new ArrayList<String>();
                        floor = (Floor) responseObj;
                        if (floor.getCode().equals("1000")||floor.getCode()=="1000"){
                            List<Floor.Obj> list= floor.getObj();
                            for (int i = 0; i< list.size(); i++){
                                mList.add(list.get(i).getFloorName());
                                if (list.get(i).getFloorName().equals("")) {
                                    mList.remove(i);
                                }
                                
                            }
                        }else {
                            mList.add("当前列表为空");
                            lv_xuanzexiaoqu.setEnabled(false);
                        }
                        Message message=new Message();
                        message.obj=mList;
                        message.what=1000;
                        handler.sendMessage(message);
                    }

                    @Override
                    public void onFailure(Object reasonObj) {
                        qinqiushibai();
                    }
                });
                break;
            case 2:
                String url2=XY_Response.URL_FINDUNIT+"mobilePhone="
                        +phone+"&communityId="+communityId+"&nperId="+nperId+"&floorId="+floorId;
                RequestCenter.findUnit(url2, new DisposeDataListener() {
                    @Override
                    public void onSuccess(Object responseObj) {
                        lv_xuanzexiaoqu.setEnabled(true);
                        mList=new ArrayList<String>();
                        unit = (Unit) responseObj;
                        if (unit.getCode().equals("1000")){
                            List<Unit.Obj> list= unit.getObj();
                            for (int i = 0; i< list.size(); i++){
                                mList.add(list.get(i).getUnitName());
                                if (list.get(i).getUnitName().equals("")) {
                                    mList.remove(i);
                                }
                            }
                        }else {
                            mList.add("当前列表为空");
                            lv_xuanzexiaoqu.setEnabled(false);
                        }
                        Message message=new Message();
                        message.obj=mList;
                        message.what=1000;
                        handler.sendMessage(message);
                    }

                    @Override
                    public void onFailure(Object reasonObj) {
                        qinqiushibai();
                    }
                });
                break;
            case 3:
                String url3=XY_Response.URL_FINDDOOR+"mobilePhone="
                        +phone+"&communityId="+communityId+"&nperId="+
                        nperId+"&floorId="+floorId+"&unitId="+unitId;
                RequestCenter.findDoor(url3, new DisposeDataListener() {
                    @Override
                    public void onSuccess(Object responseObj) {

                        lv_xuanzexiaoqu.setEnabled(true);
                        mList=new ArrayList<String>();
                        door = (Door) responseObj;
                        if (door.getCode().equals("1000")){
                            List<Door.Obj> list= door.getObj();
                            for (int i = 0; i< list.size(); i++){
                                mList.add(list.get(i).getDoorName());
                                if (list.get(i).getDoorName().equals("")) {
                                    mList.remove(i);
                                }
                            }
                        }else {
                            mList.add("当前列表为空");
                            lv_xuanzexiaoqu.setEnabled(false);
                        }
                        Message message=new Message();
                        message.obj=mList;
                        message.what=1000;
                        handler.sendMessage(message);
                    }

                    @Override
                    public void onFailure(Object reasonObj) {
                        qinqiushibai();
                    }
                });

                break;

        }
        return mList;
    }
    //重写返回键的处理事件
    @Override
    public void onBackPressed() {
       onBack();
    }
    //点击item的回掉
    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        if (isXiaoQu){
            Intent intent=new Intent();
            intent.putExtra("communityId",plots.getObj().get(position).getCommunityId());
            intent.putExtra("communityName",xiaoqu = mList.get(position));
            setResult(0,intent);
            finish();
        }else {
            if (type < 4) {
                if (type == SelectType.XIAOQU) {
                    communityId = plots.getObj().get(position).getCommunityId();
                    nperId = plots.getObj().get(position).getNperId();

                    type = SelectType.LOUHAO;
                    xiaoqu = mList.get(position);
                    update();
                } else if (type == SelectType.LOUHAO) {
                    floorId = floor.getObj().get(position).getFloorId();
                    type = SelectType.DANYUAN;
                    louhao = mList.get(position);
                    update();
                } else if (type == SelectType.DANYUAN) {
                    unitId = unit.getObj().get(position).getUnitId();
                    type = SelectType.MEN;
                    danyuan = mList.get(position);
                    update();

                } else if (type == SelectType.MEN) {
                    doorId = door.getObj().get(position).getDoorId();
                    type++;
                    showQueren();
                    //拼接地址
                    men = mList.get(position);
                    xiaoqu_dizhi = xiaoqu + "-" + louhao + "-" + danyuan + "-" + men;
                    add_xiaoqu_dizhi.setText(xiaoqu_dizhi);
                    return;
                }
                setTitle();
            }
        }

    }
    //显示确认的页面
    private void showQueren(){
        queren.setVisibility(View.VISIBLE);
        xuanze.setVisibility(View.GONE);

    }
    //显示选择的页面
    private void showXuanze(){
        xuanze.setVisibility(View.VISIBLE);
        queren.setVisibility(View.GONE);
    }
    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.ib_yezhujiashu:
                roleType= UserType.USER_OWNER;
//                ib_jiaren.setBackgroundResource(R.drawable.k_btn_zhong_n);
                ib_yezhujiashu.setBackgroundResource(R.drawable.a_icon_dui);
                ib_zuhu.setBackgroundResource(R.drawable.k_btn_zhong_n);
                break;
            case R.id.select_yezhu:
                roleType= UserType.USER_OWNER;
//                ib_jiaren.setBackgroundResource(R.drawable.k_btn_zhong_n);
                ib_yezhujiashu.setBackgroundResource(R.drawable.a_icon_dui);
                ib_zuhu.setBackgroundResource(R.drawable.k_btn_zhong_n);
                break;
            case R.id.select_zuhu:
//                ib_jiaren.setBackgroundResource(R.drawable.k_btn_zhong_n);
                roleType= UserType.USER_LESSEE;
                ib_zuhu.setBackgroundResource(R.drawable.a_icon_dui);
                ib_yezhujiashu.setBackgroundResource(R.drawable.k_btn_zhong_n);
                break;
//            case R.id.ib_zuhu:
//                ib_jiaren.setBackgroundResource(R.drawable.k_btn_zhong_n);
//                roleType= UserType.USER_LESSEE;
//                ib_zuhu.setBackgroundResource(R.drawable.a_icon_dui);
//                ib_yezhujiashu.setBackgroundResource(R.drawable.k_btn_zhong_n);
//                break;
//            case R.id.select_jiaren:
//                ib_jiaren.setBackgroundResource(R.drawable.a_icon_dui);
//                roleType= UserType.USER_FOLK;
//                ib_zuhu.setBackgroundResource(R.drawable.k_btn_zhong_n);
//                ib_yezhujiashu.setBackgroundResource(R.drawable.k_btn_zhong_n);
//                break;
            //返回的按键
            case R.id.ib_menjinjilu_fanghui:
                onBack();
                break;
            //返回的按键
            case R.id.ib_menjinjilu_fanghui1:
                onBack();
                break;
            //确认添加的按键
            case R.id.ib_quedingtianjia:
                name= String.valueOf(et_name.getText());
                uphone= String.valueOf(tv_lianxidianhua.getText());
                if (!isMobilephone(uphone)){
                    Toast.makeText(XYXuanZeXiaoQuActivity.this,"请输入正确的手机号", Toast.LENGTH_LONG).show();
                    return;
                }
                DialogUntil.showLoadingDialog(XYXuanZeXiaoQuActivity.this,"正在提交",true);
                //提交的按钮，提交数据到服务器。并跳转到小区列表页面
                String addUrl=XY_Response.URL_ADDCOMMUNITY+"mobilePhone="
                        +phone+"&communityId="+communityId+"&nperId="+
                        nperId+"&floorId="+floorId+"&unitId="+unitId+"&doorId="+doorId+
                        "&roleType="+roleType+"&customerName="+name+"&customerTel="+uphone;
                RequestCenter.addCommunity(addUrl, new DisposeDataListener() {
                    @Override
                    public void onSuccess(Object responseObj) {
                        DialogUntil.closeLoadingDialog();
                         AddFangWu fanHui = (AddFangWu) responseObj;
                        if (fanHui.getCode().equals("1000")) {
                            boolean firstload=SPManager.getInstance().getBoolean("check",false);
                            if (!firstload){
                                HomeActivity.status=2;
                                SPManager.getInstance().putInt("status",4);
                                SPManager.getInstance().putBoolean("check",true);
                            }
                            String url_add = BianLiDianResponse.URL_ORDER_USER_ADDRESS_ADD +
                                    "name=" + name + "&sex=" + 1 + "&phone=" + phone + "&communityId=" + communityId +
                                    "&nperId=" + nperId + "&floorId=" + floorId + "&unitId=" + unitId +
                                    "&doorId=" + doorId+"&address="+"";
                                RequestCenter.order_user_address_add(url_add, new DisposeDataListener() {
                                    @Override
                                    public void onSuccess(Object responseObj) {
                                    }
                                    @Override
                                    public void onFailure(Object reasonObj) {
                                    }
                                });
                            if (fanHui.getObj()!=null) {
                                if (fanHui.getObj().getType() == 1) {
                                    KaiMenYouXiDialog kaiMenYouXiDialog =
                                            new KaiMenYouXiDialog(XYXuanZeXiaoQuActivity.this, getOpenDoor_obj(fanHui.getObj()));
                                    kaiMenYouXiDialog.show();
                                    kaiMenYouXiDialog.setOnDismissListener(new DialogInterface.OnDismissListener() {
                                        @Override
                                        public void onDismiss(DialogInterface dialog) {
                                            finish();
                                            Intent intent = new Intent(XYXuanZeXiaoQuActivity.this, XYKeyAccredit.class);
                                            startActivity(intent);
                                        }
                                    });


                                } else if (fanHui.getObj().getType() == 0) {
                                    finish();
                                    Intent intent = new Intent(XYXuanZeXiaoQuActivity.this, XYKeyAccredit.class);
                                    startActivity(intent);
                                } else {
                                    finish();
                                    Intent intent = new Intent(XYXuanZeXiaoQuActivity.this, XYKeyAccredit.class);
                                    startActivity(intent);
                                }
                            }
                            }else {
                                MyUntil.show(XYXuanZeXiaoQuActivity.this,fanHui.getMsg());
                            }


                    }
                    @Override
                    public void onFailure(Object reasonObj) {
                        DialogUntil.closeLoadingDialog();
                        MyDialog.showDialog(XYXuanZeXiaoQuActivity.this);
                    }
                });
                break;
            case R.id.add_xiaoqu_dizhi:
                showXuanze();
                getData(type);
                adapter=new XYXuanZeXiaoQuAdapter2(this,mList);
                lv_xuanzexiaoqu.setAdapter(adapter);
                adapter.notifyDataSetChanged();
                setTitle();
//                getData1();
                break;

        }
    }
    private void getData1(){
        mList1=new ArrayList<>();
        DialogUntil.showLoadingDialog(XYXuanZeXiaoQuActivity.this,"正在加载",true);
        String url= XY_Response.URL_FINDCOMMUNITYBYPER+"mobilePhone="+
                UserManager.getInstance().getUser().getObj().getMobilePhone();
        RequestCenter.findCommunityByPer(url, new DisposeDataListener() {
            @Override
            public void onSuccess(Object responseObj) {
                SelectPlot selectPlot= (SelectPlot) responseObj;
                DialogUntil.closeLoadingDialog();
                if (selectPlot.getCode().equals("1000")){
                    mList1=selectPlot.getObj();
                    showActionSheet();
                }else {
                    Toast.makeText(XYXuanZeXiaoQuActivity.this,selectPlot.getMsg(),Toast.LENGTH_LONG).show();
                }
            }

            @Override
            public void onFailure(Object reasonObj) {
                DialogUntil.closeLoadingDialog();
                MyDialog.showDialog(XYXuanZeXiaoQuActivity.this,"请检查网络连接");
            }
        });

    }

    public void showActionSheet(){
        if (mList1.size()>0) {
            if (commonActionSheetDialog == null) {
                commonActionSheetDialog = new CommonActionSheetDialog(this);

                for (SelectPlot.Obj item : mList1) {
                    commonActionSheetDialog.addMenuItem(item.getCommunityName()
                            +item.getNperName()+item.getFloorName()
                            + item.getUnitName()
                            + item.getDoorName());
                }

                commonActionSheetDialog.setMenuListener(new CommonActionSheetDialog.MenuListener() {
                    @Override
                    public void onItemSelected(int position, String item) {

                        communityId = mList1.get(position).getCommunityId();
                        nperId = mList1.get(position).getNperId();
                        floorId = mList1.get(position).getFloorId();
                        unitId = mList1.get(position).getUnitId();
                        doorId = mList1.get(position).getDoorId();
                        add_xiaoqu_dizhi.setText(mList1.get(position).getCommunityName()
                                + mList1.get(position).getNperName() +
                                mList1.get(position).getUnitName() +
                                mList1.get(position).getDoorName());
                        roleType=mList1.get(position).getRoleType();
                    }

                    @Override
                    public void onCancel() {

                    }
                });

            }
            commonActionSheetDialog.show();
        }else {
//            select_xiaoqu_tianjiashouquan.setText("请添加小区");
        }

    }

    //设置title
    private void setTitle(){
        switch (type){
            case SelectType.XIAOQU:
                select_xiaoqu_title.setText("选择小区");
                break;
            case SelectType.LOUHAO:
                select_xiaoqu_title.setText("选择楼号");
                break;
            case SelectType.DANYUAN:
                select_xiaoqu_title.setText("选择单元");
                break;
            case SelectType.MEN:
                select_xiaoqu_title.setText("选择门号");
                break;
        }
    }

    //返回的逻辑操作
    private void onBack(){
        showXuanze();
        type--;
        if (type<0){
            super.onBackPressed();
        }else {
            getData(type);
            adapter=new XYXuanZeXiaoQuAdapter2(this,mList);
            lv_xuanzexiaoqu.setAdapter(adapter);
            adapter.notifyDataSetChanged();
            setTitle();
        }
    }
    //更新数据
    private void update(){
        getData(type);
        adapter=new XYXuanZeXiaoQuAdapter2(this,mList);
        lv_xuanzexiaoqu.setAdapter(adapter);
        adapter.notifyDataSetChanged();
    }
    //请求失败的方法
    private void qinqiushibai(){
        mList=new ArrayList<String>();
        mList.add("当前列表为空");
        lv_xuanzexiaoqu.setEnabled(false);
        Message message=new Message();
        message.obj=mList;
        message.what=1000;
        handler.sendMessage(message);
    }
    //判断手机号
    public static boolean isMobilephone(String phone) {
        if (phone.startsWith("86") || phone.startsWith("+86")) {
            phone = phone.substring(phone.indexOf("86") + 2);
        }
        Pattern p = Pattern.compile("^((13[0-9])|(14[0-9])|(15[0-9])|(17[0-9])|(18[0-9]))\\d{8}$");
        Matcher m = p.matcher(phone);
        return m.matches();
    }
    //将类型进行转换
    private OpenDoor.ObjBean getOpenDoor_obj(AddFangWu.ObjBean fanhui_obj){
        OpenDoor.ObjBean objBean=new OpenDoor.ObjBean();
        objBean.setBondLimit(fanhui_obj.getBondLimit());
        objBean.setBondName(fanhui_obj.getBondLimit());
        objBean.setBondUrl(fanhui_obj.getBondUrl());
        objBean.setType(fanhui_obj.getType());
        objBean.setValidityDate(objBean.getValidityDate());
        return objBean;
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

                        MyPreferences.setIsGuided(getApplicationContext(), XYXuanZeXiaoQuActivity.this.getClass().getName());//设为已引导
                    }
                });
                frameLayout.addView(guideImage);//添加引导图片

            }
        }
    }
    protected void setGuideResId(int resId) {
        this.guideResourceId = resId;
    }
}

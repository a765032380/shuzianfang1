package com.bjxiyang.shuzianfang.myapplication.view;

import android.app.Activity;
import android.content.Context;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.baisi.imoocsdk.imageloader.ImageLoaderManager;
import com.baisi.myapplication.okhttp.listener.DisposeDataListener;
import com.bjxiyang.shuzianfang.R;
import com.bjxiyang.shuzianfang.myapplication.manager.SPManager;
import com.bjxiyang.shuzianfang.myapplication.model.FanHui;
import com.bjxiyang.shuzianfang.myapplication.model.PermissionList;
import com.bjxiyang.shuzianfang.myapplication.response_xy.XY_Response;
import com.bjxiyang.shuzianfang.myapplication.until.DialogUntil;
import com.bjxiyang.shuzianfang.myapplication.until.UserState;
import com.bjxiyang.shuzianfang.myapplication.until.UserType;
import com.bjxiyang.shuzianfang.myapplication.update.network.RequestCenter;
import com.bjxiyang.shuzianfang.myapplication.view.CircleImageView;
import com.bjxiyang.shuzianfang.myapplication.view.MyDialog;
import com.bjxiyang.shuzianfang.myapplication.view.SwipeListLayout;

import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * Created by gll on 17-5-23.
 */

public class XYKeyaccreditAdapter extends BaseAdapter {
    private ImageLoaderManager manager;
    private Context mContext;
    private List<PermissionList.Obj> mList;
    HashMap<Integer, View> lmap = new HashMap<Integer, View>();
    private int communityId;
    private int nperId;
    private int floorId;
    private int unitId;
    private int doorId;
    private int status;
    private int activeUser;
    private int permissionId;
    private int clickTemp = -1;//标识被选择的item
    private int[] clickedList;//这个数组用来存放item的点击状态
    private Viewholder viewholder;
    private PermissionList.Obj obj;
    private PermissionList.Obj obj1;
    private PermissionList.Obj obj2;
    private Set<SwipeListLayout> sets = new HashSet();
    private TextView tv_delete;
    private SwipeListLayout sll_main;
    private int c_memberId = Integer.parseInt(SPManager.getInstance().getString("c_memberId",""));
    public  XYKeyaccreditAdapter(Context mContext, List mList) {
        this.mContext = mContext;
        this.mList = mList;
        clickedList=new int[mList.size()];
        for (int i =0;i<mList.size();i++){
            clickedList[i]=0;      //初始化item点击状态的数组
        }
    }
    @Override
    public int getCount() {
        return mList.size();
    }

    @Override
    public Object getItem(int position) {
        return mList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @SuppressWarnings("ResourceAsColor")
    @Override
    public View getView(final int position, View view, ViewGroup parent) {

        viewholder=null;
        if (lmap.get(position) ==null){
            viewholder=new Viewholder();
            view= LayoutInflater.from(mContext).inflate(R.layout.xy_item_keyaccredit3,null);
            viewholder.iv_touxiang= (CircleImageView) view.findViewById(R.id.iv_touxiang);
            viewholder.name= (TextView) view.findViewById(R.id.name);
            viewholder.iv_zukejiaren= (TextView) view.findViewById(R.id.iv_zukejiaren);
            viewholder.item_address= (TextView) view.findViewById(R.id.item_address);
            viewholder.iv_startusing= (ImageView) view.findViewById(R.id.iv_startusing);
            viewholder.ib_jinyong_qiyong= (TextView) view.findViewById(R.id.ib_jinyong_qiyong);
            viewholder.tv_zhuangtai= (TextView) view.findViewById(R.id.tv_zhuangtai);
            sll_main= (SwipeListLayout) view.findViewById(R.id.sll_main);
            tv_delete = (TextView) view.findViewById(R.id.tv_delete);
            sll_main.setOnSwipeStatusListener(new MyOnSlipStatusListener(sll_main));
            view.setTag(viewholder);
            lmap.put(position,view);
        }else {
            view = lmap.get(position);
            viewholder= (Viewholder) view.getTag();
        }
        manager= ImageLoaderManager.getInstance(mContext);

        obj=mList.get(position);

        //图像处理直接用框架
        //文本处理直接拿服务器数据
        //设置头像 地址为null
        if (mList.get(position).getHeadPhotoUrl()!=null
                &&!mList.get(position).getHeadPhotoUrl().equals("")){
            manager.displayImage(viewholder.iv_touxiang,mList.get(position).getHeadPhotoUrl());
        }

        viewholder.name.setText(mList.get(position).getNickName());
        viewholder.item_address.setText(obj.getCommunityName()+(obj.getNperName())+"-"+obj.getFloorName()
                +"号楼-"+obj.getUnitName()+"单元-"+obj.getDoorName()+"室");
        switch (mList.get(position).getRoleType()){
            //业主类型
            case UserType.USER_OWNER:
                showYeZhu(viewholder);
                break;
                //租户类型
            case UserType.USER_LESSEE:
                showZuHu(viewholder);
                break;
                //业主家人类型
            case UserType.USER_FOLK:
                showJiaRen(viewholder);

                break;
                //租户家人类型
            case UserType.USER_LESSEE_HOME:
                showZuHuJiaRen(viewholder);

                break;


        }


        delete(position);
//

        if (obj.getStatus()== UserState.FORBIDDEN){
            viewholder.ib_jinyong_qiyong.setBackgroundResource(R.drawable.a_btn_startusing);
            viewholder.ib_jinyong_qiyong.setText("确认启用");
            viewholder.ib_jinyong_qiyong.setTextColor(Color.WHITE);
            viewholder.tv_zhuangtai.setText("已禁用");
            viewholder.tv_zhuangtai.setTextColor(R.color.yijinyong);
            viewholder.iv_startusing.setVisibility(View.VISIBLE);
            viewholder.iv_startusing.setImageResource(R.drawable.a_icon_forbidden);
        }else if (obj.getStatus()== UserState.START_USING){
            viewholder.ib_jinyong_qiyong.setText("禁用");
            viewholder.ib_jinyong_qiyong.setTextColor(R.color.yijinyong);
            viewholder.ib_jinyong_qiyong.setBackgroundResource(R.drawable.a_btn_forbidden);
            viewholder.tv_zhuangtai.setText("已启用");
            viewholder.tv_zhuangtai.setTextColor(R.color.yiqiyong);
            viewholder.iv_startusing.setVisibility(View.VISIBLE);
            viewholder.iv_startusing.setImageResource(R.drawable.a_icon_startusing);
        }
        else if (obj.getStatus()== UserState.SHENQINGZHONG){
            viewholder.tv_zhuangtai.setText("审核中");
            viewholder.tv_zhuangtai.setTextColor(R.color.shenqizhong);
            viewholder.iv_startusing.setVisibility(View.GONE);
            viewholder.ib_jinyong_qiyong.setVisibility(View.VISIBLE);
        }else if (obj.getStatus()== UserState.JUJUE){
            viewholder.iv_startusing.setVisibility(View.GONE);
            viewholder.tv_zhuangtai.setText("已拒绝");
            viewholder.tv_zhuangtai.setTextColor(R.color.yijujue);
            viewholder.ib_jinyong_qiyong.setVisibility(View.GONE);
        }
        final int position1=position;
        viewholder.ib_jinyong_qiyong.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                obj1=mList.get(position1);
                communityId=obj1.getCommunityId();
                nperId=obj1.getNperId();
                floorId=obj1.getFloorId();
                unitId=obj1.getUnitId();
                doorId=obj1.getDoorId();
                int status1 =obj1.getStatus();
                permissionId=obj1.getPermissionId();
                DialogUntil.showLoadingDialog(mContext,"正在提交",true);

                int status2=0;
                if (UserState.START_USING==status1){
                    status2= UserState.FORBIDDEN;
                }else if (UserState.FORBIDDEN==status1|| UserState.SHENQINGZHONG==status1){
                    status2= UserState.START_USING;
                }
        String url= XY_Response.URL_UPDATEPERMISSIONS
        +"mobilePhone="+ SPManager.getInstance().getString("mobilePhone","")
        +"&communityId="+communityId+"&nperId="+nperId+"&floorId="+floorId+"&unitId="
        +unitId+"&doorId="+doorId+"&status="+status2+"&permissionId="+permissionId;

                RequestCenter.updatePermission(url, new DisposeDataListener() {
                    @Override
                    public void onSuccess(Object responseObj) {
                        DialogUntil.closeLoadingDialog();
                        FanHui fanHui= (FanHui) responseObj;
                        if (fanHui.getCode().equals("1000")){
                            //这些数据需要上传给服务器的
                            if (obj1.getStatus()== UserState.FORBIDDEN|| UserState.SHENQINGZHONG==obj1.getStatus()){
                                obj1.setStatus(UserState.START_USING);
                            }else {
                                obj1.setStatus(UserState.FORBIDDEN);
                            }

                            notifyDataSetChanged();
                        }else {
                            Toast.makeText(mContext,fanHui.getMsg(), Toast.LENGTH_LONG).show();
                        }
                    }

                    @Override
                    public void onFailure(Object reasonObj) {
                        DialogUntil.closeLoadingDialog();
                        MyDialog.showDialog((Activity) mContext,"请检查网络链接");
                    }
                });


            }
        });
        return view;
    }
    private void showYeZhu(Viewholder viewholder){
        viewholder.iv_zukejiaren.setBackgroundResource(R.drawable.c_btn_touxiang);
        viewholder.iv_zukejiaren.setText("业主");
    }
    private void showZuHu(Viewholder viewholder){
        viewholder.iv_zukejiaren.setBackgroundResource(R.drawable.c_btn_touxaing3);
        viewholder.iv_zukejiaren.setText("租客");
    }
    private void showZuHuJiaRen(Viewholder viewholder){
        viewholder.iv_zukejiaren.setBackgroundResource(R.drawable.c_btn_touxaing3);
        viewholder.iv_zukejiaren.setText("租客");
    }
    private void showJiaRen(Viewholder viewholder){
        viewholder.iv_zukejiaren.setBackgroundResource(R.drawable.c_btn_touxiang);
        viewholder.iv_zukejiaren.setText("业主家人");
    }

    private class Viewholder{
        //状态前图片
        ImageView iv_startusing;
        //头像
        CircleImageView iv_touxiang;
        //姓名
        TextView name;
        //租客还是家人
        TextView iv_zukejiaren;
        //地址
        TextView item_address;
        //时间
        TextView item_date;
        //禁用还是启用
        TextView ib_jinyong_qiyong;
        //显示禁用还是启用
        TextView tv_zhuangtai;
        //修改按钮
        TextView item_xiugai;

    }
    class MyOnSlipStatusListener implements SwipeListLayout.OnSwipeStatusListener {

        private SwipeListLayout slipListLayout;

        public MyOnSlipStatusListener(SwipeListLayout slipListLayout) {
            this.slipListLayout = slipListLayout;
        }

        @Override
        public void onStatusChanged(SwipeListLayout.Status status) {
            if (status == SwipeListLayout.Status.Open) {
                //若有其他的item的状态为Open，则Close，然后移除
                if (sets.size() > 0) {
                    for (SwipeListLayout s : sets) {
                        s.setStatus(SwipeListLayout.Status.Close, true);
                        sets.remove(s);
                    }
                }
                sets.add(slipListLayout);
            } else {
                if (sets.contains(slipListLayout))
                    sets.remove(slipListLayout);
            }
        }

        @Override
        public void onStartCloseAnimation() {

        }

        @Override
        public void onStartOpenAnimation() {

        }

    }
    private void delete(final int position1){
        tv_delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                sll_main.setStatus(SwipeListLayout.Status.Close, true);


        obj2=mList.get(position1);
                communityId=obj2.getCommunityId();
                nperId=obj2.getNperId();
                floorId=obj2.getFloorId();
                unitId=obj2.getUnitId();
                doorId=obj2.getDoorId();
                status=obj2.getStatus();
                permissionId=obj2.getPermissionId();
                DialogUntil.showLoadingDialog(mContext,"正在提交",true);
                String url1= XY_Response.URL_DELETEPERMISSIONS+"mobilePhone="+
                        SPManager.getInstance().getString("mobilePhone","")+
                        "&communityId="+communityId+"&nperId="+nperId+"&floorId="+floorId+
                        "&unitId="+unitId+"&doorId="+doorId+
                        "&permissionId="+permissionId;

                RequestCenter.deletePermission(url1, new DisposeDataListener() {
                    @Override
                    public void onSuccess(Object responseObj) {
                        DialogUntil.closeLoadingDialog();
                        FanHui fanHui= (FanHui) responseObj;
                        if (fanHui.getCode().equals("1000")){
//                            Toast.makeText(mContext,"连接成功",Toast.LENGTH_LONG).show();
                            mList.remove(position1);
                            notifyDataSetChanged();
                        }
                    }

                    @Override
                    public void onFailure(Object reasonObj) {
                        DialogUntil.closeLoadingDialog();
                        MyDialog.showDialog((Activity) mContext,"请检查网络连接");
                    }
                });
            }
        });
    }
    public void setSeclection(int posiTion) {
        clickTemp = posiTion;
    }
}

package com.bjxiyang.shuzianfang.myapplication.ui.view;

import android.animation.ObjectAnimator;
import android.content.Context;
import android.support.v7.app.AlertDialog;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.baisi.myapplication.okhttp.listener.DisposeDataListener;
import com.bjxiyang.shuzianfang.R;
import com.bjxiyang.shuzianfang.myapplication.manager.SPManager;
import com.bjxiyang.shuzianfang.myapplication.model.ByCom;
import com.bjxiyang.shuzianfang.myapplication.model.OpenDoor;
import com.bjxiyang.shuzianfang.myapplication.response_xy.XY_Response;
import com.bjxiyang.shuzianfang.myapplication.ui.dialog.KaiMenDialog;
import com.bjxiyang.shuzianfang.myapplication.ui.dialog.KaiMenYouXiDialog;
import com.bjxiyang.shuzianfang.myapplication.until.DialogUntil;
import com.bjxiyang.shuzianfang.myapplication.update.network.RequestCenter;
import com.bjxiyang.shuzianfang.myapplication.view.MyDialog;

import java.util.List;

/**
 * Created by Administrator on 2017/6/16 0016.
 */

public class MyAdapter2 extends RecyclerView.Adapter<MyAdapter2.ViewHolder>  {
    public List<ByCom.Obj> mList;
    private int lockId;
    private int customberId;
    private View view;
    public MyAdapter2(List<ByCom.Obj> mList) {
        this.mList = mList;
    }
    //创建新View，被LayoutManager所调用
    @Override
    public ViewHolder onCreateViewHolder(ViewGroup viewGroup, int viewType) {
        view= LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.item_key2,viewGroup,false);
        ViewHolder vh = new ViewHolder(view);
        return vh;
    }
    //将数据与界面进行绑定的操作
    @Override
    public void onBindViewHolder(final ViewHolder viewHolder, final int position) {
        viewHolder.tv_open_door.setText(mList.get(position).getLockName());
//        int selectColor=position%3;
//        if (selectColor==0){
//            viewHolder.iv_open_door.setBackgroundResource(R.drawable.a_btn_lan);
//        }else if (selectColor==1){
//            viewHolder.iv_open_door.setBackgroundResource(R.drawable.a_btn_zi);
//        }else {
//            viewHolder.iv_open_door.setBackgroundResource(R.drawable.a_btn_huang);
//        }
        if (position%2==1){
            viewHolder.iv_open_door.setBackgroundResource(R.drawable.a_btn_orange
            );
        }else {
            viewHolder.iv_open_door.setBackgroundResource(R.drawable.a_btn_blue);
        }



        viewHolder.iv_open_door.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(final View v) {
                onKaiMen(v,viewHolder,position);
            }
        });

    }
    //获取数据的数量
    @Override
    public int getItemCount() {
        return mList.size();
    }
    //自定义的ViewHolder，持有每个Item的的所有界面元素
    public static class ViewHolder extends RecyclerView.ViewHolder {
        public TextView tv_open_door;
        public ImageView iv_open_door;
        public ViewHolder(View view){
            super(view);
            tv_open_door = (TextView) view.findViewById(R.id.tv_open_door);
            iv_open_door= (ImageView) view.findViewById(R.id.iv_open_door);
        }
    }
    private void showDialog(String message, Context mContext){

        final AlertDialog.Builder builder;
        builder=new AlertDialog.Builder(mContext)
                .setMessage(message)
        .setPositiveButton("确定", null);
        builder.show();


    }
    private void onKaiMen(final View v, final ViewHolder viewHolder, final int position){

        ObjectAnimator anim = ObjectAnimator.ofFloat(viewHolder.iv_open_door, "scaleY", 1f, 1.3f, 1f);
        ObjectAnimator anim1 = ObjectAnimator.ofFloat(viewHolder.iv_open_door, "scaleX", 1f, 1.3f, 1f);
        anim.setDuration(1000);
        anim.start();
        anim1.setDuration(1000);
        anim1.start();
        DialogUntil.showLoadingDialog(v.getContext(),"正在开门",true);
        customberId= Integer.valueOf(SPManager.getInstance().getString("c_memberId",null));
        lockId=mList.get(position).getLockId();
        String url= XY_Response.URL_OPENDOOR+"customberId="+customberId+"&lockId="+lockId;
        RequestCenter.openDoor(url, new DisposeDataListener() {
            @Override
            public void onSuccess(Object responseObj) {
                DialogUntil.closeLoadingDialog();
                OpenDoor openDoor= (OpenDoor) responseObj;
                if (openDoor.getCode().equals("1000")){
                    KaiMenDialog dialog;
                    if (openDoor.getBannerObj().size()>0) {
                        dialog= new KaiMenDialog(v.getContext(), 1, openDoor.getBannerObj().get(0));
                    }else {
                        dialog= new KaiMenDialog(v.getContext(), 1,null);
                    }
                    dialog.show();

                    if (openDoor.getObj().getType()==1){
                        KaiMenYouXiDialog kaiMenYouXiDialog=new KaiMenYouXiDialog(v.getContext(),openDoor.getObj());
                        kaiMenYouXiDialog.show();
                    }
                }else {
                    KaiMenDialog dialog=new KaiMenDialog(v.getContext(),2,openDoor.getErrorType(),openDoor.getErrorMsg(),openDoor.getMsg());
                    dialog.setOnChongShiListener(new KaiMenDialog.OnChongShiListener() {
                        @Override
                        public void chongShi() {
                            onKaiMen(v,viewHolder,position);
                        }
                    });
                    dialog.show();
                }
            }

            @Override
            public void onFailure(Object reasonObj) {
                DialogUntil.closeLoadingDialog();
                MyDialog.showDialog(v.getContext());
            }
        });

    }

}
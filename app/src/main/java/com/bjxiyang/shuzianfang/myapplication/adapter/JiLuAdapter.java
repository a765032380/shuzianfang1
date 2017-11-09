package com.bjxiyang.shuzianfang.myapplication.adapter;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.baisi.imoocsdk.imageloader.ImageLoaderManager;
import com.bjxiyang.shuzianfang.R;
import com.bjxiyang.shuzianfang.myapplication.activity.ImageActivity;
import com.bjxiyang.shuzianfang.myapplication.model.BaoXiuLiShi;

import java.util.HashMap;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by Administrator on 2017/10/21 0021.
 */

public class JiLuAdapter extends BaseAdapter {
    private Context mContext;
    private List<BaoXiuLiShi.ObjBean> mList;
    HashMap<Integer, View> lmap = new HashMap<Integer, View>();



    public JiLuAdapter(Context mContext, List<BaoXiuLiShi.ObjBean> mList) {
        this.mContext = mContext;
        this.mList = mList;
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

    @Override
    public View getView(int position, View view, ViewGroup parent) {
        ViewHolder viewHolder=null;
        if (lmap.get(position) ==null){
            view= LayoutInflater.from(mContext).inflate(R.layout.item_lishibaoxiu,null);
            viewHolder=new ViewHolder(view);
            view.setTag(viewHolder);
            lmap.put(position,view);
        }else {
            view = lmap.get(position);
            viewHolder= (ViewHolder) view.getTag();
        }
        BaoXiuLiShi.ObjBean obj=mList.get(position);
        viewHolder.tv_weixiumingcheng.setText(obj.getContactWay());
        viewHolder.tv_baoxiumiaoshu.setText(obj.getRepairContent());
        viewHolder.tv_date.setText(obj.getAddTime());
        List<BaoXiuLiShi.ObjBean.RepairPicBean> repairPicList=mList.get(position).getRepairPic();
        switch (repairPicList.size()){
            case 1:
                showOne(viewHolder,repairPicList);
                break;
            case 2:
                showTwo(viewHolder,repairPicList);
                break;
            case 3:
                showThree(viewHolder,repairPicList);
                break;
            case 4:
                showFour(viewHolder,repairPicList);
                break;
            default:
                hideImage(viewHolder);
                break;
        }




        return view;
    }

    private void hideImage(ViewHolder viewHolder) {
        viewHolder.ll_imager.setVisibility(View.GONE);

    }


    class ViewHolder{
        @BindView(R.id.tv_weixiumingcheng)
        TextView tv_weixiumingcheng;
        @BindView(R.id.tv_baoxiumiaoshu)
        TextView tv_baoxiumiaoshu;
        @BindView(R.id.tv_date)
        TextView tv_date;
        @BindView(R.id.tv_time)
        TextView tv_time;
        @BindView(R.id.iv_img1)
        ImageView iv_img1;
        @BindView(R.id.iv_img2)
        ImageView iv_img2;
        @BindView(R.id.iv_img3)
        ImageView iv_img3;
        @BindView(R.id.iv_img4)
        ImageView iv_img4;
        @BindView(R.id.ll_imager)
        LinearLayout ll_imager;


        public ViewHolder(View view){
            ButterKnife.bind(this,view);
        }

    }
    private void showOne(ViewHolder viewHolder,List<BaoXiuLiShi.ObjBean.RepairPicBean> repairPicList){
        ImageLoaderManager.getInstance(mContext)
                .displayImage(viewHolder.iv_img1,repairPicList.get(0).getPicUrl());
        imageFangDa(viewHolder.iv_img1,repairPicList.get(0).getPicUrl());
    }
    private void showTwo(ViewHolder viewHolder,List<BaoXiuLiShi.ObjBean.RepairPicBean> repairPicList){
        ImageLoaderManager.getInstance(mContext)
                .displayImage(viewHolder.iv_img1,repairPicList.get(0).getPicUrl());
        ImageLoaderManager.getInstance(mContext)
                .displayImage(viewHolder.iv_img2,repairPicList.get(1).getPicUrl());
        imageFangDa(viewHolder.iv_img1,repairPicList.get(0).getPicUrl());
        imageFangDa(viewHolder.iv_img2,repairPicList.get(1).getPicUrl());
    }
    private void showThree(ViewHolder viewHolder,List<BaoXiuLiShi.ObjBean.RepairPicBean> repairPicList){
        ImageLoaderManager.getInstance(mContext)
                .displayImage(viewHolder.iv_img1,repairPicList.get(0).getPicUrl());
        ImageLoaderManager.getInstance(mContext)
                .displayImage(viewHolder.iv_img2,repairPicList.get(1).getPicUrl());
        ImageLoaderManager.getInstance(mContext)
                .displayImage(viewHolder.iv_img3,repairPicList.get(2).getPicUrl());
        imageFangDa(viewHolder.iv_img1,repairPicList.get(0).getPicUrl());
        imageFangDa(viewHolder.iv_img2,repairPicList.get(1).getPicUrl());
        imageFangDa(viewHolder.iv_img3,repairPicList.get(2).getPicUrl());
    }
    private void showFour(ViewHolder viewHolder,List<BaoXiuLiShi.ObjBean.RepairPicBean> repairPicList){
        ImageLoaderManager.getInstance(mContext)
                .displayImage(viewHolder.iv_img1,repairPicList.get(0).getPicUrl());
        ImageLoaderManager.getInstance(mContext)
                .displayImage(viewHolder.iv_img2,repairPicList.get(1).getPicUrl());
        ImageLoaderManager.getInstance(mContext)
                .displayImage(viewHolder.iv_img3,repairPicList.get(2).getPicUrl());
        ImageLoaderManager.getInstance(mContext)
                .displayImage(viewHolder.iv_img4,repairPicList.get(3).getPicUrl());
        imageFangDa(viewHolder.iv_img1,repairPicList.get(0).getPicUrl());
        imageFangDa(viewHolder.iv_img2,repairPicList.get(1).getPicUrl());
        imageFangDa(viewHolder.iv_img3,repairPicList.get(2).getPicUrl());
        imageFangDa(viewHolder.iv_img4,repairPicList.get(3).getPicUrl());
    }


    private void imageFangDa(ImageView imageView, final String url) {
        imageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(mContext,ImageActivity.class);
                intent.putExtra("url",url);
                mContext.startActivity(intent);

            }
        });

    }
}

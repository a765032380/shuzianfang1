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
import com.bjxiyang.shuzianfang.myapplication.model.FanKuiLiShi;

import java.util.HashMap;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by gll on 2017/10/25.
 */

public class FanKuiLiShiAdapter extends BaseAdapter {
    private Context mContext;
    private List<FanKuiLiShi.ObjBean> mList;
    HashMap<Integer, View> lmap = new HashMap<Integer, View>();
    public FanKuiLiShiAdapter(Context mContext, List<FanKuiLiShi.ObjBean> mList) {
        this.mContext = mContext;
        this.mList = mList;
    }

    @Override
    public int getCount() {
        return mList.size();
    }

    @Override
    public Object getItem(int i) {
        return mList.get(i);
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        ViewHolder holder;
        if (lmap.get(i) ==null){
            view= LayoutInflater.from(mContext).inflate(R.layout.item_lishifankui,null);
            holder=new ViewHolder(view);
            view.setTag(holder);
            lmap.put(i,view);
        }else {
            view = lmap.get(i);
            holder= (ViewHolder) view.getTag();
        }
        FanKuiLiShi.ObjBean obj=mList.get(i);
        holder.tv_lishifankui_date.setText(obj.getAddTime());
        holder.tv_fankuimiaoshu.setText(obj.getComplaintContent());
        holder.tv_lishifankui_date.setText(obj.getAddTime());
        holder.tv_time.setVisibility(View.GONE);
        List<FanKuiLiShi.ObjBean.PicUrlBean> repairPicList=mList.get(i).getPicUrl();

        switch (repairPicList.size()){
            case 1:
                showImage(holder);
                showOne(holder,repairPicList);
                break;
            case 2:
                showImage(holder);
                showTwo(holder,repairPicList);
                break;
            case 3:
                showImage(holder);
                showThree(holder,repairPicList);
                break;
            case 4:
                showImage(holder);
                showFour(holder,repairPicList);
                break;
            default:
                hideImage(holder);
                break;
        }


        return view;
    }
    private void hideImage(ViewHolder viewHolder) {
        viewHolder.ll_imager.setVisibility(View.GONE);

    }
    private void showImage(ViewHolder viewHolder) {
        viewHolder.ll_imager.setVisibility(View.VISIBLE);

    }

    class ViewHolder{
        @BindView(R.id.tv_lishifankui_date)
        TextView tv_lishifankui_date;
        @BindView(R.id.tv_fankuimiaoshu)
        TextView tv_fankuimiaoshu;
//        @BindView(R.id.tv_lishifankui_date)
//        TextView tv_lishifankui_date;
        @BindView(R.id.tv_lishifankui_time)
        TextView tv_time;
        @BindView(R.id.iv_lishifankui_img1)
        ImageView iv_img1;
        @BindView(R.id.iv_lishifankui_img2)
        ImageView iv_img2;
        @BindView(R.id.iv_lishifankui_img3)
        ImageView iv_img3;
        @BindView(R.id.iv_lishifankui_img4)
        ImageView iv_img4;
        @BindView(R.id.ll_imager)
        LinearLayout ll_imager;




        ViewHolder(View view){
            ButterKnife.bind(this,view);
        }
    }
    private void showOne(ViewHolder viewHolder, List<FanKuiLiShi.ObjBean.PicUrlBean> repairPicList){
        viewHolder.iv_img1.setVisibility(View.VISIBLE);
        viewHolder.iv_img2.setVisibility(View.INVISIBLE);
        viewHolder.iv_img3.setVisibility(View.INVISIBLE);
        viewHolder.iv_img4.setVisibility(View.INVISIBLE);
        ImageLoaderManager.getInstance(mContext)
                .displayImage(viewHolder.iv_img1,repairPicList.get(0).getPicUrl());

        imageFangDa(viewHolder.iv_img1,repairPicList.get(0).getPicUrl());
    }
    private void showTwo(ViewHolder viewHolder, List<FanKuiLiShi.ObjBean.PicUrlBean> repairPicList){
        viewHolder.iv_img1.setVisibility(View.VISIBLE);
        viewHolder.iv_img2.setVisibility(View.VISIBLE);
        viewHolder.iv_img3.setVisibility(View.INVISIBLE);
        viewHolder.iv_img4.setVisibility(View.INVISIBLE);
        ImageLoaderManager.getInstance(mContext)
                .displayImage(viewHolder.iv_img1,repairPicList.get(0).getPicUrl());
        ImageLoaderManager.getInstance(mContext)
                .displayImage(viewHolder.iv_img2,repairPicList.get(1).getPicUrl());

        imageFangDa(viewHolder.iv_img1,repairPicList.get(0).getPicUrl());
        imageFangDa(viewHolder.iv_img2,repairPicList.get(1).getPicUrl());
    }
    private void showThree(ViewHolder viewHolder, List<FanKuiLiShi.ObjBean.PicUrlBean> repairPicList){
        viewHolder.iv_img1.setVisibility(View.VISIBLE);
        viewHolder.iv_img2.setVisibility(View.VISIBLE);
        viewHolder.iv_img3.setVisibility(View.VISIBLE);
        viewHolder.iv_img4.setVisibility(View.INVISIBLE);
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
    private void showFour(ViewHolder viewHolder, List<FanKuiLiShi.ObjBean.PicUrlBean> repairPicList){

        viewHolder.iv_img1.setVisibility(View.VISIBLE);
        viewHolder.iv_img2.setVisibility(View.VISIBLE);
        viewHolder.iv_img3.setVisibility(View.VISIBLE);
        viewHolder.iv_img4.setVisibility(View.VISIBLE);

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

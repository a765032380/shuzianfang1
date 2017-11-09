package com.bjxiyang.shuzianfang.myapplication.adapter;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.bjxiyang.shuzianfang.R;
import com.bjxiyang.shuzianfang.myapplication.activity.JiaoFeiLiShiXiangQingActivity;
import com.bjxiyang.shuzianfang.myapplication.model.WuYeJiaoFeiLiShi;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by gll on 2017/10/25.
 */

public class WuYeJiaoFeiLiShiItemAdapter extends BaseAdapter{
    private Context mContext;
    private List<WuYeJiaoFeiLiShi.ObjBean.MonthpaylistBean> mList;

    public WuYeJiaoFeiLiShiItemAdapter(Context mContext, List<WuYeJiaoFeiLiShi.ObjBean.MonthpaylistBean> mList) {
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
        ViewHolder viewHolder;
        if (view==null){
            view= LayoutInflater.from(mContext).inflate(R.layout.item_item_lishijiaofei,null);
            viewHolder=new ViewHolder(view);
            view.setTag(viewHolder);
        }else {
            viewHolder= (ViewHolder) view.getTag();
        }
        viewHolder.tv_address.setText(mList.get(i).getCommunityName()
                +"\t\t"+mList.get(i).getUnitName()
                +"\t\t"+mList.get(i).getDoorName());
        if (mList.get(i).getFeeType()==0){
            viewHolder.tv_jiaofeileixing.setText("物业费");
        }else {
            viewHolder.tv_jiaofeileixing.setText("停车费");
        }
        viewHolder.tv_jiaofeijine.setText(mList.get(i).getFee()+"");
        viewHolder.tv_jiaofeiriqileixing.setText(mList.get(i).getAddTime());
        final int i1=i;
        view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(mContext,JiaoFeiLiShiXiangQingActivity.class);
                intent.putExtra("propertyId",mList.get(i1).getPropertyId());
                mContext.startActivity(intent);
            }
        });
        return view;
    }
    class ViewHolder{


        @BindView(R.id.tv_jiaofeileixing)
        TextView tv_jiaofeileixing;
        @BindView(R.id.tv_address)
        TextView tv_address;
        @BindView(R.id.tv_jiaofeiriqileixing)
        TextView tv_jiaofeiriqileixing;
        @BindView(R.id.tv_jiaofeijine)
        TextView tv_jiaofeijine;


        public ViewHolder(View view){
            ButterKnife.bind(this,view);
        }





    }
}

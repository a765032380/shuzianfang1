package com.bjxiyang.shuzianfang.myapplication.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.bjxiyang.shuzianfang.R;
import com.bjxiyang.shuzianfang.myapplication.manager.SPManager;
import com.bjxiyang.shuzianfang.myapplication.model.SelectPlot;

import java.util.List;

/**
 * Created by gll on 17-5-23.
 */

public class XYXuanZeXiaoQuAdapter extends BaseAdapter {
    private Context mContext;
    private List<SelectPlot.Obj> list;

    public XYXuanZeXiaoQuAdapter(Context mContext, List<SelectPlot.Obj> list) {
        this.mContext = mContext;
        this.list = list;
    }

    @Override
    public int getCount() {
        return list.size();
    }

    @Override
    public Object getItem(int position) {
        return list.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View view, ViewGroup parent) {
        ViewHolder viewHolder;
        if (view==null){
            view= LayoutInflater.from(mContext).inflate(R.layout.item_xunazexiaoqu3,null);
            viewHolder=new ViewHolder();
            viewHolder.tv_dingweidizhi= (TextView) view.findViewById(R.id.tv_dingweidizhi);
            viewHolder.tv_dingweiname= (TextView) view.findViewById(R.id.tv_dingweiname);
            viewHolder.tv_dingweiyezhu= (TextView) view.findViewById(R.id.tv_dingweiyezhu);

            view.setTag(viewHolder);
        }else {
            viewHolder= (ViewHolder) view.getTag();
        }
        viewHolder.tv_dingweidizhi.setText(
                list.get(position).getCommunityName()
                        +list.get(position).getNperName()
        +list.get(position).getFloorName()
        +list.get(position).getUnitName()
        +list.get(position).getDoorName());
        viewHolder.tv_dingweiname.setText(SPManager.getInstance().getString("realName","未命名"));
        if (list.get(position).getRoleType()==1){
            viewHolder.tv_dingweiyezhu.setText("租户");
        }else {
            viewHolder.tv_dingweiyezhu.setText("业主");
        }



        return view;
    }

    class ViewHolder{
        TextView tv_dingweidizhi;
        TextView tv_dingweiname;
        TextView tv_dingweiyezhu;

    }
}

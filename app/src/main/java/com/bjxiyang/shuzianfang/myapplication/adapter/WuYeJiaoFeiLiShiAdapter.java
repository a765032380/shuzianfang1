package com.bjxiyang.shuzianfang.myapplication.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ListView;
import android.widget.TextView;

import com.bjxiyang.shuzianfang.R;
import com.bjxiyang.shuzianfang.myapplication.model.WuYeJiaoFeiLiShi;
import com.bjxiyang.shuzianfang.myapplication.until.Utility;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by gll on 2017/10/25.
 */

public class WuYeJiaoFeiLiShiAdapter extends BaseAdapter {
    private Context mContext;
    private List<WuYeJiaoFeiLiShi.ObjBean> mList;

    public WuYeJiaoFeiLiShiAdapter(Context mContext, List<WuYeJiaoFeiLiShi.ObjBean> mList) {
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
            view= LayoutInflater.from(mContext).inflate(R.layout.item_lishijiaofei,null);
            viewHolder=new ViewHolder(view);
            view.setTag(viewHolder);
        }else {
            viewHolder= (ViewHolder) view.getTag();
        }
        viewHolder.tv_mouth.setText(mList.get(i).getT());
        WuYeJiaoFeiLiShiItemAdapter adapter=
                new WuYeJiaoFeiLiShiItemAdapter(mContext,mList.get(i).getMonthpaylist());
        viewHolder.lv_mListView.setAdapter(adapter);
        Utility.setListViewHeightBasedOnChildren(viewHolder.lv_mListView);
        return view;
    }
    class ViewHolder {
       @BindView(R.id.tv_mouth)
        TextView tv_mouth;
        @BindView(R.id.lv_mListView)
        ListView lv_mListView;



//        @BindView(R.id.tv_jiaofeileixing)
//        TextView tv_jiaofeileixing;
//        @BindView(R.id.tv_address)
//        TextView tv_address;
//        @BindView(R.id.tv_jiaofeiriqileixing)
//        TextView tv_jiaofeiriqileixing;
//        @BindView(R.id.tv_jiaofeijine)
//        TextView tv_jiaofeijine;

        public ViewHolder(View view){
            ButterKnife.bind(this,view);
        }



    }
}

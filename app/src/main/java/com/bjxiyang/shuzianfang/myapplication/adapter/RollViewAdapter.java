package com.bjxiyang.shuzianfang.myapplication.adapter;

import android.content.Intent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.baisi.imoocsdk.imageloader.ImageLoaderManager;
import com.bjxiyang.shuzianfang.myapplication.model.GuangGao;
import com.bjxiyang.shuzianfang.myapplication.activity.HomeActivity;
import com.bjxiyang.shuzianfang.myapplication.activity.MyWebViewActivity;
import com.jude.rollviewpager.RollPagerView;
import com.jude.rollviewpager.adapter.LoopPagerAdapter;

import java.util.List;


/**
 * Created by gll on 17-5-22.
 */

public class RollViewAdapter extends LoopPagerAdapter{

    private List<GuangGao.ObjBean.BannerObjBean.AdInfoBean> mList;
    private ImageLoaderManager manager;
    public RollViewAdapter(RollPagerView viewPager) {
        super(viewPager);
        HomeActivity.setOngetData(new HomeActivity.OngetData() {
            @Override
            public void OngetData(List<GuangGao.ObjBean.BannerObjBean.AdInfoBean> list) {
                mList=list;
                notifyDataSetChanged();
            }
        });
    }
    @Override
    public View getView(final ViewGroup container, final int position) {

        manager= ImageLoaderManager.getInstance(container.getContext());
        ImageView view = new ImageView(container.getContext());

        manager.displayImage(view, mList.get(position).getImageurl());




        final int position1=position;
            view.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent=new Intent(v.getContext(),MyWebViewActivity.class);
                    intent.putExtra("url",mList.get(position1).getUrladdress());
                    v.getContext().startActivity(intent);

                }
            });



        view.setScaleType(ImageView.ScaleType.CENTER_CROP);
        view.setLayoutParams(new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT,
                ViewGroup.LayoutParams.MATCH_PARENT));
        return view;
    }
    @Override
    public int getRealCount() {
        if (mList!=null){
            if (mList.size()<0){
                return 0;
            }else {
                return mList.size();
            }
        }else {
            return 0;
        }
    }
}

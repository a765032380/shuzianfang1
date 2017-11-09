package com.bjxiyang.shuzianfang.myapplication.ui.dialog;

import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.baisi.imoocsdk.imageloader.ImageLoaderManager;
import com.bjxiyang.shuzianfang.R;
import com.bjxiyang.shuzianfang.myapplication.app.GuardApplication;
import com.bjxiyang.shuzianfang.myapplication.model.OpenDoor;
import com.bjxiyang.shuzianfang.myapplication.ui.activity.MyWebViewActivity;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by Administrator on 2017/10/17 0017.
 */

public class KaiMenDialog extends Dialog {

    @BindView(R.id.ll_kaimenchenggong)
    LinearLayout ll_kaimenchenggong;
    @BindView(R.id.tv_chongshi)
    TextView tv_chongshi;
    @BindView(R.id.tv_lianxikefu)
    TextView tv_lianxikefu;
    @BindView(R.id.home_sun)
    ImageView home_sun;
    @BindView(R.id.ll_kaimenshibai)
    LinearLayout ll_kaimenshibai;

    private OnChongShiListener onChongShiListener;
    private int type;
    private OpenDoor.BannerObjBean bannerObjBean;


    private void showShiBai(){
        ll_kaimenchenggong.setVisibility(View.GONE);
        ll_kaimenshibai.setVisibility(View.VISIBLE);
    }

    private void showChengGong(){
        ll_kaimenchenggong.setVisibility(View.VISIBLE);
        ll_kaimenshibai.setVisibility(View.GONE);
        if (bannerObjBean!=null){
            ImageLoaderManager.getInstance(getContext())
                    .displayImage(home_sun,bannerObjBean.getImageurl());
            home_sun.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Intent intent=new Intent(getContext(), MyWebViewActivity.class);
                    intent.putExtra("url",bannerObjBean.getUrladdress());
                    getContext().startActivity(intent);
                }
            });
        }else {
            home_sun.setVisibility(View.GONE);
        }



    }
    public KaiMenDialog(@NonNull Context context, int type, OpenDoor.BannerObjBean bannerObjBean1) {
        super(context,R.style.dialog);
        this.type=type;
        bannerObjBean=bannerObjBean1;
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.dialog_kaimen);
        ButterKnife.bind(this);
        initUI();
        if (type==1){
            showChengGong();
        }else {
            showShiBai();
        }
    }

    private void initUI() {
        tv_lianxikefu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent j = new Intent();
                j.setAction(Intent.ACTION_DIAL);
                j.setData(Uri.parse("tel:4000080828"));
                getContext().startActivity(j);
                cancel();
            }
        });
        tv_chongshi.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onChongShiListener.chongShi();
//                MyUntil.show(GuardApplication.getContent(),"重新开门");
                cancel();
            }
        });

    }
    public void setOnChongShiListener(OnChongShiListener onChongShiListener){
        this.onChongShiListener=onChongShiListener;
    }

    public interface OnChongShiListener{
        public void chongShi();
    }







}

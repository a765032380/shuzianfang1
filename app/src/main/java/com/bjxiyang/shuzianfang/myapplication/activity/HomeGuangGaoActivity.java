package com.bjxiyang.shuzianfang.myapplication.activity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;

import com.baisi.imoocsdk.imageloader.ImageLoaderManager;
import com.bjxiyang.shuzianfang.R;
import com.bjxiyang.shuzianfang.myapplication.model.GuangGao;
import com.bjxiyang.shuzianfang.myapplication.ui.activity.MyWebViewActivity;
import com.bjxiyang.shuzianfang.myapplication.until.GetGuanggaoUrl;

/**
 * Created by gll on 2017/10/23.
 */

public class HomeGuangGaoActivity extends Activity {

    private ImageView iv_guanggao;
    private ImageView imageView;
//    private LinearLayout ll_guanggao;
    private String url,imageurl;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_guanggao);
        initUI();

    }

    private void initUI() {
//        ll_guanggao= (LinearLayout) findViewById(R.id.ll_guanggao);
//        ll_guanggao.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                finish();
//            }
//        });
        iv_guanggao= (ImageView) findViewById(R.id.iv_guanggao);
        imageView= (ImageView) findViewById(R.id.imageView);
        imageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
        Intent intent=getIntent();
        imageurl=intent.getStringExtra("imageurl");
        url=intent.getStringExtra("url");

        ImageLoaderManager.getInstance(HomeGuangGaoActivity.this)
                .displayImage(iv_guanggao,imageurl);
        iv_guanggao.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(HomeGuangGaoActivity.this,MyWebViewActivity.class);
                intent.putExtra("url",url);
                startActivity(intent);
            }
        });
//        getImageUrl();



    }
    private void getImageUrl(){
        GetGuanggaoUrl.setOnGetImageUrl(2, new GetGuanggaoUrl.OnGetImageUrl() {
            @Override
            public void getImageUrl(final GuangGao.ObjBean.BannerObjBean.AdInfoBean adInfo) {
                ImageLoaderManager.getInstance(HomeGuangGaoActivity.this)
                        .displayImage(iv_guanggao,adInfo.getImageurl());
                iv_guanggao.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        Intent intent=new Intent(HomeGuangGaoActivity.this,MyWebViewActivity.class);
                        intent.putExtra("url",adInfo.getUrladdress());
                        startActivity(intent);
                    }
                });


                Log.i("LLLL",adInfo.getImageurl());
            }
        });



    }

}

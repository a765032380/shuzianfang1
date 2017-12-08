package com.bjxiyang.shuzianfang.myapplication.activity;

import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

import com.baisi.imoocsdk.imageloader.ImageLoaderManager;
import com.bjxiyang.shuzianfang.R;

/**
 * Created by gll on 2017/11/1.
 */

public class ImageActivity extends MySwipeBackActivity{
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_image);
        ImageView image= (ImageView) findViewById(R.id.image);
        ImageLoaderManager.getInstance(this)
                .displayImage(image,getIntent().getStringExtra("url"));
        image.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });


    }
}

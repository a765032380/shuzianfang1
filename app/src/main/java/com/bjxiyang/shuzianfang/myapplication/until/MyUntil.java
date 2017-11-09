package com.bjxiyang.shuzianfang.myapplication.until;

import android.content.Context;
import android.content.Intent;
import android.widget.Toast;

/**
 * Created by Administrator on 2017/6/18 0018.
 */

public class MyUntil {

    public static final String WEIXIN_APPID="";

    //Toast
    public static void show(Context context,String message){
        Toast.makeText(context,message,Toast.LENGTH_LONG).show();
    }
    //跳转页面
    public static void mStartActivity(Context mContext,Class mClass){
        Intent intent=new Intent(mContext,mClass);
        mContext.startActivity(intent);

    }
}

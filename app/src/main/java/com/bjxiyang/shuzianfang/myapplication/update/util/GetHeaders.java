package com.bjxiyang.shuzianfang.myapplication.update.util;

import com.baisi.myapplication.okhttp.request.RequestParams;
import com.bjxiyang.shuzianfang.myapplication.manager.SPManager;

/**
 * Created by Administrator on 2017/7/3 0003.
 *
 */

public class GetHeaders {
    public static RequestParams getHeaders(){
        RequestParams headers=new RequestParams();
//        Log.i("YYYY",UserManager.getInstance().getUser().getObj().getMobilePhone()+
//                "_"+SPManager.getInstance().getString("loginKey",""));
        headers.put("private-token",SPManager.getInstance().getString("mobilePhone","")+
                "_"+SPManager.getInstance().getString("loginKey",""));

//        UserManager.getInstance().getUser().getObj().getMobilePhone()+
//                "_"+

//        headers.put("private-token", "a600dd01ffefbaf03d670bf5e20743e1");
//                UserManager.getInstance().getUser().getResult().getLoginKey());
        return headers;
    }

}

package com.bjxiyang.shuzianfang.myapplication.until;

import com.baisi.myapplication.okhttp.listener.DisposeDataListener;
import com.bjxiyang.shuzianfang.myapplication.manager.SPManager;
import com.bjxiyang.shuzianfang.myapplication.model.GuangGao;
import com.bjxiyang.shuzianfang.myapplication.response_xy.XY_Response;
import com.bjxiyang.shuzianfang.myapplication.update.network.RequestCenter;

/**
 * Created by gll on 2017/10/25.
 */

public class GetGuanggaoUrl {
    public static void setOnGetImageUrl(int type,OnGetImageUrl onGetImageUrl){
        getUrl(onGetImageUrl,type);
    }

    public static void getUrl(final OnGetImageUrl onGetImageUrl, int type){
        String url= XY_Response.URL_INIT_ADINFO
                +"cmemberId="+ SPManager.getInstance().getString("c_memberId","0")
                +"&position="+type;
        RequestCenter.all(url, GuangGao.class, new DisposeDataListener() {
            @Override
            public void onSuccess(Object responseObj) {

                GuangGao guangGao= (GuangGao) responseObj;
                if (guangGao.getCode()==1000){

                    if (guangGao.getObj().getBannerObj().get(0).getAdInfo().size()>0){
                        guangGao.getObj().getBannerObj().get(0).getAdInfo().get(0);
                        onGetImageUrl.getImageUrl(guangGao.getObj().getBannerObj().get(0).getAdInfo().get(0));

                    }else {
                        onGetImageUrl.getImageUrl(null);

                    }

                }else {
                    onGetImageUrl.getImageUrl(null);
                }

            }

            @Override
            public void onFailure(Object reasonObj) {

            }
        });
    }


    public interface OnGetImageUrl{
        void getImageUrl(GuangGao.ObjBean.BannerObjBean.AdInfoBean adInfo);
    }






}

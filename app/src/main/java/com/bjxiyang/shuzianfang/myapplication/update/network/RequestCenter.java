package com.bjxiyang.shuzianfang.myapplication.update.network;

import com.baisi.myapplication.okhttp.CommonOkHttpClient;
import com.baisi.myapplication.okhttp.listener.DisposeDataHandle;
import com.baisi.myapplication.okhttp.listener.DisposeDataListener;
import com.baisi.myapplication.okhttp.listener.DisposeDownloadListener;
import com.baisi.myapplication.okhttp.request.CommonRequest;
import com.baisi.myapplication.okhttp.request.RequestParams;
import com.bjxiyang.shuzianfang.myapplication.manager.SPManager;
import com.bjxiyang.shuzianfang.myapplication.model.AddFangWu;
import com.bjxiyang.shuzianfang.myapplication.model.Banner;
import com.bjxiyang.shuzianfang.myapplication.model.ByCom;
import com.bjxiyang.shuzianfang.myapplication.model.DiZhiAdd;
import com.bjxiyang.shuzianfang.myapplication.model.Door;
import com.bjxiyang.shuzianfang.myapplication.model.FanHui;
import com.bjxiyang.shuzianfang.myapplication.model.FanHui2;
import com.bjxiyang.shuzianfang.myapplication.model.Floor;
import com.bjxiyang.shuzianfang.myapplication.model.GeRenZhongXin;
import com.bjxiyang.shuzianfang.myapplication.model.GongGao;
import com.bjxiyang.shuzianfang.myapplication.model.ImageUrl;
import com.bjxiyang.shuzianfang.myapplication.model.ImageUrl2;
import com.bjxiyang.shuzianfang.myapplication.model.Init;
import com.bjxiyang.shuzianfang.myapplication.model.Loan;
import com.bjxiyang.shuzianfang.myapplication.model.MsgList;
import com.bjxiyang.shuzianfang.myapplication.model.OpenDoor;
import com.bjxiyang.shuzianfang.myapplication.model.OpenDoorList;
import com.bjxiyang.shuzianfang.myapplication.model.OrderWeiXin;
import com.bjxiyang.shuzianfang.myapplication.model.PermissionList;
import com.bjxiyang.shuzianfang.myapplication.model.Plots;
import com.bjxiyang.shuzianfang.myapplication.model.ProPayOrderByAli;
import com.bjxiyang.shuzianfang.myapplication.model.QiDongYe;
import com.bjxiyang.shuzianfang.myapplication.model.SelectPlot;
import com.bjxiyang.shuzianfang.myapplication.model.TianQi;
import com.bjxiyang.shuzianfang.myapplication.model.Unit;
import com.bjxiyang.shuzianfang.myapplication.model.UpdateVersion;
import com.bjxiyang.shuzianfang.myapplication.model.Users;
import com.bjxiyang.shuzianfang.myapplication.model.Users1;
import com.bjxiyang.shuzianfang.myapplication.model.ProPayOrder;
import com.bjxiyang.shuzianfang.myapplication.model.WuYeJiaoFeiLiShi;
import com.bjxiyang.shuzianfang.myapplication.model.XiTongXiaoXi;
import com.bjxiyang.shuzianfang.myapplication.response_xy.XY_Response;
import com.bjxiyang.shuzianfang.myapplication.update.util.GetHeaders;

import java.util.Map;

/**
 * Created by gll on 17-3-9.
 * 存放应用中所有的请求
 */
public class RequestCenter {
    public static void postRequest1(String url, RequestParams params,RequestParams headers,
                                    DisposeDataListener disposeDataListener, Class<?> clazz){
        CommonOkHttpClient.post(CommonRequest.createPostRequest1(url,params,headers)
                ,new DisposeDataHandle(disposeDataListener,clazz));
    }
    public static void postRequest(String url, RequestParams params,
                                    DisposeDataListener disposeDataListener, Class<?> clazz){
        CommonOkHttpClient.post(CommonRequest.createPostRequest1(url,params,GetHeaders.getHeaders())
                ,new DisposeDataHandle(disposeDataListener,clazz));
    }

    public static void getRequest1(String url, RequestParams params,RequestParams headers,
                                    DisposeDataListener disposeDataListener, Class<?> clazz){
        CommonOkHttpClient.get(CommonRequest.createGetRequest(url,params,headers)
                ,new DisposeDataHandle(disposeDataListener,clazz));
    }




    public static void uploadPictures(String url, Map<String, Object> map,DisposeDataListener listener){
       CommonOkHttpClient.uploadImgAndParameter(map,url,new DisposeDataHandle(listener,FanHui.class));
    }

    public static void uploadPictures2(String url,RequestParams params,DisposeDataListener listener){
        CommonOkHttpClient.uploadPictures2(
                CommonRequest.createMultiPostRequest(url,params),new DisposeDataHandle(listener,FanHui.class));
    }
    public static void cancel(){
        CommonOkHttpClient.breakLink();
    }
    /**
     * 应用版本号请求
     *
     * @param listener
     */
    public static void checkVersion(DisposeDataListener listener) {
        RequestCenter.postRequest(XY_Response.URL_UPDATEVERSION +"cmemberId="+
                        SPManager.getInstance().getString("c_memberId","")+"&vertype=3",
                null, listener, UpdateVersion.class);
    }

    public static void downloadFile(String url, String path, DisposeDownloadListener listener) {
        CommonOkHttpClient.downloadFile(CommonRequest.createGetRequest(url, null),
                new DisposeDataHandle(listener, path));
    }
    //获取JSON并转化为实体类的请求。
    public static void requestRecommandData(DisposeDataListener listener){
        //第一个参数为请求的地址
        //第二个参数为上传的参数。请求的时候为null
        //第三个参数为监听事件
        //第四个参数为JSON的实体类
        RequestParams params=new RequestParams("TEXT","我是测试数据");
        RequestCenter.postRequest(HttpConstants.TEXT,
                params,listener, null);
    }

    public static void uploadPicturesList(String url, Map<String, Object> map,DisposeDataListener listener){
        CommonOkHttpClient.uploadImgAndParameterList(map,url,new DisposeDataHandle(listener,ImageUrl.class));
    }
    public static void uploadPictures1(String url, Map<String, Object> map,DisposeDataListener listener){
        CommonOkHttpClient.uploadImgAndParameter(map,url,new DisposeDataHandle(listener,ImageUrl.class));
    }
    public static void uploadPictures2(String url, Map<String, Object> map,DisposeDataListener listener){
        CommonOkHttpClient.uploadImgAndParameter(map,url,new DisposeDataHandle(listener,ImageUrl2.class));
    }

    public static void order_user_address_add(String url,DisposeDataListener listener){
        RequestCenter.postRequest(url,GetHeaders.getHeaders(),listener, DiZhiAdd.class);
    }
    public static void login(String url,RequestParams headers,DisposeDataListener listener){
        RequestCenter.postRequest1(url,null,headers,listener,Users.class);
    }
    public static void register(String url,DisposeDataListener listener){
        RequestCenter.postRequest1(url,null,null,listener, FanHui.class);
    }

    public static void register(String url,RequestParams params,DisposeDataListener listener){
        RequestCenter.postRequest1(url,params,null,listener, FanHui.class);
    }


    public static void findCommunity(String url,DisposeDataListener listener){
        RequestCenter.postRequest(url, GetHeaders.getHeaders(),listener, Plots.class);
    }
    public static void findFloor(String url,DisposeDataListener listener){
        RequestCenter.postRequest(url,GetHeaders.getHeaders(),listener, Floor.class);
    }
    public static void findUnit(String url,DisposeDataListener listener){
        RequestCenter.postRequest(url,GetHeaders.getHeaders(),listener, Unit.class);
    }
    public static void findDoor(String url,DisposeDataListener listener){
        RequestCenter.postRequest(url,GetHeaders.getHeaders(),listener, Door.class);
    }
    public static void addCommunity(String url,DisposeDataListener listener){
        RequestCenter.postRequest(url,GetHeaders.getHeaders(),listener, AddFangWu.class);
    }
    public static void findCommunityByPer(String url,DisposeDataListener listener){
        RequestCenter.postRequest(url,GetHeaders.getHeaders(),listener, SelectPlot.class);
    }
    public static void addPermission(String url,DisposeDataListener listener){
        RequestCenter.postRequest(url,GetHeaders.getHeaders(),listener, FanHui.class);
    }
    public static void findPermissions(String url,DisposeDataListener listener){
        RequestCenter.postRequest(url,GetHeaders.getHeaders(),listener, PermissionList.class);
    }
    public static void updatePermission(String url,DisposeDataListener listener){
        RequestCenter.postRequest(url,GetHeaders.getHeaders(),listener, FanHui.class);
    }
    public static void deletePermission(String url,DisposeDataListener listener){
        RequestCenter.postRequest(url,GetHeaders.getHeaders(),listener, FanHui.class);
    }
    public static void updateUserInfo(String url,DisposeDataListener listener){
        RequestCenter.postRequest(url,GetHeaders.getHeaders(),listener, FanHui.class);
    }

    public static void bannerList(String url,DisposeDataListener listener){
        RequestCenter.postRequest(url,GetHeaders.getHeaders(),listener, Banner.class);
    }
    public static void userSuggest(String url,DisposeDataListener listener){
        RequestCenter.postRequest(url,GetHeaders.getHeaders(),listener, FanHui.class);
    }
    public static void getUserInfo(String url,DisposeDataListener listener){
        RequestCenter.postRequest(url,GetHeaders.getHeaders(),listener, Users1.class);
    }
    public static void getLockByCom(String url,DisposeDataListener listener){
        RequestCenter.postRequest(url,GetHeaders.getHeaders(),listener, ByCom.class);
    }

    public static void openDoorList(String url,DisposeDataListener listener){
        RequestCenter.postRequest(url,GetHeaders.getHeaders(),listener, OpenDoorList.class);
    }
    public static void openDoor(String url,DisposeDataListener listener){
        RequestCenter.postRequest(url,GetHeaders.getHeaders(),listener, OpenDoor.class);
    }

    public static void getNoticeList(String url,DisposeDataListener listener){
        RequestCenter.postRequest(url,GetHeaders.getHeaders(),listener, GongGao.class);
    }
    public static void getPropertyList(String url,DisposeDataListener listener){
        RequestCenter.postRequest(url,GetHeaders.getHeaders(),listener, WuYeJiaoFeiLiShi.class);
    }
    public static void weixiapi(String url,RequestParams params,DisposeDataListener listener){
        RequestCenter.postRequest1(url,params,null,listener, FanHui.class);
    }

    public static void proPayOrder(String url,DisposeDataListener listener){
        RequestCenter.postRequest(url,GetHeaders.getHeaders(),listener, ProPayOrder.class);
    }
    public static void proPayOrderByAli(String url,DisposeDataListener listener){
        RequestCenter.postRequest(url,GetHeaders.getHeaders(),listener, ProPayOrderByAli.class);
    }



    public static void addPhoneList(String url,RequestParams params,DisposeDataListener listener){
        RequestCenter.postRequest1(url,params,null,listener, FanHui.class);
    }
    public static void confirmPayOrder(String url,DisposeDataListener listener){
        RequestCenter.postRequest(url,GetHeaders.getHeaders(),listener, FanHui.class);
    }


    public static void selectAdvanceInfo(String url,DisposeDataListener listener){
        RequestCenter.postRequest(url,GetHeaders.getHeaders(),listener, Loan.class);
    }
    public static void selectSecondHand(String url,DisposeDataListener listener){
        RequestCenter.postRequest(url,GetHeaders.getHeaders(),listener, Loan.class);
    }
    public static void order_proPayOrderByWx(String url,DisposeDataListener listener){
        RequestCenter.postRequest(url,GetHeaders.getHeaders(),listener, OrderWeiXin.class);
    }
    public static void startadpage(String url,DisposeDataListener listener){
        RequestCenter.postRequest1(url,null,null,listener, QiDongYe.class);
    }

    public static void centerinfo_ebppBillAdd(String url,DisposeDataListener listener){
        RequestCenter.postRequest1(url,null,null,listener, FanHui.class);
    }

    public static void centerinfo_ebppBillGet(String url,DisposeDataListener listener){
        RequestCenter.postRequest1(url,null,null,listener, FanHui.class);
    }

    public static void neighbor_addparty(String url,DisposeDataListener listener){
        RequestCenter.postRequest1(url,null,null,listener, FanHui2.class);
    }
    public static void neighbor_addpartyimg(String url, Map<String,Object> map, DisposeDataListener listener){
        RequestCenter.uploadPicturesList(url,map,listener);
    }
    public static void neighbor_addpartyreply(String url,DisposeDataListener listener){
        RequestCenter.postRequest1(url,null,null,listener, FanHui2.class);
    }
    public static void neighbor_joinparty(String url,DisposeDataListener listener){
        RequestCenter.postRequest1(url,null,null,listener, FanHui2.class);
    }
    public static void neighbor_addfriend(String url,DisposeDataListener listener){
        RequestCenter.postRequest1(url,null,null,listener, FanHui2.class);
    }
    public static void neighbor_editremark(String url,DisposeDataListener listener){
        RequestCenter.postRequest1(url,null,null,listener, FanHui2.class);
    }
    public static void neighbor_deletefriend(String url,DisposeDataListener listener){
        RequestCenter.postRequest1(url,null,null,listener, FanHui2.class);
    }
    public static void usercenter_getUserInfo(String url,DisposeDataListener listener){
        RequestCenter.postRequest1(url,null,null,listener, GeRenZhongXin.class);
    }
    public static void usercenter_updateUserInfo(String url,DisposeDataListener listener){
        RequestCenter.postRequest1(url,null,GetHeaders.getHeaders(),listener, FanHui2.class);
    }

    public static void getTianqi(String url,RequestParams params,DisposeDataListener listener){
        RequestCenter.getRequest1(url,params,null,listener, TianQi.class);
    }
    public static void init_firstInit(String url,DisposeDataListener listener){
        RequestCenter.postRequest1(url,null,GetHeaders.getHeaders(),listener, Init.class);
    }

    public static void usercenter_getSysMsg(String url,DisposeDataListener listener){
        RequestCenter.postRequest1(url,null,GetHeaders.getHeaders(),listener, MsgList.class);
    }


    public static void all(String url,Class mClass,DisposeDataListener listener){
        RequestCenter.postRequest(url,null,listener, mClass);
    }
    public static void all(String url,RequestParams params,Class mClass,DisposeDataListener listener){
        RequestCenter.postRequest(url,params,listener, mClass);
    }
}

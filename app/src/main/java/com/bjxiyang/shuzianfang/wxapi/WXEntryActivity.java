package com.bjxiyang.shuzianfang.wxapi;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;

import com.baisi.myapplication.okhttp.listener.DisposeDataListener;
import com.baisi.myapplication.okhttp.request.RequestParams;
import com.bjxiyang.shuzianfang.myapplication.activity.MyXinXiActivity;
import com.bjxiyang.shuzianfang.myapplication.manager.SPManager;
import com.bjxiyang.shuzianfang.myapplication.model.Users;
import com.bjxiyang.shuzianfang.myapplication.model.WXLogin;
import com.bjxiyang.shuzianfang.myapplication.model.WXLoginResult;
import com.bjxiyang.shuzianfang.myapplication.response_xy.XY_Response;
import com.bjxiyang.shuzianfang.myapplication.activity.BeasActivity;
import com.bjxiyang.shuzianfang.myapplication.activity.HomeActivity;
import com.bjxiyang.shuzianfang.myapplication.activity.SDLogin2Activity;
import com.bjxiyang.shuzianfang.myapplication.activity.SDLoginActivity;
import com.bjxiyang.shuzianfang.myapplication.until.MyUntil;
import com.bjxiyang.shuzianfang.myapplication.update.network.RequestCenter;
import com.tencent.mm.opensdk.modelbase.BaseReq;
import com.tencent.mm.opensdk.modelbase.BaseResp;
import com.tencent.mm.opensdk.modelmsg.SendAuth;
import com.tencent.mm.opensdk.openapi.IWXAPI;
import com.tencent.mm.opensdk.openapi.IWXAPIEventHandler;
import com.tencent.mm.opensdk.openapi.WXAPIFactory;

/**
 * Created by Administrator on 2017/10/21 0021.
 */

public class WXEntryActivity extends BeasActivity implements IWXAPIEventHandler {
    private static final String WEIXIN_APP_ID="wxfe7d0b3660506256";
    private static final String WEIXIN_SCOPE="snsapi_userinfo";
    private static final String WEIXIN_STATE="wechat_sdk_login";
    private static final String WEIXIN_STATE2="wechat_sdk_update";
    private static final String WEIXIN_SECRET="29753bd28bf7b1df675b41b4dbabd217";

    /**
     * 微信登录相关
     */
    private IWXAPI api;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        Log.i("LLL","执行到了这里0");
        api = WXAPIFactory.createWXAPI(this, WEIXIN_APP_ID);
        api.registerApp(WEIXIN_APP_ID);

//        api.handleIntent(getIntent(), this);
        handleIntent(getIntent());
    }

    @Override
    protected void onNewIntent(Intent intent) {
        super.onNewIntent(intent);
        handleIntent(intent);
    }

    private void handleIntent(Intent intent) {
        //通过WXAPIFactory工厂获取IWXApI的示例
        api = WXAPIFactory.createWXAPI(this, WEIXIN_APP_ID,true);
        //将应用的appid注册到微信
        api.registerApp(WEIXIN_APP_ID);
        Log.i("LLL","执行到了这里");
        //注意：
        //第三方开发者如果使用透明界面来实现WXEntryActivity，需要判断handleIntent的返回值，如果返回值为false，则说明入参不合法未被SDK处理，应finish当前透明界面，避免外部通过传递非法参数的Intent导致停留在透明界面，引起用户的疑惑
        try {
            boolean result =  api.handleIntent(getIntent(), this);
            if(!result){
                finish();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }


        SendAuth.Resp resp = new SendAuth.Resp(intent.getExtras());
        Log.i("LLL","执行到了这里1");
//        if (resp.errCode == BaseResp.ErrCode.ERR_OK) {
//            Log.i("LLL","执行到了这里2");
//            //用户同意
//            String url="https://api.weixin.qq.com/sns/oauth2/access_token?";
//            RequestParams params=new RequestParams();
//            params.put("appid",WEIXIN_APP_ID);
//            params.put("secret",WEIXIN_SECRET);
//            params.put("code",resp.code);
//            params.put("grant_type","authorization_code");
//            RequestCenter.all(url, params, WXLogin.class, new DisposeDataListener() {
//                @Override
//                public void onSuccess(Object responseObj) {
//
//                }
//
//                @Override
//                public void onFailure(Object reasonObj) {
//
//                }
//            });
//
//
//
//        }
    }

    @Override
    public void onReq(BaseReq baseReq) {

    }

    @Override
    public void onResp(BaseResp baseResp) {
        Log.i("LLL","执行到了这里11");
        System.out.println("onResp");
        switch (baseResp.errCode) {
            case BaseResp.ErrCode.ERR_OK:
                Log.i("LLL","执行到了这里12");
                System.out.println("成功");
                final SendAuth.Resp response = (SendAuth.Resp) baseResp;
                System.out.println(response.state);

                if (!response.state.equals(WEIXIN_STATE)&&!response.state.equals(WEIXIN_STATE2))
                    return;// 判断请求是否是我的应用的请求

                if (baseResp.errCode == BaseResp.ErrCode.ERR_OK) {
                    Log.i("LLL","执行到了这里2");
                    //用户同意
                    String url="https://api.weixin.qq.com/sns/oauth2/access_token?";
                    RequestParams params=new RequestParams();
                    params.put("appid",WEIXIN_APP_ID);
                    params.put("secret",WEIXIN_SECRET);
                    params.put("code",((SendAuth.Resp) baseResp).code);
                    params.put("grant_type","authorization_code");

                    Log.i("LLL",url);

                    RequestCenter.all(url, params, WXLogin.class, new DisposeDataListener() {
                        @Override
                        public void onSuccess(Object responseObj) {
                            WXLogin wxLogin= (WXLogin) responseObj;
                            if (wxLogin!=null){
                                SPManager.getInstance().putString("openid",wxLogin.getOpenid());
                                SPManager.getInstance().putString("unionid",wxLogin.getUnionid());

                                String url2="https://api.weixin.qq.com/sns/userinfo?"
                                        +"access_token="+wxLogin.getAccess_token()
                                        +"&openid="+wxLogin.getOpenid()
                                        +"&lang=zh_CN";

                                RequestCenter.all(url2, WXLoginResult.class, new DisposeDataListener() {
                                    @Override
                                    public void onSuccess(Object responseObj) {

                                    }

                                    @Override
                                    public void onFailure(Object reasonObj) {

                                    }
                                });
                                String url1= XY_Response.URL_INIT_LOGINBYTHIRD
                                        +"openid="+wxLogin.getOpenid()
                                        +"&type="+0;
                                RequestCenter.all(url1, Users.class, new DisposeDataListener() {
                                    @Override
                                    public void onSuccess(Object responseObj) {
                                        Users users= (Users) responseObj;
                                        if (users.getCode()==1000){
                                            setSP(users);
                                            if (response.state.equals(WEIXIN_STATE)) {
                                                MyUntil.mStartActivity(WXEntryActivity.this, HomeActivity.class);
                                                SDLoginActivity.sdLoginActivity.finish();
                                                finish();
                                            }
                                            if (response.state.equals(WEIXIN_STATE2)){
                                                MyUntil.mStartActivity(WXEntryActivity.this, MyXinXiActivity.class);
                                                finish();
                                            }


                                        }else {
                                            if (response.state.equals(WEIXIN_STATE)) {
                                                MyUntil.mStartActivity(WXEntryActivity.this, SDLogin2Activity.class);
                                                finish();
                                            }
                                            if (response.state.equals(WEIXIN_STATE2)){
                                                MyUntil.mStartActivity(WXEntryActivity.this, MyXinXiActivity.class);
                                                finish();
                                            }
                                            finish();
                                        }
                                    }

                                    @Override
                                    public void onFailure(Object reasonObj) {

                                    }
                                });


                            }
                        }

                        @Override
                        public void onFailure(Object reasonObj) {

                        }
                    });
                }
                System.out.println(response.code);
                System.out.println(response.country);
                break;
            case BaseResp.ErrCode.ERR_USER_CANCEL:
                finish();
                System.out.println("quxiao");
                break;
            case BaseResp.ErrCode.ERR_AUTH_DENIED:
                finish();
                System.out.println("jujue");
                break;
            default:
                break;
//        String result = "";
//        switch(baseResp.errCode) {
//            case BaseResp.ErrCode.ERR_OK:
//                result ="发送成功";
//                showMsg(1,result);
//                finish();
//                break;
//            case BaseResp.ErrCode.ERR_USER_CANCEL:
//                result = "发送取消";
//                showMsg(2,result);
//                finish();
//                break;
//            case BaseResp.ErrCode.ERR_AUTH_DENIED:
//                result = "发送被拒绝";
//                showMsg(1,result);
//                finish();
//                break;
//            default:
//                result = "发送返回";
//                showMsg(0,result);
//                finish();
//                break;
        }

    }
    private void showMsg(int a,String msg){
        MyUntil.show(this,msg);
    }

    private void setSP(Users users){
        //会员编号
        SPManager.getInstance().putString("loginKey", String.valueOf(users.getObj().getLoginKey()));
        SPManager.getInstance().putString("c_memberId", String.valueOf(users.getObj().getCmemberId()));
        SPManager.getInstance().putString("mobilePhone",users.getObj().getMobilePhone());
        SPManager.getInstance().putString("realName",users.getObj().getRealName());
        SPManager.getInstance().putString("nickName",users.getObj().getNickName());
        SPManager.getInstance().putString("sex", String.valueOf(users.getObj().getSex()));
        SPManager.getInstance().putString("headPhotoUrl",users.getObj().getHeadPhotoUrl());
//        SPManager.getInstance().putString("status",users.getObj().get(0).getStatus());
//        SPManager.getInstance().putString("birthday",users.getObj().getBirthday());
//        SPManager.getInstance().putString("email",users.getObj().getEmail());
//        SPManager.getInstance().putString("address",users.getObj().getAddress());
//        SPManager.getInstance().putString("qq",users.getObj().getQq());
//        SPManager.getInstance().putString("weChat",users.getObj().getWeChat());
//        SPManager.getInstance().putString("age", users.getObj().getAge());
//        SPManager.getInstance().putString("ownerId",users.getObj().getOwnerId());
    }


}
package com.bjxiyang.shuzianfang.myapplication.model;

/**
 * Created by gll on 2017/10/24.
 */

public class WXLogin {


    /**
     * access_token : bTk9bOvLZuYyAqr0xGmcPVDQpEzM149fa_z9c2t8quTNq38MqXdMoZxdv2cqQMR0oS4rJFBtSGeTWrWXYuW5NutnazXHY0lvKr3nzWHBx50
     * expires_in : 7200
     * refresh_token : 3nIoFU6_7-IspbH-eu-gSkdUME4XXSsIlO9h-3SQCdG9NbeBsAf9Rzx-6msPVUSYmu22BvqbnQiZaYPLYtilEwXSa1woCXva4BUNBBa-rWg
     * openid : osF1ww-oh8LaQogPyozIGOaT6mq8
     * scope : snsapi_userinfo
     * unionid : ouVlZwYZ_ozPB5Vu3Qta8W-A4LiA
     */

    private String access_token;
    private int expires_in;
    private String refresh_token;
    private String openid;
    private String scope;
    private String unionid;

    public String getAccess_token() {
        return access_token;
    }

    public void setAccess_token(String access_token) {
        this.access_token = access_token;
    }

    public int getExpires_in() {
        return expires_in;
    }

    public void setExpires_in(int expires_in) {
        this.expires_in = expires_in;
    }

    public String getRefresh_token() {
        return refresh_token;
    }

    public void setRefresh_token(String refresh_token) {
        this.refresh_token = refresh_token;
    }

    public String getOpenid() {
        return openid;
    }

    public void setOpenid(String openid) {
        this.openid = openid;
    }

    public String getScope() {
        return scope;
    }

    public void setScope(String scope) {
        this.scope = scope;
    }

    public String getUnionid() {
        return unionid;
    }

    public void setUnionid(String unionid) {
        this.unionid = unionid;
    }
}

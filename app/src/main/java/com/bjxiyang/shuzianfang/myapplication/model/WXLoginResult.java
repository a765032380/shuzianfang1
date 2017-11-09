package com.bjxiyang.shuzianfang.myapplication.model;

import java.util.List;

/**
 * Created by gll on 2017/10/24.
 */

public class WXLoginResult {


    /**
     * openid : osF1ww-oh8LaQogPyozIGOaT6mq8
     * nickname : gll_android
     * sex : 1
     * language : zh_CN
     * city : 邯郸
     * province : 河北
     * country : 中国
     * headimgurl : http://wx.qlogo.cn/mmopen/vi_32/DYAIOgq83eqkUDSbrAX3iaDQxhLc4wv6mqSr6LCuTMMXqG8EFajmibYziboG4wm6ZicC6dLbAuRhHqApQ2A5K8H3iag/0
     * privilege : []
     * unionid : ouVlZwYZ_ozPB5Vu3Qta8W-A4LiA
     */

    private String openid;
    private String nickname;
    private int sex;
    private String language;
    private String city;
    private String province;
    private String country;
    private String headimgurl;
    private String unionid;
    private List<?> privilege;

    public String getOpenid() {
        return openid;
    }

    public void setOpenid(String openid) {
        this.openid = openid;
    }

    public String getNickname() {
        return nickname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    public int getSex() {
        return sex;
    }

    public void setSex(int sex) {
        this.sex = sex;
    }

    public String getLanguage() {
        return language;
    }

    public void setLanguage(String language) {
        this.language = language;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getProvince() {
        return province;
    }

    public void setProvince(String province) {
        this.province = province;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getHeadimgurl() {
        return headimgurl;
    }

    public void setHeadimgurl(String headimgurl) {
        this.headimgurl = headimgurl;
    }

    public String getUnionid() {
        return unionid;
    }

    public void setUnionid(String unionid) {
        this.unionid = unionid;
    }

    public List<?> getPrivilege() {
        return privilege;
    }

    public void setPrivilege(List<?> privilege) {
        this.privilege = privilege;
    }
}

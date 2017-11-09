package com.bjxiyang.shuzianfang.myapplication.response_xy;

/**
 * Created by Administrator on 2017/8/4 0004.
 */

public class XY_Response2 {


    public static final String URL="http://47.92.104.209:8088/anfang/";
//    public static final String URL="http://192.168.1.229:8080/anfang/";
    //获取首页信息
    public static final String URL_HOME=URL+"init/firstinit?";
    //获取地址信息后请求店铺信息
    public static final String URL_HOME_SELLER=URL+"init/firstInitSeller?";

    //街坊儿增加活动
    public static final String URL_NEIGHBOR_ADDPARTY=URL+"neighbor/addparty?";
    //街坊儿增加活动时上传图片
    public static final String URL_NEIGHBOR_ADDPARTYIMG=URL+"neighbor/addpartyimg?";
    //街坊儿查询活动列表
    public static final String URL_NEIGHBOR_FINDPARTY=URL+"neighbor/findparty?";
    //街坊儿查询活动详情
    public static final String URL_NEIGHBOR_FINDPARTYDETAILS=URL+"neighbor/findpartydetails?";
    //街坊儿添加活动评论
    public static final String URL_NEIGHBOR_ADDPARTYREPLY=URL+"neighbor/addpartyreply?";
    //街坊儿加入活动
    public static final String URL_NEIGHBOR_JOINPARTY=URL+"neighbor/joinparty?";
    //获取好友列表
    public static final String URL_NEIGHBOR_FRIENDLIST=URL+"neighbor/friendlist?";
    //添加好友
    public static final String URL_NEIGHBOR_ADDFRIEND=URL+"neighbor/addfriend?";
    //修改好友备注
    public static final String URL_NEIGHBOR_EDITREMARK=URL+"neighbor/editremark?";
    //删除好友
    public static final String URL_NEIGHBOR_DELETEFRIEND=URL+"neighbor/deletefriend?";
    //个人信息修改
    public static final String URL_UESRCENTER_UPDATEUSERINFO=URL+"image/addpic?";

    public static final String URL_UESRCENTER_UPDATEUSERINFO1=URL+"usercenter/updateUserName?";
    //上传图片地址
    public static final String URL_UESRCENTER_UPDATEUSERPIC=URL+"usercenter/updateUserPic?";



    //个人信息查询
    public static final String URL_UESRCENTER_GETUSERINFO=URL+"usercenter/getUserInfo?";
    //获取系统消息
    public static final String URL_USERCENTER_GETSYSMSG=URL+"usercenter/getSysMsg?";



}

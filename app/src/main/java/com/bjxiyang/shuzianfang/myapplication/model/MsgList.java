package com.bjxiyang.shuzianfang.myapplication.model;

import java.io.Serializable;
import java.util.List;

/**
 * Created by gll on 2017/10/31.
 */

public class MsgList {

    /**
     * code : 1000
     * msg : 获取消息列表成功
     * obj : [{"userId":268,"msgContent":"您发起的活动已有人加入，请在\u201c街坊儿\u201d中与朋友们规划行程吧！","msgType":"活动反馈","contentId":"18","addTime":"2017-08-23 14:15:03"},{"userId":268,"msgContent":"您发起的活动已有人加入，请在\u201c街坊儿\u201d中与朋友们规划行程吧！","msgType":"授权信息","contentId":"17","addTime":"2017-08-23 13:52:00"},{"userId":268,"msgContent":"您发起的活动已有人加入，请在\u201c街坊儿\u201d中与朋友们规划行程吧！","msgType":"活动反馈","contentId":"19","addTime":"2017-08-23 13:51:36"}]
     */

    private int code;
    private String msg;
    private List<ObjBean> obj;

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public List<ObjBean> getObj() {
        return obj;
    }

    public void setObj(List<ObjBean> obj) {
        this.obj = obj;
    }

    public static class ObjBean implements Serializable {
        /**
         * userId : 268
         * msgContent : 您发起的活动已有人加入，请在“街坊儿”中与朋友们规划行程吧！
         * msgType : 活动反馈
         * contentId : 18
         * addTime : 2017-08-23 14:15:03
         */

        private int userId;
        private String msgContent;
        private String msgType;
        private String contentId;
        private String addTime;

        public int getUserId() {
            return userId;
        }

        public void setUserId(int userId) {
            this.userId = userId;
        }

        public String getMsgContent() {
            return msgContent;
        }

        public void setMsgContent(String msgContent) {
            this.msgContent = msgContent;
        }

        public String getMsgType() {
            return msgType;
        }

        public void setMsgType(String msgType) {
            this.msgType = msgType;
        }

        public String getContentId() {
            return contentId;
        }

        public void setContentId(String contentId) {
            this.contentId = contentId;
        }

        public String getAddTime() {
            return addTime;
        }

        public void setAddTime(String addTime) {
            this.addTime = addTime;
        }
    }
}

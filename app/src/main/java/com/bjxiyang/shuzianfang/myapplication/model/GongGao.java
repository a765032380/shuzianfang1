package com.bjxiyang.shuzianfang.myapplication.model;

import java.io.Serializable;
import java.util.List;

/**
 * Created by Administrator on 2017/6/9 0009.
 */

public class GongGao implements Serializable {


    /**
     * code : 1000
     * msg : 获取消息列表成功
     * obj : [{"userId":89,"msgContent":"亲爱的用户您好，您的房屋有租客需要您授权开通门禁，请前往钥匙授权列表进行授权","msgType":"开通授权","contentId":"0","addTime":"2017-11-23 10:05:13"},{"userId":89,"msgContent":"亲爱的用户您好，您的房屋有租客需要您授权开通门禁，请前往钥匙授权列表进行授权","msgType":"开通授权","contentId":"0","addTime":"2017-11-22 18:39:48"},{"userId":89,"msgContent":"亲爱的用户您好，您的房屋有租客需要您授权开通门禁，请前往钥匙授权列表进行授权","msgType":"开通授权","contentId":"0","addTime":"2017-11-22 18:02:39"},{"userId":89,"msgContent":"您发起的活动已有人加入，请在\u201c街坊儿\u201d中与朋友们规划行程吧！","msgType":"活动反馈","contentId":"65","addTime":"2017-08-25 15:11:45"},{"userId":89,"msgContent":"您发起的活动已有人加入，请在\u201c街坊儿\u201d中与朋友们规划行程吧！","msgType":"活动反馈","contentId":"65","addTime":"2017-08-25 14:50:58"}]
     */

    private int code;
    private String msg;
    private List<Obj> obj;

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

    public List<Obj> getObj() {
        return obj;
    }

    public void setObj(List<Obj> obj) {
        this.obj = obj;
    }

    public static class Obj implements Serializable {
        /**
         * userId : 89
         * msgContent : 亲爱的用户您好，您的房屋有租客需要您授权开通门禁，请前往钥匙授权列表进行授权
         * msgType : 开通授权
         * contentId : 0
         * addTime : 2017-11-23 10:05:13
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

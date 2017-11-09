package com.bjxiyang.shuzianfang.myapplication.model;

/**
 * Created by Administrator on 2017/10/19 0019.
 */

public class ImageUrl2 {

    /**
     * code : 1000
     * msg : 上传图片成功
     * obj : {"picUrl":"1508405074270.jpg"}
     */

    private int code;
    private String msg;
    private ObjBean obj;

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

    public ObjBean getObj() {
        return obj;
    }

    public void setObj(ObjBean obj) {
        this.obj = obj;
    }

    public static class ObjBean {
        /**
         * picUrl : 1508405074270.jpg
         */

        private String picUrl;

        public String getPicUrl() {
            return picUrl;
        }

        public void setPicUrl(String picUrl) {
            this.picUrl = picUrl;
        }
    }
}

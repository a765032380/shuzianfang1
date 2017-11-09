package com.bjxiyang.shuzianfang.myapplication.model;

/**
 * Created by Administrator on 2017/6/7 0007.
 */

public class FanHui {
    /**
     * code : 200
     * msg : 用户名或密码错误
     */

    private String code;
    private String msg;
    private Obj obj;

    public Obj getObj() {
        return obj;
    }

    public void setObj(Obj obj) {
        this.obj = obj;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }


        public static class Obj {
            /**
             * bondUrl : http://www.bjxiyang.com
             * validityDate : 2017-07-08---2017-07-30
             * bondName : 开门有喜
             * bondLimit : 7折优惠
             */

            private String bondUrl;
            private String validityDate;
            private String bondName;
            private String bondLimit;
            private int type;

            public int getType() {
                return type;
            }

            public void setType(int type) {
                this.type = type;
            }

            public String getBondUrl() {
                return bondUrl;
            }

            public void setBondUrl(String bondUrl) {
                this.bondUrl = bondUrl;
            }

            public String getValidityDate() {
                return validityDate;
            }

            public void setValidityDate(String validityDate) {
                this.validityDate = validityDate;
            }

            public String getBondName() {
                return bondName;
            }

            public void setBondName(String bondName) {
                this.bondName = bondName;
            }

            public String getBondLimit() {
                return bondLimit;
            }

            public void setBondLimit(String bondLimit) {
                this.bondLimit = bondLimit;
            }
        }

}

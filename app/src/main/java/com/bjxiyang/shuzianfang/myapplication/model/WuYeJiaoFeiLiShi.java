package com.bjxiyang.shuzianfang.myapplication.model;

import java.util.List;

/**
 * Created by gll on 2017/10/25.
 */

public class WuYeJiaoFeiLiShi {


    /**
     * code : 1000
     * msg : 获取费用列表成功
     * obj : [{"p":"2017-08","t":"8月","monthpaylist":[{"payNo":"2017082321001004230511540425","addTime":"2017-08-25 11:35:12","unitName":"一单元","propertyName":"士大夫撒旦 3","integral":10,"fee":10,"doorName":"606","communityName":"鹏景阁大厦","feeType":0,"propertyId":10}]},{"p":"2017-07","t":"7月","monthpaylist":[{"payNo":"2017082321001004230511540424","addTime":"2017-07-23 14:15:52","unitName":"一单元","propertyName":"士大夫撒旦","integral":10,"fee":0.02,"doorName":"606","communityName":"鹏景阁大厦","feeType":0,"propertyId":9}]},{"p":"2017-06","t":"6月","monthpaylist":[{"payNo":"2017082321001004230511540423","addTime":"2017-06-23 14:15:37","unitName":"一单元","propertyName":"士大夫","integral":10,"fee":0.01,"doorName":"606","communityName":"鹏景阁大厦","feeType":0,"propertyId":8}]},{"p":"2017-05","t":"5月","monthpaylist":[{"payNo":"2017082321001004230511540426","addTime":"2017-08-25 11:35:39","propertyName":"大光和热","integral":10,"fee":0.01,"communityName":"宏源公寓","feeType":0,"propertyId":11}]}]
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

    public static class ObjBean {
        /**
         * p : 2017-08
         * t : 8月
         * monthpaylist : [{"payNo":"2017082321001004230511540425","addTime":"2017-08-25 11:35:12","unitName":"一单元","propertyName":"士大夫撒旦 3","integral":10,"fee":10,"doorName":"606","communityName":"鹏景阁大厦","feeType":0,"propertyId":10}]
         */

        private String p;
        private String t;
        private List<MonthpaylistBean> monthpaylist;

        public String getP() {
            return p;
        }

        public void setP(String p) {
            this.p = p;
        }

        public String getT() {
            return t;
        }

        public void setT(String t) {
            this.t = t;
        }

        public List<MonthpaylistBean> getMonthpaylist() {
            return monthpaylist;
        }

        public void setMonthpaylist(List<MonthpaylistBean> monthpaylist) {
            this.monthpaylist = monthpaylist;
        }

        public static class MonthpaylistBean {
            /**
             * payNo : 2017082321001004230511540425
             * addTime : 2017-08-25 11:35:12
             * unitName : 一单元
             * propertyName : 士大夫撒旦 3
             * integral : 10
             * fee : 10.0
             * doorName : 606
             * communityName : 鹏景阁大厦
             * feeType : 0
             * propertyId : 10
             */

            private String payNo;
            private String addTime;
            private String unitName;
            private String propertyName;
            private int integral;
            private double fee;
            private String doorName;
            private String communityName;
            private int feeType;
            private int propertyId;

            public String getPayNo() {
                return payNo;
            }

            public void setPayNo(String payNo) {
                this.payNo = payNo;
            }

            public String getAddTime() {
                return addTime;
            }

            public void setAddTime(String addTime) {
                this.addTime = addTime;
            }

            public String getUnitName() {
                return unitName;
            }

            public void setUnitName(String unitName) {
                this.unitName = unitName;
            }

            public String getPropertyName() {
                return propertyName;
            }

            public void setPropertyName(String propertyName) {
                this.propertyName = propertyName;
            }

            public int getIntegral() {
                return integral;
            }

            public void setIntegral(int integral) {
                this.integral = integral;
            }

            public double getFee() {
                return fee;
            }

            public void setFee(double fee) {
                this.fee = fee;
            }

            public String getDoorName() {
                return doorName;
            }

            public void setDoorName(String doorName) {
                this.doorName = doorName;
            }

            public String getCommunityName() {
                return communityName;
            }

            public void setCommunityName(String communityName) {
                this.communityName = communityName;
            }

            public int getFeeType() {
                return feeType;
            }

            public void setFeeType(int feeType) {
                this.feeType = feeType;
            }

            public int getPropertyId() {
                return propertyId;
            }

            public void setPropertyId(int propertyId) {
                this.propertyId = propertyId;
            }
        }
    }
}

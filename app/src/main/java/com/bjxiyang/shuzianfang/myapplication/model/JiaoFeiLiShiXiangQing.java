package com.bjxiyang.shuzianfang.myapplication.model;

/**
 * Created by gll on 2017/10/25.
 */

public class JiaoFeiLiShiXiangQing {

    /**
     * code : 1000
     * msg : 获取费用列表成功
     * obj : {"orderNo":"17082511351216334","addTime":"2017-08-25 11:35:12","unitName":"一单元","fee":10,"doorName":"606","havePayment":1,"paymentType":0,"payNo":"2017082321001004230511540425","propertyName":"士大夫撒旦 3","integral":10,"nperName":"一期","communityName":"鹏景阁大厦","floorName":"一栋","paymentTime":"2017-08-23 00:00:00","propertyId":10}
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
         * orderNo : 17082511351216334
         * addTime : 2017-08-25 11:35:12
         * unitName : 一单元
         * fee : 10.0
         * doorName : 606
         * havePayment : 1
         * paymentType : 0
         * payNo : 2017082321001004230511540425
         * propertyName : 士大夫撒旦 3
         * integral : 10
         * nperName : 一期
         * communityName : 鹏景阁大厦
         * floorName : 一栋
         * paymentTime : 2017-08-23 00:00:00
         * propertyId : 10
         */

        private String orderNo;
        private String addTime;
        private String unitName;
        private double fee;
        private String doorName;
        private int havePayment;
        private int paymentType;
        private String payNo;
        private String propertyName;
        private int integral;
        private String nperName;
        private String communityName;
        private String floorName;
        private String paymentTime;
        private int propertyId;

        public String getOrderNo() {
            return orderNo;
        }

        public void setOrderNo(String orderNo) {
            this.orderNo = orderNo;
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

        public int getHavePayment() {
            return havePayment;
        }

        public void setHavePayment(int havePayment) {
            this.havePayment = havePayment;
        }

        public int getPaymentType() {
            return paymentType;
        }

        public void setPaymentType(int paymentType) {
            this.paymentType = paymentType;
        }

        public String getPayNo() {
            return payNo;
        }

        public void setPayNo(String payNo) {
            this.payNo = payNo;
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

        public String getNperName() {
            return nperName;
        }

        public void setNperName(String nperName) {
            this.nperName = nperName;
        }

        public String getCommunityName() {
            return communityName;
        }

        public void setCommunityName(String communityName) {
            this.communityName = communityName;
        }

        public String getFloorName() {
            return floorName;
        }

        public void setFloorName(String floorName) {
            this.floorName = floorName;
        }

        public String getPaymentTime() {
            return paymentTime;
        }

        public void setPaymentTime(String paymentTime) {
            this.paymentTime = paymentTime;
        }

        public int getPropertyId() {
            return propertyId;
        }

        public void setPropertyId(int propertyId) {
            this.propertyId = propertyId;
        }
    }
}

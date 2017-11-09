package com.bjxiyang.shuzianfang.myapplication.model;

import java.util.List;

/**
 * Created by Administrator on 2017/6/9 0009.
 */

public class WuYeJiaoFei {

    /**
     * code : 1000
     * msg : 获取费用列表成功
     * obj : [{"orderNo":"5225555","addTime":"2017-06-30 16:26:25","propertyName":"的萨发","integral":10,"fee":30,"communityName":"国风美唐","paymentTime":"2017-10-30 16:56:35","propertyId":1,"havePayment":0,"paymentType":0},{"payNo":"2017070721001004110262588483","orderNo":"5225667212","addTime":"2017-07-01 15:25:26","propertyName":"物业公司","integral":10,"fee":0.01,"communityName":"国风美唐","paymentTime":"2017-07-07 17:25:36","propertyId":2,"havePayment":0,"paymentType":1},{"payNo":"2017070421001004440211536173","orderNo":"5225668212","addTime":"2017-07-02 15:26:36","propertyName":"物业公司","integral":10,"fee":0.01,"communityName":"国风美唐","paymentTime":"2016-07-04 20:16:24","propertyId":3,"havePayment":0,"paymentType":1},{"payNo":"4005272001201707048906748348","orderNo":"5225669212","addTime":"2017-07-03 15:27:38","propertyName":"物业公司","integral":10,"fee":0.01,"communityName":"国风美唐","paymentTime":"2017-07-04 20:24:17","propertyId":4,"havePayment":0,"paymentType":0},{"payNo":"4005272001201707048903903038","orderNo":"5225670212","addTime":"2017-07-04 15:26:23","propertyName":"物业公司","integral":10,"fee":0.01,"communityName":"国风美唐","paymentTime":"2017-07-04 20:37:51","propertyId":5,"havePayment":0,"paymentType":0},{"orderNo":"5225671212","addTime":"2017-07-05 15:26:56","propertyName":"物业公司","integral":10,"fee":0.01,"communityName":"国风美唐","paymentTime":"2017-07-01 18:50:13","propertyId":6,"havePayment":0,"paymentType":0}]
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
         * orderNo : 5225555
         * addTime : 2017-06-30 16:26:25
         * propertyName : 的萨发
         * integral : 10
         * fee : 30.0
         * communityName : 国风美唐
         * paymentTime : 2017-10-30 16:56:35
         * propertyId : 1
         * havePayment : 0
         * paymentType : 0
         * payNo : 2017070721001004110262588483
         */

        private String orderNo;
        private String addTime;
        private String propertyName;
        private int integral;
        private double fee;
        private String communityName;
        private String paymentTime;
        private int propertyId;
        private int havePayment;
        private int paymentType;
        private String payNo;

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

        public String getCommunityName() {
            return communityName;
        }

        public void setCommunityName(String communityName) {
            this.communityName = communityName;
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
    }
}

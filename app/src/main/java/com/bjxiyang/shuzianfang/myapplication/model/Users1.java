package com.bjxiyang.shuzianfang.myapplication.model;

/**
 * Created by Administrator on 2017/6/23 0023.
 */

public class Users1 {

    /**
     * code : 1000
     * msg : 获取个人信息成功
     * obj : {"qq":"","address":"","nickName":"ssss","headPhotoUrl":"1508405751783.jpg","c_memberId":268,"sex":1,"idNumber":"","realState":0,"realName":"","password":"de88e3e4ab202d87754078cbb2df6063","mobilePhone":"18813045215","integral":0,"opendoorCount":5,"weChat":"","email":""}
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
         * qq :
         * address :
         * nickName : ssss
         * headPhotoUrl : 1508405751783.jpg
         * c_memberId : 268
         * sex : 1
         * idNumber :
         * realState : 0
         * realName :
         * password : de88e3e4ab202d87754078cbb2df6063
         * mobilePhone : 18813045215
         * integral : 0
         * opendoorCount : 5
         * weChat :
         * email :
         */

        private String qq;
        private String address;
        private String nickName;
        private String headPhotoUrl;
        private int c_memberId;
        private int sex;
        private String idNumber;
        private int realState;
        private String realName;
        private String password;
        private String mobilePhone;
        private int integral;
        private int opendoorCount;
        private String weChat;
        private String email;

        public String getQq() {
            return qq;
        }

        public void setQq(String qq) {
            this.qq = qq;
        }

        public String getAddress() {
            return address;
        }

        public void setAddress(String address) {
            this.address = address;
        }

        public String getNickName() {
            return nickName;
        }

        public void setNickName(String nickName) {
            this.nickName = nickName;
        }

        public String getHeadPhotoUrl() {
            return headPhotoUrl;
        }

        public void setHeadPhotoUrl(String headPhotoUrl) {
            this.headPhotoUrl = headPhotoUrl;
        }

        public int getC_memberId() {
            return c_memberId;
        }

        public void setC_memberId(int c_memberId) {
            this.c_memberId = c_memberId;
        }

        public int getSex() {
            return sex;
        }

        public void setSex(int sex) {
            this.sex = sex;
        }

        public String getIdNumber() {
            return idNumber;
        }

        public void setIdNumber(String idNumber) {
            this.idNumber = idNumber;
        }

        public int getRealState() {
            return realState;
        }

        public void setRealState(int realState) {
            this.realState = realState;
        }

        public String getRealName() {
            return realName;
        }

        public void setRealName(String realName) {
            this.realName = realName;
        }

        public String getPassword() {
            return password;
        }

        public void setPassword(String password) {
            this.password = password;
        }

        public String getMobilePhone() {
            return mobilePhone;
        }

        public void setMobilePhone(String mobilePhone) {
            this.mobilePhone = mobilePhone;
        }

        public int getIntegral() {
            return integral;
        }

        public void setIntegral(int integral) {
            this.integral = integral;
        }

        public int getOpendoorCount() {
            return opendoorCount;
        }

        public void setOpendoorCount(int opendoorCount) {
            this.opendoorCount = opendoorCount;
        }

        public String getWeChat() {
            return weChat;
        }

        public void setWeChat(String weChat) {
            this.weChat = weChat;
        }

        public String getEmail() {
            return email;
        }

        public void setEmail(String email) {
            this.email = email;
        }
    }
}

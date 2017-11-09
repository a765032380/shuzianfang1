package com.bjxiyang.shuzianfang.myapplication.model;

/**
 * Created by Administrator on 2017/6/5 0005.
 */

public class Users {

    /**
     * code : 1000
     * msg : 登录成功
     * obj : {"birthday":"","qq":"","ownerStatus":"1","address":"","nickName":"ssss","headPhotoUrl":"1508405751783.jpg","loginKey":"1d6f54459f7448d98876577d50e3f6a4","sex":"1","idNumber":"","ownerId":"","realState":0,"realName":"","propertyStatus":"1","password":"de88e3e4ab202d87754078cbb2df6063","mobilePhone":"18813045215","cmemberId":268,"weChat":"osF1ww-oh8LaQogPyozIGOaT6mq8","age":"","email":""}
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
         * birthday :
         * qq :
         * ownerStatus : 1
         * address :
         * nickName : ssss
         * headPhotoUrl : 1508405751783.jpg
         * loginKey : 1d6f54459f7448d98876577d50e3f6a4
         * sex : 1
         * idNumber :
         * ownerId :
         * realState : 0
         * realName :
         * propertyStatus : 1
         * password : de88e3e4ab202d87754078cbb2df6063
         * mobilePhone : 18813045215
         * cmemberId : 268
         * weChat : osF1ww-oh8LaQogPyozIGOaT6mq8
         * age :
         * email :
         */

        private String birthday;
        private String qq;
        private String ownerStatus;
        private String address;
        private String nickName;
        private String headPhotoUrl;
        private String loginKey;
        private String sex;
        private String idNumber;
        private String ownerId;
        private int realState;
        private String realName;
        private String propertyStatus;
        private String password;
        private String mobilePhone;
        private int cmemberId;
        private String weChat;
        private String age;
        private String email;

        public String getBirthday() {
            return birthday;
        }

        public void setBirthday(String birthday) {
            this.birthday = birthday;
        }

        public String getQq() {
            return qq;
        }

        public void setQq(String qq) {
            this.qq = qq;
        }

        public String getOwnerStatus() {
            return ownerStatus;
        }

        public void setOwnerStatus(String ownerStatus) {
            this.ownerStatus = ownerStatus;
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

        public String getLoginKey() {
            return loginKey;
        }

        public void setLoginKey(String loginKey) {
            this.loginKey = loginKey;
        }

        public String getSex() {
            return sex;
        }

        public void setSex(String sex) {
            this.sex = sex;
        }

        public String getIdNumber() {
            return idNumber;
        }

        public void setIdNumber(String idNumber) {
            this.idNumber = idNumber;
        }

        public String getOwnerId() {
            return ownerId;
        }

        public void setOwnerId(String ownerId) {
            this.ownerId = ownerId;
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

        public String getPropertyStatus() {
            return propertyStatus;
        }

        public void setPropertyStatus(String propertyStatus) {
            this.propertyStatus = propertyStatus;
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

        public int getCmemberId() {
            return cmemberId;
        }

        public void setCmemberId(int cmemberId) {
            this.cmemberId = cmemberId;
        }

        public String getWeChat() {
            return weChat;
        }

        public void setWeChat(String weChat) {
            this.weChat = weChat;
        }

        public String getAge() {
            return age;
        }

        public void setAge(String age) {
            this.age = age;
        }

        public String getEmail() {
            return email;
        }

        public void setEmail(String email) {
            this.email = email;
        }
    }
}

package com.bjxiyang.shuzianfang.myapplication.model;

/**
 * Created by gll on 2017/10/30.
 */

public class UpdateImage {

    /**
     * code : 1000
     * msg : 更新成功
     * obj : {"birthday":"","sex":"1","ownerId":"","proStatus":1,"ownerStatus":1,"cmemberId":268,"password":"de88e3e4ab202d87754078cbb2df6063","integral":0,"mobilePhone":"18813045215","address":"","email":"","nickName":"aaaaa","age":0,"weChat":"osF1ww-oh8LaQogPyozIGOaT6mq8","realName":"xxxx","realState":0,"haveUser":1,"headPhotoUrl":"http://47.92.104.209:8088/img/1509342447266.jpg","qq":"","idNumber":""}
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
         * sex : 1
         * ownerId :
         * proStatus : 1
         * ownerStatus : 1
         * cmemberId : 268
         * password : de88e3e4ab202d87754078cbb2df6063
         * integral : 0
         * mobilePhone : 18813045215
         * address :
         * email :
         * nickName : aaaaa
         * age : 0
         * weChat : osF1ww-oh8LaQogPyozIGOaT6mq8
         * realName : xxxx
         * realState : 0
         * haveUser : 1
         * headPhotoUrl : http://47.92.104.209:8088/img/1509342447266.jpg
         * qq :
         * idNumber :
         */

        private String birthday;
        private String sex;
        private String ownerId;
        private int proStatus;
        private int ownerStatus;
        private int cmemberId;
        private String password;
        private int integral;
        private String mobilePhone;
        private String address;
        private String email;
        private String nickName;
        private int age;
        private String weChat;
        private String realName;
        private int realState;
        private int haveUser;
        private String headPhotoUrl;
        private String qq;
        private String idNumber;

        public String getBirthday() {
            return birthday;
        }

        public void setBirthday(String birthday) {
            this.birthday = birthday;
        }

        public String getSex() {
            return sex;
        }

        public void setSex(String sex) {
            this.sex = sex;
        }

        public String getOwnerId() {
            return ownerId;
        }

        public void setOwnerId(String ownerId) {
            this.ownerId = ownerId;
        }

        public int getProStatus() {
            return proStatus;
        }

        public void setProStatus(int proStatus) {
            this.proStatus = proStatus;
        }

        public int getOwnerStatus() {
            return ownerStatus;
        }

        public void setOwnerStatus(int ownerStatus) {
            this.ownerStatus = ownerStatus;
        }

        public int getCmemberId() {
            return cmemberId;
        }

        public void setCmemberId(int cmemberId) {
            this.cmemberId = cmemberId;
        }

        public String getPassword() {
            return password;
        }

        public void setPassword(String password) {
            this.password = password;
        }

        public int getIntegral() {
            return integral;
        }

        public void setIntegral(int integral) {
            this.integral = integral;
        }

        public String getMobilePhone() {
            return mobilePhone;
        }

        public void setMobilePhone(String mobilePhone) {
            this.mobilePhone = mobilePhone;
        }

        public String getAddress() {
            return address;
        }

        public void setAddress(String address) {
            this.address = address;
        }

        public String getEmail() {
            return email;
        }

        public void setEmail(String email) {
            this.email = email;
        }

        public String getNickName() {
            return nickName;
        }

        public void setNickName(String nickName) {
            this.nickName = nickName;
        }

        public int getAge() {
            return age;
        }

        public void setAge(int age) {
            this.age = age;
        }

        public String getWeChat() {
            return weChat;
        }

        public void setWeChat(String weChat) {
            this.weChat = weChat;
        }

        public String getRealName() {
            return realName;
        }

        public void setRealName(String realName) {
            this.realName = realName;
        }

        public int getRealState() {
            return realState;
        }

        public void setRealState(int realState) {
            this.realState = realState;
        }

        public int getHaveUser() {
            return haveUser;
        }

        public void setHaveUser(int haveUser) {
            this.haveUser = haveUser;
        }

        public String getHeadPhotoUrl() {
            return headPhotoUrl;
        }

        public void setHeadPhotoUrl(String headPhotoUrl) {
            this.headPhotoUrl = headPhotoUrl;
        }

        public String getQq() {
            return qq;
        }

        public void setQq(String qq) {
            this.qq = qq;
        }

        public String getIdNumber() {
            return idNumber;
        }

        public void setIdNumber(String idNumber) {
            this.idNumber = idNumber;
        }
    }
}

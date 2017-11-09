package com.bjxiyang.shuzianfang.myapplication.model;

import java.util.List;

/**
 * Created by Administrator on 2017/10/21 0021.
 */

public class BaoXiuLiShi {

    /**
     * code : 1000
     * msg : 维修单查询成功
     * obj : [{"nperId":0,"addTime":"2017-10-21 12:10:52","repairStatus":0,"repairId":8,"repairNo":"1508559052851","userId":268,"floorId":0,"repairPic":[{"picUrl":"http://47.92.106.249:8088/img/1508559052813.jpg"}],"repairContent":"qqq","unitId":0,"contactWay":"aaa","communityId":1,"doorId":0},{"nperId":14,"addTime":"2017-09-28 17:11:07","repairStatus":0,"repairId":6,"repairNo":"1506589867031","userId":268,"floorId":21,"repairPic":[{"picUrl":"http://47.92.106.249:8088/img/http://47.92.106.249:8088//img///1506589863951.jpg"},{"picUrl":"http://47.92.106.249:8088/img/http://47.92.106.249:8088//img///1506589863989.jpg"},{"picUrl":"http://47.92.106.249:8088/img/http://47.92.106.249:8088//img///1506589864022.jpg"}],"repairContent":"111","unitId":45,"contactWay":"18813045215","communityId":4,"doorId":56},{"nperId":14,"addTime":"2017-09-28 17:08:23","repairStatus":0,"repairId":5,"repairNo":"1506589702395","userId":268,"floorId":21,"repairPic":[],"repairContent":"aaa","unitId":45,"contactWay":"18813045215","communityId":4,"doorId":56},{"nperId":14,"addTime":"2017-09-28 17:07:06","repairStatus":0,"repairId":4,"repairNo":"1506589625909","userId":268,"floorId":21,"repairPic":[],"repairContent":"aaa","unitId":45,"contactWay":"18813045215","communityId":4,"doorId":56}]
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
         * nperId : 0
         * addTime : 2017-10-21 12:10:52
         * repairStatus : 0
         * repairId : 8
         * repairNo : 1508559052851
         * userId : 268
         * floorId : 0
         * repairPic : [{"picUrl":"http://47.92.106.249:8088/img/1508559052813.jpg"}]
         * repairContent : qqq
         * unitId : 0
         * contactWay : aaa
         * communityId : 1
         * doorId : 0
         */

        private int nperId;
        private String addTime;
        private int repairStatus;
        private int repairId;
        private String repairNo;
        private int userId;
        private int floorId;
        private String repairContent;
        private int unitId;
        private String contactWay;
        private int communityId;
        private int doorId;
        private List<RepairPicBean> repairPic;

        public int getNperId() {
            return nperId;
        }

        public void setNperId(int nperId) {
            this.nperId = nperId;
        }

        public String getAddTime() {
            return addTime;
        }

        public void setAddTime(String addTime) {
            this.addTime = addTime;
        }

        public int getRepairStatus() {
            return repairStatus;
        }

        public void setRepairStatus(int repairStatus) {
            this.repairStatus = repairStatus;
        }

        public int getRepairId() {
            return repairId;
        }

        public void setRepairId(int repairId) {
            this.repairId = repairId;
        }

        public String getRepairNo() {
            return repairNo;
        }

        public void setRepairNo(String repairNo) {
            this.repairNo = repairNo;
        }

        public int getUserId() {
            return userId;
        }

        public void setUserId(int userId) {
            this.userId = userId;
        }

        public int getFloorId() {
            return floorId;
        }

        public void setFloorId(int floorId) {
            this.floorId = floorId;
        }

        public String getRepairContent() {
            return repairContent;
        }

        public void setRepairContent(String repairContent) {
            this.repairContent = repairContent;
        }

        public int getUnitId() {
            return unitId;
        }

        public void setUnitId(int unitId) {
            this.unitId = unitId;
        }

        public String getContactWay() {
            return contactWay;
        }

        public void setContactWay(String contactWay) {
            this.contactWay = contactWay;
        }

        public int getCommunityId() {
            return communityId;
        }

        public void setCommunityId(int communityId) {
            this.communityId = communityId;
        }

        public int getDoorId() {
            return doorId;
        }

        public void setDoorId(int doorId) {
            this.doorId = doorId;
        }

        public List<RepairPicBean> getRepairPic() {
            return repairPic;
        }

        public void setRepairPic(List<RepairPicBean> repairPic) {
            this.repairPic = repairPic;
        }

        public static class RepairPicBean {
            /**
             * picUrl : http://47.92.106.249:8088/img/1508559052813.jpg
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
}

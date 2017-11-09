package com.bjxiyang.shuzianfang.myapplication.model;

import java.util.List;

/**
 * Created by gll on 2017/10/25.
 */

public class FanKuiLiShi {

    /**
     * code : 1000
     * msg : 查询成功
     * obj : [{"picUrl":[{"picUrl":"1508923626618.jpg"}],"complaintId":6,"addTime":"2017-10-25 17:27:06","complaintContent":"测试","cmemberId":268,"communityId":1},{"picUrl":[],"complaintId":5,"addTime":"2017-10-25 16:49:55","complaintContent":"qqq","cmemberId":268,"communityId":4},{"picUrl":[],"complaintId":2,"addTime":"2017-10-25 16:03:35","cmemberId":268,"communityId":4},{"picUrl":[],"complaintId":1,"addTime":"2017-10-25 16:03:02","cmemberId":268,"communityId":4}]
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
         * picUrl : [{"picUrl":"1508923626618.jpg"}]
         * complaintId : 6
         * addTime : 2017-10-25 17:27:06
         * complaintContent : 测试
         * cmemberId : 268
         * communityId : 1
         */

        private int complaintId;
        private String addTime;
        private String complaintContent;
        private int cmemberId;
        private int communityId;
        private List<PicUrlBean> picUrl;

        public int getComplaintId() {
            return complaintId;
        }

        public void setComplaintId(int complaintId) {
            this.complaintId = complaintId;
        }

        public String getAddTime() {
            return addTime;
        }

        public void setAddTime(String addTime) {
            this.addTime = addTime;
        }

        public String getComplaintContent() {
            return complaintContent;
        }

        public void setComplaintContent(String complaintContent) {
            this.complaintContent = complaintContent;
        }

        public int getCmemberId() {
            return cmemberId;
        }

        public void setCmemberId(int cmemberId) {
            this.cmemberId = cmemberId;
        }

        public int getCommunityId() {
            return communityId;
        }

        public void setCommunityId(int communityId) {
            this.communityId = communityId;
        }

        public List<PicUrlBean> getPicUrl() {
            return picUrl;
        }

        public void setPicUrl(List<PicUrlBean> picUrl) {
            this.picUrl = picUrl;
        }

        public static class PicUrlBean {
            /**
             * picUrl : 1508923626618.jpg
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

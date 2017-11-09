package com.bjxiyang.shuzianfang.myapplication.model;

import java.util.List;

/**
 * Created by Administrator on 2017/9/12 0012.
 */

public class Init {

    /**
     * code : 1000
     * msg : 获取首页信息成功
     * obj : {"bannerObj":[{"adInfo":[{"id":26,"urladdress":"http://www.baidu.聪明","advertype":"1","imageurl":"http://47.92.104.209:8088/img/622d627a71b34013ab6449d2e39da64e.jpg"},{"id":27,"urladdress":"http://www.baidu.聪明","advertype":"1","imageurl":"http://47.92.104.209:8088/img/3f95225d7b2d48ccb2ef43d1165dddf8.jpg"}],"adveraddressId":1},{"adInfo":[{"id":25,"urladdress":"http://bjxiyang.com/tiaozhuan","advertype":"1","imageurl":"http://47.92.104.209:8088/img/bd929ef2ea054978a8df1efba97bdd3d.jpg"}],"adveraddressId":2},{"adInfo":[],"adveraddressId":3},{"adInfo":[],"adveraddressId":4},{"adInfo":[],"adveraddressId":5},{"adInfo":[],"adveraddressId":6}],"sysMsg":0,"protectTime":"已保护您<63>天","backimg":"","doorObj":[{"nperId":1,"nperName":"一期","doorId":2,"locklist":[{"lockId":49,"lockName":"1"}],"floorName":"一栋","floorId":1,"unitName":"一单元","doorName":"102","roleType":2,"communityName":"华彩国际","unitId":1,"communityId":1},{"nperId":5,"nperName":"二期","doorId":41,"locklist":[],"floorName":"三栋","floorId":11,"unitName":"四单元","doorName":"108","roleType":0,"communityName":"国风美唐","unitId":31,"communityId":2},{"nperId":5,"nperName":"二期","doorId":42,"locklist":[],"floorName":"三栋","floorId":11,"unitName":"四单元","doorName":"109","roleType":0,"communityName":"国风美唐","unitId":31,"communityId":2},{"nperId":14,"nperName":"一期","doorId":56,"locklist":[{"lockId":48,"lockName":"606"},{"lockId":51,"lockName":"607"},{"lockId":52,"lockName":"0200"},{"lockId":53,"lockName":"0201"},{"lockId":115,"lockName":"1"}],"floorName":"一栋","floorId":21,"unitName":"一单元","doorName":"606","roleType":1,"communityName":"鹏景阁大厦","unitId":45,"communityId":4},{"nperId":22,"nperName":"一期","doorId":256,"locklist":[{"lockId":63,"lockName":"A1"},{"lockId":65,"lockName":"B1"},{"lockId":107,"lockName":"A2"},{"lockId":109,"lockName":"B2"}],"floorId":34,"roleType":2,"communityName":"宏源公寓","unitId":76,"communityId":6},{"nperId":25,"nperName":"一期","doorId":936,"locklist":[{"lockId":121,"lockName":"六单元"}],"floorName":"1栋","floorId":50,"unitName":"六单元","doorName":"600","roleType":1,"communityName":"精英家园","unitId":102,"communityId":8}],"newMsg":0}
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
         * bannerObj : [{"adInfo":[{"id":26,"urladdress":"http://www.baidu.聪明","advertype":"1","imageurl":"http://47.92.104.209:8088/img/622d627a71b34013ab6449d2e39da64e.jpg"},{"id":27,"urladdress":"http://www.baidu.聪明","advertype":"1","imageurl":"http://47.92.104.209:8088/img/3f95225d7b2d48ccb2ef43d1165dddf8.jpg"}],"adveraddressId":1},{"adInfo":[{"id":25,"urladdress":"http://bjxiyang.com/tiaozhuan","advertype":"1","imageurl":"http://47.92.104.209:8088/img/bd929ef2ea054978a8df1efba97bdd3d.jpg"}],"adveraddressId":2},{"adInfo":[],"adveraddressId":3},{"adInfo":[],"adveraddressId":4},{"adInfo":[],"adveraddressId":5},{"adInfo":[],"adveraddressId":6}]
         * sysMsg : 0
         * protectTime : 已保护您<63>天
         * backimg :
         * doorObj : [{"nperId":1,"nperName":"一期","doorId":2,"locklist":[{"lockId":49,"lockName":"1"}],"floorName":"一栋","floorId":1,"unitName":"一单元","doorName":"102","roleType":2,"communityName":"华彩国际","unitId":1,"communityId":1},{"nperId":5,"nperName":"二期","doorId":41,"locklist":[],"floorName":"三栋","floorId":11,"unitName":"四单元","doorName":"108","roleType":0,"communityName":"国风美唐","unitId":31,"communityId":2},{"nperId":5,"nperName":"二期","doorId":42,"locklist":[],"floorName":"三栋","floorId":11,"unitName":"四单元","doorName":"109","roleType":0,"communityName":"国风美唐","unitId":31,"communityId":2},{"nperId":14,"nperName":"一期","doorId":56,"locklist":[{"lockId":48,"lockName":"606"},{"lockId":51,"lockName":"607"},{"lockId":52,"lockName":"0200"},{"lockId":53,"lockName":"0201"},{"lockId":115,"lockName":"1"}],"floorName":"一栋","floorId":21,"unitName":"一单元","doorName":"606","roleType":1,"communityName":"鹏景阁大厦","unitId":45,"communityId":4},{"nperId":22,"nperName":"一期","doorId":256,"locklist":[{"lockId":63,"lockName":"A1"},{"lockId":65,"lockName":"B1"},{"lockId":107,"lockName":"A2"},{"lockId":109,"lockName":"B2"}],"floorId":34,"roleType":2,"communityName":"宏源公寓","unitId":76,"communityId":6},{"nperId":25,"nperName":"一期","doorId":936,"locklist":[{"lockId":121,"lockName":"六单元"}],"floorName":"1栋","floorId":50,"unitName":"六单元","doorName":"600","roleType":1,"communityName":"精英家园","unitId":102,"communityId":8}]
         * newMsg : 0
         */

        private int sysMsg;
        private String protectTime;
        private String backimg;
        private int newMsg;
        private List<BannerObjBean> bannerObj;
        private List<DoorObjBean> doorObj;

        public int getSysMsg() {
            return sysMsg;
        }

        public void setSysMsg(int sysMsg) {
            this.sysMsg = sysMsg;
        }

        public String getProtectTime() {
            return protectTime;
        }

        public void setProtectTime(String protectTime) {
            this.protectTime = protectTime;
        }

        public String getBackimg() {
            return backimg;
        }

        public void setBackimg(String backimg) {
            this.backimg = backimg;
        }

        public int getNewMsg() {
            return newMsg;
        }

        public void setNewMsg(int newMsg) {
            this.newMsg = newMsg;
        }

        public List<BannerObjBean> getBannerObj() {
            return bannerObj;
        }

        public void setBannerObj(List<BannerObjBean> bannerObj) {
            this.bannerObj = bannerObj;
        }

        public List<DoorObjBean> getDoorObj() {
            return doorObj;
        }

        public void setDoorObj(List<DoorObjBean> doorObj) {
            this.doorObj = doorObj;
        }

        public static class BannerObjBean {
            /**
             * adInfo : [{"id":26,"urladdress":"http://www.baidu.聪明","advertype":"1","imageurl":"http://47.92.104.209:8088/img/622d627a71b34013ab6449d2e39da64e.jpg"},{"id":27,"urladdress":"http://www.baidu.聪明","advertype":"1","imageurl":"http://47.92.104.209:8088/img/3f95225d7b2d48ccb2ef43d1165dddf8.jpg"}]
             * adveraddressId : 1
             */

            private int adveraddressId;
            private List<AdInfoBean> adInfo;

            public int getAdveraddressId() {
                return adveraddressId;
            }

            public void setAdveraddressId(int adveraddressId) {
                this.adveraddressId = adveraddressId;
            }

            public List<AdInfoBean> getAdInfo() {
                return adInfo;
            }

            public void setAdInfo(List<AdInfoBean> adInfo) {
                this.adInfo = adInfo;
            }

            public static class AdInfoBean {
                /**
                 * id : 26
                 * urladdress : http://www.baidu.聪明
                 * advertype : 1
                 * imageurl : http://47.92.104.209:8088/img/622d627a71b34013ab6449d2e39da64e.jpg
                 */

                private int id;
                private String urladdress;
                private String advertype;
                private String imageurl;

                public int getId() {
                    return id;
                }

                public void setId(int id) {
                    this.id = id;
                }

                public String getUrladdress() {
                    return urladdress;
                }

                public void setUrladdress(String urladdress) {
                    this.urladdress = urladdress;
                }

                public String getAdvertype() {
                    return advertype;
                }

                public void setAdvertype(String advertype) {
                    this.advertype = advertype;
                }

                public String getImageurl() {
                    return imageurl;
                }

                public void setImageurl(String imageurl) {
                    this.imageurl = imageurl;
                }
            }
        }

        public static class DoorObjBean {
            /**
             * nperId : 1
             * nperName : 一期
             * doorId : 2
             * locklist : [{"lockId":49,"lockName":"1"}]
             * floorName : 一栋
             * floorId : 1
             * unitName : 一单元
             * doorName : 102
             * roleType : 2
             * communityName : 华彩国际
             * unitId : 1
             * communityId : 1
             */

            private int nperId;
            private String nperName;
            private int doorId;
            private String floorName;
            private int floorId;
            private String unitName;
            private String doorName;
            private int roleType;
            private String communityName;
            private int unitId;
            private int communityId;
            private List<LocklistBean> locklist;

            public int getNperId() {
                return nperId;
            }

            public void setNperId(int nperId) {
                this.nperId = nperId;
            }

            public String getNperName() {
                return nperName;
            }

            public void setNperName(String nperName) {
                this.nperName = nperName;
            }

            public int getDoorId() {
                return doorId;
            }

            public void setDoorId(int doorId) {
                this.doorId = doorId;
            }

            public String getFloorName() {
                return floorName;
            }

            public void setFloorName(String floorName) {
                this.floorName = floorName;
            }

            public int getFloorId() {
                return floorId;
            }

            public void setFloorId(int floorId) {
                this.floorId = floorId;
            }

            public String getUnitName() {
                return unitName;
            }

            public void setUnitName(String unitName) {
                this.unitName = unitName;
            }

            public String getDoorName() {
                return doorName;
            }

            public void setDoorName(String doorName) {
                this.doorName = doorName;
            }

            public int getRoleType() {
                return roleType;
            }

            public void setRoleType(int roleType) {
                this.roleType = roleType;
            }

            public String getCommunityName() {
                return communityName;
            }

            public void setCommunityName(String communityName) {
                this.communityName = communityName;
            }

            public int getUnitId() {
                return unitId;
            }

            public void setUnitId(int unitId) {
                this.unitId = unitId;
            }

            public int getCommunityId() {
                return communityId;
            }

            public void setCommunityId(int communityId) {
                this.communityId = communityId;
            }

            public List<LocklistBean> getLocklist() {
                return locklist;
            }

            public void setLocklist(List<LocklistBean> locklist) {
                this.locklist = locklist;
            }

            public static class LocklistBean {
                /**
                 * lockId : 49
                 * lockName : 1
                 */

                private int lockId;
                private String lockName;

                public int getLockId() {
                    return lockId;
                }

                public void setLockId(int lockId) {
                    this.lockId = lockId;
                }

                public String getLockName() {
                    return lockName;
                }

                public void setLockName(String lockName) {
                    this.lockName = lockName;
                }
            }
        }
    }
}

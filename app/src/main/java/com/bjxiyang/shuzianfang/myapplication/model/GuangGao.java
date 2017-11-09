package com.bjxiyang.shuzianfang.myapplication.model;

import java.util.List;

/**
 * Created by gll on 2017/10/25.
 */

public class GuangGao {


    /**
     * code : 1000
     * msg : 获取广告信息成功
     * obj : {"bannerObj":[{"adveraddressId":1,"adInfo":[{"advertype":"1","urladdress":"http://www.baidu.com","imageurl":"http://mpic.tiankong.com/77c/e0e/77ce0efed315db29ee8d3be20d93c727/640.jpg","id":1},{"advertype":"1","urladdress":"http://www.baidu.com","imageurl":"http://mpic.tiankong.com/b2c/274/b2c274ddee7d037ff1420b8da6d42be8/640.jpg","id":2}]},{"adveraddressId":2,"adInfo":[{"advertype":"1","urladdress":"http://www.baidu.com","imageurl":"http://mpic.tiankong.com/39f/508/39f508720b586a795455c7c908458451/640.jpg","id":4}]},{"adveraddressId":3,"adInfo":[{"advertype":"1","urladdress":"http://www.baidu.com","imageurl":"http://mpic.tiankong.com/bc6/1c3/bc61c36b606e2b7c08a731eaa424f380/48fza0006rf.jpg","id":5}]},{"adveraddressId":4,"adInfo":[{"advertype":"1","urladdress":"http://www.baidu.com","imageurl":"http://mpic.tiankong.com/8d2/251/8d2251b4fc7bb57a4b8b4387c78fbfd3/640.jpg","id":3}]}]}
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
        private List<BannerObjBean> bannerObj;

        public List<BannerObjBean> getBannerObj() {
            return bannerObj;
        }

        public void setBannerObj(List<BannerObjBean> bannerObj) {
            this.bannerObj = bannerObj;
        }

        public static class BannerObjBean {
            /**
             * adveraddressId : 1
             * adInfo : [{"advertype":"1","urladdress":"http://www.baidu.com","imageurl":"http://mpic.tiankong.com/77c/e0e/77ce0efed315db29ee8d3be20d93c727/640.jpg","id":1},{"advertype":"1","urladdress":"http://www.baidu.com","imageurl":"http://mpic.tiankong.com/b2c/274/b2c274ddee7d037ff1420b8da6d42be8/640.jpg","id":2}]
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
                 * advertype : 1
                 * urladdress : http://www.baidu.com
                 * imageurl : http://mpic.tiankong.com/77c/e0e/77ce0efed315db29ee8d3be20d93c727/640.jpg
                 * id : 1
                 */

                private String advertype;
                private String urladdress;
                private String imageurl;
                private int id;

                public String getAdvertype() {
                    return advertype;
                }

                public void setAdvertype(String advertype) {
                    this.advertype = advertype;
                }

                public String getUrladdress() {
                    return urladdress;
                }

                public void setUrladdress(String urladdress) {
                    this.urladdress = urladdress;
                }

                public String getImageurl() {
                    return imageurl;
                }

                public void setImageurl(String imageurl) {
                    this.imageurl = imageurl;
                }

                public int getId() {
                    return id;
                }

                public void setId(int id) {
                    this.id = id;
                }
            }
        }
    }
}

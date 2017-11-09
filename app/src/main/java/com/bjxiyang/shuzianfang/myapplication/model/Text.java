package com.bjxiyang.shuzianfang.myapplication.model;

import java.util.List;

/**
 * Created by Administrator on 2017/9/15 0015.
 */

public class Text {

    /**
     * code : 1000
     * msg : 开门成功
     * Obj : {"type":0}
     * bannerObj : [{"advertype":"1","urladdress":"http://www.baidu.com","imageurl":"http://mpic.tiankong.com/545/854/545854a7a428c8f4974ede9f4dc3b769/640.jpg","id":6},{"advertype":"1","urladdress":"http://www.baidu.com","imageurl":"http://mpic.tiankong.com/5eb/357/5eb357b7bbfdab9cc6f3300cab7035b9/640.jpg","id":7}]
     */

    private int code;
    private String msg;
    private ObjBean Obj;
    private List<BannerObjBean> bannerObj;

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
        return Obj;
    }

    public void setObj(ObjBean Obj) {
        this.Obj = Obj;
    }

    public List<BannerObjBean> getBannerObj() {
        return bannerObj;
    }

    public void setBannerObj(List<BannerObjBean> bannerObj) {
        this.bannerObj = bannerObj;
    }

    public static class ObjBean {
        /**
         * type : 0
         */

        private int type;

        public int getType() {
            return type;
        }

        public void setType(int type) {
            this.type = type;
        }
    }

    public static class BannerObjBean {
        /**
         * advertype : 1
         * urladdress : http://www.baidu.com
         * imageurl : http://mpic.tiankong.com/545/854/545854a7a428c8f4974ede9f4dc3b769/640.jpg
         * id : 6
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

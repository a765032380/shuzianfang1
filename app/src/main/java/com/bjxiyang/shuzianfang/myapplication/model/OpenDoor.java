package com.bjxiyang.shuzianfang.myapplication.model;

import java.util.List;

/**
 * Created by Administrator on 2017/7/14 0014.
 */

public class OpenDoor {

    /**
     * code : 1000
     * msg : 开门成功
     * Obj : {"bondUrl":"http://www.bjxiyang.com","validityDate":"2017-07-08---2017-07-30","bondName":"开门有喜","bondLimit":"7折优惠"}
     */

    private String code;
    private String msg;

    private String errorMsg;
    private int errorType;

    public String getErrorMsg() {
        return errorMsg;
    }

    public int getErrorType() {
        return errorType;
    }

    public void setErrorMsg(String errorMsg) {
        this.errorMsg = errorMsg;
    }

    public void setErrorType(int errorType) {
        this.errorType = errorType;
    }

    private ObjBean Obj;
    private List<BannerObjBean> bannerObj;
    public String getCode() {
        return code;
    }

    public void setCode(String code) {
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
         * bondUrl : http://www.bjxiyang.com
         * validityDate : 2017-07-08---2017-07-30
         * bondName : 开门有喜
         * bondLimit : 7折优惠
         */

        private String bondUrl;
        private String validityDate;
        private String bondName;
        private String bondLimit;
        private int type;

        public int getType() {
            return type;
        }

        public void setType(int type) {
            this.type = type;
        }

        public String getBondUrl() {
            return bondUrl;
        }

        public void setBondUrl(String bondUrl) {
            this.bondUrl = bondUrl;
        }

        public String getValidityDate() {
            return validityDate;
        }

        public void setValidityDate(String validityDate) {
            this.validityDate = validityDate;
        }

        public String getBondName() {
            return bondName;
        }

        public void setBondName(String bondName) {
            this.bondName = bondName;
        }

        public String getBondLimit() {
            return bondLimit;
        }

        public void setBondLimit(String bondLimit) {
            this.bondLimit = bondLimit;
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

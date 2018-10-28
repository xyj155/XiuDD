package com.example.xiudd.sese.gson;

/**
 * Created by Xuyijie on 2018/10/21.
 */

public class AVUserGson {

    /**
     * emailVerified : false
     * createdAt : 2018-10-21T10:36:18.983Z
     * authData : {"qq":{"access_token":"E2C8368F5474EF4071DFF5BD78D88328","avatar":"http://thirdqq.qlogo.cn/qqapp/100371282/23FF7BB562064A8117580037F55E60A9/100","expires_in":7776000,"openid":"23FF7BB562064A8117580037F55E60A9","username":"Privous"}}
     * sessionToken : xp1nvxpk802wvv72i7n1ohjvc
     * className : _User
     * mobilePhoneVerified : false
     * objectId : 5bcc56a2fb4ffecf3b26b660
     * username : nzaff15kru0j68ba8l4aip8ws
     * updatedAt : 2018-10-21T10:52:21.124Z
     */

    private boolean emailVerified;
    private String createdAt;
    private AuthDataBean authData;
    private String score;
    private String coin;

    public String getCoin() {
        return coin;
    }

    public void setCoin(String coin) {
        this.coin = coin;
    }

    public String getScore() {
        return score;
    }

    public void setScore(String score) {
        this.score = score;
    }

    private String sessionToken;
    private String className;
    private boolean mobilePhoneVerified;
    private String objectId;
    private String username;
    private String updatedAt;
    private String sign;
    private String vip;

    public String getVip() {
        return vip;
    }

    public void setVip(String vip) {
        this.vip = vip;
    }

    public String getSign() {
        return sign;
    }

    public void setSign(String sign) {
        this.sign = sign;
    }

    public boolean isEmailVerified() {
        return emailVerified;
    }

    public void setEmailVerified(boolean emailVerified) {
        this.emailVerified = emailVerified;
    }

    public String getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(String createdAt) {
        this.createdAt = createdAt;
    }

    public AuthDataBean getAuthData() {
        return authData;
    }

    public void setAuthData(AuthDataBean authData) {
        this.authData = authData;
    }

    public String getSessionToken() {
        return sessionToken;
    }

    public void setSessionToken(String sessionToken) {
        this.sessionToken = sessionToken;
    }

    public String getClassName() {
        return className;
    }

    public void setClassName(String className) {
        this.className = className;
    }

    public boolean isMobilePhoneVerified() {
        return mobilePhoneVerified;
    }

    public void setMobilePhoneVerified(boolean mobilePhoneVerified) {
        this.mobilePhoneVerified = mobilePhoneVerified;
    }

    public String getObjectId() {
        return objectId;
    }

    public void setObjectId(String objectId) {
        this.objectId = objectId;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getUpdatedAt() {
        return updatedAt;
    }

    public void setUpdatedAt(String updatedAt) {
        this.updatedAt = updatedAt;
    }

    public static class AuthDataBean {
        /**
         * qq : {"access_token":"E2C8368F5474EF4071DFF5BD78D88328","avatar":"http://thirdqq.qlogo.cn/qqapp/100371282/23FF7BB562064A8117580037F55E60A9/100","expires_in":7776000,"openid":"23FF7BB562064A8117580037F55E60A9","username":"Privous"}
         */

        private QqBean qq;

        public QqBean getQq() {
            return qq;
        }

        public void setQq(QqBean qq) {
            this.qq = qq;
        }

        public static class QqBean {
            /**
             * access_token : E2C8368F5474EF4071DFF5BD78D88328
             * avatar : http://thirdqq.qlogo.cn/qqapp/100371282/23FF7BB562064A8117580037F55E60A9/100
             * expires_in : 7776000
             * openid : 23FF7BB562064A8117580037F55E60A9
             * username : Privous
             */

            private String access_token;
            private String avatar;
            private int expires_in;
            private String openid;
            private String username;

            public String getAccess_token() {
                return access_token;
            }

            public void setAccess_token(String access_token) {
                this.access_token = access_token;
            }

            public String getAvatar() {
                return avatar;
            }

            public void setAvatar(String avatar) {
                this.avatar = avatar;
            }

            public int getExpires_in() {
                return expires_in;
            }

            public void setExpires_in(int expires_in) {
                this.expires_in = expires_in;
            }

            public String getOpenid() {
                return openid;
            }

            public void setOpenid(String openid) {
                this.openid = openid;
            }

            public String getUsername() {
                return username;
            }

            public void setUsername(String username) {
                this.username = username;
            }
        }
    }
}

package com.example.xiudd.sese.gson;

import java.util.List;

/**
 * Created by Xuyijie on 2018/10/16.
 */

public class PostListGson {

    /**
     * title : less一个
     * content : 老公不在家，好难受
     * kind : 求约
     * time : 2018-10-19 02:48:11
     * comment : 0
     * thumb : 1
     * pic : []
     * user : {"username":"噜噜","avatar":"http://img3.imgtn.bdimg.com/it/u=3329523335,731969596&fm=26&gp=0.jpg","location":"浙江嘉兴","vip":"2","sex":"1"}
     */

    private String title;
    private String content;
    private String kind;
    private String time;
    private int comment;
    private int thumb;
    private boolean iscollection;

    public boolean getIscollection() {
        return iscollection;
    }

    public void setIscollection(boolean iscollection) {
        this.iscollection = iscollection;
    }

    private UserBean user;
    private List<String> pic;
    private String id;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    @Override
    public String toString() {
        return "PostListGson{" +
                "title='" + title + '\'' +
                ", content='" + content + '\'' +
                ", kind='" + kind + '\'' +
                ", time='" + time + '\'' +
                ", comment=" + comment +
                ", thumb=" + thumb +
                ", user=" + user +
                ", pic=" + pic +
                '}';
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getKind() {
        return kind;
    }

    public void setKind(String kind) {
        this.kind = kind;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public int getComment() {
        return comment;
    }

    public void setComment(int comment) {
        this.comment = comment;
    }

    public int getThumb() {
        return thumb;
    }

    public void setThumb(int thumb) {
        this.thumb = thumb;
    }

    public UserBean getUser() {
        return user;
    }

    public void setUser(UserBean user) {
        this.user = user;
    }

    public List<String> getPic() {
        return pic;
    }

    public void setPic(List<String> pic) {
        this.pic = pic;
    }

    public static class UserBean {
        /**
         * username : 噜噜
         * avatar : http://img3.imgtn.bdimg.com/it/u=3329523335,731969596&fm=26&gp=0.jpg
         * location : 浙江嘉兴
         * vip : 2
         * sex : 1
         */
private String sign;

        public String getSign() {
            return sign;
        }

        public void setSign(String sign) {
            this.sign = sign;
        }

        private String username;
        private String avatar;
        private String location;
        private String vip;
        private String sex;

        public String getUsername() {
            return username;
        }

        public void setUsername(String username) {
            this.username = username;
        }

        public String getAvatar() {
            return avatar;
        }

        public void setAvatar(String avatar) {
            this.avatar = avatar;
        }

        public String getLocation() {
            return location;
        }

        public void setLocation(String location) {
            this.location = location;
        }

        public String getVip() {
            return vip;
        }

        public void setVip(String vip) {
            this.vip = vip;
        }

        public String getSex() {
            return sex;
        }

        public void setSex(String sex) {
            this.sex = sex;
        }
    }

}



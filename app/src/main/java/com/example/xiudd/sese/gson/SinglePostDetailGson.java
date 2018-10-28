package com.example.xiudd.sese.gson;

import java.util.List;

/**
 * Created by 徐易杰 on 2018/10/26.
 */

public class SinglePostDetailGson {


    /**
     * title : 嘉兴约
     * content : 本人处男，求约
     * kind : 求约
     * time : 2018-10-19 02:55:17
     * comment : 2
     * thumb : 3
     * pic : ["https://pic4.iqiyipic.com/image/20181022/4d/f7/a_100202469_m_601_m5_260_360.jpg","https://pic4.iqiyipic.com/image/20181022/4d/f7/a_100202469_m_601_m5_260_360.jpg","https://pic4.iqiyipic.com/image/20181022/4d/f7/a_100202469_m_601_m5_260_360.jpg","https://pic4.iqiyipic.com/image/20181022/4d/f7/a_100202469_m_601_m5_260_360.jpg"]
     * commentData : [{"username":"123","comment":"我可以约啊","avatar":"http://img4.imgtn.bdimg.com/it/u=3797481993,1929347741&fm=26&gp=0.jpg"},{"username":"123","comment":"约","avatar":"http://img4.imgtn.bdimg.com/it/u=3797481993,1929347741&fm=26&gp=0.jpg"}]
     * user : {"username":"123","avatar":"http://img4.imgtn.bdimg.com/it/u=3797481993,1929347741&fm=26&gp=0.jpg","location":"嘉兴市","vip":"0","sign":"是","sex":"1","id":1}
     */

    private String title;
    private String content;
    private String kind;
    private boolean like;

    public boolean isLike() {
        return like;
    }

    public void setLike(boolean like) {
        this.like = like;
    }

    private String time;
    private int comment;
    private int thumb;
    private UserBean user;
    private List<String> pic;
    private List<CommentDataBean> commentData;

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

    public List<CommentDataBean> getCommentData() {
        return commentData;
    }

    public void setCommentData(List<CommentDataBean> commentData) {
        this.commentData = commentData;
    }

    public static class UserBean {
        /**
         * username : 123
         * avatar : http://img4.imgtn.bdimg.com/it/u=3797481993,1929347741&fm=26&gp=0.jpg
         * location : 嘉兴市
         * vip : 0
         * sign : 是
         * sex : 1
         * id : 1
         */

        private String username;
        private String avatar;
        private String location;
        private String vip;
        private String sign;
        private String sex;
        private int id;

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

        public String getSign() {
            return sign;
        }

        public void setSign(String sign) {
            this.sign = sign;
        }

        public String getSex() {
            return sex;
        }

        public void setSex(String sex) {
            this.sex = sex;
        }

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }
    }

    public static class CommentDataBean {
        /**
         * username : 123
         * comment : 我可以约啊
         * avatar : http://img4.imgtn.bdimg.com/it/u=3797481993,1929347741&fm=26&gp=0.jpg
         */

        private String username;
        private String comment;
        private String avatar;

        public String getUsername() {
            return username;
        }

        public void setUsername(String username) {
            this.username = username;
        }

        public String getComment() {
            return comment;
        }

        public void setComment(String comment) {
            this.comment = comment;
        }

        public String getAvatar() {
            return avatar;
        }

        public void setAvatar(String avatar) {
            this.avatar = avatar;
        }
    }
}

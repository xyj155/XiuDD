package com.example.xiudd.sese.gson;

/**
 * Created by Xuyijie on 2018/10/20.
 */

public class UserGson {

    @Override
    public String toString() {
        return "UserGson{" +
                "token='" + token + '\'' +
                ", id=" + id +
                ", username='" + username + '\'' +
                ", password='" + password + '\'' +
                ", email='" + email + '\'' +
                ", location='" + location + '\'' +
                ", updatetime='" + updatetime + '\'' +
                ", avatar='" + avatar + '\'' +
                ", vip='" + vip + '\'' +
                ", sex='" + sex + '\'' +
                ", nickname='" + nickname + '\'' +
                ", coin='" + coin + '\'' +
                ", score='" + score + '\'' +
                ", sign='" + sign + '\'' +
                ", observe=" + observe +
                ", fans=" + fans +
                ", day=" + day +
                '}';
    }

    /**
     * id : 1
     * username : 123
     * password : 123
     * email : 485451@login_qq.com
     * location : 浙江嘉兴
     * updatetime : 2018-10-20 16:32:08
     * avatar : http://img4.imgtn.bdimg.com/it/u=3797481993,1929347741&fm=26&gp=0.jpg
     * vip : 0
     * sex : 1
     * nickname : 徐易杰
     * coin : 50
     * score : 2
     * sign : null
     * observe : 4
     * fans : 6
     */

    private String token;

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    private String id;
    private String username;
    private String password;
    private String email;
    private String location;
    private String updatetime;
    private String avatar;
    private String vip;
    private String sex;
    private String nickname;
    private String coin;
    private String score;
    private String sign;
    private String observe;
    private String fans;

    public String getDay() {
        return day;
    }

    public void setDay(String day) {
        this.day = day;
    }

    private String day;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String getUpdatetime() {
        return updatetime;
    }

    public void setUpdatetime(String updatetime) {
        this.updatetime = updatetime;
    }

    public String getAvatar() {
        return avatar;
    }

    public void setAvatar(String avatar) {
        this.avatar = avatar;
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

    public String getNickname() {
        return nickname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

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

    public String getSign() {
        return sign;
    }

    public void setSign(String sign) {
        this.sign = sign;
    }

    public String getObserve() {
        return observe;
    }

    public void setObserve(String observe) {
        this.observe = observe;
    }

    public String getFans() {
        return fans;
    }

    public void setFans(String fans) {
        this.fans = fans;

    }
}


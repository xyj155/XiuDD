package com.example.xiudd.sese.gson;

/**
 * Created by Xuyijie on 2018/10/22.
 */

public class SignGson {


    /**
     * score : 5
     * day : 10
     * time : 2018-10-22 19:17:48
     * coin : 20
     * signsucccess : true
     */

    private String score;
    private int day;
    private String time;
    private int coin;
    private boolean signsucccess;

    public String getScore() {
        return score;
    }

    public void setScore(String score) {
        this.score = score;
    }

    public int getDay() {
        return day;
    }

    public void setDay(int day) {
        this.day = day;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public int getCoin() {
        return coin;
    }

    public void setCoin(int coin) {
        this.coin = coin;
    }

    public boolean isSignsucccess() {
        return signsucccess;
    }

    public void setSignsucccess(boolean signsucccess) {
        this.signsucccess = signsucccess;
    }
}

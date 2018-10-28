package com.example.xiudd.sese.base;

import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Created by Xuyijie on 2018/10/16.
 */

public class BaseGson<T> {
    private int code;
    private boolean success;
    private List<T> dataList;
    private String error;
    private T pageData;


    @Override
    public String toString() {
        return "BaseGson{" +
                "code=" + code +
                ", success=" + success +
                ", dataList=" + dataList +
                ", error='" + error + '\'' +
                ", pageData=" + pageData +
                '}';
    }

    private static final String TAG = "BaseGson";
    public T getPageData() {
        return pageData;
    }

    public void setPageData(T pageData) {
        this.pageData = pageData;
    }

    public String getError() {
        return error;
    }

    public void setError(String error) {
        this.error = error;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public boolean isSuccess() {
        return success;
    }

    public void setSuccess(boolean success) {
        this.success = success;
    }

    public List<T> getDataList() {
        return dataList;
    }

    public void setDataList(List<T> dataList) {
        this.dataList = dataList;
    }

}

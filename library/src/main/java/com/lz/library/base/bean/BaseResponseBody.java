package com.lz.library.base.bean;

public class BaseResponseBody<T> {
    private int repCode;
    private String repMsg;
    private T data;


    public int getRepCode() {
        return repCode;
    }

    public void setRepCode(int repCode) {
        this.repCode = repCode;
    }

    public String getRepMsg() {
        return repMsg;
    }

    public void setRepMsg(String repMsg) {
        this.repMsg = repMsg;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }

}

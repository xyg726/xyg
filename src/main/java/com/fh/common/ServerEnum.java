package com.fh.common;

public enum ServerEnum {

    SUCCESS(200,"操作成功"),
    ERROR(1000,"操作失败")
    ;

    private  int code;
    private  String msg;


    ServerEnum(int code, String msg) {
        this.code = code;
        this.msg = msg;
    }
    ServerEnum() {

    }
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
}

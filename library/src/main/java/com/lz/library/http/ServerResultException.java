package com.lz.library.http;


public class ServerResultException extends BaseException {

    public ServerResultException(int code,String msg) {
        super(code, "服务器错误");
    }
}

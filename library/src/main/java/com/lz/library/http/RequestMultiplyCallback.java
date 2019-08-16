package com.lz.library.http;

public interface RequestMultiplyCallback<T> extends RequestCallback<T>{
    void onFail(BaseException e);
}

package com.lz.library.http;

public interface RequestCallback<T> {
    void onSuccess(T data);
}

package com.JummumCo.Jummum.Interface;

/**
 * Created by likit on 23/5/2560.
 */

public interface IHttpCallback<T> {
    void onSuccess(T response);
    void onError(String message);

}

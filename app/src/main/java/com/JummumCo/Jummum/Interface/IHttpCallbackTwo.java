package com.JummumCo.Jummum.Interface;

/**
 * Created by likit on 23/5/2560.
 */

public interface IHttpCallbackTwo<T,T2> {
    void onSuccess(T response, T2 response2);
    void onError(String message);

}

package com.JummumCo.Jummum.Interface;

import java.util.List;

/**
 * Created by likit on 23/5/2560.
 */

public interface IHttpLoginCallback<T> {
    void onSuccess(List<List<T>> dataJson);
    void onError(String message);


}

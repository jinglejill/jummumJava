package com.JummumCo.Jummum.Interface;

/**
 * Created by Phitakphong on 23/5/2560.
 */

public interface HTTPCallback {
    void OnSuccess(Object response);
    void OnError(String message);
}

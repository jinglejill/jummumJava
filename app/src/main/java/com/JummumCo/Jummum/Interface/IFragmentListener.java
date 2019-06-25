package com.JummumCo.Jummum.Interface;

/**
 * Created by Phitakphong on 5/6/2560.
 */

public interface IFragmentListener {
    void onLoginRequire();

    void onLogout();

    void onStartEditSellItem(int postID);

    void openSelectImage(int requestCode);

}
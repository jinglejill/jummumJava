package com.JummumCo.Jummum.Model;

import android.provider.BaseColumns;

public class ImageMenuBaseData {

    private String menuId;
    private String imageBase64;

    public String getMenuId() {
        return menuId;
    }

    public void setMenuId(String menuId) {
        this.menuId = menuId;
    }

    public String getImageBase64() {
        return imageBase64;
    }

    public void setImageBase64(String imageBase64) {
        this.imageBase64 = imageBase64;
    }
}

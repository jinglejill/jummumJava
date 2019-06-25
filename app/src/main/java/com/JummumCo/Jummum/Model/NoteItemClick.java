package com.JummumCo.Jummum.Model;

import org.parceler.Parcel;

@Parcel
public class NoteItemClick {
    private int rootPosition ;
    private int posirion;
    private MenuListResultData menu;

    public int getRootPosition() {
        return rootPosition;
    }

    public void setRootPosition(int rootPosition) {
        this.rootPosition = rootPosition;
    }

    public int getPosition() {
        return posirion;
    }

    public void setPosition(int posirion) {
        this.posirion = posirion;
    }

    public MenuListResultData getMenu() {
        return menu;
    }

    public void setMenu(MenuListResultData menu) {
        this.menu = menu;
    }
}

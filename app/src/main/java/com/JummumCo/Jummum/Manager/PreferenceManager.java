package com.JummumCo.Jummum.Manager;

import android.content.Context;
import android.content.SharedPreferences;

import com.JummumCo.Jummum.Model.CreditCardResultData;
import com.JummumCo.Jummum.Util.Contextor;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.Dictionary;
import java.util.Hashtable;
import java.util.List;

/**
 * Created by nuuneoi on 11/16/2014.
 */
public class PreferenceManager {

    private static PreferenceManager instance;

    private SharedPreferences prefs;
    private SharedPreferences.Editor editor;

    public static PreferenceManager getInstance() {
        if (instance == null)
            instance = new PreferenceManager();
        return instance;
    }

    private Context mContext;

    private PreferenceManager() {
        mContext = Contextor.getInstance().getContext();

        prefs = mContext.getSharedPreferences("preference", Context.MODE_PRIVATE);
        editor = prefs.edit();
    }

    /****************************
     * Preferences
     ****************************/

    public void setSaveCreditCard(List<CreditCardResultData> creditCardResultData) {
        Gson gson = new Gson();
        String json = gson.toJson(creditCardResultData);
        editor.putString("CreditCard",json);
        editor.apply();
    }

    public List<CreditCardResultData> getSaveCreditCard(){
        Gson gson = new Gson();
        String json = prefs.getString("CreditCard", null);
        Type type = new TypeToken<List<CreditCardResultData>>() {}.getType();
        return gson.fromJson(json, type);
    }

    public String getFullName() {
        return prefs.getString("FullName", null);
    }

    public void setFullName(String fullName) {
        editor.putString("FullName", fullName);
        editor.apply();
    }

    public String getUserName() {
        return prefs.getString("Username", null);
    }

    public void setUsername(String username) {
        editor.putString("Username", username);
        editor.apply();
    }

    public int getTransferMoney() {
        return prefs.getInt("Status", 0);
    }

    public void setTransferMoney(int status) {
        editor.putInt("Status", status);
        editor.apply();
    }

    public String getMemberId() {
        return prefs.getString("memberID", null);
    }

    public void setMemberId(String memberId) {
        editor.putString("memberID", memberId);
        editor.apply();
    }

    public int getSelectCardId() {
        return prefs.getInt("selectCardId", 0);
    }

    public void setSelectCardId(int selectCardId) {
        editor.putInt("selectCardId", selectCardId);
        editor.apply();
    }

    public String getUrl() {
        return prefs.getString("Url", null);
    }

    public void setUrl(String url) {
        editor.putString("Url", url);
        editor.apply();
    }

    public String getToken() {
        return prefs.getString("Token", null);
    }

    public void setToken(String token) {
        editor.putString("Token", token);
        editor.apply();
    }

    public void setFbLoginDic(Dictionary fbLoginDic) {
        Gson gson = new Gson();
        String json = gson.toJson(fbLoginDic);
        editor.putString("FbLogin",json);
        editor.apply();
    }

    public Dictionary getFbLoginDic(){
        Gson gson = new Gson();
        String json = prefs.getString("FbLogin", null);
        Dictionary dic = gson.fromJson(json, Hashtable.class);
        return dic;
    }

    public int getFirstTimeInstalled() {
        return prefs.getInt("firstTimeInstalled", 0);
    }

    public void setFirstTimeInstalled(int firstTimeInstalled) {
        editor.putInt("firstTimeInstalled", firstTimeInstalled);
        editor.apply();
    }


    public String getLocale() {
        return prefs.getString("Locale", "");
    }

    public void setLocale(String locale) {
        editor.putString("Locale", locale);
        editor.apply();
    }

}

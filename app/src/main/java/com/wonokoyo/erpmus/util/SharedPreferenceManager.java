package com.wonokoyo.erpmus.util;

import android.content.Context;
import android.content.SharedPreferences;

public class SharedPreferenceManager {
    public static final String SP_MUS_ERP_APP = "spMusErpApp";
    public static final String SP_NO_RHK = "spNoRhk";

    SharedPreferences preferences;
    SharedPreferences.Editor editor;

    public SharedPreferenceManager(Context context) {
        preferences = context.getSharedPreferences(SP_MUS_ERP_APP, Context.MODE_PRIVATE);
        editor = preferences.edit();
    }

    public void saveSPString(String key, String value) {
        editor.putString(key, value);
        editor.commit();
    }

    public void saveSPInt(String key, int value) {
        editor.putInt(key, value);
        editor.commit();
    }

    public int getSpNoRhk() {
        return preferences.getInt(SP_NO_RHK, 0);
    }
}

package com.blogspot.ramannada.samplesharedpreferences;

import android.content.Context;
import android.content.SharedPreferences;

/**
 * Created by ramannada on 10/21/2017.
 */

public class SharedData {
    private SharedPreferences preferences;
    private SharedPreferences.Editor editor;
    private static SharedData sharedData;
    private static final String PREFERENCES_NAME = "com.blogspot.ramannada.SampleSharedPreferences";
    private static final String NAME_STORAGE = "name storage", LOGGED_IN_STORAGE = "login storage";

    public SharedData(Context context) {
        this.preferences = context.getSharedPreferences(PREFERENCES_NAME, context.MODE_PRIVATE);
        this.editor = preferences.edit();
    }

    public static void init(Context context) {
        sharedData = new SharedData(context);
    }

    public synchronized static SharedData getSharedData() {
        return sharedData;
    }

    public void saveName(String name) {
        editor.putString(NAME_STORAGE, name);
        editor.commit();
    }

    public String getName() {
        return preferences.getString(NAME_STORAGE, "");
    }

    public void clear() {
        editor.clear();
        editor.commit();
    }

    public void saveLoginStatus(Boolean loginStatus) {
        editor.putBoolean(LOGGED_IN_STORAGE, loginStatus);
        editor.commit();
    }

    public boolean getLoginStatus() {
        return preferences.getBoolean(LOGGED_IN_STORAGE, false);
    }

    public void clearLoginStatus() {
        editor.remove(LOGGED_IN_STORAGE);
        editor.commit();
    }



}

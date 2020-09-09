package com.cavista.leaveragesapp.preferences;

import android.content.Context;
import android.content.SharedPreferences;

public class AppPreferences {
    private static final String KEY_LOGIN_STATE = "key_image_name";

    private SharedPreferences preferences;

    public AppPreferences(Context context) {
        preferences = context.getSharedPreferences("app-blog", Context.MODE_PRIVATE);
    }

    public String getImageName() {
        return preferences.getString(KEY_LOGIN_STATE, null);
    }

    public void setImageName(String imageName) {
        preferences.edit().putString(KEY_LOGIN_STATE, imageName).apply();
    }
}

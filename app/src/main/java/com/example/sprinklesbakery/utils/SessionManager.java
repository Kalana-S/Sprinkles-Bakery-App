package com.example.sprinklesbakery.utils;

import android.content.Context;
import android.content.SharedPreferences;
public class SessionManager {

    private static final String PREF_NAME = "MemberSession";
    private static final String KEY_MEMBER_ID = "memberId";
    private static final String KEY_MEMBER_NAME = "memberName";
    private static final String KEY_MEMBER_EMAIL = "memberEmail";
    private SharedPreferences sharedPreferences;
    private SharedPreferences.Editor editor;

    public SessionManager(Context context) {
        sharedPreferences = context.getSharedPreferences(PREF_NAME, Context.MODE_PRIVATE);
        editor = sharedPreferences.edit();
    }

    public void saveMemberSession(int id, String name, String email) {
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putInt("memberId", id);
        editor.putString("memberName", name);
        editor.putString("memberEmail", email);
        editor.apply();
    }

    public int getMemberId() {
        return sharedPreferences.getInt(KEY_MEMBER_ID, -1);
    }

    public String getMemberName() {
        return sharedPreferences.getString(KEY_MEMBER_NAME, "");
    }

    public String getMemberEmail() {
        return sharedPreferences.getString(KEY_MEMBER_EMAIL, "");
    }

    public void clearSession() {
        editor.clear();
        editor.apply();
    }

}

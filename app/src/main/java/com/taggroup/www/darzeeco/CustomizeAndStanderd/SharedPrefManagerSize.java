package com.taggroup.www.darzeeco.CustomizeAndStanderd;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;

import com.taggroup.www.darzeeco.UsersContent.LoginPage;
import com.taggroup.www.darzeeco.UsersContent.User;

/**
 * Created by muhammad.sufwan on 11/22/2017.
 */

public class SharedPrefManagerSize {

    //the constants

    private static final String SHARED_PREF_NAME_SIZE = "darzeesharedprefsize";
    private static final String KEY_ID = "keyid";
    private static final String KEY_USER_ID = "keyuserid";
    private static final String KEY_S_NAME = "sizename";
    private static final String KEY_S_SHOULDER = "shoulder";
    private static final String KEY_S_CHEST = "chest";
    private static final String KEY_S_WAIST = "waist";
    private static final String KEY_S_ARMHOLE = "armhole";
    private static final String KEY_S_SLEEVELENGTH = "sleevelength";
    private static final String KEY_S_SLEEVE = "sleeve";
    private static final String KEY_S_DAAMAN = "daaman";
    private static final String KEY_S_LENGTH = "length";
    private static final String KEY_T_WAIST = "twaist";
    private static final String KEY_T_HIP = "hip";
    private static final String KEY_T_THIGH = "thigh";
    private static final String KEY_T_KNEE = "knee";
    private static final String KEY_T_LENGTH = "tlength";
    private static final String KEY_T_OPENING = "opening";
    private static final String KEY_T_INSEAMLENGTH = "inseamlength";

    private static SharedPrefManagerSize mInstance;
    private static Context mCtx;

    private SharedPrefManagerSize(Context context) {
        mCtx = context;
    }

    public static synchronized SharedPrefManagerSize getInstance(Context context) {
        if (mInstance == null) {
            mInstance = new SharedPrefManagerSize(context);
        }
        return mInstance;
    }

    //method to let the user login
    //this method will store the user data in shared preferences
    public void userLoginSize(Sizes size) {
        SharedPreferences sharedPreferences = mCtx.getSharedPreferences(SHARED_PREF_NAME_SIZE, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putInt(KEY_ID, size.getId());
        editor.putInt(KEY_USER_ID, size.getUser_id());
        editor.putString(KEY_S_NAME, size.getSizeName());
        editor.putString(KEY_S_SHOULDER, String.valueOf(size.getsShoulder()));
        editor.putString(KEY_S_CHEST, String.valueOf(size.getsChest()));
        editor.putString(KEY_S_WAIST, String.valueOf(size.getsWaist()));
        editor.putString(KEY_S_ARMHOLE, String.valueOf(size.getsArmHole()));
        editor.putString(KEY_S_SLEEVELENGTH, String.valueOf(size.getsSleeveLength()));
        editor.putString(KEY_S_SLEEVE, String.valueOf(size.getsSleeve()));
        editor.putString(KEY_S_DAAMAN, String.valueOf(size.getsDaaman()));
        editor.putString(KEY_S_LENGTH, String.valueOf(size.getsLength()));
        editor.putString(KEY_T_WAIST, String.valueOf(size.gettWaist()));
        editor.putString(KEY_T_HIP, String.valueOf(size.gettHip()));
        editor.putString(KEY_T_THIGH, String.valueOf(size.gettThigh()));
        editor.putString(KEY_T_KNEE, String.valueOf(size.gettKnee()));
        editor.putString(KEY_T_LENGTH, String.valueOf(size.gettLength()));
        editor.putString(KEY_T_OPENING, String.valueOf(size.gettOpening()));
        editor.putString(KEY_T_INSEAMLENGTH, String.valueOf(size.gettInseamLength()));

        editor.apply();
    }

    //this method will checker whether user is already logged in or not
    public boolean isLoggedIn() {
        SharedPreferences sharedPreferences = mCtx.getSharedPreferences(SHARED_PREF_NAME_SIZE, Context.MODE_PRIVATE);
        return sharedPreferences.getString(KEY_S_NAME, null) != null;
    }

    //this method will give the logged in user
    public Sizes getUserSize() {
        SharedPreferences sharedPreferences = mCtx.getSharedPreferences(SHARED_PREF_NAME_SIZE, Context.MODE_PRIVATE);
        return new Sizes(
                sharedPreferences.getInt(KEY_ID, 1),
                sharedPreferences.getInt(KEY_USER_ID, 1),
                sharedPreferences.getString(KEY_S_NAME, null),
                sharedPreferences.getFloat(KEY_S_SHOULDER, 1),
                sharedPreferences.getFloat(KEY_S_CHEST, 1),
                sharedPreferences.getFloat(KEY_S_WAIST, 1),
                sharedPreferences.getFloat(KEY_S_ARMHOLE, 1),
                sharedPreferences.getFloat(KEY_S_SLEEVELENGTH, 1),
                sharedPreferences.getFloat(KEY_S_SLEEVE, 1),
                sharedPreferences.getFloat(KEY_S_DAAMAN, 1),
                sharedPreferences.getFloat(KEY_S_LENGTH, 1),
                sharedPreferences.getFloat(KEY_T_WAIST, 1),
                sharedPreferences.getFloat(KEY_T_HIP, 1),
                sharedPreferences.getFloat(KEY_T_THIGH, 1),
                sharedPreferences.getFloat(KEY_T_KNEE, 1),
                sharedPreferences.getFloat(KEY_T_LENGTH, 1),
                sharedPreferences.getFloat(KEY_T_OPENING, 1),
                sharedPreferences.getFloat(KEY_T_INSEAMLENGTH, 1)
        );
    }

    //this method will logout the user
    public void ClearSize() {
        SharedPreferences sharedPreferences = mCtx.getSharedPreferences(SHARED_PREF_NAME_SIZE, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.clear();
        editor.apply();
        mCtx.startActivity(new Intent(mCtx, CategoryDesign.class));
    }





}

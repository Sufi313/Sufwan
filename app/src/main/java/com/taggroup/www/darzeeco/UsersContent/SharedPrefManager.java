package com.taggroup.www.darzeeco.UsersContent;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;

/**
 * Created by muhammad.sufwan on 11/22/2017.
 */

public class SharedPrefManager {

    //the constants

    private static final String SHARED_PREF_NAME = "darzeesharedpref";
    private static final String KEY_ID = "keyid";
    private static final String KEY_FIRSTNAME = "keyfirstname";
    private static final String KEY_LASTNAME = "keylastname";
    private static final String KEY_EMAIL = "keyemail";
    private static final String KEY_PHONENUMBER = "keyphonenumber";
    private static final String KEY_ADDRESS = "keyaddress";
    private static final String KEY_GENDER = "keygender";
    private static final String KEY_COUNTRY = "keycountry";
    private static final String KEY_CITY = "keyphonenumber";
    private static final String KEY_DATEOFBIRTH = "keydob";

    private static SharedPrefManager mInstance;
    private static Context mCtx;

    private SharedPrefManager(Context context) {
        mCtx = context;
    }

    public static synchronized SharedPrefManager getInstance(Context context) {
        if (mInstance == null) {
            mInstance = new SharedPrefManager(context);
        }
        return mInstance;
    }

    //method to let the user login
    //this method will store the user data in shared preferences
    public void userLogin(User user) {
        SharedPreferences sharedPreferences = mCtx.getSharedPreferences(SHARED_PREF_NAME, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putInt(KEY_ID, user.getId());
        editor.putString(KEY_FIRSTNAME, user.getFirstname());
        editor.putString(KEY_LASTNAME, user.getLastname());
        editor.putString(KEY_EMAIL, user.getEmail());
        editor.putString(KEY_PHONENUMBER, user.getPhonenumber());
        editor.putString(KEY_ADDRESS, user.getAddress());
        editor.putString(KEY_GENDER, user.getGender());
        editor.putString(KEY_COUNTRY, user.getCountry());
        editor.putString(KEY_CITY, user.getCity());
        editor.putString(KEY_DATEOFBIRTH, user.getDateofbirth());
        editor.apply();
    }

    //this method will checker whether user is already logged in or not
    public boolean isLoggedIn() {
        SharedPreferences sharedPreferences = mCtx.getSharedPreferences(SHARED_PREF_NAME, Context.MODE_PRIVATE);
        return sharedPreferences.getString(KEY_FIRSTNAME, null) != null;
    }

    //this method will give the logged in user
    public User getUser() {
        SharedPreferences sharedPreferences = mCtx.getSharedPreferences(SHARED_PREF_NAME, Context.MODE_PRIVATE);
        return new User(
                sharedPreferences.getInt(KEY_ID, -1),
                sharedPreferences.getString(KEY_FIRSTNAME, null),
                sharedPreferences.getString(KEY_LASTNAME, null),
                sharedPreferences.getString(KEY_EMAIL, null),
                sharedPreferences.getString(KEY_PHONENUMBER, null),
                sharedPreferences.getString(KEY_ADDRESS, null),
                sharedPreferences.getString(KEY_GENDER, null),
                sharedPreferences.getString(KEY_COUNTRY, null),
                sharedPreferences.getString(KEY_CITY, null),
                sharedPreferences.getString(KEY_DATEOFBIRTH, null)
        );
    }

    //this method will logout the user
    public void logout() {
        SharedPreferences sharedPreferences = mCtx.getSharedPreferences(SHARED_PREF_NAME, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.clear();
        editor.apply();
        mCtx.startActivity(new Intent(mCtx, LoginPage.class));
    }





}

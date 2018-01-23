package com.taggroup.www.darzeeco.CustomizeAndStanderd;

import android.content.Context;
import android.content.SharedPreferences;


/**
 * Created by muhammad.sufwan on 1/22/2018.
 */

public class LetSelectSizePrefMngr {

    private Context mContext;
    private static LetSelectSizePrefMngr mInstance;
    private static final String PREF_SIZE = "select_size";
    private static final String PREF_FRONT = "select_front_neck";
    private static final String PREF_BACK = "select_back_neck";
    private static final String PREF_SHALWAR = "select_shalwar";
    private static final String PREF_DAAMAN = "select_daaman";
    private static final String PREF_DUPATTA = "select_dupatta";

    public LetSelectSizePrefMngr(Context mContext) {
        this.mContext = mContext;
    }

    public static synchronized LetSelectSizePrefMngr getInstance(Context context) {
        if (mInstance == null) {
            mInstance = new LetSelectSizePrefMngr(context);
        }
        return mInstance;
    }

    public void selectSize(int sizeId, String sizeName) {
        SharedPreferences sharedPreferences = mContext.getSharedPreferences(PREF_SIZE, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putInt("size_id", sizeId);
        editor.putString("size_name", sizeName);
        editor.commit();
    }

    public boolean isSizeIn() {
        SharedPreferences sharedPreferences = mContext.getSharedPreferences(PREF_SIZE, Context.MODE_PRIVATE);
        return sharedPreferences.getString("size_name", null) != null;
    }

    public int getSizeId() {
        SharedPreferences sharedPreferences = mContext.getSharedPreferences(PREF_SIZE, Context.MODE_PRIVATE);
        return sharedPreferences.getInt("size_id", 0);
    }

    public String getSizeName() {
        SharedPreferences sharedPreferences = mContext.getSharedPreferences(PREF_SIZE, Context.MODE_PRIVATE);
        return sharedPreferences.getString("size_name", null);
    }

    public void ClearSize() {
        SharedPreferences sharedPreferences = mContext.getSharedPreferences(PREF_SIZE, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.clear();
        editor.apply();
    }

    public void selectFrontNeck(int frontNeckId, String frontNeckName, float frontNeckPrice, String frontNeckImage) {
        SharedPreferences sharedPreferences = mContext.getSharedPreferences(PREF_FRONT, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putInt("front_neck_id", frontNeckId);
        editor.putString("front_neck_name", frontNeckName);
        editor.putFloat("front_neck_price", frontNeckPrice);
        editor.putString("front_neck_image", frontNeckImage);
        editor.commit();
    }

    public boolean isFrontNeckIn() {
        SharedPreferences sharedPreferences = mContext.getSharedPreferences(PREF_FRONT, Context.MODE_PRIVATE);
        return sharedPreferences.getString("front_neck_name", null) != null;
    }

    public int getFrontNeckId() {
        SharedPreferences sharedPreferences = mContext.getSharedPreferences(PREF_FRONT, Context.MODE_PRIVATE);
        return sharedPreferences.getInt("front_neck_id", 0);
    }

    public String getFrontNeckName() {
        SharedPreferences sharedPreferences = mContext.getSharedPreferences(PREF_FRONT, Context.MODE_PRIVATE);
        return sharedPreferences.getString("front_neck_name", null);
    }

    public float getFrontNeckPrice() {
        SharedPreferences sharedPreferences = mContext.getSharedPreferences(PREF_FRONT, Context.MODE_PRIVATE);
        return sharedPreferences.getFloat("front_neck_price", 0);
    }

    public String getFrontNeckImage() {
        SharedPreferences sharedPreferences = mContext.getSharedPreferences(PREF_FRONT, Context.MODE_PRIVATE);
        return sharedPreferences.getString("front_neck_image", null);
    }

    public void ClearSelectFrontNeck() {
        SharedPreferences sharedPreferences = mContext.getSharedPreferences(PREF_FRONT, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.clear();
        editor.apply();
    }

    public void selectBackNeck(int backNeckId, String backNeckName, float backNeckPrice, String backNeckImage) {
        SharedPreferences sharedPreferences = mContext.getSharedPreferences(PREF_BACK, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putInt("back_neck_id", backNeckId);
        editor.putString("back_neck_name", backNeckName);
        editor.putFloat("back_neck_price", backNeckPrice);
        editor.putString("back_neck_image", backNeckImage);
        editor.commit();
    }

    public boolean isBackNeckIn() {
        SharedPreferences sharedPreferences = mContext.getSharedPreferences(PREF_BACK, Context.MODE_PRIVATE);
        return sharedPreferences.getString("back_neck_name", null) != null;
    }

    public int getBackNeckId() {
        SharedPreferences sharedPreferences = mContext.getSharedPreferences(PREF_BACK, Context.MODE_PRIVATE);
        return sharedPreferences.getInt("back_neck_id", 0);
    }

    public String getBackNeckName() {
        SharedPreferences sharedPreferences = mContext.getSharedPreferences(PREF_BACK, Context.MODE_PRIVATE);
        return sharedPreferences.getString("back_neck_name", null);
    }

    public float getBackNeckPrice() {
        SharedPreferences sharedPreferences = mContext.getSharedPreferences(PREF_BACK, Context.MODE_PRIVATE);
        return sharedPreferences.getFloat("back_neck_price", 0);
    }

    public String getBackNeckImage() {
        SharedPreferences sharedPreferences = mContext.getSharedPreferences(PREF_BACK, Context.MODE_PRIVATE);
        return sharedPreferences.getString("back_neck_image", null);
    }

    public void ClearSelectBackNeck() {
        SharedPreferences sharedPreferences = mContext.getSharedPreferences(PREF_BACK, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.clear();
        editor.apply();
    }

    public void selectShalwar(int shalwarId, String shalwarName, float shalwarPrice, String shalwarImage) {
        SharedPreferences sharedPreferences = mContext.getSharedPreferences(PREF_SHALWAR, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putInt("shalwar_id", shalwarId);
        editor.putString("shalwar_name", shalwarName);
        editor.putFloat("shalwar_price", shalwarPrice);
        editor.putString("shalwar_image", shalwarImage);
        editor.commit();
    }

    public boolean isShalwarIn() {
        SharedPreferences sharedPreferences = mContext.getSharedPreferences(PREF_SHALWAR, Context.MODE_PRIVATE);
        return sharedPreferences.getString("shalwar_name", null) != null;
    }

    public int getShalwarId() {
        SharedPreferences sharedPreferences = mContext.getSharedPreferences(PREF_SHALWAR, Context.MODE_PRIVATE);
        return sharedPreferences.getInt("shalwar_id", 0);
    }

    public String getShalwarName() {
        SharedPreferences sharedPreferences = mContext.getSharedPreferences(PREF_SHALWAR, Context.MODE_PRIVATE);
        return sharedPreferences.getString("shalwar_name", null);
    }

    public float getShalwarPrice() {
        SharedPreferences sharedPreferences = mContext.getSharedPreferences(PREF_SHALWAR, Context.MODE_PRIVATE);
        return sharedPreferences.getFloat("shalwar_price", 0);
    }

    public String getShalwarImage() {
        SharedPreferences sharedPreferences = mContext.getSharedPreferences(PREF_SHALWAR, Context.MODE_PRIVATE);
        return sharedPreferences.getString("shalwar_image", null);
    }

    public void ClearSelectShalwar() {
        SharedPreferences sharedPreferences = mContext.getSharedPreferences(PREF_SHALWAR, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.clear();
        editor.apply();
    }

    public void selectDaaman(int daamanId, String daamanName, float daamanPrice, String daamanImage) {
        SharedPreferences sharedPreferences = mContext.getSharedPreferences(PREF_DAAMAN, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putInt("daaman_id", daamanId);
        editor.putString("daaman_name", daamanName);
        editor.putFloat("daaman_price", daamanPrice);
        editor.putString("daaman_image", daamanImage);
        editor.commit();
    }

    public boolean isDaamanIn() {
        SharedPreferences sharedPreferences = mContext.getSharedPreferences(PREF_DAAMAN, Context.MODE_PRIVATE);
        return sharedPreferences.getString("daaman_name", null) != null;
    }

    public int getDaamanId() {
        SharedPreferences sharedPreferences = mContext.getSharedPreferences(PREF_DAAMAN, Context.MODE_PRIVATE);
        return sharedPreferences.getInt("daaman_id", 0);
    }

    public String getDaamanName() {
        SharedPreferences sharedPreferences = mContext.getSharedPreferences(PREF_DAAMAN, Context.MODE_PRIVATE);
        return sharedPreferences.getString("daaman_name", null);
    }

    public float getDaamanPrice() {
        SharedPreferences sharedPreferences = mContext.getSharedPreferences(PREF_DAAMAN, Context.MODE_PRIVATE);
        return sharedPreferences.getFloat("daaman_price", 0);
    }

    public String getDaamanImage() {
        SharedPreferences sharedPreferences = mContext.getSharedPreferences(PREF_DAAMAN, Context.MODE_PRIVATE);
        return sharedPreferences.getString("daaman_image", null);
    }

    public void ClearSelectDaaman() {
        SharedPreferences sharedPreferences = mContext.getSharedPreferences(PREF_DAAMAN, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.clear();
        editor.apply();
    }

    public void selectDupatta(int dupattaId, String dupattaName, float dupattaPrice, String dupattaImage) {
        SharedPreferences sharedPreferences = mContext.getSharedPreferences(PREF_DUPATTA, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putInt("dupatta_id", dupattaId);
        editor.putString("dupatta_name", dupattaName);
        editor.putFloat("dupatta_price", dupattaPrice);
        editor.putString("dupatta_image", dupattaImage);
        editor.commit();
    }

    public boolean isDupattaIn() {
        SharedPreferences sharedPreferences = mContext.getSharedPreferences(PREF_DUPATTA, Context.MODE_PRIVATE);
        return sharedPreferences.getString("dupatta_name", null) != null;
    }

    public int getDupattaId() {
        SharedPreferences sharedPreferences = mContext.getSharedPreferences(PREF_DUPATTA, Context.MODE_PRIVATE);
        return sharedPreferences.getInt("dupatta_id", 0);
    }

    public String getDupattaName() {
        SharedPreferences sharedPreferences = mContext.getSharedPreferences(PREF_DUPATTA, Context.MODE_PRIVATE);
        return sharedPreferences.getString("dupatta_name", null);
    }

    public float getDupattaPrice() {
        SharedPreferences sharedPreferences = mContext.getSharedPreferences(PREF_DUPATTA, Context.MODE_PRIVATE);
        return sharedPreferences.getFloat("dupatta_price", 0);
    }

    public String getDupattaImage() {
        SharedPreferences sharedPreferences = mContext.getSharedPreferences(PREF_DUPATTA, Context.MODE_PRIVATE);
        return sharedPreferences.getString("dupatta_image", null);
    }

    public void ClearSelectDupatta() {
        SharedPreferences sharedPreferences = mContext.getSharedPreferences(PREF_DUPATTA, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.clear();
        editor.apply();
    }
}

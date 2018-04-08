package com.example.android.colorconverter;

/**
 * Created by guidovdriet on 05-04-18.
 */

public class ColorValueCard {

    private String mName;
    private String mHex;
    private String mCmyk;
    private String mRgb;

    ColorValueCard(String name, String hex, String cmyk, String rgb) {
        mName = name;
        mHex = hex;
        mCmyk = cmyk;
        mRgb = rgb;
    }

    String getName() {
        return mName;
    }
    String getHex() {
        return mHex;
    }
    String getCmyk() {
        return mCmyk;
    }
    String getRgb() {
        return mRgb;
    }
}

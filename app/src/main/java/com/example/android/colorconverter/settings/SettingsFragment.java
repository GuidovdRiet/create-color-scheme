package com.example.android.colorconverter.settings;

import android.os.Bundle;
import android.preference.PreferenceFragment;
import android.support.v7.preference.PreferenceFragmentCompat;

import com.example.android.colorconverter.R;

/**
 * Created by guidovdriet on 10-04-18.
 */

public class SettingsFragment extends PreferenceFragmentCompat {

    @Override
    public void onCreatePreferences(Bundle savedInstanceState, String rootKey) {
        addPreferencesFromResource(R.xml.prefrence_color_scheme_converter);
    }
}

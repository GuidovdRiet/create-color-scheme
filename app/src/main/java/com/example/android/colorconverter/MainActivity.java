package com.example.android.colorconverter;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

import com.example.android.colorconverter.settings.SettingsActivity;

public class MainActivity extends AppCompatActivity implements SharedPreferences.OnSharedPreferenceChangeListener{

    private final static String HEX_VALUE = "hex";
    private final static String RGB_VALUE = "rgb";
    private final static String CMYK_VALUE = "cmyk";

    private Button mHexButton;
    private Button mRgbButton;
    private Button mCmykButton;

    private ImageView mBrandIconImageView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        mHexButton = findViewById(R.id.btn_hex_color);
        mRgbButton = findViewById(R.id.btn_rgb_color);
        mCmykButton = findViewById(R.id.btn_cmyk_color);
        mBrandIconImageView = findViewById(R.id.iv_icon_brand);

        mBrandIconImageView.setImageResource(R.drawable.cd_brand_color_type_picker_icon);

        setupSharedPreferences();

        mHexButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                setIntent(
                        MainActivity.this,
                        ColorConvertActivity.class,
                        Intent.EXTRA_TEXT,
                        HEX_VALUE);
            }
        });

        mRgbButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                setIntent(
                        MainActivity.this,
                        ColorConvertActivity.class,
                        Intent.EXTRA_TEXT, RGB_VALUE
                );
            }
        });

        mCmykButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                setIntent(
                        MainActivity.this,
                        ColorConvertActivity.class,
                        Intent.EXTRA_TEXT, CMYK_VALUE
                );
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.color_scheme_converter_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        if (id == R.id.action_settings) {
            Intent startSettings = new Intent(this, SettingsActivity.class);
            startActivity(startSettings);
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    private void setIntent(Context context, Class intentClass, String intentName, String intentData) {
        Intent startColorConverter = new Intent(context, intentClass);
        startColorConverter.putExtra(intentName, intentData);
        startActivity(startColorConverter);
    }

    private void setShowButton(Boolean showInView, Button button) {
        if(showInView) {
            button.setVisibility(View.VISIBLE);
        } else {
            button.setVisibility(View.INVISIBLE);
        }
    }

    // Set default setup for settings screen
    private void setupSharedPreferences() {
        SharedPreferences sharedPreferences = PreferenceManager.
                getDefaultSharedPreferences(this);
        setShowButton(
                sharedPreferences.getBoolean(getString(R.string.pref_show_hex_key),
                getResources().getBoolean(R.bool.pref_show_hex_default)),
                mHexButton);

        setShowButton(true, mRgbButton);
        setShowButton(true, mCmykButton);

        sharedPreferences.registerOnSharedPreferenceChangeListener(this);
    }

    @Override
    public void onSharedPreferenceChanged(SharedPreferences sharedPreferences, String key) {
        if (key.equals(getString(R.string.pref_show_hex_key))) {
            setShowButton(sharedPreferences.getBoolean(key,
                    getResources().getBoolean(R.bool.pref_show_hex_default)),
                    mHexButton);
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        PreferenceManager.getDefaultSharedPreferences(this).
                unregisterOnSharedPreferenceChangeListener(this);
    }
}

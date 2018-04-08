package com.example.android.colorconverter;

import android.content.Context;
import android.content.Intent;
import android.media.Image;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

public class HomeActivity extends AppCompatActivity {

    private final static String HEX_VALUE = "hex";
    private final static String RGB_VALUE = "rgb";
    private final static String CMYK_VALUE = "cmyk";

    private Button mHexButtonTextView;
    private Button mRgbButtonTextView;
    private Button mCmykButtonTextView;

    private ImageView mBrandIconImageView;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        mHexButtonTextView = findViewById(R.id.btn_hex_color);
        mRgbButtonTextView = findViewById(R.id.btn_rgb_color);
        mCmykButtonTextView = findViewById(R.id.btn_cmyk_color);
        mBrandIconImageView = findViewById(R.id.iv_icon_brand);

        mBrandIconImageView.setImageResource(R.drawable.cd_brand_color_type_picker_icon);

        mHexButtonTextView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                setIntent(
                        HomeActivity.this,
                        ColorConvertActivity.class,
                        Intent.EXTRA_TEXT,
                        HEX_VALUE);
            }
        });

        mRgbButtonTextView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                setIntent(
                        HomeActivity.this,
                        ColorConvertActivity.class,
                        Intent.EXTRA_TEXT, RGB_VALUE
                );
            }
        });

        mCmykButtonTextView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                setIntent(
                        HomeActivity.this,
                        ColorConvertActivity.class,
                        Intent.EXTRA_TEXT, CMYK_VALUE
                );
            }
        });
    }

    private void setIntent(Context context, Class intentClass, String intentName, String intentData) {
        Intent startColorConverter = new Intent(context, intentClass);
        startColorConverter.putExtra(intentName, intentData);
        startActivity(startColorConverter);
    }
}

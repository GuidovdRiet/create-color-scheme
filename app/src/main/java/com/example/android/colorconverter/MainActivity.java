package com.example.android.colorconverter;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    private final static String HEX_VALUE = "hex";
    private final static String RGB_VALUE = "rgb";
    private final static String CMYK_VALUE = "cmyk";

    private Button mHexButtonTextView;
    private Button mRgbButtonTextView;
    private Button mCmykButtonTextView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mHexButtonTextView = findViewById(R.id.btn_hex_color);
        mRgbButtonTextView = findViewById(R.id.btn_rgb_color);
        mCmykButtonTextView = findViewById(R.id.btn_cmyk_color);

        mHexButtonTextView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                setIntent(
                    MainActivity.this,
                    ColorConvertActivity.class,
                    Intent.EXTRA_TEXT,
                    HEX_VALUE);
            }
        });

        mRgbButtonTextView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                setIntent(
                    MainActivity.this,
                    ColorConvertActivity.class,
                    Intent.EXTRA_TEXT, RGB_VALUE
                );
            }
        });

        mCmykButtonTextView.setOnClickListener(new View.OnClickListener() {
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

    private void setIntent(Context context, Class intentClass, String intentName, String intentData) {
        Intent startColorConverter = new Intent(context, intentClass);
        startColorConverter.putExtra(intentName, intentData);
        startActivity(startColorConverter);
    }
}

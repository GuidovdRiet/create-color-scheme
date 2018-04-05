package com.example.android.colorconverter;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class MainActivity extends AppCompatActivity {
    private EditText mUserColorInput;
    private Button mConvertColorButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mUserColorInput = findViewById(R.id.et_color_input);
        mConvertColorButton = findViewById(R.id.convert_color_button);

        mConvertColorButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String insertedColor = mUserColorInput.getText().toString();
                Context context = MainActivity.this;
                Class colorResultsActivity = ColorConvertResultsActivity.class;
                Intent startColorResultsActivity = new Intent(context, colorResultsActivity);
                startColorResultsActivity.putExtra(Intent.EXTRA_TEXT, insertedColor);
                startActivity(startColorResultsActivity);
            }
        });
    }
}

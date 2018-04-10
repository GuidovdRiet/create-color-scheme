package com.example.android.colorconverter.settings;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.example.android.colorconverter.R;

public class SettingsActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_settings);

//        // prevents back button to close the app.
//        ActionBar actionBar = this.getActionBar();
//        if (actionBar != null) {
//            actionBar.setDisplayHomeAsUpEnabled(true);
//        }
    }

//    // prevents back button to close the app.
//    @Override
//    public boolean onOptionsItemSelected(MenuItem item) {
//        int id = item.getItemId();
//        if( id == android.R.id.home) {
//            NavUtils.navigateUpFromSameTask(this);
//        }
//        return super.onOptionsItemSelected(item);
//    }
}

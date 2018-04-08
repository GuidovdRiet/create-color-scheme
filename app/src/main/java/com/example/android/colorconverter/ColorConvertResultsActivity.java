package com.example.android.colorconverter;

import android.content.Intent;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.example.android.colorconverter.utilities.NetworkUtils;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class ColorConvertResultsActivity extends AppCompatActivity {

    TextView mColorResultsDisplay;
    private RecyclerView mColorRecyclerView;
    private TextView mErrorMessageDisplay;
    private ProgressBar mLoadingIndicator;
    private ColorAdapter mColorAdapter;
    private ArrayList<ColorValueCard> mColorValueCardsList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_color_convert_results);

        mColorResultsDisplay = findViewById(R.id.tv_color_convert_display);
        mErrorMessageDisplay = findViewById(R.id.tv_error_message_display);
        mLoadingIndicator = findViewById(R.id.pb_loading_color_indicator);
        mColorRecyclerView = findViewById(R.id.rv_color_result);

        // Set the layout manager for the converted color item results
        LinearLayoutManager colorLayoutManager = new LinearLayoutManager(this);
        mColorRecyclerView.setLayoutManager(colorLayoutManager);
        mColorRecyclerView.setHasFixedSize(true);

        // Get the color from user input in MainActivity
        Intent intentStart = getIntent();
        Bundle intentExtras = intentStart.getExtras();
        String colorEntered = intentExtras.getString("EXTRA_COLOR");
        String colorTypeEntered = intentExtras.getString("EXTRA_COLOR_TYPE");
        createColorSearchQuery(colorEntered, colorTypeEntered);
    }

    private void createColorSearchQuery(String color, String colorType) {
        URL colorConverterUrl = NetworkUtils.buildUrl(color, colorType);
        new ColorQueryTask().execute(colorConverterUrl);
    }

    private void showDataView() {
        mErrorMessageDisplay.setVisibility(View.INVISIBLE);
        mColorRecyclerView.setVisibility(View.VISIBLE);
    }

    private void showErrorMessage() {
        mErrorMessageDisplay.setVisibility(View.VISIBLE);
        mColorRecyclerView.setVisibility(View.INVISIBLE);
    }

    public class ColorQueryTask extends AsyncTask<URL, Void, String> {

        private static final String TAG = "The keys";

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            mLoadingIndicator.setVisibility(View.VISIBLE);
        }

        @Override
        protected String doInBackground(URL... params) {
            URL searchUrl = params[0];
            String colorConverterResults = null;
            try {
                colorConverterResults = NetworkUtils.getResponseFromHttpUrl(searchUrl);
            } catch (IOException e) {
                e.printStackTrace();
            }
            return colorConverterResults;
        }

        // To get the value from nested field from the COLOR schema API
        String getJsonResultNestedValue(JSONObject jsonObject, String keyType, String valueType) throws JSONException {
            JSONObject colorType = jsonObject.getJSONObject(keyType);
            String colorValue = colorType.getString(valueType);
            return colorValue;
        }

        ArrayList<ColorValueCard> getCondition(String JSONString) throws JSONException {
            JSONObject jsonObject = new JSONObject(JSONString);
            JSONArray jsonArray = jsonObject.getJSONArray("colors");

            mColorValueCardsList = new ArrayList<>();
            for (int i = 0; i < jsonArray.length(); i++) {
                JSONObject ColorsjsonObject = jsonArray.getJSONObject(i);
                String nameValue = getJsonResultNestedValue(ColorsjsonObject, "name", "value");
                String hexValue = getJsonResultNestedValue(ColorsjsonObject, "hex", "value");
                String cmykValue = getJsonResultNestedValue(ColorsjsonObject, "cmyk", "value");
                String rgbValue = getJsonResultNestedValue(ColorsjsonObject, "rgb", "value");
                mColorValueCardsList.add(new ColorValueCard(nameValue, hexValue, cmykValue, rgbValue));
            }

            return mColorValueCardsList;
        }

        @Override
        protected void onPostExecute(String colorConverterResults) {
            mLoadingIndicator.setVisibility(View.INVISIBLE);
            if(colorConverterResults != null && !colorConverterResults.equals("")) {
                try {
                    ArrayList<ColorValueCard> rgbConvertion = getCondition(colorConverterResults);
                    // Create Adapter to inflate Recycler list
                    mColorAdapter = new ColorAdapter(ColorConvertResultsActivity.this, rgbConvertion);
                    mColorRecyclerView.setAdapter(mColorAdapter);
                } catch (JSONException e) {
                    e.printStackTrace();
                }
                showDataView();
            } else {
                showErrorMessage();
            }
        }
    }
}

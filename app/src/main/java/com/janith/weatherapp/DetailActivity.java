package com.janith.weatherapp;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;

public class DetailActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);

        String forecast = getIntent().getStringExtra(MainActivity.FORECAST);

        TextView detailField = (TextView) findViewById(R.id.detail_field);
        detailField.setText(forecast);
    }
}

package com.mobile.iglo.training;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class DetailScreen extends AppCompatActivity {


    public static int RESULT_CODE = 69;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail_screen);
        setTitle("DetailScreen");


        ((Button) findViewById(R.id.btn_finish)).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent();
                intent.putExtra("data", "ini dari detail Screen");
                setResult(RESULT_CODE, intent);
                finish();
            }
        });

    }}



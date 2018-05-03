package com.mobile.iglo.training;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class WelcomeScreen extends AppCompatActivity {

    public static int RESULT_CODE = 68;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_welcome_screen);

        String username = getIntent().getStringExtra("username");
        ((TextView)findViewById(R.id.username)).setText(username);

        ((Button)findViewById(R.id.btnFinish)).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent();
                intent.putExtra("data","ini dari Welcome Screen");
                setResult(RESULT_CODE,intent);
                finish();
            }
        });
    }

}

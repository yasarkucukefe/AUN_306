package com.ybs306.androidapp;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.Button;

public class MainActivity2 extends AppCompatActivity {
    Button buton1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);

        buton1 = findViewById(R.id.btn_back_1);

        buton1.setOnClickListener(v->{finish();});
    }
}
package com.ybs306.listeactivity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

public class SehirActivity extends AppCompatActivity {
    TextView tv_sehir;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sehir);

        tv_sehir = findViewById(R.id.isim_sehir);

        Intent intent = getIntent();
        String sehir = intent.getStringExtra("SEHIR_AD");

        tv_sehir.setText(sehir);
    }
}
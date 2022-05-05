package com.ybs306.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;

import org.chromium.net.CronetEngine;
import org.chromium.net.UrlRequest;

import java.util.concurrent.Executor;
import java.util.concurrent.Executors;

public class MainActivity2 extends AppCompatActivity {
    TextView tv_sehir, tv_havaDurumu;
    Button kapat;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);

        tv_sehir = findViewById(R.id.sehirAd);
        tv_havaDurumu = findViewById(R.id.havaDurumu);
        kapat = findViewById(R.id.kapat);

        tv_sehir.setOnClickListener(v -> ucuncu_sayfa());

        kapat.setOnClickListener(v -> {finish();});

        Intent intent = getIntent();
        String sehir = intent.getStringExtra("SECILEN_SEHIR");
        tv_sehir.setText(sehir);

        get_data_from_hava_durum_url();
    }

    private void get_data_from_hava_durum_url(){

        UrlData udt = new UrlData();

        String url = "https://api.openweathermap.org/data/2.5/weather?lat=41.0677417&lon=29.0105776&appid=90bf1f6964f09d9036c80992c58550d7";

        udt.execute(url,this);


    }

    public void response_received (String resp){
        tv_havaDurumu.setText(resp);
    }

    private void ucuncu_sayfa() {
        Intent intent = new Intent(this,MainActivity3.class);
        startActivity(intent);
    }
}
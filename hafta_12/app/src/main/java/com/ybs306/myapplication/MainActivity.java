package com.ybs306.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {
    Button button1,button2,button3;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        button1 = findViewById(R.id.sehir1);
        button2 = findViewById(R.id.sehir2);
        button3 = findViewById(R.id.sehir3);

        // click events
        button1.setOnClickListener(view -> goster_hava_durumu("İstanbul"));
        button2.setOnClickListener(view -> goster_hava_durumu("Ankara"));
        button3.setOnClickListener(view -> goster_hava_durumu("İzmir"));
    }

    private void goster_hava_durumu(String sehir) {
        Intent  intent = new Intent(this, MainActivity2.class);
        intent.putExtra("SECILEN_SEHIR",sehir);
        startActivity(intent);
    }


}
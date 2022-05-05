package com.ybs306.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;

public class MainActivity3 extends AppCompatActivity {
    Button buton1, buton2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main3);

        buton1 = findViewById(R.id.onceki);
        buton2 = findViewById(R.id.anasayfa);

        buton1.setOnClickListener( v -> {finish();});  // Önceki acvitity'e döner

        buton2.setOnClickListener( v -> {ana_sayfa();});  // Ana activity'e döner
    }

    private void ana_sayfa(){
        finishAffinity();
        startActivity(new Intent(this,MainActivity.class));
    };
}
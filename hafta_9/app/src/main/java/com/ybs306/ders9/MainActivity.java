package com.ybs306.ders9;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    Button buton;
    TextView tv_ziyaretci;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //button1 ID no'lu layout button'a ilişkilendir.
        buton = findViewById(R.id.button1);
        tv_ziyaretci = findViewById(R.id.ziyaretciSay);
        tv_ziyaretci.setText("0");

        buton.setOnClickListener(view -> {
            //Toast.makeText(this,"Butona basıldı",Toast.LENGTH_SHORT).show();
            yeni_ziyaretci_ekle();
        });



        Toast.makeText(this,"onCreate çalıştı",Toast.LENGTH_SHORT).show();
        Log.d("durum","onCreate çalıştı");
    }

    private void yeni_ziyaretci_ekle(){
        String mevcut_str = (String) tv_ziyaretci.getText();
        int mevcut = Integer.parseInt(mevcut_str);
        int yeni = mevcut+ 1 ; // mevcut = mevcut + 1
        String yeni_str = Integer.toString(yeni);
        tv_ziyaretci.setText(yeni_str);
    }



}
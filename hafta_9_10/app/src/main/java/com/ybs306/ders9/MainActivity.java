package com.ybs306.ders9;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    Button buton1,buton2;
    TextView tv_ziyaretci;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //button1 ID no'lu layout button'a ilişkilendir.
        buton1 = findViewById(R.id.button1);
        buton2 = findViewById(R.id.button2);

        tv_ziyaretci = findViewById(R.id.ziyaretciSay);
        tv_ziyaretci.setText("0");

        buton1.setOnClickListener(view -> {
            //Toast.makeText(this,"Butona basıldı",Toast.LENGTH_SHORT).show();
            yeni_ziyaretci_ekle();
        });

        buton2.setOnClickListener(view -> {
            //Toast.makeText(this,"Buton-2 basıldı",Toast.LENGTH_SHORT).show();
            handle_giden_ziyeretci();
        });

        Toast.makeText(this,"onCreate çalıştı",Toast.LENGTH_SHORT).show();
        Log.d("durum","onCreate çalıştı");
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle item selection
        switch (item.getItemId()) {
            case R.id.reset:
                ziyaretci_say_reset();
                return true;
            case R.id.about:
                Toast.makeText(this,"Uygulama hakkında ...",Toast.LENGTH_SHORT).show();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    private void ziyaretci_say_reset() {
        tv_ziyaretci.setText("0");
        ziyaretci_sayisi_sakla(0);
    }

    @Override
    protected void onResume() {
        super.onResume();
        Toast.makeText(this,"On resume() çalıştı",Toast.LENGTH_SHORT).show();
        tv_ziyaretci.setText(ziyaretci_sayisini_oku()+"");
    }

    @Override
    protected void onPause() {
        super.onPause();
        Toast.makeText(this,"On pause() çalıştı",Toast.LENGTH_SHORT).show();

    }

    @Override
    protected void onStop() {
        super.onStop();
        //Toast.makeText(this,"On stop() çalıştı",Toast.LENGTH_SHORT).show();
    }

    private void yeni_ziyaretci_ekle(){
        String mevcut_str = (String) tv_ziyaretci.getText();
        int mevcut = Integer.parseInt(mevcut_str);
        int yeni = mevcut + 1 ; // mevcut = mevcut + 1
        ziyaretci_sayisi_sakla(yeni);
        String yeni_str = Integer.toString(yeni);
        tv_ziyaretci.setText(yeni_str);
    }

    private void handle_giden_ziyeretci() {
        String mevcut_str = (String) tv_ziyaretci.getText();
        int mevcut = Integer.parseInt(mevcut_str);
        int yeni = mevcut - 1 ; // mevcut = mevcut - 1
        if(yeni < 0) {return;}
        ziyaretci_sayisi_sakla(yeni);
        String yeni_str = Integer.toString(yeni);
        tv_ziyaretci.setText(yeni_str);
    }

    private int ziyaretci_sayisini_oku(){
        SharedPreferences sharedPref = getPreferences(Context.MODE_PRIVATE);
        int ziyaretci_sayisi = sharedPref.getInt("ZIYARETCI_SAYISI", 0);
        return ziyaretci_sayisi;
    }

    private void ziyaretci_sayisi_sakla(int ziyaretci_sayisi){
        SharedPreferences sharedPref = getPreferences(Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPref.edit();
        editor.putInt("ZIYARETCI_SAYISI", ziyaretci_sayisi);
        editor.apply();
        //Toast.makeText(this,"Değer saklandı.",Toast.LENGTH_SHORT).show();
    }



}
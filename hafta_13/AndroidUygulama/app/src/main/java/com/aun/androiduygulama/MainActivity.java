package com.aun.androiduygulama;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import org.json.JSONException;
import org.json.JSONObject;

public class MainActivity extends AppCompatActivity {
    TextView tv_baslik, tv_metin, tv_sicaklik, tv_hava_durumu;
    ImageView img_hava_durumu;
    String sehir = "Istanbul,tr";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //değişken ve layout id atamaları
        tv_baslik = findViewById(R.id.baslik);
        tv_metin = findViewById(R.id.metin);
        img_hava_durumu = findViewById(R.id.img_hava_durumu);
        tv_sicaklik = findViewById(R.id.sicaklik);
        tv_hava_durumu = findViewById(R.id.hava_durumu);

        //Olay atamaları
        tv_baslik.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mesaj_goster("Sayfa başlığına tıklandı", false);
            }
        });

        tv_metin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mesaj_goster("Metin kısmına tıklandı", true);
            }
        });

        img_hava_durumu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                hava_durumunu_getir();
                mesaj_goster("Hava durumu resmine tıklandı", true);
            }
        });

        //Toast
        Toast.makeText(this, "Yaşam Döngüsü Evresi: onCreate",Toast.LENGTH_LONG).show();

        hava_durumunu_getir();
    }

    private void hava_durumunu_getir(){
        GetHavaDurumu ghd = new GetHavaDurumu();
        ghd.execute(sehir,this);
    }

    public void goster_hava_durumu(String resp) throws JSONException {
        Log.d("resp",resp);
        JSONObject json = new JSONObject(resp);
        double temp = json.getJSONObject("main").getDouble("temp")-273;

        tv_sicaklik.setText(String.format("%.1f",temp));
    }

    private void mesaj_goster(String mesaj, boolean kisa_sureli){
        int sure = Toast.LENGTH_LONG;
        if(kisa_sureli){sure = Toast.LENGTH_SHORT;}
        Toast.makeText(this, mesaj, sure).show();
    }

    @Override
    protected void onStart() {
        super.onStart();
        Toast.makeText(this, "Yaşam Döngüsü Evresi: onStart",Toast.LENGTH_LONG).show();
    }


    @Override
    protected void onPostResume() {
        super.onPostResume();
        Toast.makeText(this, "Yaşam Döngüsü Evresi: onPostResume",Toast.LENGTH_LONG).show();
        hava_durumunu_getir();
    }

    @Override
    protected void onPause() {
        super.onPause();
        Toast.makeText(this, "Yaşam Döngüsü Evresi: onPause",Toast.LENGTH_LONG).show();
    }

    @Override
    protected void onStop() {
        super.onStop();
        Toast.makeText(this, "Yaşam Döngüsü Evresi: onStop",Toast.LENGTH_LONG).show();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        Toast.makeText(this, "Yaşam Döngüsü Evresi: onDestroy",Toast.LENGTH_LONG).show();

    }
}
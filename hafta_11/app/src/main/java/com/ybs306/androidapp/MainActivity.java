package com.ybs306.androidapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.Button;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    Button buton1, buton2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        buton1 = findViewById(R.id.buton1);
        buton2 = findViewById(R.id.buton2);

        buton1.setOnClickListener(v -> {
            sayfa_1_goster();
        });

        buton2.setOnClickListener(v -> {
            sayfa_2_goster();
        });

        //dataTypes();
        //loops();
        //java_oop();//Object Oriented Programming - Nesneye Yönelik - Nesne Tabanlı
        java_listeler();
        int topla = toplam(3,5); // 8
        kosulKontrol(25); // Abone olabilir.
    }

    private void sayfa_1_goster() {
        Intent intent = new Intent(this,MainActivity2.class);
        startActivity(intent);
    }

    private void sayfa_2_goster() {
        Intent intent = new Intent(this,MainActivity3.class);
        startActivity(intent);
    }


    private void kosulKontrol(int a){
        if(a > 18){
            Log.d("abone","Abone olabilir");
        }else{
            Log.d("abone","Abone olamaz");
        }
    }

    private int toplam(int a, int b){
        return a + b;
    }

    private void java_listeler() {
        List<String> isimListesi = new ArrayList<>();
        List<Arac> aracListeler = new ArrayList<>();

        isimListesi.add("Burak");
        isimListesi.add("Tayfur");

        aracListeler.add(new Arac("Mercedes","E190",2020,2000));
        aracListeler.add(new Arac("BMW","1.16",2019,4000));

        //liste
        for(int i=0;i<aracListeler.size();i++){
            Arac arac = aracListeler.get(i);
            arac.setSahibiKim(isimListesi.get(i));
            Log.d("arac liste",arac.getFirmaAd());
        }

        //for:
        for(Arac arac:aracListeler){
            Log.d("arac liste",arac.getFirmaAd());
        }
    }

    private void java_oop(){
        Arac arac = new Arac(); // Arac adlı Class'tan arac adlı nesne türetildi.
        arac.firmaAd = "Nissan";
        arac.model = "Juke";
        arac.km = 10000;
        arac.sene = 2022;
        arac.setSahibiKim("Tayfur");
        Log.d("arac",arac.firmaAd+" "+arac.model);

        Arac arac2 = new Arac("Ford","Puma",2022,1000);
        Log.d("arac2",arac2.firmaAd+" "+arac2.model);
        Log.d("arac2",arac2.getFirmaAd()+" "+arac2.getModel());
        arac2.setSahibiKim("Burak");
        Log.d("araç sahipleri",arac.getSahibiKim()+" ve "+arac2.getSahibiKim());

        TrendyolExpress trendyolExpress = new TrendyolExpress();
        trendyolExpress.setFirmaAd("Fiat"); // Superclass'tan gelen metod
        trendyolExpress.setPaketSay(5);
        Log.d("paket",trendyolExpress.getFirmaAd()+" "+trendyolExpress.getPaketSay());



    }

    private void loops(){
        for(int i=1;i<=10;i++){
            if(i==2){continue;} // i=2 için for kod bloğunun devamı çalıştırılmaz.
            if (i == 5){break;} // i=5 olduğunda for loop'tan çıkılır.
            Log.d("rakam",Integer.toString(i));

        }

        String[] meyveler = {"elma","portakal","armut"};
        for(int i=0;i<meyveler.length;i++){
            Log.d("meyve",meyveler[i]);
        }

        int j = 0;
        while(j < meyveler.length){
            Log.d("meyve",meyveler[j]);
            j++;
        }

        int k = 0;
        do{
            Log.d("meyve",meyveler[k]);
            k++;
        }while(k < meyveler.length);

        // foreach benzeri yöntem
        for(String meyve:meyveler){
            Log.d("meyve",meyve);
        }

    }

    private void dataTypes(){
        int a = 1; // Tam sayı, integer
        String mesaj = "String mesajı";
        boolean trueFalse = true;
        char karakter = 'A';
        float sayiOndalik = 1.23f; // Ondalık sayı
        int[] rakamlar = {1,2,3,4,5};
        String[] isimler = {"Burak","Tayfur"};
        // Array'ler immutable'dır, yeni eleman eklenemez/çıkarılamaz.
        isimler[0] = "Burak Töremiş"; // {"Burak Töremiş","Tayfur"}
        Log.d("isim",isimler[1]); // Tayfur
    };

}



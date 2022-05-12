package com.ybs306.listeactivity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        setTitle("Şehir Listesi"); // Uygulama üst başlık metnini değiştirir.

        String[] sehirler = {"İstanbul","Ankara","İzmir","Antalya","Adana","Hakkari","Eskişehir","Zonguldak","Tekirdağ","Yalova","Adıyaman","Gaziantep","Manisa","Balıkesir","Hatay"};

        ArrayAdapter adapter = new ArrayAdapter<>(this,R.layout.list_item,sehirler);

        ListView listView = (ListView) findViewById(R.id.liste);
        listView.setAdapter(adapter);

        listView.setOnItemClickListener((adapterView, view, i, l) -> {
            String secilenSehir = (String) sehirler[i];
            Toast.makeText(getBaseContext(),secilenSehir,Toast.LENGTH_SHORT).show();
            goster_sehir_activity(secilenSehir);
        });
    }

    private void goster_sehir_activity(String sehir){
        Intent intent = new Intent(this,SehirActivity.class);
        intent.putExtra("SEHIR_AD",sehir);
        startActivity(intent);
    }
}
package com.ybs306.muzeeserleri;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.util.ArrayList;

public class Eserler extends AppCompatActivity {

    ListView liste;
    ArrayList<String> eserler = new ArrayList<>();
    ArrayAdapter<String> adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_eserler);
        //
        liste = findViewById(R.id.listview);

        //item click listener
        liste.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                listede_eser_secildi(position);
            }
        });

        //Adapter
        adapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1,eserler);

        //Mevcut eserler
        eserler.add("Büyük İskender heykeli");
        eserler.add("Apollon heykeli");
        eserler.add("Okeanos heykeli");
        eserler.add("Hermes");
        eserler.add("Afrodit heykeli");
        eserler.add("Arkaik tapınak alınlığı");
        eserler.add("Siloam yazıt");
        eserler.add("Dua eden Meryem Ana rölyefi");
        eserler.add("İslami seramikler");
        eserler.add("Mezopotamya heykelleri");
        eserler.add("Mari Valisi Puzur Ishtar");
        eserler.add("Roma İmparatoru II. Valentinian'ın Heykeli");
        eserler.add("Babil Kabartmaları");
        eserler.add("Palmira Kabartmaları");
        eserler.add("Marco Aurélio'nun Büstü");
        eserler.add("Babil'deki İştar Kapısı'ndan bir panel");

        liste.setAdapter(adapter);
    }

    private void listede_eser_secildi(int index){
        Intent intent = new Intent(this, EserActivity.class);
        intent.putExtra("ESER_INDEX", index);
        intent.putExtra("ESER_AD", eserler.get(index));
        startActivity(intent);
    }
}
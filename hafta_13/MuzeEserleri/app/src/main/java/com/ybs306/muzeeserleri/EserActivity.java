package com.ybs306.muzeeserleri;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

public class EserActivity extends AppCompatActivity {

    TextView tv_eserAd, tv_eserBilgi;
    ImageView img_eser;

    int seciliEserIndex = 0;
    String seciliEserAd = "";



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_eser);
        //
        Intent intent = getIntent();
        seciliEserIndex = intent.getIntExtra("ESER_INDEX",0);
        seciliEserAd = intent.getStringExtra("ESER_AD");
        //
        tv_eserAd = findViewById(R.id.eserAd);
        tv_eserBilgi = findViewById(R.id.eserBilgi);
        img_eser = findViewById(R.id.eserResim);
        //
        tv_eserAd.setText(seciliEserAd);
        hangi_resim_dosyasi();
        tv_eserBilgi.setText(eser_bilgi());
    }

    private void hangi_resim_dosyasi(){
        switch (seciliEserIndex){
            case 0: img_eser.setImageResource(R.drawable.iskender);break;
            case 2: img_eser.setImageResource(R.drawable.okeanos);break;
        }
    }

    private String eser_bilgi(){
        switch (seciliEserIndex){
            case 0: return "Büyük İskender heykeli hakkında bilgi ...";
            case 2: return "Okeanos heykeli hakkında bilgi ...";
            default: return "Eser hakkında bilgi ...";
        }
    }

}
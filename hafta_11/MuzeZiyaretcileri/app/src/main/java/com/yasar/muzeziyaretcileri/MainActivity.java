package com.yasar.muzeziyaretcileri;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.snackbar.Snackbar;

public class MainActivity extends AppCompatActivity {
    Button btn_yeni, btn_giden;
    TextView tv_mevcut, tv_gunluk, tv_limit;
    LinearLayout ll_limitler;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btn_yeni = findViewById(R.id.yeni_ziyaretci);
        btn_giden = findViewById(R.id.giden_ziyaretci);

        tv_mevcut = findViewById(R.id.ziyaretci_say);
        tv_gunluk = findViewById(R.id.say_gunluk_ziyaretci);
        tv_limit = findViewById(R.id.limit_rakam);

        ll_limitler = findViewById(R.id.limitler);

        btn_yeni.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                yeni_ziyaretci();
            }
        });

        btn_giden.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                giden_ziyaretci();
            }
        });
    }

    private void giden_ziyaretci() {
        //Toast.makeText(this, "Giden ziyaretçi",Toast.LENGTH_LONG).show();
        guncelle_ziyaretci_sayisi(-1);
    }

    private void yeni_ziyaretci(){
        //Toast.makeText(this,"Yeni ziyaretçi",Toast.LENGTH_LONG).show();
        guncelle_ziyaretci_sayisi(1);
    }

    private void guncelle_ziyaretci_sayisi(int rakam){
        String mevcut_ziyaretci = (String)tv_mevcut.getText();
        String gunluk_ziyaretci = (String)tv_gunluk.getText();
        String limit = (String) tv_limit.getText();
        //Log.d("Mevcut ziyaretçi",mevcut_ziyaretci);
        //Log.d("Günlük Ziyaretçi",gunluk_ziyaretci);
        //Log.d("limit",limit);
        int yeni_mevcut = Integer.parseInt(mevcut_ziyaretci) + rakam;
        if(yeni_mevcut > Integer.parseInt(limit)){
            ll_limitler.setVisibility(View.VISIBLE);
            Toast.makeText(this,"Günlük ziyaretçi limit aşıldı.",Toast.LENGTH_LONG).show();
            return;
        }else{
            ll_limitler.setVisibility(View.GONE);
        }
        if(yeni_mevcut < 0){return;}
        tv_mevcut.setText(Integer.toString(yeni_mevcut));
        if(rakam == 1){
            int yeni_gunluk = Integer.parseInt(gunluk_ziyaretci) + 1;
            tv_gunluk.setText(yeni_gunluk + "");
        }
    }
}
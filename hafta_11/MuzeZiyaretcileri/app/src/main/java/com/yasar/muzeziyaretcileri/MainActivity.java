package com.yasar.muzeziyaretcileri;

import androidx.appcompat.app.AppCompatActivity;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.text.Editable;
import android.text.InputType;
import android.util.Log;
import android.view.ContextMenu;
import android.view.ContextThemeWrapper;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    Button btn_yeni, btn_giden;
    TextView tv_mevcut, tv_gunluk, tv_limit;
    LinearLayout ll_limitler;

    SharedPreferences sharedPreferences;
    public static final String muzePref = "MuzeZiyaretcileri";

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

    @Override
    protected void onPostResume() {
        super.onPostResume();
        sharedPreferences = getSharedPreferences(muzePref, Context.MODE_PRIVATE);

        ziyareciRakamlariniOku();

        //Kalıcı verilerin shared preferences ile kayıt edilmesi
        preferencesYeniString("KALICI_ISIM", "Yaşar Küçükefe");
        preferencesYeniString("KALICI_SEHIR", "İstanbul");
        //Değerlerin okunması
        String isim = sharedPreferences.getString("KALICI_ISIM","?");
        String sehir = sharedPreferences.getString("KALICI_SEHIR", "?");
        String plakaKodu = sharedPreferences.getString("PLAKA_KODU","?");
        //
        Log.d("KALICI_ISIM",isim);
        Log.d("KALICI_SEHIR",sehir);
        Log.d("PLAKA_KODU",plakaKodu);

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu_app, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.reset:
                onay_rakamlariSifirla();
                return true;
            case R.id.setGunluk:
                dialog_setGunlukZiyaretciSayisi();
                return true;
            case R.id.setLimit:
                dialog_setZiyaretciLimit();
                return true;
            case R.id.about:
                dialog_about_app();
                return true;
            default:
                return super.onContextItemSelected(item);
        }
    }

    private void dialog_about_app() {
        AlertDialog.Builder builder = new AlertDialog.Builder(new ContextThemeWrapper(this,android.R.style.Theme_Holo_Dialog));
        AlertDialog dialog;
        builder.setTitle("Müze Ziyaretçileri");
        builder.setMessage("Altınbaş Üniversitesi YBS-306 2021-v1");
        builder.setPositiveButton(getResources().getString(R.string.ok), new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int whichButton) {
                rakamlariSifirla();
            }
        });
        dialog=builder.create();
        dialog.show();
    }

    private void dialog_setGunlukZiyaretciSayisi(){
        AlertDialog.Builder builder = new AlertDialog.Builder(new ContextThemeWrapper(this,android.R.style.Theme_Holo_Light));
        AlertDialog dialog;
        builder.setTitle("Günlük Ziyaretçi Sayısı");
        builder.setMessage("Yeni rakam:");
        final EditText input=new EditText(this);
        input.setHint("ziyaretçi sayısı");
        input.setText("0");
        input.setInputType(InputType.TYPE_CLASS_NUMBER);
        input.requestFocus();
        builder.setView(input);

        builder.setPositiveButton(getResources().getString(R.string.tamam), new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                String deger = input.getText().toString();
                int yeni = Integer.parseInt(deger);
                gunlukZiyaretciYeniRakam(yeni);
            }
        });

        builder.setNegativeButton(getResources().getString(R.string.iptal), (dialog1, which) -> {});
        dialog=builder.create();
        dialog.getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_VISIBLE);
        dialog.show();

    }

    private void dialog_setZiyaretciLimit(){
        AlertDialog.Builder builder = new AlertDialog.Builder(new ContextThemeWrapper(this,android.R.style.Theme_Holo_Light));
        AlertDialog dialog;
        builder.setTitle("Ziyaretçi Limiti");
        builder.setMessage("Yeni limit:");
        final EditText input=new EditText(this);
        input.setHint("ziyaretçi limiti");
        int mevcutLimit = getZiyaretciLimit();
        input.setText(Integer.toString(mevcutLimit));
        input.setInputType(InputType.TYPE_CLASS_NUMBER);
        input.requestFocus();
        builder.setView(input);

        builder.setPositiveButton(getResources().getString(R.string.tamam), new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                String deger = input.getText().toString();
                int yeni = Integer.parseInt(deger);
                setZiyaretciLimiti(yeni);
            }
        });

        builder.setNegativeButton(getResources().getString(R.string.iptal), (dialog1, which) -> {});
        dialog=builder.create();
        dialog.getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_VISIBLE);
        dialog.show();
    }

    private void gunlukZiyaretciYeniRakam (int yeni){
        if(yeni < 0){return;}
        int mevcut = sharedPreferences.getInt("MEVCUT_ZIYARETCI_SAYISI",0);
        if(yeni < mevcut){return;}
        ziyaretciSaySakla("GUNLUK_ZIYARETCI_SAYISI", yeni);
        ziyareciRakamlariniOku();
    }

    private void onay_rakamlariSifirla(){
        AlertDialog.Builder builder = new AlertDialog.Builder(new ContextThemeWrapper(this,android.R.style.Theme_Holo_Dialog));
        AlertDialog dialog;
        builder.setTitle("Reset");
        builder.setMessage("Ziyaretçi sayılarını sıfırla");
        builder.setPositiveButton(getResources().getString(R.string.sifirla), new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int whichButton) {
                rakamlariSifirla();
            }
        });

        builder.setNegativeButton(getResources().getString(R.string.iptal), (dialog1, which) -> {}); //iptal, her hangi bir kod eklemeye gerek yok. Dialog pencersi otomatik olarak kapanacak.
        dialog=builder.create();
        dialog.show();
    }

    private int getZiyaretciLimit(){
        return sharedPreferences.getInt("ZIYARETCI_LIMITI",10);
    }

    private void setZiyaretciLimiti(int yeni){
        if(yeni<1){return;}
        ziyaretciSaySakla("ZIYARETCI_LIMITI",yeni);
        tv_limit.setText(Integer.toString(yeni));
    }

    private void rakamlariSifirla() {
        ziyaretciSaySakla("MEVCUT_ZIYARETCI_SAYISI",0);
        ziyaretciSaySakla("GUNLUK_ZIYARETCI_SAYISI", 0);
        ziyareciRakamlariniOku();
    }

    private void ziyareciRakamlariniOku() {
        int mevcutZiyaretci = sharedPreferences.getInt("MEVCUT_ZIYARETCI_SAYISI",0);
        int gunlukZiyaretci = sharedPreferences.getInt("GUNLUK_ZIYARETCI_SAYISI",0);
        tv_mevcut.setText(Integer.toString(mevcutZiyaretci));
        tv_gunluk.setText(Integer.toString(gunlukZiyaretci));
    }

    private void preferencesYeniString(String anahtar,String yeniDeger){
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putString(anahtar,yeniDeger);
        editor.commit();
    }

    private void ziyaretciSaySakla(String anahtar, int yeniRakam){
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putInt(anahtar,yeniRakam);
        editor.commit();
    }



    private void giden_ziyaretci() {
        //Toast.makeText(this, "Giden ziyaretçi",Toast.LENGTH_LONG).show();
        guncelle_ziyaretci_sayisi(-1);
    }

    private void yeni_ziyaretci(){
        //Toast.makeText(this,"Yeni ziyaretçi",Toast.LENGTH_LONG).show();
        guncelle_ziyaretci_sayisi(1);
    }

    private void guncelle_ziyaretci_sayisi(int rakam) {
        String mevcut_ziyaretci = (String) tv_mevcut.getText();
        String gunluk_ziyaretci = (String) tv_gunluk.getText();
        int limit = getZiyaretciLimit();
        //Log.d("Mevcut ziyaretçi",mevcut_ziyaretci);
        //Log.d("Günlük Ziyaretçi",gunluk_ziyaretci);
        //Log.d("limit",limit);
        int yeni_mevcut = Integer.parseInt(mevcut_ziyaretci) + rakam;
        if (yeni_mevcut > limit) {
            ll_limitler.setVisibility(View.VISIBLE);
            Toast.makeText(this, "Günlük ziyaretçi limit aşıldı.", Toast.LENGTH_LONG).show();
            return;
        } else {
            ll_limitler.setVisibility(View.GONE);
        }
        if (yeni_mevcut < 0) {
            return;
        }
        tv_mevcut.setText(Integer.toString(yeni_mevcut));
        ziyaretciSaySakla("MEVCUT_ZIYARETCI_SAYISI", yeni_mevcut);
        if (rakam == 1) {
            int yeni_gunluk = Integer.parseInt(gunluk_ziyaretci) + 1;
            tv_gunluk.setText(yeni_gunluk + "");
            ziyaretciSaySakla("GUNLUK_ZIYARETCI_SAYISI", yeni_gunluk);
        }
    }
}
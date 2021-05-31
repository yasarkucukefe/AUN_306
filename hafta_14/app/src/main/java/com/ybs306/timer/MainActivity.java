package com.ybs306.timer;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {
    Button btn_eylem, btn_60, btn_120, btn_180;
    LinearLayout ll_sureler;
    TextView tv_sure;

    boolean timer_on = false;
    int kalan_sure = 120;
    int set_sure = 120;

    //Handler
    Handler timerHandler = new Handler();
    Runnable timerRunnable = new Runnable() {
        @Override
        public void run() {
            //Log.d("timer","thick");
            kalan_sure--;
            if(kalan_sure>0){timerHandler.postDelayed(this, 1000);}
            gosterKalanSure();
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //
        btn_eylem = findViewById(R.id.button);
        tv_sure = findViewById(R.id.timer);
        btn_60 = findViewById(R.id.btn_sure_60);
        btn_120 = findViewById(R.id.btn_sure_120);
        btn_180 = findViewById(R.id.btn_sure_180);
        ll_sureler = findViewById(R.id.sureler);

        gosterKalanSure();
        btn_eylem.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                timer_on = !timer_on;
                handle_button_click();
            }
        });

        btn_60.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                kalan_sure = 60;
                set_sure = 60;
                gosterKalanSure();
            }
        });

        btn_120.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                kalan_sure = 120;
                set_sure = 120;
                gosterKalanSure();
            }
        });

        btn_180.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                kalan_sure = 180;
                set_sure = 180;
                gosterKalanSure();
            }
        });
    }

    private void gosterKalanSure(){
        int dakika = kalan_sure / 60;
        int saniye = kalan_sure % 60;
        String str_sure = String.format("%d:%02d",dakika,saniye);
        tv_sure.setText(str_sure);
    }

    private void timer_on(){
        btn_eylem.setText("DURDUR");
        kalan_sure = set_sure;
        ll_sureler.setVisibility(View.GONE);
        timerHandler.postDelayed(timerRunnable,0);
        gosterKalanSure();
    }

    private void timer_off(){
        timerHandler.removeCallbacks(timerRunnable);
        kalan_sure = set_sure;
        ll_sureler.setVisibility(View.VISIBLE);
        btn_eylem.setText("BAŞLA");
        gosterKalanSure();
    }

    private void handle_button_click(){
        if(timer_on){//Zamanlayıcıyı başlat
            timer_on();
        }else{//Zamanlayıcıyı durdur
            timer_off();
        }
    }

    @Override
    public void onPause(){
        super.onPause();
        timer_off();
    }
}
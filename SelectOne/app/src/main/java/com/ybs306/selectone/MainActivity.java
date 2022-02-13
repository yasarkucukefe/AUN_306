package com.ybs306.selectone;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Build;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.Display;
import android.view.ViewGroup;

import java.lang.reflect.Method;
import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    CustomAdapter adapter;
    RecyclerView recyclerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        recyclerView = findViewById(R.id.liste);



        String[] secItems = {"A","B","C","D","E","F","G","H","I","J","K","L","M"};
        adapter = new CustomAdapter(secItems);
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        get_location();
    }

    private void get_location(){
        recyclerView.post(()->{
            //Get screen size
            Display display = this.getWindowManager().getDefaultDisplay();
            int realWidth;
            int realHeight;

            if (Build.VERSION.SDK_INT >= 17){
                //new pleasant way to get real metrics
                DisplayMetrics realMetrics = new DisplayMetrics();
                display.getRealMetrics(realMetrics);
                realWidth = realMetrics.widthPixels;
                realHeight = realMetrics.heightPixels;

            } else if (Build.VERSION.SDK_INT >= 14) {
                //reflection for this weird in-between time
                try {
                    Method mGetRawH = Display.class.getMethod("getRawHeight");
                    Method mGetRawW = Display.class.getMethod("getRawWidth");
                    realWidth = (Integer) mGetRawW.invoke(display);
                    realHeight = (Integer) mGetRawH.invoke(display);
                } catch (Exception e) {
                    //this may not be 100% accurate, but it's all we've got
                    realWidth = display.getWidth();
                    realHeight = display.getHeight();
                    Log.e("Display Info", "Couldn't use reflection to get the real display metrics.");
                }

            } else {
                //This should be close, as lower API devices should not have window navigation bars
                realWidth = display.getWidth();
                realHeight = display.getHeight();
            }

            //location
            int[] location = new int[2];
            recyclerView.getLocationOnScreen(location);
            Log.d("loc",location[0]+" and " + location[1]);
            // new height
            int newHeight = realHeight - location[1] - 150;
            ViewGroup.LayoutParams params=recyclerView.getLayoutParams();
            params.height=newHeight;
            recyclerView.setLayoutParams(params);
        });

    }

}
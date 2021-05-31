package com.aun.androiduygulama;

import android.os.AsyncTask;
import android.util.Log;

import org.json.JSONException;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.lang.ref.WeakReference;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

public class GetHavaDurumu extends AsyncTask<Object, Boolean, String> {
    private WeakReference<MainActivity> contextRef;

    @Override
    protected String doInBackground(Object... params) {
        String sehir = (String) params[0];
        contextRef = new WeakReference<>((MainActivity) params[1]);

        String resp = "Bağlantı hatası";
        try {
            URL url = new URL("https://api.openweathermap.org/data/2.5/weather?q=Istanbul,tr&appid=4c3c5151b609f1f31e2dc18d9474dec6");
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            conn.setReadTimeout(5000);
            conn.setConnectTimeout(5000);
            conn.setRequestMethod("GET");
            conn.setDoInput(true);
            conn.setDoOutput(true);
            int respCode = conn.getResponseCode();
            Log.d("code",respCode+"");
            if(respCode == HttpURLConnection.HTTP_OK){
                String str;
                StringBuilder sb = new StringBuilder();
                BufferedReader br = new BufferedReader(new InputStreamReader(conn.getInputStream()));
                while ((str=br.readLine()) != null){
                    sb.append(str);
                }
                resp = sb.toString();
            }
        }catch (MalformedURLException e) {
            e.printStackTrace();
            return "URL hatası";
        } catch (IOException e) {
            Log.d("hata",e.toString());
            e.printStackTrace();
        }
        return resp;
    }

    @Override
    protected void onPostExecute(String resp){
        super.onPostExecute(resp);
        try {
            contextRef.get().goster_hava_durumu(resp);
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }
}

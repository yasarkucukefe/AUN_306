package com.ybs306.myapplication;

import android.os.AsyncTask;
import android.util.Log;

import org.chromium.net.CronetException;
import org.chromium.net.UrlRequest;
import org.chromium.net.UrlResponseInfo;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.lang.ref.WeakReference;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.nio.ByteBuffer;
import java.nio.charset.StandardCharsets;
import java.util.HashMap;

public class UrlData extends AsyncTask<Object,Boolean,String> {

    private WeakReference<MainActivity2> contextRef;

    @Override
    protected String doInBackground(Object... params) {

        String url_adres = (String) params[0];
        contextRef =  new WeakReference<>((MainActivity2) params[1]);

        String resp;

        try {
            Log.d("adres",url_adres);
            URL url = new URL(url_adres);
            try {
                HttpURLConnection conn=(HttpURLConnection) url.openConnection();
                conn.setReadTimeout(120000);
                conn.setConnectTimeout(5000);
                conn.setDoInput(true);
                conn.setDoOutput(true);
                conn.setRequestMethod("GET");


                int responseCode=conn.getResponseCode();


                if (responseCode == HttpURLConnection.HTTP_OK) {
                    String line;
                    StringBuilder sb = new StringBuilder();
                    BufferedReader br=new BufferedReader(new InputStreamReader(conn.getInputStream()));
                    while ((line=br.readLine()) != null) {
                        sb.append(line);
                    }
                    resp=sb.toString();
                }
                else {
                    resp="n_c";
                }

            } catch (IOException e) {
                e.printStackTrace();
                return "n_c_io_exception";
            }
        } catch (MalformedURLException e) {
            e.printStackTrace();
            return "n_c_malformed";
        }

        return resp;
    }

    @Override
    protected void onPostExecute(String response){
        super.onPostExecute(response);
        Log.d("response",response);

        contextRef.get().response_received(response.trim());
    }
}

package com.example.masa_corporal.Escuchadores;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.AsyncTask;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import com.example.masa_corporal.Actividades.Main2Activity;
import com.example.masa_corporal.Actividades.MainActivity;

import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.URL;

public class Listener_calcular implements View.OnClickListener {
    private MainActivity context;
    private String peso,altura,edad;
    private Long genero;

    public Listener_calcular(MainActivity context) {
        this.context = context;
    }

    @Override
    public void onClick(View v) {
        Toast.makeText(context,"Calculando...",Toast.LENGTH_SHORT).show();
        peso=context.getPeso().getText().toString();
        altura=context.getAltura().getText().toString();
        genero=context.getGenero().getSelectedItemId();
        edad=context.getEdad().getText().toString();

        //Creo un objeto jsonMasa
        new jsonMasa(peso, altura, genero, edad).execute("https://bmi.p.mashape.com/");
        //Intent cambio de Activity
        Intent intent = new Intent(context, Main2Activity.class);
        context.startActivity(intent);
    }

    private class jsonMasa extends AsyncTask<String,Void,String> {
        String dataJson ="";
        jsonMasa(String peso,String altura,Long genero,String edad){
            String generoJson="";
            if (genero==1){
                generoJson="m";
            }
            if (genero==2){
                generoJson="f";
            }
            dataJson = "{ \"weight\": { \"value\": \""+peso+"\", \"unit\": \"kg\" },"+
                    " \"height\": { \"value\":\""+altura+"\", \"unit\": \"cm\" },"+
                    " \"sex\": \""+generoJson+"\", \"age\":\""+edad+"\","+
                    "\"waist\": \"34.00\", \"hip\": \"40.00\" }\n";
        }

        @Override
        protected String doInBackground(String... urls) {
            InputStream inputStream = null;
            String result = "";
            HttpURLConnection httpcon;

            try {
                //Connect
                httpcon = (HttpURLConnection) ((new URL(urls[0]).openConnection()));
                httpcon.setDoOutput(true);
                httpcon.setRequestProperty("X-Mashape-Key", "hRtSku8fDqmsh7SAIsKMaGABTE9up1iXrzjjsnJUbVJImKtDfF");
                httpcon.setRequestProperty("Content-Type", "application/json");
                httpcon.setRequestProperty("Accept", "application/json");
                httpcon.setRequestMethod("POST");
                httpcon.connect();

                //Write
                OutputStream os = httpcon.getOutputStream();
                BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(os, "UTF-8"));
                writer.write(dataJson);
                writer.close();
                os.close();

                //Read
                BufferedReader br = new BufferedReader(new InputStreamReader(httpcon.getInputStream(),"UTF-8"));

                String line = null;
                StringBuilder sb = new StringBuilder();

                while ((line = br.readLine()) != null) {
                    sb.append(line);
                }

                br.close();
                result = sb.toString();

            } catch (Exception e) {
                // ERROR;
                Log.d("InputStream", e.getLocalizedMessage());
            }
            return result;
        }

        @Override
        protected void onPostExecute(String s) {

            try{
                JSONObject jsonObject = new JSONObject(s);
                JSONObject jsonBmi=jsonObject.getJSONObject("bmi");

                String valueJason =jsonBmi.getString("value");
                String statusJason= jsonBmi.getString("status");
                String ideal_weightJason =jsonObject.getString("ideal_weight");
                String riskJason= jsonBmi.getString("risk");

                /*<<<<Paso los valores obtenidos por el sharedPreference>>>>>*/
                SharedPreferences shared = getSharedPreferences("valores",Context.MODE_PRIVATE);

                SharedPreferences.Editor editor = shared.edit();

                editor.putString("value",valueJason);
                editor.putString("status",statusJason);
                editor.putString("ideal_weight",ideal_weightJason);
                editor.putString("risk",riskJason);

                editor.commit();


            } catch (Exception e) {

                // Cualquier problema en la lectura del JSON, se ir� por este camino.
                Toast.makeText(context,"Hubo un problema", Toast.LENGTH_LONG).show();
            }

        }
    }
}
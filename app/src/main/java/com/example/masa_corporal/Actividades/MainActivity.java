package com.example.masa_corporal.Actividades;

import android.content.Context;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;

import com.example.masa_corporal.Escuchadores.Listener_calcular;
import com.example.masa_corporal.R;

public class MainActivity extends AppCompatActivity {
    private EditText edad,altura,peso;
    private Spinner genero;
    private Button calcular;

    public EditText getEdad() {
        return edad;
    }

    public EditText getAltura() {
        return altura;
    }

    public EditText getPeso() {
        return peso;
    }

    public Spinner getGenero() {
        return genero;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //Vinculo
        peso=findViewById(R.id.Id_Peso);
        altura=findViewById(R.id.Id_Altura);
        genero=findViewById(R.id.genero);
        edad=findViewById(R.id.Id_Edad);
        calcular=findViewById(R.id.Id_calcular);

        //Listener
        Listener_calcular listCalcular=new Listener_calcular(this);
        calcular.setOnClickListener(listCalcular);

        /*SharedPreferences preferences=getSharedPreferences("valores", Context.MODE_PRIVATE);
        //Shared
        SharedPreferences.Editor editor=preferences.edit();
        editor.putString("peso", String.valueOf(peso));
        editor.putString("altura", String.valueOf(altura));
        Long genero= getGenero().getSelectedItemId();
        editor.putLong("genero",genero);
        editor.putString("edad", String.valueOf(edad));
        editor.commit();*/




    }
}

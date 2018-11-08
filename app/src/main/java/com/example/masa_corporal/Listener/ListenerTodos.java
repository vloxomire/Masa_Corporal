package com.example.masa_corporal.Listener;

import android.content.Context;
import android.content.Intent;
import android.view.View;

import com.example.masa_corporal.Actividades.lista;

public class ListenerTodos implements View.OnClickListener {
    private Context context;

    public ListenerTodos(Context context) {
        this.context = context;
    }

    @Override
    public void onClick(View view) {
        Intent listaIntent = new Intent(context ,lista.class);
        context.startActivity(listaIntent);
    }
}

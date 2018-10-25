package com.example.masa_corporal.SharedPreference;

import android.content.Context;
import android.content.SharedPreferences;

public class Shared {
    public static void setSharedPrefenceVariables(Context context,String valor){
        SharedPreferences shared =context.getSharedPreferences("miShared",Context.MODE_PRIVATE);
        SharedPreferences.Editor editor= shared.edit();
        editor.putString("x",valor);
        editor.commit();
    }
}

package com.example.masa_corporal;

import android.appwidget.AppWidgetManager;
import android.appwidget.AppWidgetProvider;
import android.content.ComponentName;
import android.content.Context;
import android.widget.RemoteViews;

public class NewAppWidget extends AppWidgetProvider {


    @Override
    public void onUpdate(Context context, AppWidgetManager appWidgetManager, int[] appWidgetIds) {

        RemoteViews remote= new RemoteViews(context.getPackageName(),R.layout.new_app_widget);
        ComponentName thisWidget  = new ComponentName(context, NewAppWidget.class);

        appWidgetManager.updateAppWidget(thisWidget, remote);

    }

    @Override
    public void onDeleted(Context context, int[] appWidgetIds) {

    }

    @Override
    public void onEnabled(Context context) {

    }

    @Override
    public void onDisabled(Context context) {

    }
}


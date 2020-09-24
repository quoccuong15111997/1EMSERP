package com.firstems.erp.helper.toast;

import android.app.Activity;
import android.content.Context;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.cardview.widget.CardView;

import com.firstems.erp.R;

/**
 * Created by Nguyen Quoc Cuong on 7/30/2020.
 */
public class ToastHelper {
    private static ToastHelper instance;
    private static Activity context;
    private ToastHelper() {
    }

    public static ToastHelper getInstance() {
        if (instance==null){
            instance =  new ToastHelper();
        }
        return instance;
    }
    public static void init(Activity ct){
        context = ct;
    }
    public void toastIconError(String message, int time) {
        Toast toast = new Toast(context);
        toast.setDuration(time);

        //inflate view
        View custom_view = context.getLayoutInflater().inflate(R.layout.toast_icon_text, null);
        ((TextView) custom_view.findViewById(R.id.message)).setText(message);
        ((ImageView) custom_view.findViewById(R.id.icon)).setImageResource(R.drawable.ic_close_white);
        ((CardView) custom_view.findViewById(R.id.parent_view)).setCardBackgroundColor(context.getResources().getColor(R.color.red_600));

        toast.setView(custom_view);
        toast.show();
    }

    public void toastIconSuccess(String message, int time) {
        Toast toast = new Toast(context);
        toast.setDuration(time);

        //inflate view
        View custom_view = context.getLayoutInflater().inflate(R.layout.toast_icon_text, null);
        ((TextView) custom_view.findViewById(R.id.message)).setText(message);
        ((ImageView) custom_view.findViewById(R.id.icon)).setImageResource(R.drawable.ic_baseline_check_24);
        ((CardView) custom_view.findViewById(R.id.parent_view)).setCardBackgroundColor(context.getResources().getColor(R.color.green_500));

        toast.setView(custom_view);
        toast.show();
    }

    public void toastIconInfo(String message, int time) {
        Toast toast = new Toast(context);
        toast.setDuration(time);

        //inflate view
        View custom_view = context.getLayoutInflater().inflate(R.layout.toast_icon_text, null);
        ((TextView) custom_view.findViewById(R.id.message)).setText(message);
        ((ImageView) custom_view.findViewById(R.id.icon)).setImageResource(R.drawable.ic_baseline_error_outline_24);
        ((CardView) custom_view.findViewById(R.id.parent_view)).setCardBackgroundColor(context.getResources().getColor(R.color.blue_500));

        toast.setView(custom_view);
        toast.show();
    }
}

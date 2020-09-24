package com.firstems.erp.helper.snackbar;

import android.content.Context;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.firstems.erp.R;
import com.google.android.material.snackbar.Snackbar;

/**
 * Created by Nguyen Quoc Cuong on 8/13/2020.
 */
public class SnackbarHelper {
    private static SnackbarHelper instance;
    private SnackbarHelper(){

    }
    public static SnackbarHelper getInstance(){
        if (instance==null){
            instance= new SnackbarHelper();
        }
        return instance;
    }
    public void snackBarIconSuccess(View view,String mess) {
        final Snackbar snackbar = Snackbar.make(view, "", Snackbar.LENGTH_SHORT);
        //inflate view
        LayoutInflater layoutInflater =LayoutInflater.from(view.getContext());
        View custom_view = layoutInflater.inflate(R.layout.snackbar_icon_text, null);

        snackbar.getView().setBackgroundColor(Color.TRANSPARENT);
        Snackbar.SnackbarLayout snackBarView = (Snackbar.SnackbarLayout) snackbar.getView();
        snackBarView.setPadding(0, 0, 0, 0);

        ((TextView) custom_view.findViewById(R.id.message)).setText(mess);
        ((ImageView) custom_view.findViewById(R.id.icon)).setImageResource(R.drawable.ic_baseline_done_all_24);
        (custom_view.findViewById(R.id.parent_view)).setBackgroundColor(view.getContext().getResources().getColor(R.color.green_500));
        snackBarView.addView(custom_view, 0);
        snackbar.show();
    }

    public void snackBarIconError(View view,String mess) {
        final Snackbar snackbar = Snackbar.make(view, "", Snackbar.LENGTH_SHORT);
        //inflate view
        LayoutInflater layoutInflater =LayoutInflater.from(view.getContext());
        View custom_view = layoutInflater.inflate(R.layout.snackbar_icon_text, null);

        snackbar.getView().setBackgroundColor(Color.TRANSPARENT);
        Snackbar.SnackbarLayout snackBarView = (Snackbar.SnackbarLayout) snackbar.getView();
        snackBarView.setPadding(0, 0, 0, 0);

        ((TextView) custom_view.findViewById(R.id.message)).setText(mess);
        ((ImageView) custom_view.findViewById(R.id.icon)).setImageResource(R.drawable.ic_baseline_close_24_red);
        (custom_view.findViewById(R.id.parent_view)).setBackgroundColor(view.getContext().getResources().getColor(R.color.red_500));
        snackBarView.addView(custom_view, 0);
        snackbar.show();
    }
}

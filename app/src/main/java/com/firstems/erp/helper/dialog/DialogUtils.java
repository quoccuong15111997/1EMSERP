package com.firstems.erp.helper.dialog;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.drawable.ColorDrawable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.CalendarView;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.firstems.erp.R;
import com.firstems.erp.callback.DatePickerCallback;
import com.firstems.erp.sharedpreferences.SharedPreferencesManager;

/**
 * Created by Nguyen Quoc Cuong on 7/20/2020.
 */
public class DialogUtils {
    private Context context;

    public DialogUtils(Context context) {
        this.context = context;
    }
    public void showWithURL(String url) {
        final Dialog dialog = new Dialog(context);
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialog.setContentView(R.layout.dialog_image);
        ImageView img = dialog.findViewById(R.id.img);
        Glide.with(context).load(url).into(img);
        dialog.getWindow().setBackgroundDrawable(new ColorDrawable(android.graphics.Color.TRANSPARENT));
        dialog.setCancelable(true);
        dialog.show();
    }
    public void showWithBitmap(Bitmap bitmap) {
        final Dialog dialog = new Dialog(context);
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialog.setContentView(R.layout.dialog_image);
        ImageView img = dialog.findViewById(R.id.img);
        img.setImageBitmap(bitmap);
        dialog.getWindow().setBackgroundDrawable(new ColorDrawable(android.graphics.Color.TRANSPARENT));
        dialog.setCancelable(true);
        dialog.show();
    }
}

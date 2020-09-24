package com.firstems.erp.helper.load;

import android.app.Dialog;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.WindowManager;
import android.widget.TextView;

import com.firstems.erp.R;

public class Loading {
    private Dialog pDialog;
    private Context context;
    private static Loading instance;
    private Loading(){
    
    }
    
    public static Loading getInstance() {
        if (instance==null){
            instance= new Loading();
        }
        return instance;
    }
    public void init(Context mcontext){
        context = mcontext;
    }
    public void showLoadingDialog(String mess){
        pDialog= new Dialog(context);
        LayoutInflater inflater = LayoutInflater.from(context);
        View dialogView = inflater.inflate(R.layout.item_loading_dialog, null);
        pDialog.setContentView(dialogView);
        TextView textView =dialogView.findViewById(R.id.tvLoading);
        textView.setText(mess);
        pDialog.setCancelable(false);
        WindowManager.LayoutParams lp = new WindowManager.LayoutParams();
        lp.copyFrom(pDialog.getWindow().getAttributes());
        lp.width = WindowManager.LayoutParams.WRAP_CONTENT;
        lp.height = WindowManager.LayoutParams.WRAP_CONTENT;
        pDialog.show();
    }
}

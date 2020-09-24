package com.firstems.erp.helper.customui;

import android.content.Context;
import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.GradientDrawable;
import android.widget.TextView;

import androidx.constraintlayout.widget.ConstraintLayout;

import com.firstems.erp.R;
import com.firstems.erp.helper.DrawableHelper;
import com.firstems.erp.sharedpreferences.SharedPreferencesManager;

/**
 * Created by Nguyen Quoc Cuong on 7/18/2020.
 */
public class CustomUIHelper {
    private static CustomUIHelper instance;
    private CustomUIHelper(){
    }
    public static CustomUIHelper getInstance() {
        if (instance==null){
            instance= new CustomUIHelper();
        }
        return instance;
    }
    public void customBackgroundConstrainLayoutTitle(Context context, ConstraintLayout constraintLayout){
        constraintLayout.setBackground(DrawableHelper.customLayerList(context, R.drawable.custom_background_item,
                Color.parseColor(SharedPreferencesManager.getInstance().getAccentColor()),
                        Color.parseColor(SharedPreferencesManager.getInstance().getSecondColor())));
    }
    public void customTextViewTitle(Context context,TextView textView){
        textView.setBackground(DrawableHelper.customDrawableWithBorderTitle(context, R.drawable.cutom_edittext_main, Color.WHITE,Color.parseColor(SharedPreferencesManager.getInstance().getBorderTextColor())));
    }

}

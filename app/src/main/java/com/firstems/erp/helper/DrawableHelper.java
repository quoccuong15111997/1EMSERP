package com.firstems.erp.helper;

import android.content.Context;
import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.GradientDrawable;
import android.graphics.drawable.LayerDrawable;
import android.graphics.drawable.StateListDrawable;

import androidx.constraintlayout.widget.ConstraintLayout;

import com.firstems.erp.R;
import com.firstems.erp.sharedpreferences.SharedPreferencesManager;


public class DrawableHelper {
    public static Drawable customDrawable(Context context,int resource,int color, int radius){
        Drawable drawable = context.getDrawable(resource);
        GradientDrawable gradientDrawable = (GradientDrawable) drawable;
        gradientDrawable.setColor(color);
       // gradientDrawable.setCornerRadius((float) SharedPreferencesManager.getPrefBorderRadius());
        return  gradientDrawable;
    }
    public static Drawable customDrawable(Context context,int resource,int color){
        Drawable drawable = context.getDrawable(resource);
        GradientDrawable gradientDrawable = (GradientDrawable) drawable;
        gradientDrawable.setColor(color);
       // gradientDrawable.setCornerRadius((float) SharedPreferencesManager.getPrefBorderRadius());
        return  gradientDrawable;
    }
    public static Drawable customDrawableCircler(Context context,int resource,int color){
        Drawable drawable = context.getDrawable(resource);
        StateListDrawable gradientDrawable = (StateListDrawable) drawable;
        int [] startes = gradientDrawable.getState();
        for (int i=0;i<startes.length;i++){

        }
        return  gradientDrawable;
    }
    public static Drawable customDrawableWithBorder(Context context,int resource,int color){
        Drawable drawable = context.getDrawable(resource);
        GradientDrawable gradientDrawable = (GradientDrawable) drawable;
        gradientDrawable.setColor(color);
        //gradientDrawable.setStroke(SharedPreferencesManager.getPrefBorderWith(), Color.parseColor(SharedPreferencesManager.getPrefColorBorder()));
       // gradientDrawable.setCornerRadius((float) SharedPreferencesManager.getPrefBorderRadius());
        return gradientDrawable;
    }
    public static Drawable customDrawableWithBorderTitle(Context context,int resource,int colorBackground, int colorStroke){
        Drawable drawable = context.getDrawable(resource);
        GradientDrawable gradientDrawable = (GradientDrawable) drawable;
        gradientDrawable.setColor(colorBackground);
        gradientDrawable.setStroke(1,colorStroke);
        // gradientDrawable.setCornerRadius((float) SharedPreferencesManager.getPrefBorderRadius());
        return gradientDrawable;
    }
    public static Drawable customLayerList(Context context, int resource,int colorBackground, int colorStroke){
        LayerDrawable layerDrawable = (LayerDrawable) context.getResources()
                .getDrawable(resource);
        GradientDrawable gradientDrawable = (GradientDrawable) layerDrawable
                .findDrawableByLayerId(R.id.gradientDrawble);
        gradientDrawable.setColor(colorBackground);
        gradientDrawable.setStroke(1,colorStroke);
        return gradientDrawable;
    }
}

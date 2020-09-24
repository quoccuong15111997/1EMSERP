package com.firstems.erp.helper.animation;

import android.app.Activity;

import com.firstems.erp.R;

/**
 * Created by Nguyen Quoc Cuong on 7/15/2020.
 */
public class AnimationHelper {
    private static AnimationHelper instance;
    private AnimationHelper() {
    }
    public static AnimationHelper getInstance(){
        if (instance==null){
            instance= new AnimationHelper();
        }
        return instance;
    }
    public void setAnimationBottomToTop(Activity activity){
        activity.overridePendingTransition(R.anim.slide_in_bottom, R.anim.slide_out_bottom);
    }
    public void setAnimationTopToBottom(Activity activity){
        activity.overridePendingTransition(R.anim.slide_in_top,R.anim.slide_out_top);
    }
    public void setAnimationRightToLeft(Activity activity){
        activity.overridePendingTransition(R.anim.slide_in_right, R.anim.slide_out_left);
    }
    public void setAnimationLeftToRight(Activity activity){
        activity.overridePendingTransition(R.anim.slide_in_left, R.anim.slide_out_right);
    }
}

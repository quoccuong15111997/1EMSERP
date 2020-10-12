package com.firstems.erp.helper.animation;

import android.animation.TimeInterpolator;

import androidx.cardview.widget.CardView;

import org.jetbrains.annotations.NotNull;

public class CircleCardViewAnimatorHelper extends CardViewAnimatorHelper {
    public CircleCardViewAnimatorHelper(CardView cardView, float startWidth, float endWidth, float startHeight, float endHeight, float startX, float startY, float endX, float endY, float startRadius, float endRadius, float startElevation, float endElevation, Boolean isArcPath, Long duration, TimeInterpolator interpolator, float progress) {
        super(cardView, startWidth, endWidth, startHeight, endHeight, startX, startY, endX = startX + (startWidth - endWidth) / 2, endY = startY + (startWidth - endWidth) / 2,
                startRadius = startWidth / 2, endRadius = endWidth / 2, startElevation, endElevation, isArcPath, duration, interpolator, progress);
    }

}

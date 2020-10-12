package com.firstems.erp.helper.animation;

import android.animation.TimeInterpolator;
import android.view.animation.AccelerateDecelerateInterpolator;

import androidx.cardview.widget.CardView;

import com.firstems.erp.helper.animation.ArcAnimator.ArcMetric;
import com.firstems.erp.helper.animation.ArcAnimator.ArcUtils;
import com.firstems.erp.helper.animation.ArcAnimator.Side;
import com.google.android.datatransport.runtime.util.PriorityMapping;
import com.tuyenmonkey.mkloader.model.Arc;

public class CardViewAnimatorHelper {
    private CardView cardView;
    private float startWidth = (float) cardView.getWidth();
    private float endWidth = -1f;
    private float startHeight = (float) cardView.getHeight();
    private float endHeight = -1f;
    private float startX = cardView.getX();
    private float startY = cardView.getY();
    private float endX = -1f;
    private float endY= -1f;
    private float startRadius = cardView.getRadius();
    private float endRadius = -1f;
    private float startElevation = cardView.getElevation();
    private float endElevation = -1f;
    private Boolean isArcPath= false;
    Long duration = 300L;
    TimeInterpolator interpolator = new AccelerateDecelerateInterpolator();

    public CardViewAnimatorHelper(CardView cardView, float startWidth, float endWidth, float startHeight, float endHeight, float startX, float startY, float endX, float endY, float startRadius, float endRadius, float startElevation, float endElevation, Boolean isArcPath, Long duration, TimeInterpolator interpolator, float progress) {
        this.cardView = cardView;
        this.startWidth = startWidth;
        this.endWidth = endWidth;
        this.startHeight = startHeight;
        this.endHeight = endHeight;
        this.startX = startX;
        this.startY = startY;
        this.endX = endX;
        this.endY = endY;
        this.startRadius = startRadius;
        this.endRadius = endRadius;
        this.startElevation = startElevation;
        this.endElevation = endElevation;
        this.isArcPath = isArcPath;
        this.duration = duration;
        this.interpolator = interpolator;
        this.progress = progress;
    }

    private float progress;

    public void setProgress(float value) {
        progress = value;

        if (!isArcPath) {
            if (endWidth >= 0) cardView.getLayoutParams().width = (int) getProgressValue(startWidth, endWidth, progress);
            if (endHeight >= 0) cardView.getLayoutParams().height = (int) getProgressValue(startHeight, endHeight, progress);
            if (endWidth >= 0 || endHeight >= 0) cardView.requestLayout();

            if (endX >= 0) cardView.setX(getProgressValue(startX, endX, progress));
            if (endY >= 0) cardView.setY(getProgressValue(startY, endY, progress));

        } else {
            ArcMetric arcMetric = ArcMetric.evaluate(startX, startY, endX, endY, 90f, Side.LEFT);
            float degree = arcMetric.getDegree(progress);
            cardView.setX(arcMetric.getAxisPoint().x + arcMetric.mRadius * ArcUtils.cos(degree));
            cardView.setY(arcMetric.getAxisPoint().y - arcMetric.mRadius * ArcUtils.sin(degree));
        }
        if (endRadius >= 0) cardView.setRadius(getProgressValue(startRadius, endRadius, progress));
        if (endElevation >= 0) cardView.setElevation(getProgressValue(startElevation, endElevation, progress));
    }

    private float getProgressValue(float startWidth, float endWidth, float progress) {
        return startWidth + (endWidth - startWidth) * progress;
    }
}

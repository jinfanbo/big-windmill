package com.vongvia1209.jinfanbo;

import android.animation.ObjectAnimator;
import android.animation.ValueAnimator;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.RectF;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.View;
import android.view.animation.LinearInterpolator;

import com.vongvia1209.windmillview.R;

public class jinfanbo extends View {
    Paint testPaint;
    Paint Paint1;
    Paint Paint2;
    Paint Paint3;
    Paint Paint4;

    public jinfanbo(Context context) {
        this(context, null);
    }

    public jinfanbo(Context context, @Nullable AttributeSet attrs) {
        this(context, attrs, 0);

    }

    public jinfanbo(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        initPaint();
        initAnim();
    }

    ValueAnimator animator;
    float degree;
    private void initAnim() {
        animator=ValueAnimator.ofFloat(0,360);
        animator.setDuration(1500);
        animator.setInterpolator(new LinearInterpolator());
        animator.setRepeatCount(ObjectAnimator.INFINITE);
        animator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator valueAnimator) {
                degree= (float) valueAnimator.getAnimatedValue();
                postInvalidate();
            }
        });
        animator.start();
    }

    Path path1;
    Path path2;
    Path path3;
    Path path4;

    private void initPath() {
        path1 = new Path();
        path1.moveTo(pointX, pointY);
        path1.lineTo(pointX - 200, pointY);
        RectF rect1 = new RectF(pointX - 200,pointY - 100,pointX,pointY + 100);
        path1.arcTo(rect1,0,-180);
        path1.close();

        path2 = new Path();
        path2.moveTo(pointX, pointY);
        path2.lineTo(pointX, pointY - 200);
        RectF rect2 = new RectF(pointX - 100,pointY - 200,pointX + 100,pointY);
        path2.arcTo(rect2,270,180);
        path2.close();

        path3 = new Path();
        path3.moveTo(pointX + 200, pointY);
        path3.lineTo(pointX, pointY);
        RectF rect3 = new RectF(pointX,pointY - 100,pointX + 200,pointY + 100);
        path3.arcTo(rect3,0,180);
        path3.close();

        path4 = new Path();
        path4.moveTo(pointX, pointY + 200);
        path4.lineTo(pointX, pointY);
        RectF rect4 = new RectF(pointX - 100,pointY,pointX + 100,pointY + 200);
        path4.arcTo(rect4,90,180);
        path4.close();
    }

    private void initPaint() {
        testPaint = new Paint();
        testPaint.setAntiAlias(true);
        testPaint.setStrokeCap(Paint.Cap.ROUND);
        testPaint.setStyle(Paint.Style.FILL);

        Paint1 = new Paint(testPaint);
        Paint1.setStyle(Paint.Style.FILL);
        Paint1.setColor(getResources().getColor(R.color.startColor1));

        Paint2 = new Paint(testPaint);
        Paint2.setStyle(Paint.Style.FILL);
        Paint2.setColor(getResources().getColor(R.color.startColor2));

        Paint3 = new Paint(testPaint);
        Paint3.setStyle(Paint.Style.FILL);
        Paint3.setColor(getResources().getColor(R.color.startColor3));

        Paint4 = new Paint(testPaint);
        Paint4.setStyle(Paint.Style.FILL);
        Paint4.setColor(getResources().getColor(R.color.startColor4));

        testPaint.setStrokeWidth(40);
        testPaint.setColor(getResources().getColor(R.color.white));
    }
    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        canvas.drawLine(pointX1, pointY1, pointX1, pointY1 + 400, testPaint);
        canvas.save();
        canvas.translate(pointX1, pointY1);
        canvas.rotate(degree);
        canvas.drawPath(path4, Paint4);
        canvas.drawPath(path3, Paint3);
        canvas.drawPath(path2, Paint2);
        canvas.drawPath(path1, Paint1);
        canvas.drawCircle(pointX, pointY, 30, testPaint);
        canvas.restore();
    }
    int height;
    int width;
    float pointX;
    float pointY;
    float pointX1;
    float pointY1;
    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);
        height = h;
        width = w;
        pointX1 = width / 2;
        pointY1 = height / 2;
        initPath();
    }
}

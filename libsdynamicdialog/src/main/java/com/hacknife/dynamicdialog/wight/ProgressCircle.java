package com.hacknife.dynamicdialog.wight;

import android.animation.ValueAnimator;
import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.RectF;
import android.util.AttributeSet;
import android.view.View;
import android.view.animation.AccelerateDecelerateInterpolator;

import com.hacknife.dynamicdialog.R;


/**
 * author  : Black Chopper
 * e-mail  : 4884280@qq.com
 * github  : http://github.com/BlackChopper
 * project : DynamicDialog
 */
public class ProgressCircle extends View {
    private Paint mPaint;
    private Path mPath;
    private int mRealWidth;
    private RectF mOval;
    private float mStartAngle;
    private float mEndAngle;
    private int mColor;
    private float mCurrentAngle;
    private boolean mIsDisaplay;
    private float mStrokeWidth;
    private ValueAnimator mAnim;

    public ProgressCircle(Context context) {
        this(context, null);
    }

    public ProgressCircle(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public ProgressCircle(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        TypedArray ta=context.getTheme().obtainStyledAttributes(attrs, R.styleable.ProgressCircle, defStyleAttr, 0);
        mColor =ta.getColor(R.styleable.ProgressCircle_circleColor, Color.BLUE);
        ta.recycle();
        init(context);
    }



    private void init(Context context) {

        mPaint = new Paint();
        mPaint.setAntiAlias(true);
        mPaint.setColor(mColor);
        mPaint.setStyle(Paint.Style.STROKE);
        mStrokeWidth = dip2px(2);
        mPaint.setStrokeWidth(mStrokeWidth);
        mPath = new Path();
        mEndAngle = 0;
        mStartAngle = 0;
        mCurrentAngle = 270f;
        mIsDisaplay = true;

    }


    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        if (getMeasuredHeight() > getMeasuredWidth()) {
            mRealWidth = getMeasuredWidth();
        } else if (getMeasuredHeight() < getMeasuredWidth()) {
            mRealWidth = getMeasuredHeight();
        } else {
            mRealWidth = getMeasuredHeight();
        }
        mOval = new RectF(mStrokeWidth, mStrokeWidth, mRealWidth - mStrokeWidth, mRealWidth - mStrokeWidth);
        mPath.addArc(mOval, mStartAngle, mEndAngle);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        canvas.save();
        canvas.rotate(mCurrentAngle, mRealWidth / 2, mRealWidth / 2);
        canvas.drawPath(mPath, mPaint);
        canvas.restore();
    }

    private float dip2px(float dpValue) {
        return dpValue * getResources().getDisplayMetrics().density + 0.5f;
    }

    public void startAnimation() {
        mAnim = ValueAnimator.ofFloat(0f, 360f);
        mAnim.setDuration(2000);
        mAnim.setInterpolator(new AccelerateDecelerateInterpolator());
        mAnim.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator valueAnimator) {
                float f = (float) valueAnimator.getAnimatedValue();
                mPath.reset();
                if (mIsDisaplay) {
                    if (mOval != null)
                        mPath.addArc(mOval, 0f, f);

                } else {
                    if (mOval != null)
                        mPath.addArc(mOval, f, 360f - f);
                }
                mCurrentAngle = mCurrentAngle + 3;
                invalidate();
                if (f == 360f) {
                    mIsDisaplay = mIsDisaplay == true ? false : true;
                    startAnimation();
                }
            }
        });
        mAnim.start();
    }

    public void endAnimation() {
        if (mAnim != null) {
            mAnim.cancel();
        }
        mPath.reset();
        invalidate();
    }
}

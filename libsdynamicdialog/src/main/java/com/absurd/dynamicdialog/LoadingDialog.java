package com.absurd.dynamicdialog;

import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.LayoutInflater;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationSet;
import android.view.animation.AnimationUtils;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.TextView;

import com.absurd.dynamicdialog.base.AnimationLoader;
import com.absurd.dynamicdialog.base.OnTickListener;
import com.absurd.dynamicdialog.wight.ProgressCircle;
import com.absurd.dynamicdialog.wight.SuccessTickView;

/**
 * Created by 段泽全 on 2017/9/30.
 * Github：https://github.com/mr-absurd
 * Emile:4884280@qq.com
 */

public class LoadingDialog extends Dialog implements OnTickListener, Animation.AnimationListener {
    private View mContentView;
    private FrameLayout mErrorFrame;
    private FrameLayout mSuccessFrame;
    private SuccessTickView mSuccessTickView;
    private ProgressCircle mProgressCircle;
    private Animation mDialogIn;
    private Animation mDialogOut;
    private Animation mSuccessLeftMaskAnim;
    private Animation mSuccessRightMaskAnim;
    private Animation mSuccessBowAnim;
    private Animation mErrorInAnim;
    private AnimationSet mErrorXInAnim;
    private View mSuccessLeftMask;
    private View mSuccessRightMask;
    private ImageView mErrorX;
    private TextView mContentTextView;
    private String mLoadText, mSuccessText, mErrorText;
    private boolean mResult = false;
    private static int SUCCESS = 1;
    private static int ERROR = 2;
    private static int DISMISS = 3;
    private Handler mHandler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            if (msg.arg1 == DISMISS) {
                LoadingDialog.super.dismiss();
            } else if (msg.arg1 == ERROR) {
                mContentView.startAnimation(mDialogOut);
                mDialogOut.setAnimationListener(LoadingDialog.this);
            } else if (msg.arg1 == SUCCESS) {
                mContentView.startAnimation(mDialogOut);
                mDialogOut.setAnimationListener(LoadingDialog.this);
            }
        }
    };

    public LoadingDialog(Context context) {
        this(context, R.style.dynamic_dialog);
    }

    public LoadingDialog(Context context, int themeResId) {
        super(context, themeResId);
        mDialogIn = AnimationUtils.loadAnimation(context, R.anim.dynamicdialog_animation_in);
        mDialogOut = AnimationUtils.loadAnimation(context, R.anim.dynamicdialog_animation_out);
        mSuccessLeftMaskAnim = AnimationUtils.loadAnimation(context, R.anim.dynamicdialog_success_left_mask_layout);
        mSuccessLeftMaskAnim.setFillAfter(true);
        mSuccessRightMaskAnim = AnimationUtils.loadAnimation(context, R.anim.dynamicdialog_success_right_mask_layout);
        mSuccessRightMaskAnim.setFillAfter(true);
        mSuccessBowAnim = AnimationUtils.loadAnimation(context, R.anim.dynamicdialog_success_bow_roate);
        mErrorInAnim = AnimationLoader.loadAnimation(context, R.anim.dynamicdialog_error_frame_in);
        mErrorXInAnim = (AnimationSet) AnimationUtils.loadAnimation(getContext(), R.anim.dynamicdialog_error_x_in);
        setCanceledOnTouchOutside(false);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mContentView = LayoutInflater.from(getContext()).inflate(R.layout.dynamicdialog_loading, null);
        setContentView(mContentView);
        mSuccessTickView = findViewById(R.id.st_view);
        mSuccessLeftMask = findViewById(R.id.mask_left);
        mSuccessRightMask = findViewById(R.id.mask_right);
        mProgressCircle = findViewById(R.id.progress_circle);
        mSuccessFrame = findViewById(R.id.success_frame);
        mErrorFrame = findViewById(R.id.error_frame);
        mErrorX = findViewById(R.id.error_x);
        mContentTextView = findViewById(R.id.tv_content);
        startAnimation();
    }

    private void startAnimation() {
        mSuccessTickView.setVisibility(View.GONE);
        mContentView.startAnimation(mDialogIn);
        mProgressCircle.startAnimation();
        if (mLoadText != null) {
            mContentTextView.setVisibility(View.VISIBLE);
            mContentTextView.setText(mLoadText);
        } else {
            mContentTextView.setVisibility(View.GONE);
        }
    }


    @Override
    public void dismiss() {

        if (mResult) {
            if (mSuccessText != null) {
                mContentTextView.setVisibility(View.VISIBLE);
                mContentTextView.setText(mSuccessText);
            } else {
                mContentTextView.setVisibility(View.GONE);
            }
            endSuccessAnimation();
        } else {
            if (mErrorText != null) {
                mContentTextView.setVisibility(View.VISIBLE);
                mContentTextView.setText(mErrorText);
            } else {
                mContentTextView.setVisibility(View.GONE);
            }
            endErrorAnimation();
        }

    }



    private void endErrorAnimation() {
        mProgressCircle.endAnimation();
        mSuccessFrame.setVisibility(View.GONE);
        mErrorFrame.setVisibility(View.VISIBLE);
        mErrorFrame.startAnimation(mErrorInAnim);
        mErrorX.startAnimation(mErrorXInAnim);
        mErrorInAnim.setAnimationListener(this);
    }

    private void endSuccessAnimation() {
        mSuccessTickView.setVisibility(View.VISIBLE);
        mProgressCircle.endAnimation();
        mSuccessRightMask.startAnimation(mSuccessRightMaskAnim);
        mSuccessLeftMask.startAnimation(mSuccessLeftMaskAnim);
        mSuccessTickView.startTickAnim();
        mSuccessRightMask.startAnimation(mSuccessBowAnim);
        mSuccessTickView.setOnTickListener(this);
    }

    @Override
    public void onAnimationEnd() {
        Message msg = Message.obtain();
        msg.arg1 = SUCCESS;
        mHandler.sendMessageDelayed(msg, 500);
    }

    @Override
    public void onAnimationStart(Animation animation) {

    }

    @Override
    public void onAnimationEnd(Animation animation) {
        Message msg = Message.obtain();
        if (animation == mErrorInAnim) {
            msg.arg1 = ERROR;
            mHandler.sendMessageDelayed(msg, 500);
        } else {
            msg.arg1 = DISMISS;
            mHandler.sendMessage(msg);
        }
    }

    @Override
    public void onAnimationRepeat(Animation animation) {

    }

    public LoadingDialog setLoadText(String loadText) {
        this.mLoadText = loadText;
        return this;
    }

    public LoadingDialog setSuccessText(String successText) {
        this.mSuccessText = successText;
        return this;
    }

    public LoadingDialog setErrorText(String errorText) {
        this.mErrorText = errorText;
        return this;
    }

    public LoadingDialog setResult(boolean result) {
        this.mResult = result;
        return this;
    }
}

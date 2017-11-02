package com.aliletter.dynamicdialog;

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

import com.aliletter.dynamicdialog.base.AnimationLoader;

/**
 * Created by 段泽全 on 2017/10/1.
 * Github：https://github.com/mr-absurd
 * Emile:4884280@qq.com
 */

public class ErrorDialog extends Dialog implements Animation.AnimationListener {
    private View mContentView;
    private FrameLayout mErrorFrame;
    private Animation mDialogIn;
    private Animation mDialogOut;
    private Animation mErrorInAnim;
    private AnimationSet mErrorXInAnim;
    private ImageView mErrorX;
    private TextView mContentTextView;
    private String mErrorText;
    private Handler mHandler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            mContentView.startAnimation(mDialogOut);
            mDialogOut.setAnimationListener(ErrorDialog.this);
        }
    };

    public ErrorDialog(Context context) {
        this(context, R.style.dynamic_dialog);
    }

    public ErrorDialog(Context context, int themeResId) {
        super(context, themeResId);
        mDialogIn = AnimationUtils.loadAnimation(context, R.anim.dynamicdialog_animation_in);
        mDialogOut = AnimationUtils.loadAnimation(context, R.anim.dynamicdialog_animation_out);
        mErrorInAnim = AnimationLoader.loadAnimation(context, R.anim.dynamicdialog_error_frame_in);
        mErrorXInAnim = (AnimationSet) AnimationUtils.loadAnimation(getContext(), R.anim.dynamicdialog_error_x_in);
        setCanceledOnTouchOutside(false);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mContentView = LayoutInflater.from(getContext()).inflate(R.layout.dynamicdialog_error, null);
        setContentView(mContentView);
        mErrorFrame = findViewById(R.id.error_frame);
        mErrorX = findViewById(R.id.error_x);
        mContentTextView = findViewById(R.id.tv_content);
        startAnimation();
    }

    private void startAnimation() {
        if (mErrorText != null) {
            mContentTextView.setVisibility(View.VISIBLE);
            mContentTextView.setText(mErrorText);
        }
        mContentView.startAnimation(mDialogIn);
        mErrorFrame.startAnimation(mErrorInAnim);
        mErrorX.startAnimation(mErrorXInAnim);
        mErrorInAnim.setAnimationListener(this);
    }


    @Override
    public void onAnimationStart(Animation animation) {

    }

    @Override
    public void onAnimationEnd(Animation animation) {
        if (mErrorInAnim == animation) {
            mHandler.sendEmptyMessageDelayed(1, 1000);
        } else {
            dismiss();
        }
    }

    @Override
    public void onAnimationRepeat(Animation animation) {

    }

    public ErrorDialog setErrorText(String errortext) {
        this.mErrorText = errortext;
        return this;
    }
}

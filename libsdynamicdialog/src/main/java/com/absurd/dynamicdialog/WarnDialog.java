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
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.TextView;

import com.absurd.dynamicdialog.base.AnimationLoader;
import com.absurd.dynamicdialog.base.OnTickListener;
import com.absurd.dynamicdialog.base.WarnDialogListener;
import com.absurd.dynamicdialog.wight.ProgressCircle;
import com.absurd.dynamicdialog.wight.SuccessTickView;

/**
 * Created by 段泽全 on 2017/10/2.
 * Github：https://github.com/mr-absurd
 * Emile:4884280@qq.com
 */

public class WarnDialog extends Dialog implements View.OnClickListener, Animation.AnimationListener, OnTickListener {
    private View mContentView;
    private FrameLayout mSuccessFrame;
    private FrameLayout mWarnFrame;
    private FrameLayout mErrorFrame;
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
    private TextView mContentTextView;
    private ImageView mErrorX;
    private Button mCancleButton, mConfirmButton;
    private String mLoadText, mErrorText, mSuccessText, mCancelText, mConfirmText, mWarnText;
    private static final int DISMISS = 1;
    private static final int SUCCESS = 2;
    private static final int ERROR = 3;
    private boolean mResult = false;
    private WarnDialogListener mListener;

    public WarnDialog setWarnDialogListener(WarnDialogListener listener) {
        this.mListener = listener;
        return this;
    }

    private Handler mHandler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            switch (msg.arg1) {
                case DISMISS:
                    WarnDialog.super.dismiss();
                    break;
                case SUCCESS:
                    mContentView.startAnimation(mDialogOut);
                    mDialogOut.setAnimationListener(WarnDialog.this);
                    break;
                case ERROR:
                    mContentView.startAnimation(mDialogOut);
                    mDialogOut.setAnimationListener(WarnDialog.this);
                    break;
            }
        }
    };

    public WarnDialog(Context context) {
        this(context, R.style.dynamic_dialog);
    }

    public WarnDialog(Context context, int themeResId) {
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
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mContentView = LayoutInflater.from(getContext()).inflate(R.layout.dynamicdialog_warn, null);
        setContentView(mContentView);
        mWarnFrame = findViewById(R.id.warning_frame);
        mSuccessFrame = findViewById(R.id.success_frame);
        mSuccessTickView = findViewById(R.id.st_view);
        mProgressCircle = findViewById(R.id.progress_circle);
        mSuccessLeftMask = findViewById(R.id.mask_left);
        mSuccessRightMask = findViewById(R.id.mask_right);
        mContentTextView = findViewById(R.id.tv_content);
        mCancleButton = findViewById(R.id.btn_cancle);
        mConfirmButton = findViewById(R.id.btn_confirm);
        mErrorFrame = findViewById(R.id.error_frame);
        mErrorX = findViewById(R.id.error_x);
        mConfirmButton.setOnClickListener(this);
        mCancleButton.setOnClickListener(this);
        setCanceledOnTouchOutside(false);
        startAnimation();
    }

    private void startAnimation() {
        mContentView.startAnimation(mDialogIn);
        if (mWarnText != null) {
            mContentTextView.setVisibility(View.VISIBLE);
            mContentTextView.setText(mWarnText);
        } else {
            mContentTextView.setVisibility(View.GONE);
        }
        if (mCancelText != null) {
            mCancleButton.setText(mCancelText);
        }
        if (mConfirmText != null) {
            mConfirmButton.setText(mConfirmText);
        }
    }

    private void endAnimation() {
        mContentView.startAnimation(mDialogOut);
        mDialogOut.setAnimationListener(this);
    }


    @Override
    public void dismiss() {
        if (mResult == true) {
            successAnimation();
        } else {
            errorAnimation();
        }
    }

    private void successAnimation() {
        if (mSuccessText != null) {
            mContentTextView.setVisibility(View.VISIBLE);
            mContentTextView.setText(mSuccessText);
        } else {
            mContentTextView.setVisibility(View.GONE);
        }
        mProgressCircle.endAnimation();
        mSuccessTickView.setVisibility(View.VISIBLE);
        mSuccessRightMask.startAnimation(mSuccessRightMaskAnim);
        mSuccessLeftMask.startAnimation(mSuccessLeftMaskAnim);
        mSuccessTickView.startTickAnim();
        mSuccessRightMask.startAnimation(mSuccessBowAnim);
        mSuccessTickView.setOnTickListener(this);
    }

    private void errorAnimation() {
        if (mErrorText != null) {
            mContentTextView.setVisibility(View.VISIBLE);
            mContentTextView.setText(mErrorText);
        } else {
            mContentTextView.setVisibility(View.GONE);
        }
        mProgressCircle.endAnimation();
        mSuccessFrame.setVisibility(View.GONE);
        mErrorFrame.setVisibility(View.VISIBLE);
        mErrorFrame.startAnimation(mErrorInAnim);
        mErrorX.startAnimation(mErrorXInAnim);
        mErrorInAnim.setAnimationListener(this);
    }


    @Override
    public void onClick(View view) {
        if (view.getId() == R.id.btn_cancle) {
            endAnimation();
            if (mListener != null) {
                mListener.onCancle();
            }
        } else if (view.getId() == R.id.btn_confirm) {
            loadAnimation();
            if (mListener != null) {
                mListener.onConfirm();
            }
        }
    }

    private void loadAnimation() {
        mWarnFrame.setVisibility(View.GONE);
        mCancleButton.setVisibility(View.GONE);
        mConfirmButton.setVisibility(View.GONE);
        mSuccessFrame.setVisibility(View.VISIBLE);
        mSuccessTickView.setVisibility(View.GONE);
        if (mLoadText == null) {
            mContentTextView.setVisibility(View.GONE);
        } else {
            mContentTextView.setVisibility(View.VISIBLE);
            mContentTextView.setText(mLoadText);
        }
        mProgressCircle.startAnimation();
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
        if (animation == mDialogOut) {
            msg.arg1 = DISMISS;
            mHandler.sendMessage(msg);
        } else if (animation == mErrorInAnim) {
            msg.arg1 = ERROR;
            mHandler.sendMessageDelayed(msg, 500);
        }
    }

    @Override
    public void onAnimationRepeat(Animation animation) {

    }

    public WarnDialog setCancelText(String cancelText) {
        this.mCancelText = cancelText;
        return this;
    }

    public WarnDialog setConfirmText(String confirmText) {
        this.mConfirmText = confirmText;
        return this;
    }

    public WarnDialog setWarnText(String warnText) {
        this.mWarnText = warnText;
        return this;
    }

    public WarnDialog setLoadText(String LoadText) {
        this.mLoadText = LoadText;
        return this;
    }

    public WarnDialog setErrorText(String ErrorText) {
        this.mErrorText = ErrorText;
        return this;
    }

    public WarnDialog setSuccessText(String SuccessText) {
        this.mSuccessText = SuccessText;
        return this;
    }

    public WarnDialog setResult(boolean result) {
        mResult = result;
        return this;
    }

    @Override
    public void onBackPressed() {

    }
}

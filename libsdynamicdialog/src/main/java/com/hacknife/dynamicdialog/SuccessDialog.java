package com.hacknife.dynamicdialog;

import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.LayoutInflater;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.TextView;


import com.hacknife.dynamicdialog.base.OnTickListener;
import com.hacknife.dynamicdialog.wight.SuccessTickView;

/**
 * author  : Black Chopper
 * e-mail  : 4884280@qq.com
 * github  : http://github.com/BlackChopper
 * project : DynamicDialog
 */
public class SuccessDialog extends Dialog implements OnTickListener, Animation.AnimationListener {
    private View mContentView;
    private SuccessTickView mSuccessTickView;
    private Animation mDialogIn;
    private Animation mDialogOut;
    private Animation mSuccessLeftMaskAnim;
    private Animation mSuccessRightMaskAnim;
    private Animation mSuccessBowAnim;
    private View mSuccessLeftMask;
    private View mSuccessRightMask;
    private TextView mContentTextView;
    private String mSuccessText;
    private Handler mHandler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            mContentView.startAnimation(mDialogOut);
            mDialogOut.setAnimationListener(SuccessDialog.this);
        }
    };

    public SuccessDialog(Context context) {
        this(context, R.style.dynamic_dialog);
    }

    public SuccessDialog(Context context, int themeResId) {
        super(context, themeResId);
        mDialogIn = AnimationUtils.loadAnimation(context, R.anim.dynamicdialog_animation_in);
        mDialogOut = AnimationUtils.loadAnimation(context, R.anim.dynamicdialog_animation_out);
        mSuccessLeftMaskAnim = AnimationUtils.loadAnimation(context, R.anim.dynamicdialog_success_left_mask_layout);
        mSuccessLeftMaskAnim.setFillAfter(true);
        mSuccessRightMaskAnim = AnimationUtils.loadAnimation(context, R.anim.dynamicdialog_success_right_mask_layout);
        mSuccessRightMaskAnim.setFillAfter(true);
        mSuccessBowAnim = AnimationUtils.loadAnimation(context, R.anim.dynamicdialog_success_bow_roate);

    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mContentView = LayoutInflater.from(getContext()).inflate(R.layout.dynamicdialog_succcess, null);
        setContentView(mContentView);
        mSuccessTickView = findViewById(R.id.st_view);
        mSuccessLeftMask = findViewById(R.id.mask_left);
        mSuccessRightMask = findViewById(R.id.mask_right);
        mContentTextView = findViewById(R.id.tv_content);
        startAnimation();
    }

    private void startAnimation() {
        if (mSuccessText != null) {
            mContentTextView.setVisibility(View.VISIBLE);
            mContentTextView.setText(mSuccessText);
        } else {
            mContentTextView.setVisibility(View.GONE);
        }
        mContentView.startAnimation(mDialogIn);
        mSuccessRightMask.startAnimation(mSuccessRightMaskAnim);
        mSuccessLeftMask.startAnimation(mSuccessLeftMaskAnim);
        mSuccessTickView.startTickAnim();
        mSuccessRightMask.startAnimation(mSuccessBowAnim);
        mSuccessTickView.setOnTickListener(this);
    }

    @Override
    public void onAnimationEnd() {
        mHandler.sendEmptyMessageDelayed(1, 500);
    }


    @Override
    public void onAnimationStart(Animation animation) {

    }

    @Override
    public void onAnimationEnd(Animation animation) {
        dismiss();
    }

    @Override
    public void onAnimationRepeat(Animation animation) {

    }

    public SuccessDialog setSuccessText(String successText) {
        this.mSuccessText = successText;
        return this;
    }
}

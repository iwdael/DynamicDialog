package com.blackchopper.demo_dynamicdialog;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import com.blackchopper.dynamicdialog.ErrorDialog;
import com.blackchopper.dynamicdialog.LoadingDialog;
import com.blackchopper.dynamicdialog.SuccessDialog;
import com.blackchopper.dynamicdialog.WarnDialog;
import com.blackchopper.dynamicdialog.base.WarnDialogListener;

public class MainActivity extends AppCompatActivity implements WarnDialogListener {
    private boolean b = false;
    WarnDialog warndialog;
    LoadingDialog loadingDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }


    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.SuccessDialog:
                new SuccessDialog(this).setSuccessText("删除成功").show();
                break;
            case R.id.ErrorDialog:
                new ErrorDialog(this).setErrorText("删除失败").show();
                break;
            case R.id.LoadingDialog:
                loadingDialog = new LoadingDialog(this)
                        .setLoadText("删除中...")
                        .setErrorText("删除失败")
                        .setSuccessText("删除成功");
                loadingDialog.show();
                new Thread(new Runnable() {
                    @Override
                    public void run() {
                        try {
                            Thread.sleep(3000);
                            runOnUiThread(new Runnable() {
                                @Override
                                public void run() {
                                    b = b == true ? false : true;
                                    loadingDialog.setResult(b).dismiss();
                                }
                            });
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }

                    }
                }).start();
                break;
            case R.id.WarnDialog:
                warndialog = new WarnDialog(this)
                        .setWarnText("你确定要删除此文件吗？")
                        .setLoadText("删除中...")
                        .setSuccessText("删除成功")
                        .setErrorText("删除失败")
                        .setWarnDialogListener(this);
                warndialog.show();
                break;

        }
    }

    @Override
    public void onConfirm() {
//        b = b == true ? false : true;
//        warndialog.setResult(b).dismiss();
        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    Thread.sleep(3000);
                    runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            b = b == true ? false : true;
                            warndialog.setResult(b).dismiss();
                        }
                    });
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }).start();
    }

    @Override
    public void onCancle() {

    }
}

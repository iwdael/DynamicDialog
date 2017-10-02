# DynamicDialog  [![](https://www.jitpack.io/v/mr-absurd/dynamicdialog.svg)](https://www.jitpack.io/#mr-absurd/dynamicdialog)
Dynamicdialog is an Android component that prompts the user. The component contains a lot of animation, including dialog box pops up, dialog box destroyed, loaded animation, successful animation, failed animation.
# How to
To get a Git project into your build:
## Step 1. Add the JitPack repository to your build file
Add it in your root build.gradle at the end of repositories:

	allprojects {
		repositories {
			...
			maven { url 'https://jitpack.io' }
		}
	}
  
## Step 2. Add the dependency

	dependencies {
          compile 'com.github.mr-absurd:dynamicdialog:v1.0.0'
          
	}
  

# Instraction
## SuccessDialog
The dialog box will automatically disappear after a period of time.
```Java
    new SuccessDialog(this).setSuccessText("删除成功").show();
```
![](https://github.com/mr-absurd/DynamicDialog/blob/master/Screenshots/Screenshot_2017-10-02-13-51-51.png)
## ErrorDialog
The dialog box will automatically disappear after a period of time.
```Java
    new ErrorDialog(this).setErrorText("删除失败").show();
```
![](https://github.com/mr-absurd/DynamicDialog/blob/master/Screenshots/Screenshot_2017-10-02-13-51-57.png)

## LoadingDialog
This dialog box is used for operations that require the user to wait and can display the results of the operation.
```Java
      //Set the relevant parameters
      loadingDialog = new LoadingDialog(this)
                        .setLoadText("删除中...")
                        .setErrorText("删除失败")
                        .setSuccessText("删除成功");
      loadingDialog.show();
      
      //Destroy the dialog box and prompt the user to manipulate the results
      loadingDialog.setResult(boolean).dismiss();
      
```
![](https://github.com/mr-absurd/DynamicDialog/blob/master/Screenshots/Screenshot_2017-10-02-14-07-36.png)

## WarnDialog
This dialog box is used for operations that require user selection, which is usually irreversible.
```Java
       //Set the relevant parameters
      warndialog = new WarnDialog(this)
                .setWarnText("你确定要删除此文件吗？")
                .setLoadText("删除中...")
                .setSuccessText("删除成功")
                .setErrorText("删除失败")
                .setWarnDialogListener(this);
      warndialog.show();
      //This interface is a callback for user clicks
     @Override
    public void onConfirm() {
       
    }

    @Override
    public void onCancle() {

    }
    
      //Destroy the dialog box and prompt the user to manipulate the results
     warndialog.setResult(b).dismiss();
    
```
![](https://github.com/mr-absurd/DynamicDialog/blob/master/Screenshots/Screenshot_2017-10-02-14-07-42.png)

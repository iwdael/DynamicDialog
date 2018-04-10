# DynamicDialog  [![](https://jitpack.io/v/blackchopper/dynamicdialog.svg)](https://jitpack.io/#blackchopper/dynamicdialog)
Dynamicdialog is an android control that prompts the user. The control contains a lot of animation, including dialog box pops up, dialog box destroyed, loaded animation, successful animation, failed animation.[中文文档](https://github.com/blackchopper/Dynamicdialog/blob/master/README_CHINESE.md)
## Instruction
Dynamicdialog contains four different styles of dialog, which is used in different scenarios. Users can choose the right dialog according to their needs.
### Code Sample
The success dialog box will automatically disappear after a period of time.
```Java
    new SuccessDialog(this).setSuccessText("删除成功").show();
```
The error dialog box will automatically disappear after a period of time.
```Java
    new ErrorDialog(this).setErrorText("删除失败").show();
```
This load dialog box is used for operations that require the user to wait and can display the results of the operation.
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
This warn dialog box is used for operations that require user selection, which is usually irreversible.
```Java
 
      warndialog = new WarnDialog(this)
                .setWarnText("你确定要删除此文件吗？")
                .setLoadText("删除中...")
                .setSuccessText("删除成功")
                .setErrorText("删除失败")
                .setWarnDialogListener(new WarnDialogListener() {
                                                     @Override
                                                     public void onConfirm() {
                                                                                                              
                                                     }
                                                                              
                                                     @Override
                                                     public void onCancle() {
                                                                              
                                                     }
                                        });
      warndialog.show();
 
```

## How to
To get a Git project into your build:
### Step 1. Add the JitPack repository to your build file
Add it in your root build.gradle at the end of repositories.   [click here for details](https://github.com/blackchopper/CarouselBanner/blob/master/root_build.gradle.png)
 ```Java
 	allprojects {
 		repositories {
 			...
 			maven { url 'https://jitpack.io' }
 		}
 	}
 ```
### Step 2. Add the dependency
Add it in your application module build.gradle at the end of dependencies where you want to use.[click here for details](https://github.com/blackchopper/CarouselBanner/blob/master/application_build.gradle.png)
 ```Java
 	dependencies {
                 ...
 	        compile 'com.github.blackchopper:dynamicdialog:v1.0.5'
 	}
 ```
<br><br>
![Image text](https://github.com/blackchopper/DynamicDialog/blob/master/dynamicdialog.gif)
<br><br><br>
## Thank you for your browsing
If you have any questions, please join the QQ group. I will do my best to answer it for you. Welcome to star and fork this repository, alse follow me.
<br>
![Image Text](https://github.com/blackchopper/CarouselBanner/blob/master/qq_group.png)
 
	 
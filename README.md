# DynamicDialog
[![](https://img.shields.io/badge/platform-android-orange.svg)](https://github.com/hacknife) [![](https://img.shields.io/badge/language-java-yellow.svg)](https://github.com/hacknife) [![](https://img.shields.io/badge/JCenter-1.0.8-brightgreen.svg)](http://jcenter.bintray.com/com/hacknife/dynamicdialog/) [![](https://img.shields.io/badge/build-passing-brightgreen.svg)](https://github.com/hacknife) [![](https://img.shields.io/badge/license-apache--2.0-green.svg)](https://github.com/hacknife) [![](https://img.shields.io/badge/api-11+-green.svg)](https://github.com/hacknife)<br/><br/>
DynamicDialog是一个提示用户的安卓控件。该控件包含大量动画，包括弹出对话框、加载动画、成功动画、失败动画等。
<br><br>
![Image text](https://github.com/hacknife/DynamicDialog/blob/master/dynamicdialog.gif)
<br><br><br>
## 使用说明
Dynamicdialog包含四种不同风格的dialog，实用于不同的场景。使用者可以根据自己的需求选择合适的dialog。
### 代码示例
此Dialog用于展示成功的信息，且在一段时间后自动消失。
```Java
    new SuccessDialog(this).setSuccessText("删除成功").show();
```
此Dialog用于展示错误的信息，且在一段时间后自动消失。
```Java
    new ErrorDialog(this).setErrorText("删除失败").show();
```
This dialog box is used for operations that require the user to wait and can display the results of the operation.
此Dialog用于要求用户等待并显示操作结果的操作。
```Java
      //Set the relevant parameters
      loadingDialog = new LoadingDialog(this)
                        .setLoadText("删除中...")
                        .setErrorText("删除失败")
                        .setSuccessText("删除成功");
      loadingDialog.show();
      
      //设置操作结果，并关闭Dialog.
      loadingDialog.setResult(boolean).dismiss();
      
```
此对话框用于需要用户选择的操作，该操作通常是不可逆的。
```Java
       //Set the relevant parameters
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
## 快速引入项目
合并以下代码到需要使用Module的dependencies中。
```Java
	dependencies {
                ...
	        compile 'com.hacknife:dynamicdialog:1.0.8'
	}
```

## 感谢浏览
如果你有任何疑问，请加入QQ群，我将竭诚为你解答。欢迎Star和Fork本仓库，当然也欢迎你关注我。
<br>
![Image Text](https://github.com/hacknife/CarouselBanner/blob/master/qq_group.png)
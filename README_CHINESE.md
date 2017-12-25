# DynamicDialog  [![](https://jitpack.io/v/aliletter/dynamicdialog.svg)](https://jitpack.io/#aliletter/dynamicdialog)
dynamicdialog是一个Android控件提示用户。的controlcontains很多动画，包括弹出对话框，对话框破坏，加载动画，成功的动画，没有动画。
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
## 如何配置
将本仓库引入你的项目:
### Step 1. 添加JitPack仓库到Build文件
合并以下代码到项目根目录下的build.gradle文件的repositories尾。[点击查看详情](https://github.com/aliletter/CarouselBanner/blob/master/root_build.gradle.png)
```Java
	allprojects {
		repositories {
			...
			maven { url 'https://jitpack.io' }
		}
	}
```
### Step 2. 添加依赖   
合并以下代码到需要使用的application Module的dependencies尾。[点击查看详情](https://github.com/aliletter/CarouselBanner/blob/master/application_build.gradle.png)
```Java
	dependencies {
                ...
	        compile 'com.github.aliletter:dynamicdialog:v1.0.1'
	}
```
<br><br>
![Image text](https://github.com/aliletter/arcmenu/blob/master/dynamicdialog.gif)
<br><br><br>
## 感谢浏览
如果你有任何疑问，请加入QQ群，我将竭诚为你解答。欢迎Star和Fork本仓库，当然也欢迎你关注我。
<br>
![Image Text](https://github.com/aliletter/CarouselBanner/blob/master/qq_group.png)
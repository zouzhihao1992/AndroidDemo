Android开发中常用的Demo

# 项目遵循的命名规范
###1、控件id的命名： 控件名+所在Activity名（或者Fragment）+ 用途  ,全部小写，采用下划线命名法.
	eg: listview_mainactivity_mainmenu
	//好处是，在java中，输入listview，ide就会给出提示。

###2、Activity的命名：用途+Activity，采用驼峰命名
	eg:MainActicity //清晰易懂

###3、资源的命名
####1）string和string-array
	//命名使用下划线命名法，采用以下规则：所在模块名+逻辑名称
	<string-array name="main_activity_menu">
		<item>常用控件</item>
		<item>MaterialDesign控件</item>
	</string-array>
	<string name="app_name">AndridDemo</string>

####2)layout文件的命名
使用下划线命名法。
类型 + 用途（或者逻辑名）

类型有：

* activity_layout :表示activity中的布局文件
* fragment_layout :表示fragment中的布局文件
* item :listview , gridview等的item。

###4、域的命名
####1）一般原则（首字母确定）
* 非公有，非静态字段命名以m开头。
* 静态字段命名以s开头。
* 公有非静态字段命名以p开头。
* 公有静态字段（全局变量）命名以g开头。
public static final 字段(常量) 全部大写，并用下划线连起来。

```java
public class MyClass {
	public static final int SOME_CONSTANT = 42;
	public int pField;
	private static MyClass sSingleton;
	int mPackagePrivate;
	private int mPrivate;
	protected int mProtected;
	public static int gField;
}
```
####2）具体情况
* Activity或者Fragment这样的“view”中域，第一个字母小写，后面单词首字母大写。
	* 如果是需要展示的数据，首字母+数据类型+用途（或者逻辑名称）

	```
	private String[] mStringsMenuFirstLevel;
	```
	* 如果是控件，首字母+控件名+用途

	```
	private ListView mListViewMainMenu;
	```

### 5、局部变量命名
局部变量名以LowerCamelCase风格编写，一般使用用途命名。


### 6、临时变量命名
临时变量通常被取名为i，j，k，m和n，它们一般用于整型；c，d，e，它们一般用于字符型。 如： for (int i = 0; i < len ; i++)，并且它和第一个单词间没有空格。



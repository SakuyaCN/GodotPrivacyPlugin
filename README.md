# GodotPrivacyPlugin 使用说明

此源码包括sdk与godot两部分的完整项目，下载后导入到4.2版本的引擎中便可使用。
* 支持引擎版本：Godot 4.2+

_________________
## 安装方法
1. 复制项目本目录下plugin\demo\addons\GodotPrivacyPlugin文件内全部内容到你的add项目中或者自行编译整个项目。
2. 
   编译命令：./gradlew assemble，编译后的所有东西都在plugin\demo\addons\GodotPrivacyPlugin
2. 打开项目设置-插件-启用GodotPrivacyPlugin。
3. 项目导出时在**自定义构建** 中勾选启动。
4. 导出配置中取消勾选显示在App库中与作为启动器App显示。
_________________
## 参数说明
```
{
	"textPath":"res://test.txt", //隐私协议文本路径
	"backgroundColor":"#F2F2F2", //背景颜色
	"contentColor":"#2E5266", //内容背景颜色
	"textColor":"#F2F2F2", //内容文本颜色
	"agreeBtn":{
		"color":"#70C1B3", //同意按钮颜色
		"textColor":"#0f0f0f" //同意按钮文本颜色
	},
	"cancelBtn":{
		"color":"#F25F5C", //取消按钮颜色
		"textColor":"#F2F2F2" //取消按钮文本颜色
	}
}

```
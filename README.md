# GodotPrivacyPlugin 使用说明

此源码包括sdk与godot两部分的完整项目，下载后导入到4.2版本的引擎中便可使用。
* 支持引擎版本：Godot 4.2+

_________________
## 安装方法
1. 复制项目本目录下plugin\demo\addons\GodotPrivacyPlugin文件内全部内容到你的项目addons目录中，或者自行编译整个项目。
2. 编译命令：`./gradlew assemble`，编译后的所有东西都在 `plugin\demo\addons\GodotPrivacyPlugin`
3. 打开项目设置-插件-启用GodotPrivacyPlugin。
4. 项目导出时在**自定义构建** 中勾选启动。
5. 导出配置中取消勾选显示在App库中与作为启动器App显示。
_________________
## 参数说明
```json
{
	"textPath":"res://test.txt", //隐私协议文本路径
	"backgroundColor":"#F2F2F2", //背景颜色
	"contentColor":"#2E5266",    //内容背景颜色
	"textColor":"#F2F2F2",       //内容文本颜色
	"agreeBtn":{
		"color":"#70C1B3",       //同意按钮颜色
		"textColor":"#0f0f0f"    //同意按钮文本颜色
	},
	"cancelBtn":{
		"color":"#F25F5C",       //取消按钮颜色
		"textColor":"#F2F2F2"    //取消按钮文本颜色
	}
}
```

_________________
## 隐私协议文本格式

隐私协议文本支持 **Markdown** 和部分 **HTML** 语法。

### 支持的格式

| 格式 | 语法 | 示例 |
|------|------|------|
| 标题 | `#` / `##` / `###` | `# 一级标题` |
| 粗体 | `**文本**` | `**重要内容**` |
| 斜体 | `*文本*` | `*提示信息*` |
| 链接 | `[显示文本](URL)` | `[用户协议](https://example.com/agreement)` |
| 列表 | `- 项目` 或 `1. 项目` | `- 第一条` |
| 分割线 | `---` | `---` |
| 对齐 | `<align center>文本</align>` | `<align center>居中内容</align>` |




### 快速使用
隐私协议文本默认位于 `addons/GodotPrivacyPlugin/privacy.txt`，支持 **Markdown** 和部分 **HTML** 语法。
只需修改 `privacy.txt` 中的两个链接地址即可：

```markdown
# 登录前请先阅读并同意：
## [**《用户协议》**](https://www.xxxx.com)
## [**《隐私政策》**](https://www.xxxx.com)
```

将 `https://www.xxxx.com` 替换为你的实际网址即可。

### 链接示例

```markdown
请阅读并同意我们的[用户协议](https://example.com/user-agreement)和[隐私政策](https://example.com/privacy-policy)。
```

用户点击链接后会自动在浏览器中打开对应网址。

_________________
## 更新日志

### v1.1.0
- 新增：隐私协议文本支持 Markdown 链接语法，用户可直接点击链接跳转网页
- 优化：添加 markwon-linkify 依赖增强链接识别能力

### v1.0.0
- 初始版本
- 支持隐私协议弹窗显示
- 支持自定义配色方案
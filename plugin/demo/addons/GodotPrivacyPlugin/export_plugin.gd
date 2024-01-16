@tool
extends EditorPlugin

const preview = preload("res://addons/GodotPrivacyPlugin/Preivew.tscn")

var export_plugin : AndroidExportPlugin

var ins

func _enter_tree():
	# Initialization of the plugin goes here.

	export_plugin = AndroidExportPlugin.new()
	add_export_plugin(export_plugin)
	add_tool_menu_item("隐私页面预览",func onClick():
		if ins:
			ins.popup_centered()
		else:
			ins = preview.instantiate()
			get_editor_interface().get_base_control().add_child(ins)
			ins.popup_centered()
		)


func _exit_tree():
	remove_tool_menu_item("隐私页面预览")
	remove_export_plugin(export_plugin)
	export_plugin = null
	if ins:
		ins.queue_free()

func _make_visible(visible):
	if ins:
		ins.visible = visible

func _has_main_screen():
	return true

func _get_plugin_icon():
	# Must return some kind of Texture for the icon.
	return EditorInterface.get_editor_theme().get_icon("Node", "EditorIcons")

class AndroidExportPlugin extends EditorExportPlugin:
	# TODO: Update to your plugin's name.
	var _plugin_name = "GodotPrivacyPlugin"

	func _supports_platform(platform):
		if platform is EditorExportPlatformAndroid:
			return true
		return false

	func _get_android_libraries(platform, debug):
		if debug:
			return PackedStringArray([_plugin_name + "/bin/debug/" + _plugin_name + "-debug.aar"])
		else:
			return PackedStringArray([_plugin_name + "/bin/release/" + _plugin_name + "-release.aar"])

	func _get_android_dependencies(platform: EditorExportPlatform, debug: bool) -> PackedStringArray:
		return PackedStringArray(["androidx.appcompat:appcompat:1.6.1","androidx.constraintlayout:constraintlayout:2.1.3","com.google.android.material:material:1.5.0","io.noties.markwon:core:4.6.2","io.noties.markwon:html:4.6.2","com.google.code.gson:gson:2.10.1"])

	func _get_name():
		return _plugin_name

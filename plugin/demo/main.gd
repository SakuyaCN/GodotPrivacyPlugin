extends Node2D

# TODO: Update to match your plugin's name
var _plugin_name = "GodotPrivacyPlugin"
var _android_plugin

func _ready():
	if Engine.has_singleton(_plugin_name):
		_android_plugin = Engine.get_singleton(_plugin_name)
	else:
		printerr("Couldn't find plugin " + _plugin_name)
		
	var config = ConfigFile.new()
	# Load data from a file.

func _on_Button_pressed():
	if _android_plugin:
		# TODO: Update to match your plugin's API
		_android_plugin.helloWorld()

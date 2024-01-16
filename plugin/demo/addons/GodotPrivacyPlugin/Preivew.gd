@tool
extends Window

const json_file = "res://addons/GodotPrivacyPlugin/config.json"

@onready var background = $ColorRect
@onready var panel = $ColorRect/Panel
@onready var label = $ColorRect/Panel/ScrollContainer/Label
@onready var agree_btn = $ColorRect/Panel/Button
@onready var cancel_btn = $ColorRect/Panel/Button2

func loadJson():
	var file = FileAccess.open(json_file, FileAccess.READ)
	var content = file.get_as_text()
	return JSON.parse_string(content)

func loadText(entity):
	var file = FileAccess.open(entity.textPath, FileAccess.READ)
	var content = file.get_as_text()
	label.text = content

func loadPanel():
	var entity = loadJson()
	loadText(entity)
	background.color = Color(entity.backgroundColor)
	label.label_settings.set_font_color(Color(entity.textColor))
	panel.get("theme_override_styles/panel").set("bg_color",Color(entity.contentColor))
	agree_btn.get("theme_override_styles/normal").set("bg_color",Color(entity.agreeBtn.color))
	agree_btn.set("theme_override_colors/font_color",Color(entity.agreeBtn.textColor))
	cancel_btn.get("theme_override_styles/normal").set("bg_color",Color(entity.cancelBtn.color))
	cancel_btn.set("theme_override_colors/font_color",Color(entity.cancelBtn.textColor))

func _on_close_requested() -> void:
	hide()

func _on_about_to_popup() -> void:
	loadPanel()


func _on_focus_entered() -> void:
	loadPanel()

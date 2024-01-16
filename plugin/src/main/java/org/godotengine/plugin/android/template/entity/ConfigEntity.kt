package org.godotengine.plugin.android.template.entity

data class ConfigEntity(
    val agreeBtn: AgreeBtn,
    val backgroundColor: String,
    val cancelBtn: CancelBtn,
    val contentColor: String,
    val textColor: String,
    val textPath: String
)

data class AgreeBtn(
    val color: String,
    val textColor: String
)

data class CancelBtn(
    val color: String,
    val textColor: String
)


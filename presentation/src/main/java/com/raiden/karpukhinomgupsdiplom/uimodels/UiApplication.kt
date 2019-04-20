package com.raiden.karpukhinomgupsdiplom.uimodels

data class UiApplication(
    val name: String,
    val currentVersionNameMd5: String,
    val oldVersionNameMd5: String = "",
    val packageName: String = ""
) : UiContent {
    override val primaryKey = packageName
    override val nameContent = name
    override var isDeleted: Boolean = false
    override var isInstalled: Boolean = false
    override var isUpdated: Boolean = false
}
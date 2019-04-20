package com.raiden.karpukhinomgupsdiplom.uimodels

data class UiApplication(
    val name: String,
    val versionNameMd5: String,
    val packageName: String = "",
    var oldVersionNameMd5: String = ""
) : UiContent {
    override val primaryKey = versionNameMd5
    override val nameContent = name
    override var isDeleted: Boolean = false
    override var isInstalled: Boolean = false
    override var isUpdated: Boolean = false
}
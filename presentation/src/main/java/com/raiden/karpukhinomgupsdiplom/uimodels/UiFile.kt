package com.raiden.karpukhinomgupsdiplom.uimodels

data class UiFile(
    val name: String
) : UiContent {
    override val primaryKey = name
    override val nameContent = name
    override var isDeleted: Boolean = false
    override var isInstalled: Boolean = false
    override var isUpdated: Boolean = false
}
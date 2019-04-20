package com.raiden.karpukhinomgupsdiplom.uimodels

data class UiContact(
    val id: String,
    val currentName: String,
    val currentPhoneNumber: String,
    var oldName: String = "",
    var oldPoneNumber: String = ""
) : UiContent {
    override val primaryKey = id
    override val nameContent = currentName
    override var isDeleted: Boolean = false
    override var isInstalled: Boolean = false
    override var isUpdated: Boolean = false
}
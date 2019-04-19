package com.raiden.karpukhinomgupsdiplom.content.applications.model

import com.raiden.karpukhinomgupsdiplom.content.common.models.UiContent

data class UiApplication(
    val name: String,
    val versionName: String,
    val packageName: String
) : UiContent {
    override val primaryKey = packageName
    override val nameContent = name
    override var isDeleted: Boolean = false
    override var isInstalled: Boolean = false
    override var isUpdated: Boolean = false
}
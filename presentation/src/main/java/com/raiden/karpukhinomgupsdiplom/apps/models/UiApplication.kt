package com.raiden.karpukhinomgupsdiplom.apps.models

data class UiApplication(
    val name: String,
    val versionName: String,
    val isDeleted: Boolean = false,
    val isUpdated: Boolean = false,
    val isInstalled: Boolean = false
)
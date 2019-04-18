package com.raiden.karpukhinomgupsdiplom.apps.models

data class UiApplication(
    val name: String,
    val versionName: String,
    var isDeleted: Boolean = false,
    var isUpdated: Boolean = false,
    var isInstalled: Boolean = false
)
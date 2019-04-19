package com.raiden.karpukhinomgupsdiplom.content.common.models

interface UiContent {
    val nameContent: String
    val primaryKey: String
    var isDeleted: Boolean
    var isInstalled: Boolean
    var isUpdated: Boolean
}
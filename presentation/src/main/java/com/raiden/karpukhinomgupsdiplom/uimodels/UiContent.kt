package com.raiden.karpukhinomgupsdiplom.uimodels

import java.io.Serializable

interface UiContent : Serializable {
    val nameContent: String
    val primaryKey: String
    var isDeleted: Boolean
    var isInstalled: Boolean
    var isUpdated: Boolean
}
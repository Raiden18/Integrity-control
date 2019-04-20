package com.raiden.karpukhinomgupsdiplom.screens.content.applications

import com.raiden.domain.models.Application
import com.raiden.karpukhinomgupsdiplom.uimodels.UiApplication

internal fun Application.convertToUi(): UiApplication {
    return UiApplication(name, versionNameMd5, packageName)
}

internal fun List<Application>.convertToUi(): List<UiApplication> {
    return map { it.convertToUi() }
}
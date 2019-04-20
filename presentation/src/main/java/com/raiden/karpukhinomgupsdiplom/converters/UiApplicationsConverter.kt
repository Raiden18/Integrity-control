package com.raiden.karpukhinomgupsdiplom.converters

import com.raiden.domain.models.Application
import com.raiden.karpukhinomgupsdiplom.uimodels.UiApplication

internal fun Application.convertToUi(): UiApplication {
    return UiApplication(name, versionNameMd5, packageName)
}

internal fun List<Application>.convertToUi(): List<UiApplication> {
    return map { it.convertToUi() }
}

internal fun UiApplication.convertToDomain(): Application {
    return Application(name, currentVersionNameMd5, packageName)
}
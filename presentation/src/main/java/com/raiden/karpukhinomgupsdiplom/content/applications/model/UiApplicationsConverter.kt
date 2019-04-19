package com.raiden.karpukhinomgupsdiplom.content.applications.model

import com.raiden.domain.models.Application

internal fun Application.convertToUi(): UiApplication {
    return UiApplication(name, versionName)
}

internal fun List<Application>.convertToUi(): List<UiApplication> {
    return map { it.convertToUi() }
}
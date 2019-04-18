package com.raiden.karpukhinomgupsdiplom.apps.models

import com.raiden.domain.models.Application

fun Application.convertToUi(): UiApplication {
    return UiApplication(name, versionName)
}

fun List<Application>.convertToUi(): List<UiApplication> {
    return map { it.convertToUi() }
}
package com.raiden.data.repositories.applications

import com.raiden.data.datasources.database.entities.ApplicationEntity
import com.raiden.domain.models.Application

internal fun ApplicationEntity.convertToDomainApplication(): Application {
    return Application(name, versionName)
}

internal fun Application.convertToEntityApplication(): ApplicationEntity {
    return ApplicationEntity(name, versionName)
}

internal fun Iterable<ApplicationEntity>.convertToDomainApps(): Iterable<Application> {
    return map { it.convertToDomainApplication() }
}

internal fun Iterable<Application>.convertToEntites(): Iterable<ApplicationEntity> {
    return map { it.convertToEntityApplication() }
}
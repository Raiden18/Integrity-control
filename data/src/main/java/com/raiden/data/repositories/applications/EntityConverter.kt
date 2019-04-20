package com.raiden.data.repositories.applications

import com.raiden.data.datasources.database.entities.ApplicationEntity
import com.raiden.domain.models.Application

internal fun ApplicationEntity.convertToDomainApplication(): Application {
    return Application(name, versionName, packageName)
}

internal fun Application.convertToEntityApplication(): ApplicationEntity {
    return ApplicationEntity(name, versionNameMd5, packageName)
}

internal fun Iterable<ApplicationEntity>.convertToDomainApps(): Iterable<Application> {
    return map { it.convertToDomainApplication() }
}

internal fun Iterable<Application>.convertToEntites(): Iterable<ApplicationEntity> {
    return map { it.convertToEntityApplication() }
}
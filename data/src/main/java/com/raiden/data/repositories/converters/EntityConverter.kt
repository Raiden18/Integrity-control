package com.raiden.data.repositories.converters

import com.raiden.data.datasources.database.entities.ApplicationEntity
import com.raiden.domain.models.Application

internal fun ApplicationEntity.convertToDomainApplication(): Application {
    return Application(name, versionName)
}

internal fun Application.convertToEntityApplication(): ApplicationEntity {
    return ApplicationEntity(name, versionName)
}

internal fun List<ApplicationEntity>.convertToDomainApps(): List<Application> {
    return map { it.convertToDomainApplication() }
}
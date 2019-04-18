package com.raiden.domain.interactors.applications

import com.raiden.domain.models.Application

interface ApplicationsInteractor {
    suspend fun getSavedApps(): Iterable<Application>
    suspend fun getDeviceApps(): Iterable<Application>
}
package com.raiden.domain.gateways

import com.raiden.domain.models.Application

interface ApplicationsGateway {
    suspend fun saveApplicationsFromDevice()
    suspend fun getSavedApplications(): Iterable<Application>
    suspend fun getAppsFromDevice(): Iterable<Application>
}
package com.raiden.domain.interactors.applications

import com.raiden.domain.gateways.ApplicationsGateway
import com.raiden.domain.models.Application

internal class ApplicationsInteractorImpl(private val applicationsGateway: ApplicationsGateway) :
    ApplicationsInteractor {

    override suspend fun getSavedApps(): Iterable<Application> {
        return applicationsGateway.getSavedApplications()
    }

    override suspend fun getDeviceApps(): Iterable<Application> {
        return applicationsGateway.getAppsFromDevice()
    }
}
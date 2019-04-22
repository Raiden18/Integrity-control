package com.raiden.domain.interactors.info.commands

import com.raiden.domain.gateways.ApplicationsGateway
import com.raiden.domain.models.Application

internal class GetCountOfDeletedApps(private val gateway: ApplicationsGateway) {
    private var countOfDeletedApps = 0

    suspend fun getCountOfDeletedApps(): Int {
        val savedApps = gateway.getSavedApplications().toList()
        val appsFromDevice = gateway.getAppsFromDevice()
        if (!savedApps.isEmpty()) {
            countOfDeletedApps(savedApps, appsFromDevice)
        }
        return countOfDeletedApps
    }

    private fun countOfDeletedApps(savedApps: Iterable<Application>, appsFromDevice: Iterable<Application>) {
        savedApps.forEach { savedApp ->
            if (!appsFromDevice.isContain(savedApp)) {
                countOfDeletedApps++
            }
        }
    }

    private fun Iterable<Application>.isContain(application: Application): Boolean {
        return find { it.packageName == application.packageName } != null
    }
}
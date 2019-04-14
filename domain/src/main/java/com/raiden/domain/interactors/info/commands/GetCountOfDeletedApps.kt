package com.raiden.domain.interactors.info.commands

import com.raiden.domain.gateways.ApplicationsGateway
import com.raiden.domain.models.Application

internal class GetCountOfDeletedApps(private val gateway: ApplicationsGateway) {
    private var countOfdeletedApps = 0

    suspend fun getCountOfDeletedApps(): Int {
        countOfdeletedApps = 0
        val savedApps = gateway.getSavedApplications().toList()
        val appsFromDevice = gateway.getAppsFromDevice()
        if (!savedApps.isEmpty()) {
            countOfDeletedApps(savedApps, appsFromDevice)
        }
        return countOfdeletedApps
    }

    private fun countOfDeletedApps(savedApps: Iterable<Application>, appsFromDevice: Iterable<Application>) {
        savedApps.forEach { savedApp ->
            if (!appsFromDevice.isInstalled(savedApp)) {
                countOfdeletedApps++
            }
        }
    }

    private fun Iterable<Application>.isInstalled(savedApp: Application): Boolean {
        return find { it.name == savedApp.name } != null
    }
}
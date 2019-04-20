package com.raiden.domain.interactors.info.commands

import com.raiden.domain.gateways.ApplicationsGateway
import com.raiden.domain.models.Application

internal class GetUpdatedAppsCommand(private val gateway: ApplicationsGateway) {
    private var countOfUpdatedApps = 0

    suspend fun getCountOfUpdatedApps(): Int {
        val savedApps = gateway.getSavedApplications().toList()
        val currentDevice = gateway.getAppsFromDevice()
        if (!savedApps.isEmpty()) {
            countUpdatedApps(savedApps, currentDevice)
        }
        return countOfUpdatedApps
    }

    private fun countUpdatedApps(savedApps: Iterable<Application>, currentDevice: Iterable<Application>) {
        countOfUpdatedApps = 0
        currentDevice.forEach { appsFromDevice ->
            savedApps.forEach { savedApp ->
                checkVersionCodeIfAppsNameEquals(appsFromDevice, savedApp)
            }
        }
    }

    private fun checkVersionCodeIfAppsNameEquals(appsFromDevice: Application, savedApp: Application) {
        if (appsFromDevice.packageName == savedApp.packageName) {
            incCountOfUpdateAppIfVersionCodeDifferent(appsFromDevice, savedApp)
        }
    }

    private fun incCountOfUpdateAppIfVersionCodeDifferent(appsFromDevice: Application, savedApp: Application) {
        if (appsFromDevice.versionNameMd5 != savedApp.versionNameMd5) {
            countOfUpdatedApps++
        }
    }
}
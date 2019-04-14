package com.raiden.domain.interactors.info.commands

import com.raiden.domain.gateways.ApplicationsGateway
import com.raiden.domain.models.Application

internal class GetCountOfInstalledApp(private val gateway: ApplicationsGateway) {
    private var countOfUpdatedApps = 0
    suspend fun getCountOfDeletedApps(): Int {
        val installedApps = gateway.getAppsFromDevice().toList()
        val savedApps = gateway.getSavedApplications()
        if (!installedApps.isEmpty()) {
            findInstalledApps(installedApps, savedApps)
        }
        return countOfUpdatedApps
    }

    private fun findInstalledApps(installedApps: Iterable<Application>, savedApps: Iterable<Application>) {
        installedApps.forEach { installedApp ->
            if (!savedApps.isUninstall(installedApp)){
                countOfUpdatedApps++
            }
        }
    }

    private fun Iterable<Application>.isUninstall(app: Application): Boolean {
        return find { it.name == app.name } != null
    }
}
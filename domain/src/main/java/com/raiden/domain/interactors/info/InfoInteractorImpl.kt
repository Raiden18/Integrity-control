package com.raiden.domain.interactors.info

import com.raiden.domain.gateways.ApplicationsGateway
import com.raiden.domain.interactors.info.commands.GetCountOfDeletedApps
import com.raiden.domain.interactors.info.commands.GetCountOfInstalledApp
import com.raiden.domain.interactors.info.commands.GetUpdatedAppsCommand
import sun.rmi.runtime.Log

internal class InfoInteractorImpl(private val gateway: ApplicationsGateway) : InfoInteractor {

    override suspend fun getCountOfUpdatedApps(): Int {
        val commandGetUpdatedApps = GetUpdatedAppsCommand(gateway)
        return commandGetUpdatedApps.getCountOfUpdatedApps()
    }

    override suspend fun getCountOfDeletedApps(): Int {
        val commandGetCountOfDeletedApps = GetCountOfDeletedApps(gateway)
        return commandGetCountOfDeletedApps.getCountOfDeletedApps()
    }

    override suspend fun getCountOfInstalledApps(): Int {
        val commandGetCountOfInstalledApp = GetCountOfInstalledApp(gateway)
        return commandGetCountOfInstalledApp.getCountOfDeletedApps()
    }

    override suspend fun getCountOfChangedFiles(): Int {
        return 0
    }

    override suspend fun getCountOfAddedFiles(): Int {
        return 0
    }

    override suspend fun getCountOfDeletedFiles(): Int {
        return 0
    }

    override suspend fun isChangedContacts(): Boolean {
        return false
    }
}
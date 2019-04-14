package com.raiden.domain.interactors.info

import com.raiden.domain.gateways.ApplicationsGateway
import com.raiden.domain.interactors.info.commands.GetUpdatedAppsCommand
import com.raiden.domain.models.Application

internal class InfoInteractorImpl(gateway: ApplicationsGateway) : InfoInteractor {
    private val commandGetUpdatedApps = GetUpdatedAppsCommand(gateway)
    private val commandGetCountOfDeletedApps = GetUpdatedAppsCommand(gateway)

    override suspend fun getCountOfUpdatedApps(): Int {
        return commandGetUpdatedApps.getCountOfUpdatedApps()
    }

    override suspend fun getCountOfDeletedApps(): Int {
        return commandGetCountOfDeletedApps.getCountOfUpdatedApps()
    }

    override suspend fun getCountOfInstalledApps(): Int {
        return 1
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
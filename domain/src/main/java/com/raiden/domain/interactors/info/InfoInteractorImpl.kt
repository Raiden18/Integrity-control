package com.raiden.domain.interactors.info

import com.raiden.domain.gateways.ApplicationsGateway
import com.raiden.domain.gateways.FilesGateway
import com.raiden.domain.interactors.info.commands.GetCountOFAddedFiles
import com.raiden.domain.interactors.info.commands.GetCountOfDeletedApps
import com.raiden.domain.interactors.info.commands.GetCountOfInstalledApp
import com.raiden.domain.interactors.info.commands.GetUpdatedAppsCommand

internal class InfoInteractorImpl(private val applicationsGateway: ApplicationsGateway,
                                  private val filesGateway: FilesGateway) : InfoInteractor {

    override suspend fun getCountOfUpdatedApps(): Int {
        val commandGetUpdatedApps = GetUpdatedAppsCommand(applicationsGateway)
        return commandGetUpdatedApps.getCountOfUpdatedApps()
    }

    override suspend fun getCountOfDeletedApps(): Int {
        val commandGetCountOfDeletedApps = GetCountOfDeletedApps(applicationsGateway)
        return commandGetCountOfDeletedApps.getCountOfDeletedApps()
    }

    override suspend fun getCountOfInstalledApps(): Int {
        val commandGetCountOfInstalledApp = GetCountOfInstalledApp(applicationsGateway)
        return commandGetCountOfInstalledApp.getCountOfDeletedApps()
    }

    override suspend fun getCountOfChangedFiles(): Int {
        //TODO: Implement changing files
        return 0
    }

    override suspend fun getCountOfAddedFiles(): Int {
        val commandCountOfAddedField = GetCountOFAddedFiles(filesGateway)
        return commandCountOfAddedField.getCountOfAddedFiles()
    }

    override suspend fun getCountOfDeletedFiles(): Int {
        return 0
    }

    override suspend fun isChangedContacts(): Boolean {
        return false
    }
}
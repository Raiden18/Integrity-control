package com.raiden.domain.interactors.info

import com.raiden.domain.gateways.ApplicationsGateway
import com.raiden.domain.gateways.ContactsGateway
import com.raiden.domain.gateways.FilesGateway
import com.raiden.domain.interactors.info.commands.*

internal class InfoInteractorImpl(
    private val applicationsGateway: ApplicationsGateway,
    private val filesGateway: FilesGateway,
    private val contactsGateway: ContactsGateway
) : InfoInteractor {

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
        val commandGetCountOfDeletedFiles = GetCountOfDeletedFiles(filesGateway)
        return commandGetCountOfDeletedFiles.getCountOfDeletedFiles()
    }

    override suspend fun isChangedContacts(): Boolean {
        val isChangedContactsCommand = IsChangedContactsCommand(contactsGateway)
        return isChangedContactsCommand.isChange()
    }
}
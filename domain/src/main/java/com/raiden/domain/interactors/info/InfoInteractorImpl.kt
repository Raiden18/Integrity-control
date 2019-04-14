package com.raiden.domain.interactors.info

import com.raiden.domain.gateways.ApplicationsGateway

internal class InfoInteractorImpl(private val gateway: ApplicationsGateway) : InfoInteractor {

    override fun getCountOfUpdatedApps(): Int {
        return 0
    }

    override fun getCountOfDeletedApps(): Int {
        return 0
    }


    override fun getCountOfInstalledApps(): Int {
        return 1
    }

    override fun getCountOfChangedFiles(): Int {
        return 0
    }

    override fun getCountOfAddedFiles(): Int {
        return 0
    }

    override fun getCountOfDeletedFiles(): Int {
        return 0
    }

    override fun isChangedContacts(): Boolean {
        return false
    }
}
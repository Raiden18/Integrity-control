package com.raiden.data.repositories.applications

import android.util.Log
import com.raiden.data.datasources.database.dao.ApplicationsDao
import com.raiden.data.datasources.device.applications.DeviceApplications
import com.raiden.domain.gateways.ApplicationsGateway
import com.raiden.domain.models.Application

internal class ApplicationsRepository(
    private val device: DeviceApplications,
    private val appsDao: ApplicationsDao
) : ApplicationsGateway {

    override suspend fun saveApplicationsFromDevice() {
        val devicesApps = device.getApplications()
        appsDao.clearTable()
        devicesApps.forEach {
            convertToEntityAndAddToDb(it)
        }
    }

    override suspend fun getSavedApplications(): Iterable<Application> {
        if (appsDao.getApplications().isEmpty()) {
            saveApplicationsFromDevice()
        }
        return appsDao.getApplications()
            .convertToDomainApps()
    }

    override suspend fun getAppsFromDevice(): Iterable<Application> {
        return device.getApplications()
    }

    private suspend fun convertToEntityAndAddToDb(app: Application) {
        val entity = app.convertToEntityApplication()
        appsDao.insert(entity)
    }
}
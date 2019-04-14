package com.raiden.data.repositories

import com.raiden.data.datasources.database.dao.ApplicationsDao
import com.raiden.data.datasources.device.DeviceApplications
import com.raiden.data.repositories.converters.convertToDomainApps
import com.raiden.data.repositories.converters.convertToEntityApplication
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
        val savedApps = appsDao.getApplications()
        return savedApps.convertToDomainApps()
    }

    override suspend fun getAppsFromDevice(): Iterable<Application> {
        return device.getApplications()
    }

    private suspend fun convertToEntityAndAddToDb(app: Application) {
        val entity = app.convertToEntityApplication()
        appsDao.insert(entity)
    }
}
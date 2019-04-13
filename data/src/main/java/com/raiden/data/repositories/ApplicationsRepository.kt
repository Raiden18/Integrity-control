package com.raiden.data.repositories

import com.raiden.data.sources.database.dao.ApplicationsDao
import com.raiden.data.sources.device.DeviceApplications
import com.raiden.domain.gateways.ApplicationsGateway
import com.raiden.domain.models.Application

class ApplicationsRepository(private val device: DeviceApplications,
                             private val appsDao: ApplicationsDao) : ApplicationsGateway {
    override fun getApplications(): Iterable<Application> {
        return device.getApplications()
    }
}
package com.raiden.data.datasources.device.applications

import com.raiden.domain.models.Application

interface DeviceApplications {
    fun getApplications(): Iterable<Application>
}
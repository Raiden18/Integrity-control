package com.raiden.data.datasources.device.applications

import com.raiden.domain.models.Application

internal interface DeviceApplications {
    fun getApplications(): Iterable<Application>
}
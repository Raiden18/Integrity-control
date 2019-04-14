package com.raiden.data.datasources.device

import com.raiden.domain.models.Application

interface DeviceApplications {
    fun getApplications(): Iterable<Application>
}
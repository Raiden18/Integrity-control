package com.raiden.data.sources.device

import com.raiden.domain.models.Application

interface DeviceApplications {
    fun getApplications(): Iterable<Application>
}
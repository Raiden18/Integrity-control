package com.raiden.domain.gateways

import com.raiden.domain.models.Application

interface ApplicationsGateway {
    fun getApplications(): Iterable<Application>
}
package com.raiden.domain.gateways

import com.raiden.domain.models.UpdateTime

interface UpdatedTimeGateway {
    suspend fun saveUpdatedTime()
    suspend fun getUpdatedTime(): UpdateTime
}
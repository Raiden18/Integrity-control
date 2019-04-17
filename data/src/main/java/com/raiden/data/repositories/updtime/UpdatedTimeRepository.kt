package com.raiden.data.repositories.updtime

import com.raiden.data.datasources.sharedpreferences.updatedtime.UpdatedSharedPreferences
import com.raiden.domain.gateways.UpdatedTimeGateway
import com.raiden.domain.models.UpdateTime

internal class UpdatedTimeRepository(private val updatedSharedPreferences: UpdatedSharedPreferences) : UpdatedTimeGateway {

    override suspend fun saveUpdatedTime(updateTime: UpdateTime) {
        updatedSharedPreferences.save(updateTime)
    }

    override suspend fun getUpdatedTime(): UpdateTime {
        return updatedSharedPreferences.get()
    }
}
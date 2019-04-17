package com.raiden.data.datasources.sharedpreferences.updatedtime

import com.raiden.domain.models.UpdateTime

internal interface UpdatedSharedPreferences {
    suspend fun save(updateTime: UpdateTime)
    suspend fun get(): UpdateTime
}
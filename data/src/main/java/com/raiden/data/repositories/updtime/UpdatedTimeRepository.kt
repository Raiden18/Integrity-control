package com.raiden.data.repositories.updtime

import android.annotation.SuppressLint
import com.raiden.data.datasources.sharedpreferences.updatedtime.UpdatedSharedPreferences
import com.raiden.domain.gateways.UpdatedTimeGateway
import com.raiden.domain.models.UpdateTime
import java.text.SimpleDateFormat
import java.util.*

internal class UpdatedTimeRepository(private val updatedSharedPreferences: UpdatedSharedPreferences) :
    UpdatedTimeGateway {
    private companion object {
        const val TIME_PATTERN = "dd/M/yyyy HH:mm:ss"
    }

    override suspend fun saveUpdatedTime() {
        val locale = Locale.getDefault()
        val currentDate = Calendar.getInstance().time
        val currentDateString = SimpleDateFormat(TIME_PATTERN, locale).format(currentDate)
        val updateTime = UpdateTime(currentDateString)
        updatedSharedPreferences.save(updateTime)
    }

    override suspend fun getUpdatedTime(): UpdateTime {
        if (updatedSharedPreferences.get().time.isEmpty()) {
            saveUpdatedTime()
        }
        return updatedSharedPreferences.get()
    }
}
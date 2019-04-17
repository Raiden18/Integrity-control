package com.raiden.data.datasources.sharedpreferences.updatedtime

import android.content.Context
import com.raiden.domain.models.UpdateTime

internal class UpdatedSharedPreferencesImpl(context: Context) : UpdatedSharedPreferences {
    private val KEY_SHARED_PREF = "com.raiden.data.datasources.sharedpreferences.time"
    private val TIME_KEY = "com.raiden.data.datasources.sharedpreferences.time.key"
    private val sharedPref = context.getSharedPreferences(KEY_SHARED_PREF, Context.MODE_PRIVATE)
    private val editor = sharedPref.edit()

    override suspend fun save(updateTime: UpdateTime) {
        val time = updateTime.time
        editor.putString(TIME_KEY, time)
        editor.commit()
    }

    override suspend fun get(): UpdateTime {
        val time = sharedPref.getString(TIME_KEY, "")!!
        return UpdateTime(time)
    }
}
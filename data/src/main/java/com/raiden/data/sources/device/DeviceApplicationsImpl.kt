package com.raiden.data.sources.device

import android.content.Context
import com.raiden.domain.models.Application

internal class DeviceApplicationsImpl(private val context: Context) : DeviceApplications {
    private companion object {
        const val ALL_APPS = 0
    }

    override fun getApplications(): Iterable<Application> {
        val packageInfos = context.packageManager.getInstalledPackages(ALL_APPS)
        val apps = arrayListOf<Application>()
        packageInfos.forEach {
            val versionName = it.versionName
            val appLabel = context.packageManager.getApplicationLabel(it.applicationInfo).toString()
            val application = Application(appLabel, versionName)
            apps.add(application)
        }
        return apps
    }
}
package com.raiden.data.datasources.device.applications

import android.content.pm.PackageInfo
import android.content.pm.PackageManager
import com.raiden.domain.models.Application

internal class DeviceApplicationsImpl(private val packageManager: PackageManager) :
    DeviceApplications {
    private companion object {
        const val ALL_APPS = 0
    }

    private val devicesApps = arrayListOf<Application>()

    override fun getApplications(): Iterable<Application> {
        devicesApps.clear()
        val packageInfos = packageManager.getInstalledPackages(ALL_APPS)
        packageInfos.forEach {
            convertToDomainAndAddToAppsList(it)
        }
        //I just don't want explain to teachers and commission of diploma why there are apps with the same titles in my device
        return devicesApps.distinctBy { it.name }
    }

    private fun convertToDomainAndAddToAppsList(packageInfo: PackageInfo) {
        val versionName = packageInfo.versionName
        val appLabel = packageManager.getApplicationLabel(packageInfo.applicationInfo).toString()
        val application = Application(appLabel, versionName)
        devicesApps.add(application)
    }
}
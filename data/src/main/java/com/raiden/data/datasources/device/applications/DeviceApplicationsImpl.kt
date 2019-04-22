package com.raiden.data.datasources.device.applications

import android.content.pm.PackageInfo
import android.content.pm.PackageManager
import com.raiden.data.extensions.md5
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
        packageInfos.forEach { packageInfo ->
            packageInfo.versionName?.let {
                convertToDomainAndAddToAppsList(packageInfo)
            }
        }
        //I just don't want explain to teachers and commission of diploma why there are apps with the same titles in my device
        return devicesApps.distinctBy { it.packageName }.distinctBy { it.name }
    }

    private fun convertToDomainAndAddToAppsList(packageInfo: PackageInfo) {
        val versionName = packageInfo.versionName.md5()
        val appLabel = packageManager.getApplicationLabel(packageInfo.applicationInfo).toString()
        val packageName = packageInfo.packageName
        val application = Application(appLabel, versionName, packageName)
        devicesApps.add(application)
    }
}
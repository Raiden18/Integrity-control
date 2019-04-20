package com.raiden.karpukhinomgupsdiplom.screens.content.applications

import com.raiden.domain.interactors.applications.ApplicationsInteractor
import com.raiden.karpukhinomgupsdiplom.converters.convertToUi
import com.raiden.karpukhinomgupsdiplom.screens.content.common.ContentViewModel
import com.raiden.karpukhinomgupsdiplom.uimodels.UiApplication
import com.raiden.karpukhinomgupsdiplom.uimodels.UiContent
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers

class ApplicationsViewModel(
    private val applicationsInteractor: ApplicationsInteractor,
    IO: CoroutineDispatcher = Dispatchers.Main,
    DEFAULT: CoroutineDispatcher = Dispatchers.Default
) : ContentViewModel(IO, DEFAULT) {

    override suspend fun loadDeviceContent(): List<UiContent> {
        return applicationsInteractor.getDeviceApps().toList().convertToUi()
    }

    override suspend fun loadSavedContent(): List<UiContent> {
        return applicationsInteractor.getSavedApps().toList().convertToUi()
    }

    override fun calculateChanged() {
        savedContent.forEach { savedApp ->
            savedApp as UiApplication
            deviceContent.forEach { deviceApps ->
                deviceApps as UiApplication
                if (savedApp.packageName == deviceApps.packageName) {
                    if (savedApp.versionNameMd5 != deviceApps.versionNameMd5) {
                        deviceApps.isUpdated = true
                        deviceApps.oldVersionNameMd5 = savedApp.versionNameMd5
                        changesContent.add(deviceApps)
                    }
                }
            }
        }
    }

}
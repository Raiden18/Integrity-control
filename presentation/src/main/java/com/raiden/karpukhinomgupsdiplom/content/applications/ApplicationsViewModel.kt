package com.raiden.karpukhinomgupsdiplom.content.applications

import com.raiden.domain.interactors.applications.ApplicationsInteractor
import com.raiden.karpukhinomgupsdiplom.content.applications.model.UiApplication
import com.raiden.karpukhinomgupsdiplom.content.applications.model.convertToUi
import com.raiden.karpukhinomgupsdiplom.content.common.UiContentViewModel
import com.raiden.karpukhinomgupsdiplom.content.common.models.UiContent
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers

class ApplicationsViewModel(
    private val applicationsInteractor: ApplicationsInteractor,
    IO: CoroutineDispatcher = Dispatchers.Main,
    DEFAULT: CoroutineDispatcher = Dispatchers.Default
) : UiContentViewModel(IO, DEFAULT) {

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
                if (savedApp.name == deviceApps.name) {
                    if (savedApp.versionName != deviceApps.versionName) {
                        deviceApps.isUpdated = true
                        changesContent.add(deviceApps)
                    }
                }
            }
        }
    }

}
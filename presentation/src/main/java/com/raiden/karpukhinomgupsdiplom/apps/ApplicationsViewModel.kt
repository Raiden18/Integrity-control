package com.raiden.karpukhinomgupsdiplom.apps

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.raiden.domain.interactors.applications.ApplicationsInteractor
import com.raiden.karpukhinomgupsdiplom.apps.models.UiApplication
import com.raiden.karpukhinomgupsdiplom.apps.models.convertToUi
import kotlinx.coroutines.*

class ApplicationsViewModel(
    private val applicationsInteractor: ApplicationsInteractor,
    private val IO: CoroutineDispatcher = Dispatchers.Main,
    private val DEFAULT: CoroutineDispatcher = Dispatchers.Default
) : ViewModel() {

    val changedApps = MutableLiveData<List<UiApplication>>()
    val isLoading = MutableLiveData<Boolean>()
    private val savedApps = arrayListOf<UiApplication>()
    private val deviceApps = arrayListOf<UiApplication>()
    private val deletedApps = arrayListOf<UiApplication>()
    private val updatedApps = arrayListOf<UiApplication>()
    private val installedApps = arrayListOf<UiApplication>()

    init {
        loadSavedAndDeviceApps()
        calculateDeletedApps()




        setChangedApps()
    }

    private fun loadSavedAndDeviceApps() {
        GlobalScope.launch(IO) {
            val savedApps = async { applicationsInteractor.getSavedApps() }
            val deviceApps = async { applicationsInteractor.getDeviceApps() }
            val uiSavedApps = savedApps.await()
                .toList()
                .convertToUi()
            val uiDeviceApps = deviceApps.await()
                .toList()
                .convertToUi()
            this@ApplicationsViewModel.savedApps.addAll(uiSavedApps)
            this@ApplicationsViewModel.deviceApps.addAll(uiDeviceApps)
        }
    }

    private fun calculateDeletedApps() {
        savedApps.forEach { savedApp ->
            if (!deviceApps.containsApp(savedApp)) {
                deletedApps.add(savedApp)
            }
        }
    }

    private fun List<UiApplication>.containsApp(uiApp: UiApplication): Boolean {
        return find { it.name == uiApp.name } != null
    }

    private fun setChangedApps() {
        val changedApps = arrayListOf<UiApplication>()
        changedApps.addAll(deletedApps)
        changedApps.addAll(updatedApps)
        changedApps.addAll(installedApps)
        this.changedApps.postValue(changedApps)
    }
}
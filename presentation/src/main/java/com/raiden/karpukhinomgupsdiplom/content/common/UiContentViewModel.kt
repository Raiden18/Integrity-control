package com.raiden.karpukhinomgupsdiplom.content.common

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.raiden.karpukhinomgupsdiplom.content.common.models.UiContent
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.async
import kotlinx.coroutines.launch

//I have no idea why my tests doesn't work.
// Probably, it needs more time to get best practice and libs to test kotlin coroutines
abstract class UiContentViewModel(
    private val IO: CoroutineDispatcher,
    private val DEFAULT: CoroutineDispatcher
) : ViewModel() {

    val changedApps = MutableLiveData<List<UiContent>>()
    val isLoading = MutableLiveData<Boolean>()
    protected val savedContent = arrayListOf<UiContent>()
    protected val deviceContent = arrayListOf<UiContent>()
    protected val deletedContent = arrayListOf<UiContent>()
    protected val changesContent = arrayListOf<UiContent>()
    protected val addedContent = arrayListOf<UiContent>()

    init {
        loadSavedAndDeviceApps()
    }

    abstract suspend fun loadDeviceContent(): List<UiContent>

    abstract suspend fun loadSavedContent(): List<UiContent>

    fun loadSavedAndDeviceApps() {
        GlobalScope.launch(IO) {
            isLoading.postValue(true)
            val savedApps = async(DEFAULT) { loadDeviceContent() }
            val deviceApps = async(DEFAULT) { loadSavedContent() }
            val uiSavedApps = savedApps.await()
                .toList()
            val uiDeviceApps = deviceApps.await()
                .toList()
            this@UiContentViewModel.savedContent.addAll(uiSavedApps)
            this@UiContentViewModel.deviceContent.addAll(uiDeviceApps)
            calculateDeletedContent()
            calculateAdded()
            calculateChanged()
            setChangedApps()
        }
    }


    private fun calculateDeletedContent() {
        savedContent.forEach { savedApp ->
            if (!deviceContent.containsApp(savedApp)) {
                savedApp.isDeleted = true
                deletedContent.add(savedApp)
            }
        }
    }

    private fun List<UiContent>.containsApp(uiContent: UiContent): Boolean {
        return find { it.nameContent == uiContent.nameContent } != null
    }

    private fun calculateAdded() {
        deviceContent.forEach { deviceApp ->
            if (!savedContent.containsApp(deviceApp)) {
                deviceApp.isInstalled = true
                changesContent.add(deviceApp)
            }
        }
    }

    protected abstract fun calculateChanged()

    private fun setChangedApps() {
        val changedApps = arrayListOf<UiContent>()
        changedApps.addAll(deletedContent)
        changedApps.addAll(changesContent)
        changedApps.addAll(addedContent)
        isLoading.postValue(false)
        this.changedApps.postValue(changedApps)
    }
}
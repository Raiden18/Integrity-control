package com.raiden.karpukhinomgupsdiplom.content.common

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.raiden.karpukhinomgupsdiplom.content.common.models.UiContent
import kotlinx.coroutines.*

//I have no idea why my tests doesn't work.
//Probably, it needs more time to get best practice and libs to test kotlin coroutines and live data.
//And of course I need more experience with JUnit, because it's my first project that was writing in TDD way
abstract class ContentViewModel(
    private val IO: CoroutineDispatcher,
    private val DEFAULT: CoroutineDispatcher
) : ViewModel() {

    val changedApps = MutableLiveData<List<UiContent>>()
    val isLoading = MutableLiveData<Boolean>()
    protected val savedContent = arrayListOf<UiContent>()
    protected val deviceContent = arrayListOf<UiContent>()
    private val deletedContent = arrayListOf<UiContent>()
    protected val changesContent = arrayListOf<UiContent>()
    private val addedContent = arrayListOf<UiContent>()
    private lateinit var job: Job
    init {
        loadSavedAndDeviceApps()
    }

    abstract suspend fun loadDeviceContent(): List<UiContent>

    abstract suspend fun loadSavedContent(): List<UiContent>

    fun loadSavedAndDeviceApps() {
        job = GlobalScope.launch(IO) {
            isLoading.postValue(true)
            val savedApps = async(DEFAULT) { loadDeviceContent() }
            val deviceApps = async(DEFAULT) { loadSavedContent() }
            withContext(DEFAULT) {
                val uiSavedApps = savedApps.await()
                    .toList()
                val uiDeviceApps = deviceApps.await()
                    .toList()
                this@ContentViewModel.savedContent.addAll(uiSavedApps)
                this@ContentViewModel.deviceContent.addAll(uiDeviceApps)
                findInstalled()
                findDeleted()
                calculateChanged()
                setChangedApps()
                isLoading.postValue(false)
            }
        }
    }

    private fun findInstalled() {
        savedContent.forEach { savedApp ->
            if (!deviceContent.containsApp(savedApp)) {
                savedApp.isInstalled = true
                deletedContent.add(savedApp)
            }
        }
    }

    private fun List<UiContent>.containsApp(uiContent: UiContent): Boolean {
        return find { it.nameContent == uiContent.nameContent } != null
    }

    private fun findDeleted() {
        deviceContent.forEach { deviceApp ->
            if (!savedContent.containsApp(deviceApp)) {
                deviceApp.isDeleted = true
                changesContent.add(deviceApp)
            }
        }
    }

    override fun onCleared() {
        job.cancel()
        super.onCleared()
    }
    protected abstract fun calculateChanged()

    private fun setChangedApps() {
        val changes = arrayListOf<UiContent>()
        changes.addAll(deletedContent)
        changes.addAll(changesContent)
        changes.addAll(addedContent)
        this.changedApps.postValue(changes)
    }
}
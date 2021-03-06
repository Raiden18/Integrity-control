package com.raiden.karpukhinomgupsdiplom.screens.info

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.raiden.domain.interactors.info.InfoInteractor
import kotlinx.coroutines.*

class InfoViewModel(
    private val interactor: InfoInteractor,
    private val IO: CoroutineDispatcher = Dispatchers.Main,
    private val DEFAULT: CoroutineDispatcher = Dispatchers.Default
) : ViewModel() {

    val timeLastUpdate = MutableLiveData<String>()
    val countUpdatedApps = MutableLiveData<String>()
    val countDeletedApps = MutableLiveData<String>()
    val countUploadApps = MutableLiveData<String>()
    val countChangedFiles = MutableLiveData<String>()
    val countAddedFiles = MutableLiveData<String>()
    val countDeletedFiles = MutableLiveData<String>()
    val isChangedContacts = MutableLiveData<Boolean>()
    val isShowLoading = MutableLiveData<Boolean>()
    private var loadingJob: Job
    private lateinit var updateJob: Job

    init {
        loadingJob = GlobalScope.launch(IO) {
            isShowLoading.postValue(true)
            loadAndDataAndBind()
        }
    }

    private suspend fun loadAndDataAndBind() {
        val updatedApps = withContext(DEFAULT) { interactor.getCountOfUpdatedApps() }
        val updatedFiles = withContext(DEFAULT) { interactor.getCountOfChangedFiles() }
        val deletedApps = withContext(DEFAULT) { interactor.getCountOfDeletedApps() }
        val deletedFiles = withContext(DEFAULT) { interactor.getCountOfDeletedFiles() }
        val installedApps = withContext(DEFAULT) { interactor.getCountOfInstalledApps() }
        val addedFiles = withContext(DEFAULT) { interactor.getCountOfAddedFiles() }
        val isChangedContact = withContext(DEFAULT) { interactor.isChangedContacts() }
        val updatedTime = withContext(DEFAULT) { interactor.getSavedTime() }
        countDeletedApps.postValue(deletedApps.toString())
        countDeletedFiles.postValue(deletedFiles.toString())
        countUploadApps.postValue(installedApps.toString())
        countAddedFiles.postValue(addedFiles.toString())
        countUpdatedApps.postValue(updatedApps.toString())
        countChangedFiles.postValue(updatedFiles.toString())
        isChangedContacts.postValue(isChangedContact)
        timeLastUpdate.postValue(updatedTime)
        isShowLoading.postValue(false)
    }

    fun updateData() {
        updateJob = GlobalScope.launch(IO) {
            isShowLoading.postValue(true)
            launch(DEFAULT) {
                interactor.updateInfo()
                loadAndDataAndBind()
            }
        }
    }

    override fun onCleared() {
        loadingJob.cancel()
        if (::updateJob.isInitialized) {
            updateJob.cancel()
        }
        super.onCleared()
    }
}
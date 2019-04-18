package com.raiden.karpukhinomgupsdiplom.info

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

    init {
        GlobalScope.launch(IO) {
            loadAndDataAndBind()
        }
    }

    private suspend fun loadAndDataAndBind() {
        isShowLoading.postValue(true)
        val updatedApps = withContext(DEFAULT) { interactor.getCountOfUpdatedApps() }
        val updatedFiles = withContext(DEFAULT) { interactor.getCountOfChangedFiles() }
        val deletedApps = withContext(DEFAULT) { interactor.getCountOfDeletedApps() }
        val deletedFiles = withContext(DEFAULT) { interactor.getCountOfDeletedFiles() }
        val installedApps = withContext(DEFAULT) { interactor.getCountOfInstalledApps() }
        val addedFiles = withContext(DEFAULT) { interactor.getCountOfAddedFiles() }
        val isChangedContact = withContext(DEFAULT) { interactor.isChangedContacts() }
        val updatedTime = withContext(DEFAULT) { interactor.getSavedTime() }
        isShowLoading.postValue(false)
        countDeletedApps.postValue(deletedApps.toString())
        countDeletedFiles.postValue(deletedFiles.toString())
        countUploadApps.postValue(installedApps.toString())
        countAddedFiles.postValue(addedFiles.toString())
        countUpdatedApps.postValue(updatedApps.toString())
        countChangedFiles.postValue(updatedFiles.toString())
        isChangedContacts.postValue(isChangedContact)
        timeLastUpdate.postValue(updatedTime)

    }

    fun updateData() {
        GlobalScope.launch(IO) {
            isShowLoading.postValue(true)
            launch(DEFAULT) { interactor.updateInfo() }
            loadAndDataAndBind()
        }
    }
}
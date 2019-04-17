package com.raiden.karpukhinomgupsdiplom.info

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.raiden.domain.interactors.info.InfoInteractor
import kotlinx.coroutines.*

class InfoViewModel(
    private val interactor: InfoInteractor,
    private val IO: CoroutineDispatcher = Dispatchers.Main
) : ViewModel() {
    val timeLastUpdate = MutableLiveData<String>()
    val countUpdatedApps = MutableLiveData<String>()
    val countDeletedApps = MutableLiveData<String>()
    val countUploadApps = MutableLiveData<String>()
    val countChangedFiles = MutableLiveData<String>()
    val countAddedFiles = MutableLiveData<String>()
    val countDeletedFiles = MutableLiveData<String>()
    var isChangedContacts = MutableLiveData<Boolean>()

    init {
        GlobalScope.launch(IO) {
            val updatedApps = async { interactor.getCountOfUpdatedApps() }
            val updatedFiles = async { interactor.getCountOfChangedFiles() }
            countUpdatedApps.postValue(updatedApps.await().toString())
            countChangedFiles.postValue(updatedFiles.await().toString())
            val deletedApps = async { interactor.getCountOfDeletedApps() }
            val deletedFiles = async { interactor.getCountOfDeletedFiles() }
            countDeletedApps.postValue(deletedApps.await().toString())
            countDeletedFiles.postValue(deletedFiles.await().toString())
            val installedApps = async { interactor.getCountOfInstalledApps() }
            val addedFiles = async { interactor.getCountOfAddedFiles() }
            countUploadApps.postValue(installedApps.await().toString())
            countAddedFiles.postValue(addedFiles.await().toString())
            val isChangedContact = async { interactor.isChangedContacts() }
            val updatedTime = async { interactor.getSavedTime() }
            isChangedContacts.postValue(isChangedContact.await())
            timeLastUpdate.postValue(updatedTime.await())
        }
    }
}
package com.raiden.karpukhinomgupsdiplom.info

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.raiden.domain.interactors.info.InfoInteractor
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

class InfoViewModel(
    private val interactor: InfoInteractor,
    private val IO: CoroutineDispatcher = Dispatchers.IO
) : ViewModel() {
    val lastUpdate = MutableLiveData<String>()
    val countUpdatedApps = MutableLiveData<String>()
    val countDeletedApps = MutableLiveData<String>()
    val countUploadApps = MutableLiveData<String>()
    val countChangedFiles = MutableLiveData<String>()
    val countAddedFiles = MutableLiveData<String>()
    val countDeletedFiles = MutableLiveData<String>()
    var isChangedContacts = MutableLiveData<Boolean>().apply {
        value = false
    }

    init {
        GlobalScope.launch {
            launch(IO) {
                loadAndUpdateApps()
                loadAndUpdateFiles()
            }
        }
    }

    private suspend fun loadAndUpdateApps(){
        val updatedApps = interactor.getCountOfUpdatedApps()
        val deletedApps = interactor.getCountOfDeletedApps()
        val installedApps = interactor.getCountOfInstalledApps()
        countUpdatedApps.postValue(updatedApps.toString())
        countDeletedApps.postValue(deletedApps.toString())
        countUploadApps.postValue(installedApps.toString())
    }

    private suspend fun loadAndUpdateFiles(){
        val updatedFiles = interactor.getCountOfChangedFiles()
        val deletedFiles = interactor.getCountOfDeletedFiles()
        val addedFiles = interactor.getCountOfAddedFiles()
        countChangedFiles.postValue(updatedFiles.toString())
        countDeletedFiles.postValue(deletedFiles.toString())
        countAddedFiles.postValue(addedFiles.toString())
    }
}
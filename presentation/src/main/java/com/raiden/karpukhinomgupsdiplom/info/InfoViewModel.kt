package com.raiden.karpukhinomgupsdiplom.info

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.raiden.domain.interactors.info.InfoInteractor
import kotlinx.coroutines.*

class InfoViewModel(
    private val interactor: InfoInteractor,
    private val IO: CoroutineDispatcher = Dispatchers.IO
) : ViewModel() {
    val lastUpdate = MutableLiveData<String>()
    val countUpdatedApps = MutableLiveData<String>()
    val countDeletedApps = MutableLiveData<String>()
    val countUploadApps = MutableLiveData<String>()
    val countChangedFiles = MutableLiveData<String>().apply {
        value = 0.toString()
    }
    val countAddedFiles = MutableLiveData<String>().apply {
        value = 0.toString()
    }
    val countDeletedFiles = MutableLiveData<String>().apply {
        value = 0.toString()
    }
    var isChangedContacts = MutableLiveData<Boolean>().apply {
        value = false
    }

    init {
        GlobalScope.launch {
            launch(IO) {
                val updatedApps = async { interactor.getCountOfUpdatedApps() }
                val deletedApps = async { interactor.getCountOfDeletedApps() }
                val installedApps = async { interactor.getCountOfInstalledApps() }
                countUpdatedApps.postValue(updatedApps.await().toString())
                countDeletedApps.postValue(deletedApps.await().toString())
                countUploadApps.postValue(installedApps.await().toString())
            }
        }

    }
}
package com.raiden.karpukhinomgupsdiplom.info

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.raiden.domain.interactors.info.InfoInteractor

class InfoViewModel(private val interactor: InfoInteractor) : ViewModel() {
    val lastUpdate = MutableLiveData<String>().apply {
        value = 0.toString()
    }
    val countUpdatedApps = MutableLiveData<String>().apply {
        value = 0.toString()
    }
    val countDeletedApps = MutableLiveData<String>().apply {
        value = 0.toString()
    }
    val countUploadApps = MutableLiveData<String>().apply {
        value = 0.toString()
    }
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
        countUploadApps.postValue(interactor.getCountOfInstalledApps().toString())
    }
}
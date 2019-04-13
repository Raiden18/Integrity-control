package com.raiden.karpukhinomgupsdiplom.info

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class InfoViewModel() : ViewModel() {
    val lastUpdate = MutableLiveData<Int>()
    val countUpdatedApps = MutableLiveData<Int>()
    val countDeletedApps = MutableLiveData<Int>()
    val countUploadApps = MutableLiveData<Int>()
    val countChangedFiles = MutableLiveData<Int>()
    val countAddedFiles = MutableLiveData<Int>()
    val countDeletedFiles = MutableLiveData<Int>()
    var isChangedContacts: Boolean = false
}
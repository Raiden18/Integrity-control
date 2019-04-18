package com.raiden.karpukhinomgupsdiplom.permissions

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class PermissionsViewModel : ViewModel() {
    var isAllPermissionsGranted = MutableLiveData<Boolean>().apply {
        value = null
    }

    fun checkPermissions(permissionsResult: HashMap<String, Boolean>) {
        var isAllPermissionsGranted = true
        for ((_, value) in permissionsResult) {
            if (!value) {
                isAllPermissionsGranted = false
                break
            }
        }
        this.isAllPermissionsGranted.postValue(isAllPermissionsGranted)
    }
}
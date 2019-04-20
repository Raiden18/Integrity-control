package com.raiden.karpukhinomgupsdiplom.screens.singleapplication

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.raiden.karpukhinomgupsdiplom.uimodels.UiApplication

class SingleApplicationViewModel : ViewModel() {
    private val singleApplication = MutableLiveData<UiApplication>().apply {
        value = UiApplication(
            "Google",
            "12312312",
            "asdasd",
            "asdasd"
        )
    }
}
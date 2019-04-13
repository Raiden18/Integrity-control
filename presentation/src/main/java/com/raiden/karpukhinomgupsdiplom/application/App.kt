package com.raiden.karpukhinomgupsdiplom.application

import android.app.Application
import com.raiden.data.data
import com.raiden.domain.domain
import com.raiden.karpukhinomgupsdiplom.presentation
import org.koin.android.ext.android.startKoin

class App: Application() {

    override fun onCreate() {
        super.onCreate()
        val dependencies = listOf(presentation, data, domain)
        startKoin(this, dependencies)
    }
}
package com.raiden.karpukhinomgupsdiplom

import com.raiden.karpukhinomgupsdiplom.info.InfoViewModel
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import org.koin.android.viewmodel.ext.koin.viewModel
import org.koin.dsl.module.module

val presentation = module {
    viewModel { InfoViewModel(get(), get()) }
}
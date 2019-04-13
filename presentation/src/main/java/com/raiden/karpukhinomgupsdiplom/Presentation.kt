package com.raiden.karpukhinomgupsdiplom

import com.raiden.karpukhinomgupsdiplom.info.InfoViewModel
import org.koin.android.viewmodel.ext.koin.viewModel
import org.koin.dsl.module.module

val presentation = module {
    viewModel { InfoViewModel(get()) }
}
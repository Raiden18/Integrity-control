package com.raiden.karpukhinomgupsdiplom

import com.raiden.karpukhinomgupsdiplom.content.applications.ApplicationsViewModel
import com.raiden.karpukhinomgupsdiplom.info.InfoViewModel
import com.raiden.karpukhinomgupsdiplom.permissions.PermissionsViewModel
import org.koin.android.viewmodel.ext.koin.viewModel
import org.koin.dsl.module.module

val presentation = module {
    viewModel { InfoViewModel(get()) }
    viewModel { PermissionsViewModel() }
    viewModel { ApplicationsViewModel(get()) }
}
package com.raiden.karpukhinomgupsdiplom

import com.raiden.karpukhinomgupsdiplom.screens.content.applications.ApplicationsViewModel
import com.raiden.karpukhinomgupsdiplom.screens.content.contacts.ContactsViewModel
import com.raiden.karpukhinomgupsdiplom.screens.content.files.FilesViewModel
import com.raiden.karpukhinomgupsdiplom.screens.info.InfoViewModel
import com.raiden.karpukhinomgupsdiplom.screens.permissions.PermissionsViewModel
import com.raiden.karpukhinomgupsdiplom.screens.singleapplication.SingleApplicationViewModel
import org.koin.android.viewmodel.ext.koin.viewModel
import org.koin.dsl.module.module

val presentation = module {
    viewModel { InfoViewModel(get()) }
    viewModel { PermissionsViewModel() }
    viewModel { ApplicationsViewModel(get()) }
    viewModel { FilesViewModel(get()) }
    viewModel { ContactsViewModel(get()) }
    viewModel { SingleApplicationViewModel() }
}
package com.raiden.karpukhinomgupsdiplom.screens.content.contacts

import com.raiden.domain.interactors.contacts.ContactsInteractor
import com.raiden.karpukhinomgupsdiplom.converters.convertToUi
import com.raiden.karpukhinomgupsdiplom.screens.content.common.ContentViewModel
import com.raiden.karpukhinomgupsdiplom.uimodels.UiContact
import com.raiden.karpukhinomgupsdiplom.uimodels.UiContent
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers

class ContactsViewModel(
    private val filesInteractor: ContactsInteractor,
    IO: CoroutineDispatcher = Dispatchers.Main,
    DEFAULT: CoroutineDispatcher = Dispatchers.Default
) : ContentViewModel(IO, DEFAULT) {

    override suspend fun loadDeviceContent(): List<UiContent> {
        return filesInteractor.getDeviceContacts().toList().convertToUi()
    }

    override suspend fun loadSavedContent(): List<UiContent> {
        return filesInteractor.getSavedContacts().toList().convertToUi()
    }

    override fun calculateChanged() {
        savedContent.forEach { savedContent ->
            savedContent as UiContact
            deviceContent.forEach { deviceContent ->
                deviceContent as UiContact
                if (savedContent.id == deviceContent.id) {
                    if (savedContent.currentName != deviceContent.currentName || savedContent.currentPhoneNumber != deviceContent.currentPhoneNumber) {
                        deviceContent.isUpdated = true
                        changesContent.add(deviceContent)
                    }
                }
            }
        }
    }

}
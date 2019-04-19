package com.raiden.karpukhinomgupsdiplom.content.contacts

import com.raiden.domain.interactors.contacts.ContactsInteractor
import com.raiden.karpukhinomgupsdiplom.content.common.ContentViewModel
import com.raiden.karpukhinomgupsdiplom.content.common.models.UiContent
import com.raiden.karpukhinomgupsdiplom.content.contacts.models.UiContact
import com.raiden.karpukhinomgupsdiplom.content.contacts.models.convertToUi
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
                    if (savedContent.name != deviceContent.name || savedContent.phoneNumber != deviceContent.phoneNumber) {
                        deviceContent.isUpdated = true
                        changesContent.add(deviceContent)
                    }
                }
            }
        }
    }
}
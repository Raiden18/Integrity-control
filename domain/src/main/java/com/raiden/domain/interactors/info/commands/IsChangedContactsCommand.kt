package com.raiden.domain.interactors.info.commands

import com.raiden.domain.gateways.ContactsGateway

class IsChangedContactsCommand(private val contactsGateway: ContactsGateway) {
    private var isChanged = false

    suspend fun isChange(): Boolean {
        val savedContacts = contactsGateway.getSavedContacts().toList()
        val deviceContacts = contactsGateway.getContactsFromDevice().toList()
        if (!savedContacts.isEmpty()) {
            isChanged = savedContacts != deviceContacts
        }
        return isChanged
    }
}
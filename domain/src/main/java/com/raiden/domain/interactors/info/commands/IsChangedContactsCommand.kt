package com.raiden.domain.interactors.info.commands

import com.raiden.domain.gateways.ContactsGateway
import com.raiden.domain.models.Contact

class IsChangedContactsCommand(private val contactsGateway: ContactsGateway) {
    private var isChanged = false
    lateinit var savedContacts: Iterable<Contact>
    lateinit var deviceContacts: Iterable<Contact>

    suspend fun isChange(): Boolean {
        savedContacts = contactsGateway.getSavedContacts().toList()
        deviceContacts = contactsGateway.getContactsFromDevice().toList()
        if (!savedContacts.toList().isEmpty()) {
            isChangeOrDeleteOrAdded()
        }
        return isChanged
    }

    private fun isChangeOrDeleteOrAdded() {
        isDeleteContact()
        if (!isChanged) {
            isAddedOrChanged()
        }
    }

    private fun isAddedOrChanged() {
        isAddedContact()
        if (!isChanged) {
            isChanged = false
        }
    }

    private fun isDeleteContact() {
        savedContacts.forEach { savedContact ->
            if (!deviceContacts.contains(savedContact)) {
                isChanged = true
            }
        }
    }

    private fun isAddedContact() {
        deviceContacts.forEach { deviceContact ->
            if (!savedContacts.contains(deviceContact)) {
                isChanged = true
            }
        }
    }
}
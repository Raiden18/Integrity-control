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
            isDeleteContact()
            if (!isChanged) {
                isAddedContact()
                if (!isChanged) {
                    isChanged = false
                }
            }
        }
        return isChanged
    }

    private fun isDeleteContact() {
        var isDeleted: Boolean
        savedContacts.forEach { savedContact ->
            isDeleted = false
            if (deviceContacts.contains(savedContact)) {
                isDeleted = true
            }
            if (!isDeleted) {
                isChanged = true
            }
        }
    }

    private fun isAddedContact() {
        var isAdded: Boolean
        deviceContacts.forEach { deviceContact ->
            isAdded = false
            if (!savedContacts.contains(deviceContact)) {
                isAdded = true
            }
            if (isAdded) {
                isChanged = true
                return
            }
        }
    }
}
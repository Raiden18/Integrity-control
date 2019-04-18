package com.raiden.domain.interactors.contacts

import com.raiden.domain.gateways.ContactsGateway
import com.raiden.domain.models.Contact

internal class ContactsInteractorImpl(private val contactsGateway: ContactsGateway) : ContactsInteractor {

    override suspend fun getSavedContacts(): Iterable<Contact> {
        return contactsGateway.getSavedContacts()
    }

    override suspend fun getDeviceContacts(): Iterable<Contact> {
        return contactsGateway.getContactsFromDevice()
    }
}
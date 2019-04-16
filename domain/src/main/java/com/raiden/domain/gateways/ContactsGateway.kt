package com.raiden.domain.gateways

import com.raiden.domain.models.Contact

interface ContactsGateway {
    suspend fun saveContactsFromDevice()
    suspend fun getSavedContacts(): Iterable<Contact>
    suspend fun getContactsFromDevice(): Iterable<Contact>
}
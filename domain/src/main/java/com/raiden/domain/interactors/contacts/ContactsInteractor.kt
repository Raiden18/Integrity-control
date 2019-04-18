package com.raiden.domain.interactors.contacts

import com.raiden.domain.models.Contact

interface ContactsInteractor {
    suspend fun getSavedContacts(): Iterable<Contact>
    suspend fun getDeviceContacts(): Iterable<Contact>
}
package com.raiden.data.repositories.contacts

import com.raiden.data.datasources.database.dao.ContactsDao
import com.raiden.data.datasources.device.contacts.DeviceContacts
import com.raiden.domain.gateways.ContactsGateway
import com.raiden.domain.models.Contact

internal class ContactsRepository(
    private val deviceSource: DeviceContacts,
    private val dataBaseSource: ContactsDao
) : ContactsGateway {

    override suspend fun saveContactsFromDevice() {
        dataBaseSource.clearTable()
        val deviceContacts = deviceSource.getContacts()
        deviceContacts.forEach {
            covertToEntityAndInsertToDb(it)
        }
    }

    private suspend fun covertToEntityAndInsertToDb(deviceContact: Contact) {
        val deviceEntity = deviceContact.convertToEntity()
        dataBaseSource.insert(deviceEntity)
    }

    override suspend fun getSavedContacts(): Iterable<Contact> {
        if (dataBaseSource.getContacts().isEmpty()){
            saveContactsFromDevice()
        }
        val savedContacts = dataBaseSource.getContacts()
        return savedContacts.convertToDomains()
    }

    override suspend fun getContactsFromDevice(): Iterable<Contact> {
        return deviceSource.getContacts()
    }
}
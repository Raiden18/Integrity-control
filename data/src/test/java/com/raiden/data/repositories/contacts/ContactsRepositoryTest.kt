package com.raiden.data.repositories.contacts

import com.nhaarman.mockitokotlin2.*
import com.raiden.data.datasources.database.dao.ContactsDao
import com.raiden.data.datasources.database.entities.ContactsEntity
import com.raiden.data.datasources.device.contacts.DeviceContacts
import com.raiden.domain.gateways.ContactsGateway
import com.raiden.domain.models.Contact
import junit.framework.Assert.assertEquals
import kotlinx.coroutines.runBlocking
import org.junit.Before
import org.junit.Test

internal class ContactsRepositoryTest {
    private lateinit var repository: ContactsGateway
    private lateinit var deviceSource: DeviceContacts
    private lateinit var dataBaseSource: ContactsDao

    @Before
    fun setUp() {
        deviceSource = mock()
        dataBaseSource = mock()
        repository = ContactsRepository(deviceSource, dataBaseSource)
    }

    @Test
    fun `Should get applications from device`() = runBlocking {
        deviceSource.stub {
            onBlocking { getContacts() }.doReturn(
                listOf(
                    Contact("123", "123", "123"),
                    Contact("123", "321", "321"),
                    Contact("123", "asd", "asd"),
                    Contact("123", "sss", "sss")
                )
            )
        }
        val deviceContacts = repository.getContactsFromDevice()
        assertEquals(deviceSource.getContacts(), deviceContacts)
    }

    @Test
    fun `Should get saved applications`() = runBlocking {
        dataBaseSource.stub {
            onBlocking { getContacts() }.doReturn(
                listOf(
                    ContactsEntity("123", "123", "123"),
                    ContactsEntity("123", "321", "321"),
                    ContactsEntity("123", "asd", "asd"),
                    ContactsEntity("123", "sss", "sss")
                )
            )
        }
        val savedContacts = repository.getSavedContacts()
        val domainContacts = dataBaseSource.getContacts().convertToDomains()
        assertEquals(domainContacts, savedContacts)
    }

    @Test
    fun `Should save all contacts to db`() = runBlocking {
        val contacts = listOf(
            Contact("123", "123", "123"),
            Contact("123", "321", "321"),
            Contact("123", "asd", "asd"),
            Contact("123", "sss", "sss")
        )
        deviceSource.stub { onBlocking { getContacts() }.doReturn(contacts) }
        repository.saveContactsFromDevice()
        verify(dataBaseSource, times(contacts.size)).insert(any())
    }
}
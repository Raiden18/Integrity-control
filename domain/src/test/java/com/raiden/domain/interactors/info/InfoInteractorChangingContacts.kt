package com.raiden.domain.interactors.info

import com.nhaarman.mockitokotlin2.doReturn
import com.nhaarman.mockitokotlin2.mock
import com.nhaarman.mockitokotlin2.stub
import com.raiden.domain.gateways.ApplicationsGateway
import com.raiden.domain.gateways.ContactsGateway
import com.raiden.domain.gateways.FilesGateway
import com.raiden.domain.models.Contact
import junit.framework.Assert.assertFalse
import junit.framework.Assert.assertTrue
import kotlinx.coroutines.runBlocking
import org.junit.Before
import org.junit.Test

class InfoInteractorChangingContacts {
    lateinit var interactor: InfoInteractor
    lateinit var contactsGateway: ContactsGateway
    @Before
    fun setUp() {
        val filesGateway: FilesGateway = mock()
        val appsGateway: ApplicationsGateway = mock()
        contactsGateway = mock()
        interactor = InfoInteractorImpl(appsGateway, filesGateway, contactsGateway)
    }

    @Test
    fun `Should return false if db is empty`() = runBlocking {
        contactsGateway.stub {
            onBlocking { getSavedContacts() }.doReturn(emptyList())
        }
        contactsGateway.stub {
            onBlocking { getContactsFromDevice() }.doReturn(listOf(Contact("123", "122")))
        }

        assertFalse(interactor.isChangedContacts())
    }

    @Test
    fun `Should return true if contact was added`() = runBlocking {
        contactsGateway.stub {
            onBlocking { getSavedContacts() }.doReturn(listOf(Contact("123asd", "asdcxz ")))
        }
        contactsGateway.stub {
            onBlocking { getContactsFromDevice() }.doReturn(
                listOf(Contact("123", "122"))
            )
        }
        assertTrue(interactor.isChangedContacts())
    }

    @Test
    fun `Should return true if contact were added 3 contacts and db is empty`() = runBlocking {
        contactsGateway.stub {
            onBlocking { getSavedContacts() }.doReturn(
                listOf(
                    Contact("123", "122")
                )
            )
        }
        contactsGateway.stub {
            onBlocking { getContactsFromDevice() }.doReturn(
                listOf(
                    Contact("123", "332"),
                    Contact("asd", "zxc"),
                    Contact("wqdas", "124thryhgdfd")
                )
            )
        }

        assertTrue(interactor.isChangedContacts())
    }

    @Test
    fun `Should return false if contact from db and device equal`() = runBlocking {
        contactsGateway.stub {
            onBlocking { getSavedContacts() }.doReturn(listOf(
                Contact("123", "122"),
                Contact("asd", "zxc"),
                Contact("wqdas", "124thryhgdfd")))
        }
        contactsGateway.stub {
            onBlocking { getContactsFromDevice() }.doReturn(listOf(
                Contact("123", "122"),
                Contact("asd", "zxc"),
                Contact("wqdas", "124thryhgdfd")))
        }

        assertFalse(interactor.isChangedContacts())
    }

    @Test
    fun `Should return true if contact was delete`() = runBlocking {
        contactsGateway.stub {
            onBlocking { getSavedContacts() }.doReturn(
                listOf(
                    Contact("123", "122"),
                    Contact("asd", "zxc"),
                    Contact("wqdas", "124thryhgdfd")
                )
            )
        }
        contactsGateway.stub {
            onBlocking { getContactsFromDevice() }.doReturn(
                listOf(
                    Contact("123", "122"),
                    Contact("asd", "zxc")
                )
            )
        }

        assertTrue(interactor.isChangedContacts())
    }

    @Test
    fun `Should return true if all contact was deleted`() = runBlocking {
        contactsGateway.stub {
            onBlocking { getSavedContacts() }.doReturn(
                listOf(
                    Contact("123", "122"),
                    Contact("asd", "zxc"),
                    Contact("wqdas", "124thryhgdfd")
                )
            )
        }
        contactsGateway.stub {
            onBlocking { getContactsFromDevice() }.doReturn(listOf())
        }

        assertTrue(interactor.isChangedContacts())
    }
}
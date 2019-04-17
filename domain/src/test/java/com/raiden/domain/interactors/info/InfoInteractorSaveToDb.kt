package com.raiden.domain.interactors.info

import com.nhaarman.mockitokotlin2.mock
import com.nhaarman.mockitokotlin2.verify
import com.raiden.domain.gateways.ApplicationsGateway
import com.raiden.domain.gateways.ContactsGateway
import com.raiden.domain.gateways.FilesGateway
import com.raiden.domain.gateways.UpdatedTimeGateway
import kotlinx.coroutines.runBlocking
import org.junit.Before
import org.junit.Test

class InfoInteractorSaveToDb {
    lateinit var interactor: InfoInteractor
    lateinit var filesGateway: FilesGateway;
    lateinit var appsGateway: ApplicationsGateway
    lateinit var contactsGateway: ContactsGateway
    lateinit var time: UpdatedTimeGateway

    @Before
    fun setUp() {
        filesGateway = mock()
        appsGateway = mock()
        contactsGateway = mock()
        time = mock()
        interactor = InfoInteractorImpl(appsGateway, filesGateway, contactsGateway, time)
    }

    @Test
    fun `Should save files`() = runBlocking {
        interactor.updateInfo()
        verify(filesGateway).saveFilesFromDevices()
    }

    @Test
    fun `Should save apps`() = runBlocking {
        interactor.updateInfo()
        verify(appsGateway).saveApplicationsFromDevice()
    }

    @Test
    fun `Should save contacts`() = runBlocking {
        interactor.updateInfo()
        verify(contactsGateway).saveContactsFromDevice()
    }

    @Test
    fun `Should save time`() = runBlocking {
        interactor.updateInfo()
        verify(time).saveUpdatedTime()
    }
}
package com.raiden.domain.interactors.info

import com.nhaarman.mockitokotlin2.doReturn
import com.nhaarman.mockitokotlin2.mock
import com.nhaarman.mockitokotlin2.stub
import com.raiden.domain.gateways.ApplicationsGateway
import com.raiden.domain.gateways.ContactsGateway
import com.raiden.domain.gateways.FilesGateway
import com.raiden.domain.gateways.UpdatedTimeGateway
import com.raiden.domain.models.UpdateTime
import kotlinx.coroutines.runBlocking
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Test
import org.mockito.Mockito

class InfoInteractorUpdateTime {
    lateinit var interactor: InfoInteractor
    lateinit var timeGateway: UpdatedTimeGateway;

    @Before
    fun setUp() {
        val gateway: ApplicationsGateway = mock()
        val fileGateway: FilesGateway = mock()
        val contactsGateway: ContactsGateway = mock()
        timeGateway = mock()
        interactor = InfoInteractorImpl(gateway, fileGateway, contactsGateway, timeGateway)
    }

    @Test
    fun `Should save time`() = runBlocking {
        interactor.saveUpdatedTime()
        Mockito.verify(timeGateway).saveUpdatedTime()
    }

    @Test
    fun `Should get saved time`() = runBlocking {
        timeGateway.stub {
            onBlocking { getUpdatedTime() }.doReturn(UpdateTime("123"))
        }
        val savedTime = interactor.getSavedTime()
        assertEquals(timeGateway.getUpdatedTime().time, savedTime)
    }
}
package com.raiden.domain.interactors.info

import com.nhaarman.mockitokotlin2.doReturn
import com.nhaarman.mockitokotlin2.mock
import com.nhaarman.mockitokotlin2.stub
import com.raiden.domain.gateways.ApplicationsGateway
import com.raiden.domain.gateways.ContactsGateway
import com.raiden.domain.gateways.FilesGateway
import com.raiden.domain.gateways.UpdatedTimeGateway
import com.raiden.domain.models.Application
import kotlinx.coroutines.runBlocking
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Test

internal class InfoInteractorCountOfInstalledApps {
    lateinit var interactor: InfoInteractor
    lateinit var gateway: ApplicationsGateway

    @Before
    fun setUp() {
        gateway = mock()
        val fileGateway: FilesGateway = mock()
        val contactsGateway: ContactsGateway = mock()
        val time : UpdatedTimeGateway = mock()
        interactor = InfoInteractorImpl(gateway, fileGateway, contactsGateway, time)
    }

    @Test
    fun `Should return zero apps if there is no apps`() = runBlocking {
        gateway.stub {
            onBlocking { getSavedApplications() }.doReturn(
                listOf()
            )
            onBlocking { getAppsFromDevice() }.doReturn(
                listOf()
            )
        }
        val updatedApps = interactor.getCountOfInstalledApps()
        assertEquals(0, updatedApps)
    }

    @Test
    fun `Should return one app if there one`() = runBlocking {
        gateway.stub {
            onBlocking { getSavedApplications() }.doReturn(
                listOf()
            )
            onBlocking { getAppsFromDevice() }.doReturn(
                listOf(
                    Application("123213", "asdasdasd", "132123123")
                )
            )
        }
        val updatedApps = interactor.getCountOfInstalledApps()
        assertEquals(1, updatedApps)
    }

    @Test
    fun `Should return 3 installed apps if 3 wass uninstalled`() = runBlocking {
        gateway.stub {
            onBlocking { getSavedApplications() }.doReturn(
                listOf(
                    Application("asdsad", "asdasdasd", "asd"),
                    Application("asdasd", "asdasdasd", "dasd"),
                    Application("zxczxc", "asdasdasd", "sdg"),
                    Application("444444", "asdasdasd", "132123123")
                )
            )
            onBlocking { getAppsFromDevice() }.doReturn(
                listOf(
                    Application("123213", "asdasdasd", "aa"),
                    Application("2222", "asdasdasd", "ss"),
                    Application("3333", "asdasdasd", "dd"),
                    Application("444444", "asdasdasd", "132123123")
                )
            )
        }
        val updatedApps = interactor.getCountOfInstalledApps()
        assertEquals(3, updatedApps)
    }
}
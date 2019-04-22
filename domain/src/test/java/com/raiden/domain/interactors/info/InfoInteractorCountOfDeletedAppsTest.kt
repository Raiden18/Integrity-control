package com.raiden.domain.interactors.info

import com.nhaarman.mockitokotlin2.mock
import com.raiden.domain.gateways.ApplicationsGateway
import com.raiden.domain.gateways.ContactsGateway
import com.raiden.domain.gateways.FilesGateway
import com.raiden.domain.gateways.UpdatedTimeGateway
import com.raiden.domain.models.Application
import io.mockk.coEvery
import io.mockk.spyk
import junit.framework.Assert.assertEquals
import kotlinx.coroutines.runBlocking
import org.junit.Before
import org.junit.Test

internal class InfoInteractorCountOfDeletedAppsTest {
    lateinit var interactor: InfoInteractor
    lateinit var gateway: ApplicationsGateway

    @Before
    fun setUp() {
        gateway = spyk()
        val fileGateway: FilesGateway = mock()
        val contactsGateway: ContactsGateway = mock()
        val time: UpdatedTimeGateway = mock()
        interactor = InfoInteractorImpl(gateway, fileGateway, contactsGateway, time)
    }

    @Test
    fun `Should return zero if db table is empty`() = runBlocking {
        coEvery { gateway.getSavedApplications() }.returns(listOf())
        val updatedApps = interactor.getCountOfDeletedApps()
        assertEquals(0, updatedApps)
    }

    @Test
    fun `Should return one count of deleted apps`() = runBlocking {
        coEvery { gateway.getSavedApplications() }.returns(listOf(Application("123", "123", "132123123")))
        coEvery { gateway.getAppsFromDevice() }.returns(emptyList())
        val countOfUpdatedapps = interactor.getCountOfDeletedApps()
        assertEquals(countOfUpdatedapps, 1)
    }

    @Test
    fun `Should return 5 count of deleted apps if there are another apps`() = runBlocking {
        coEvery { gateway.getSavedApplications() }.returns(
            listOf(
                Application("123", "123", "xzc"),
                Application("222", "123", "asdasd"),
                Application("11123", "123", "12ew"),
                Application("2122", "123", "sdamf "),
                Application("aaaaa", "123", "W[RLQWOL")
            )
        )
        coEvery { gateway.getAppsFromDevice() }.returns(
            listOf(
                Application("asdasd", "123", "132123123"),
                Application("asdsad", "123", "132123123"),
                Application("zxczxc", "123", "132123123"),
                Application("hhhhhh", "123", "132123123")
            )
        )
        val countOfUpdatedapps = interactor.getCountOfDeletedApps()
        assertEquals(countOfUpdatedapps, 5)
    }

    @Test
    fun `Should return 0 deleted apps if apps was updated`() = runBlocking {
        coEvery { gateway.getSavedApplications() }.returns(
            listOf(
                Application("123", "123", "xzc"),
                Application("222", "123", "asdasd"),
                Application("11123", "123", "12ew"),
                Application("2122", "123", "sdamf "),
                Application("aaaaa", "123", "W[RLQWOL")
            )
        )
        coEvery { gateway.getAppsFromDevice() }.returns(
            listOf(
                Application("123", "321", "xzc"),
                Application("222", "222", "asdasd"),
                Application("11123", "334455", "12ew"),
                Application("2122", "123", "sdamf "),
                Application("aaaaa", "qasdasd", "W[RLQWOL")
            )
        )
        val countOfUpdatedApps = interactor.getCountOfDeletedApps()
        assertEquals(0, countOfUpdatedApps)

    }
}
package com.raiden.domain.interactors.info

import com.nhaarman.mockitokotlin2.doReturn
import com.nhaarman.mockitokotlin2.mock
import com.nhaarman.mockitokotlin2.stub
import com.raiden.domain.gateways.ApplicationsGateway
import com.raiden.domain.models.Application
import junit.framework.Assert.assertEquals
import kotlinx.coroutines.runBlocking
import org.junit.Before
import org.junit.Test

class InfoInteractorCountOfDeletedAppsTest {
    lateinit var interactor: InfoInteractor
    lateinit var gateway: ApplicationsGateway;

    @Before
    fun setUp() {
        gateway = mock()
        interactor = InfoInteractorImpl(gateway)
    }

    @Test
    fun `Should return zero if db table is empty`() = runBlocking {
        gateway.stub {
            onBlocking { getSavedApplications() }.doReturn(
                listOf()
            )
        }
        val updatedApps = interactor.getCountOfDeletedApps()
        assertEquals(0, updatedApps)
    }

    @Test
    fun `Should return one count of deleted apps`() = runBlocking {
        gateway.stub {
            onBlocking { getSavedApplications() }.doReturn(
                listOf(Application("123", "123"))
            )
            onBlocking { getAppsFromDevice() }.doReturn(
                listOf()
            )
        }
        val countOfUpdatedapps = interactor.getCountOfDeletedApps()
        assertEquals(countOfUpdatedapps, 1)
    }

    @Test
    fun `Should return 4 count of deleted apps if there are no apps in device`() = runBlocking {
        gateway.stub {
            onBlocking { getSavedApplications() }.doReturn(
                listOf(
                    Application("123", "123"),
                    Application("222", "123"),
                    Application("11123", "123"),
                    Application("2122", "123")
                )
            )
            onBlocking { getAppsFromDevice() }.doReturn(
                listOf()
            )
        }
        val countOfUpdatedapps = interactor.getCountOfDeletedApps()
        assertEquals(countOfUpdatedapps, 4)
    }

    @Test
    fun `Should return 5 count of deleted apps if there are another apps`() = runBlocking {
        gateway.stub {
            onBlocking { getSavedApplications() }.doReturn(
                listOf(
                    Application("123", "123"),
                    Application("222", "123"),
                    Application("11123", "123"),
                    Application("2122", "123"),
                    Application("aaaaa", "123")
                )
            )
            onBlocking { getAppsFromDevice() }.doReturn(
                listOf(
                    Application("asdasd", "123"),
                    Application("asdsad", "123"),
                    Application("zxczxc", "123"),
                    Application("hhhhhh", "123")
                )
            )
        }
        val countOfUpdatedapps = interactor.getCountOfDeletedApps()
        assertEquals(countOfUpdatedapps, 5)
    }
}
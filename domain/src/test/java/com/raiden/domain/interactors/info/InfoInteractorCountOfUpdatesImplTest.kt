package com.raiden.domain.interactors.info

import com.nhaarman.mockitokotlin2.doReturn
import com.nhaarman.mockitokotlin2.mock
import com.nhaarman.mockitokotlin2.stub
import com.raiden.domain.gateways.ApplicationsGateway
import com.raiden.domain.gateways.FilesGateway
import com.raiden.domain.models.Application
import kotlinx.coroutines.runBlocking
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Test
import org.mockito.Mockito.`when`

internal class InfoInteractorCountOfUpdatesImplTest {
    lateinit var interactor: InfoInteractor
    lateinit var gateway: ApplicationsGateway;

    @Before
    fun setUp() {
        gateway = mock()
        val fileGateway: FilesGateway = mock()
        interactor = InfoInteractorImpl(gateway, fileGateway)
    }

    @Test
    fun `Should return zero count of updates if table is clear`() {
        runBlocking {
            gateway.stub {
                onBlocking { getSavedApplications() }.doReturn(
                    listOf(                    )
                )
                onBlocking { getAppsFromDevice() }.doReturn(
                    listOf(
                        Application("123", "321")
                    )
                )
            }
            val updatedApps = interactor.getCountOfUpdatedApps()
            assertEquals(updatedApps, 0)
        }
    }

    @Test
    fun `Should return one count of updated apps`() = runBlocking {
        gateway.stub {
            onBlocking { getSavedApplications() }.doReturn(
                listOf
                    (
                    Application("123", "123")
                )
            )
            onBlocking { getAppsFromDevice() }.doReturn(
                listOf(
                    Application("123", "321")
                )
            )
        }
        val countOfUpdatedapps = interactor.getCountOfUpdatedApps()
        assertEquals(countOfUpdatedapps, 1)
    }

    @Test
    fun `Should return all count of updated apps`() = runBlocking {
        gateway.stub {
            onBlocking { getSavedApplications() }.doReturn(
                listOf(
                    Application("123", "123"),
                    Application("321", "123"),
                    Application("22", "123"),
                    Application("21", "123")
                )
            )
            onBlocking { getAppsFromDevice() }.doReturn(
                listOf(
                    Application("123", "1123123123aszxc23"),
                    Application("321", "1asdasd23"),
                    Application("22", "1asdasda23"),
                    Application("21", "1zxcxzc23")
                )
            )
        }
        val countOfUpdatedapps = interactor.getCountOfUpdatedApps()
        assertEquals(4, countOfUpdatedapps)
    }

    @Test
    fun `Should return 3 updated app but there are 4 apps`() = runBlocking {
        gateway.stub {
            onBlocking { getSavedApplications() }.doReturn(
                listOf(
                    Application("123", "123"),
                    Application("321", "123"),
                    Application("22", "123"),
                    Application("221", "123")
                )
            )
            onBlocking { getAppsFromDevice() }.doReturn(
                listOf(
                    Application("123", "123"),
                    Application("321", "1asdasd23"),
                    Application("22", "1asdasda23"),
                    Application("221", "1zxcxzc23")
                )
            )
        }
        val countOfUpdatedapps = interactor.getCountOfUpdatedApps()
        assertEquals(3, countOfUpdatedapps)
    }

}
package com.raiden.domain.interactors.applications

import com.nhaarman.mockitokotlin2.mock
import com.nhaarman.mockitokotlin2.stub
import com.raiden.domain.gateways.ApplicationsGateway
import com.raiden.domain.models.Application
import kotlinx.coroutines.runBlocking
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Test

class ApplicationsInteractorImplTest {
    lateinit var applicationInteractor: ApplicationsInteractor
    lateinit var applicationsGateway: ApplicationsGateway

    @Before
    fun setUp() {
        applicationsGateway = mock()
        applicationInteractor = ApplicationsInteractorImpl(applicationsGateway)
    }

    @Test
    fun `Should return apps from device`() = runBlocking {
        applicationsGateway.stub {
            onBlocking { getAppsFromDevice() }.thenReturn(
                listOf(
                    Application("123123", "123", "132123123"),
                    Application("xzc", "adscxz", "132123123"),
                    Application("qewfdsgbf", "ZXCxc", "132123123"),
                    Application("wadsczx", "qwewfas", "132123123")
                )
            )
        }
        assertEquals(applicationInteractor.getDeviceApps(), applicationsGateway.getAppsFromDevice())
    }

    @Test
    fun `Should return apps from database`() = runBlocking {
        applicationsGateway.stub {
            onBlocking { getSavedApplications() }.thenReturn(
                listOf(
                    Application("123123", "123", "132123123"),
                    Application("xzc", "adscxz", "132123123"),
                    Application("qewfdsgbf", "ZXCxc", "132123123"),
                    Application("wadsczx", "qwewfas", "132123123")
                )
            )
        }
        assertEquals(applicationInteractor.getSavedApps(), applicationsGateway.getSavedApplications())
    }
}
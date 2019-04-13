package com.raiden.data.repositories

import com.raiden.data.sources.database.dao.ApplicationsDao
import com.raiden.data.sources.device.DeviceApplications
import com.raiden.domain.gateways.ApplicationsGateway
import com.raiden.domain.models.Application
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Test
import org.mockito.Mockito.*

class ApplicationsRepositoryTest {
    private lateinit var gateway: ApplicationsGateway
    private lateinit var device: DeviceApplications
    private lateinit var db: ApplicationsDao
    @Before
    fun getApplications() {
        device = mock(DeviceApplications::class.java)
        db = mock(ApplicationsDao::class.java)
        gateway = ApplicationsRepository(device, db)
    }

    @Test
    fun `Should return devices applications`() {
        `when`(device.getApplications()).thenReturn(
            arrayListOf(
                Application("123", "123"),
                Application("321", "321")
            )
        )
        val deviceApps = device.getApplications();
        val gatewayApps = gateway.getApplications()
        assertEquals(deviceApps, gatewayApps)
    }
}
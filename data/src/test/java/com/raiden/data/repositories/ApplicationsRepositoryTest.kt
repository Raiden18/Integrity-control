package com.raiden.data.repositories

import com.nhaarman.mockitokotlin2.any
import com.nhaarman.mockitokotlin2.times
import com.nhaarman.mockitokotlin2.verify
import com.raiden.data.datasources.database.dao.ApplicationsDao
import com.raiden.data.datasources.database.entities.ApplicationEntity
import com.raiden.data.datasources.device.DeviceApplications
import com.raiden.domain.gateways.ApplicationsGateway
import com.raiden.domain.models.Application
import junit.framework.Assert.assertEquals
import junitparams.JUnitParamsRunner
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mockito.*


@RunWith(JUnitParamsRunner::class)
internal class ApplicationsRepositoryTest {
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
    fun `Should get saved applications`(){
        GlobalScope.launch {
            `when`(db.getApplications()).thenReturn(
                arrayListOf(
                    ApplicationEntity("123", "123"),
                    ApplicationEntity("321", "321"),
                    ApplicationEntity("asdasd", "32asdasd1"),
                    ApplicationEntity("32asssaq1", "3erghrh21")
                )
            )
            val savedApps = gateway.getSavedApplications()
            val domainApps = db.getApplications()
            assertEquals(savedApps, domainApps)
        }
    }

    @Test
    fun `Should add all devices' applications to the data base`() {
        runBlocking {
            `when`(device.getApplications()).thenReturn(
                arrayListOf(
                    Application("123", "123"),
                    Application("321", "321"),
                    Application("asdasd", "32asdasd1"),
                    Application("32asssaq1", "3erghrh21")
                )

            )
            gateway.saveApplicationsFromDevice()
            val size = device.getApplications().toList().size
            verify(db, times(size)).insert(any())
        }
    }

    @Test
    fun `Should get all apps from device`(){
        runBlocking {
            `when`(device.getApplications()).thenReturn(
                arrayListOf(
                    Application("123", "123"),
                    Application("321", "321"),
                    Application("asdasd", "32asdasd1"),
                    Application("32asssaq1", "3erghrh21")
                )
            )
            val appsFromDevice = device.getApplications()
            val appsFromDeviceRep = gateway.getAppsFromDevice()
            assertEquals(appsFromDevice, appsFromDeviceRep)
        }
    }
}
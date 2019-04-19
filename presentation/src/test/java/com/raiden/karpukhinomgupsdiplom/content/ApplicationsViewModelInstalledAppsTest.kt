package com.raiden.karpukhinomgupsdiplom.content

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.nhaarman.mockitokotlin2.doReturn
import com.nhaarman.mockitokotlin2.mock
import com.nhaarman.mockitokotlin2.stub
import com.raiden.domain.interactors.applications.ApplicationsInteractor
import com.raiden.domain.models.Application
import com.raiden.karpukhinomgupsdiplom.content.applications.ApplicationsViewModel
import com.raiden.karpukhinomgupsdiplom.content.applications.model.UiApplication
import com.raiden.karpukhinomgupsdiplom.content.applications.model.convertToUi
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.runBlocking
import org.junit.Assert
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.rules.TestRule

class ApplicationsViewModelInstalledAppsTest {
    @get:Rule
    var rule: TestRule = InstantTaskExecutorRule()
    lateinit var viewModel: ApplicationsViewModel
    lateinit var applicationInteractor: ApplicationsInteractor

    @Before
    fun setUp() {
        applicationInteractor = mock()
    }

    @Test
    fun `Should post installed apps if there are no apps in db`() = runBlocking {
        applicationInteractor.stub {
            onBlocking { getSavedApps() }.doReturn(
                listOf(
                )
            )
        }
        applicationInteractor.stub {
            onBlocking { getDeviceApps() }.doReturn(
                listOf(
                    Application("123", "asd"),
                    Application("asdc", "Adadfdsf"),
                    Application("zcxasdcasdgf", "wasdadsgsg"),
                    Application("sdafadAD", "ADAXVCZXV"),
                    Application("zxCXBVADSF", "adqwwe12easdfaD")
                )
            )
        }
        val uiInstalledApps = applicationInteractor.getDeviceApps().toList().convertToUi()
            .map {
                UiApplication(
                    it.name,
                    it.versionName
                ).apply {
                    isInstalled = true
                }
            }
        viewModel = ApplicationsViewModel(
            applicationInteractor,
            Dispatchers.Unconfined,
            Dispatchers.Unconfined
        )
        viewModel.changedApps.observeForever {
            Assert.assertEquals(uiInstalledApps, it.filter { it.isInstalled })
        }
    }

    @Test
    fun `Should post installed apps if there are apps in db`() = runBlocking {
        applicationInteractor.stub {
            onBlocking { getSavedApps() }.doReturn(
                listOf(
                    Application("asdsad", "asd"),
                    Application("asdas", "Adadfdsf"),
                    Application("aadvsdxvc", "wasdadsgsg"),
                    Application("adfasxv", "ADAXVCZXV"),
                    Application("awedweofj;;;", "adqwwe12easdfaD")
                )
            )
        }
        applicationInteractor.stub {
            onBlocking { getDeviceApps() }.doReturn(
                listOf(
                    Application("123", "asd"),
                    Application("asdc", "Adadfdsf"),
                    Application("zcxasdcasdgf", "wasdadsgsg"),
                    Application("sdafadAD", "[ppl[pl"),
                    Application("zxCXBVADSF", "adqwwe12easdfaD")
                )
            )
        }
        val uiInstalledApps = applicationInteractor.getDeviceApps().toList().convertToUi()
            .map {
                UiApplication(
                    it.name,
                    it.versionName
                ).apply {
                    isInstalled = true
                }
            }
        viewModel = ApplicationsViewModel(
            applicationInteractor,
            Dispatchers.Unconfined,
            Dispatchers.Unconfined
        )
        viewModel.changedApps.observeForever {
            Assert.assertEquals(uiInstalledApps, it.filter { it.isInstalled })
        }
    }
}
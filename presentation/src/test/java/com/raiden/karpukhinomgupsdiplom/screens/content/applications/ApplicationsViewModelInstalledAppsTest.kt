package com.raiden.karpukhinomgupsdiplom.screens.content.applications

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.nhaarman.mockitokotlin2.doReturn
import com.nhaarman.mockitokotlin2.mock
import com.nhaarman.mockitokotlin2.stub
import com.raiden.domain.interactors.applications.ApplicationsInteractor
import com.raiden.domain.models.Application
import com.raiden.karpukhinomgupsdiplom.uimodels.UiApplication
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
                    Application("123", "asd", "packageName"),
                    Application("asdc", "Adadfdsf", "packageName"),
                    Application("zcxasdcasdgf", "wasdadsgsg", "packageName"),
                    Application("sdafadAD", "ADAXVCZXV", "packageName"),
                    Application("zxCXBVADSF", "adqwwe12easdfaD", "packageName")
                )
            )
        }
        val uiInstalledApps = applicationInteractor.getDeviceApps().toList().convertToUi()
            .map {
                UiApplication(
                    it.name,
                    it.currentVersionNameMd5,
                    it.packageName
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
                    Application("asdsad", "asd", "packageName"),
                    Application("asdas", "Adadfdsf", "packageName"),
                    Application("aadvsdxvc", "wasdadsgsg", "packageName"),
                    Application("adfasxv", "ADAXVCZXV", "packageName"),
                    Application("awedweofj;;;", "adqwwe12easdfaD", "packageName")
                )
            )
        }
        applicationInteractor.stub {
            onBlocking { getDeviceApps() }.doReturn(
                listOf(
                    Application("123", "asd", "packageName"),
                    Application("asdc", "Adadfdsf", "packageName"),
                    Application("zcxasdcasdgf", "wasdadsgsg", "packageName"),
                    Application("sdafadAD", "[ppl[pl", "packageName"),
                    Application("zxCXBVADSF", "adqwwe12easdfaD", "packageName")
                )
            )
        }
        val uiInstalledApps = applicationInteractor.getDeviceApps().toList().convertToUi()
            .map {
                UiApplication(
                    it.name,
                    it.currentVersionNameMd5,
                    it.packageName
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
package com.raiden.karpukhinomgupsdiplom.content.applications

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.nhaarman.mockitokotlin2.doReturn
import com.nhaarman.mockitokotlin2.mock
import com.nhaarman.mockitokotlin2.stub
import com.raiden.domain.interactors.applications.ApplicationsInteractor
import com.raiden.domain.models.Application
import com.raiden.karpukhinomgupsdiplom.content.applications.model.UiApplication
import com.raiden.karpukhinomgupsdiplom.content.applications.model.convertToUi
import com.raiden.karpukhinomgupsdiplom.content.common.UiContentViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.runBlocking
import org.junit.Assert
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.rules.TestRule

class ApplicationsViewModelUpdatedAppsTest {
    @get:Rule
    var rule: TestRule = InstantTaskExecutorRule()
    lateinit var viewModel: UiContentViewModel
    lateinit var applicationInteractor: ApplicationsInteractor

    @Before
    fun setUp() {
        applicationInteractor = mock()
    }

    @Test
    fun `Should post updated apps`() = runBlocking {
        applicationInteractor.stub {
            onBlocking { getSavedApps() }.doReturn(
                listOf(
                    Application("123", "1"),
                    Application("asdc", "22"),
                    Application("zcxasdcasdgf", "33"),
                    Application("sdafadAD", "44"),
                    Application("zxCXBVADSF", "55")
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
                    isUpdated = true
                }
            }
        viewModel = ApplicationsViewModel(
            applicationInteractor,
            Dispatchers.Unconfined,
            Dispatchers.Unconfined
        )
        viewModel.changedApps.observeForever {
            Assert.assertEquals(uiInstalledApps, it.filter { it.isUpdated })
        }
    }

    @Test
    fun `Should post updated apps if unupdated exists`() = runBlocking {
        applicationInteractor.stub {
            onBlocking { getSavedApps() }.doReturn(
                listOf(
                    Application("123", "1"),
                    Application("asdc", "22"),
                    Application("zcxasdcasdgf", "33"),
                    Application("sdafadAD", "44"),
                    Application("zxCXBVADSF", "55")
                )
            )
        }
        applicationInteractor.stub {
            onBlocking { getDeviceApps() }.doReturn(
                listOf(
                    Application("123", "asd"),
                    Application("asdc", "22"),
                    Application("zcxasdcasdgf", "wasdadsgsg"),
                    Application("sdafadAD", "ADAXVCZXV"),
                    Application("zxCXBVADSF", "adqwwe12easdfaD")
                )
            )
        }
        val listOfUpdated = listOf(
            Application("123", "asd"),
            Application("zcxasdcasdgf", "wasdadsgsg"),
            Application("sdafadAD", "ADAXVCZXV"),
            Application("zxCXBVADSF", "adqwwe12easdfaD")
        )
        val uiInstalledApps = listOfUpdated.toList().convertToUi()
            .map {
                UiApplication(
                    it.name,
                    it.versionName
                ).apply {
                    isUpdated = true
                }
            }
        viewModel = ApplicationsViewModel(
            applicationInteractor,
            Dispatchers.Unconfined,
            Dispatchers.Unconfined
        )
        viewModel.changedApps.observeForever {
            Assert.assertEquals(uiInstalledApps, it.filter { it.isUpdated })
        }
    }
}
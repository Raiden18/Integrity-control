package com.raiden.karpukhinomgupsdiplom.screens.content.applications

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.nhaarman.mockitokotlin2.doReturn
import com.nhaarman.mockitokotlin2.mock
import com.nhaarman.mockitokotlin2.stub
import com.raiden.domain.interactors.applications.ApplicationsInteractor
import com.raiden.domain.models.Application
import com.raiden.karpukhinomgupsdiplom.screens.content.common.ContentViewModel
import com.raiden.karpukhinomgupsdiplom.uimodels.UiApplication
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
    lateinit var viewModel: ContentViewModel
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
                    Application("123", "1", "packageName"),
                    Application("asdc", "22", "packageName"),
                    Application("zcxasdcasdgf", "33", "packageName"),
                    Application("sdafadAD", "44", "packageName"),
                    Application("zxCXBVADSF", "55", "packageName")
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
                    Application("123", "1", "packageName"),
                    Application("asdc", "22", "packageName"),
                    Application("zcxasdcasdgf", "33", "packageName"),
                    Application("sdafadAD", "44", "packageName"),
                    Application("zxCXBVADSF", "55", "packageName")
                )
            )
        }
        applicationInteractor.stub {
            onBlocking { getDeviceApps() }.doReturn(
                listOf(
                    Application("123", "asd", "packageName"),
                    Application("asdc", "22", "packageName"),
                    Application("zcxasdcasdgf", "wasdadsgsg", "packageName"),
                    Application("sdafadAD", "ADAXVCZXV", "packageName"),
                    Application("zxCXBVADSF", "adqwwe12easdfaD", "packageName")
                )
            )
        }
        val listOfUpdated = listOf(
            Application("123", "asd", "packageName"),
            Application("zcxasdcasdgf", "wasdadsgsg", "packageName"),
            Application("sdafadAD", "ADAXVCZXV", "packageName"),
            Application("zxCXBVADSF", "adqwwe12easdfaD", "packageName")
        )
        val uiInstalledApps = listOfUpdated.toList().convertToUi()
            .map {
                UiApplication(
                    it.name,
                    it.currentVersionNameMd5,
                    it.packageName
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
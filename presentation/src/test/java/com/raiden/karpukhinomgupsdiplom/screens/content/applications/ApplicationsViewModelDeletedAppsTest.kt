package com.raiden.karpukhinomgupsdiplom.screens.content.applications

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.nhaarman.mockitokotlin2.doReturn
import com.nhaarman.mockitokotlin2.mock
import com.nhaarman.mockitokotlin2.stub
import com.raiden.domain.interactors.applications.ApplicationsInteractor
import com.raiden.domain.models.Application
import com.raiden.karpukhinomgupsdiplom.converters.convertToUi
import com.raiden.karpukhinomgupsdiplom.uimodels.UiApplication
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.runBlocking
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.rules.TestRule

class ApplicationsViewModelDeletedAppsTest {
    @get:Rule
    var rule: TestRule = InstantTaskExecutorRule()
    lateinit var viewModel: ApplicationsViewModel
    lateinit var applicationInteractor: ApplicationsInteractor

    @Before
    fun setUp() {
        applicationInteractor = mock()
    }

    @Test
    fun `Should post deleted apps if there are no apps `() = runBlocking {
        applicationInteractor.stub {
            onBlocking { getSavedApps() }.doReturn(
                listOf(
                    Application("123", "asd", "packageName"),
                    Application("asdc", "Adadfdsf", "packageName"),
                    Application("zcxasdcasdgf", "wasdadsgsg", "packageName"),
                    Application("sdafadAD", "ADAXVCZXV", "packageName"),
                    Application("zxCXBVADSF", "adqwwe12easdfaD", "packageName")
                )
            )
        }
        applicationInteractor.stub {
            onBlocking { getDeviceApps() }.doReturn(listOf())
        }
        val uiSavedApps = applicationInteractor.getSavedApps().toList().convertToUi()
            .map {
                UiApplication(
                    it.name,
                    it.versionNameMd5,
                    it.packageName
                ).apply {
                    isDeleted = true
                }
            }

        viewModel = ApplicationsViewModel(
            applicationInteractor,
            Dispatchers.Unconfined,
            Dispatchers.Unconfined
        )
        viewModel.changedApps.observeForever {
            assertEquals(uiSavedApps, it)
        }
    }

    @Test
    fun `Should post only one deleted apps if there are another apps `() = runBlocking {
        applicationInteractor.stub {
            onBlocking { getSavedApps() }.doReturn(
                listOf(
                    Application("123", "asd", "packageName"),
                    Application("asdc", "Adadfdsf", "packageName"),
                    Application("zcxasdcasdgf", "wasdadsgsg", "packageName"),
                    Application("sdafadAD", "ADAXVCZXV", "packageName"),
                    Application("zxCXBVADSF", "adqwwe12easdfaD", "packageName")
                )
            )
        }
        applicationInteractor.stub {
            onBlocking { getDeviceApps() }.doReturn(
                listOf(
                    Application("asdsad", "asd", "packageName"),
                    Application("asdas", "Adadfdsf", "packageName"),
                    Application("aadvsdxvc", "wasdadsgsg", "packageName"),
                    Application("adfasxv", "ADAXVCZXV", "packageName"),
                    Application("awedweofj;;;", "adqwwe12easdfaD", "packageName")
                )
            )
        }
        val uiSavedApps = applicationInteractor.getSavedApps().toList().convertToUi()
            .map {
                UiApplication(
                    it.name,
                    it.versionNameMd5,
                    it.packageName
                ).apply {
                    isDeleted = true
                }
            }
        viewModel = ApplicationsViewModel(
            applicationInteractor,
            Dispatchers.Unconfined,
            Dispatchers.Unconfined
        )
        viewModel.changedApps.observeForever { deletedApps ->
            assertEquals(uiSavedApps, deletedApps.filter { it.isDeleted })
        }
    }
}
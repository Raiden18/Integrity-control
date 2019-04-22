package com.raiden.karpukhinomgupsdiplom.screens.content.applications

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.raiden.domain.interactors.applications.ApplicationsInteractor
import com.raiden.domain.models.Application
import io.mockk.coEvery
import io.mockk.mockk
import junit.framework.Assert.assertEquals
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.runBlocking
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.rules.TestRule

class ApplicationsViewModelUpdatedAppsTest {
    @get:Rule
    var rule: TestRule = InstantTaskExecutorRule()
    lateinit var viewModel: ApplicationsViewModel
    lateinit var applicationInteractor: ApplicationsInteractor

    @Before
    fun setUp() {
        applicationInteractor = mockk()
    }

    @Test
    fun `Should set 1 updated app`() = runBlocking {
        coEvery { applicationInteractor.getSavedApps() }.returns(
            listOf(
                Application("123", "222", "222")
            )
        )
        coEvery { applicationInteractor.getDeviceApps() }.returns(
            listOf(Application("123", "321", "222"))
        )
        viewModel = ApplicationsViewModel(applicationInteractor, Dispatchers.Unconfined, Dispatchers.Unconfined)

        viewModel.changedApps.observeForever {
            assertEquals(1, it.size)
        }
    }
}
package com.raiden.karpukhinomgupsdiplom.screens.content.common

import com.nhaarman.mockitokotlin2.mock
import com.nhaarman.mockitokotlin2.stub
import com.raiden.karpukhinomgupsdiplom.uimodels.UiContent
import kotlinx.coroutines.runBlocking
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Test

//Look at MockK
class UiContentViewModelTest {
    lateinit var viewModel: ContentViewModel
    @Before
    fun setUp() {
        viewModel = mock()
    }

    @Test
    fun `Should post deleted content if there are no content in device `() = runBlocking {
        val deviceContents = listOf<UiContent>(
            mock(),
            mock(),
            mock(),
            mock()
        )
        viewModel.stub {
            onBlocking { loadSavedContent() }.thenReturn(deviceContents)
            onBlocking { loadDeviceContent() }.thenReturn(listOf())
        }

        viewModel.loadSavedAndDeviceApps()

        viewModel.changedApps.observeForever {
            assertEquals(deviceContents, it)
        }
    }
}
package com.raiden.karpukhinomgupsdiplom.content.common

import com.nhaarman.mockitokotlin2.mock
import com.nhaarman.mockitokotlin2.stub
import com.raiden.karpukhinomgupsdiplom.content.common.models.UiContent
import kotlinx.coroutines.runBlocking
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Test

class UiContentViewModelTest {
    lateinit var viewModel: UiContentViewModel
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
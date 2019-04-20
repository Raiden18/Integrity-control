package com.raiden.karpukhinomgupsdiplom.screens.content.files

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.nhaarman.mockitokotlin2.doReturn
import com.nhaarman.mockitokotlin2.mock
import com.nhaarman.mockitokotlin2.stub
import com.raiden.domain.interactors.files.FilesInteractor
import com.raiden.domain.models.InternalFile
import com.raiden.karpukhinomgupsdiplom.screens.content.common.ContentViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.runBlocking
import org.junit.Assert
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.rules.TestRule

class FilesViewModelTest {
    @get:Rule
    var rule: TestRule = InstantTaskExecutorRule()
    lateinit var viewModel: ContentViewModel

    @Before
    fun setUp() {
        viewModel = mock()
    }

    @Test
    fun `Should post deleted files if there are no apps in device`() = runBlocking {
        val savedFiles = listOf(
            InternalFile("123"),
            InternalFile("asdc"),
            InternalFile("zcxasdcasdgf"),
            InternalFile("sdafadAD"),
            InternalFile("zxCXBVADSF")
        )
        viewModel.stub {
            onBlocking { loadSavedContent() }.doReturn(savedFiles.convertToUi())
            onBlocking { loadDeviceContent() }.doReturn(listOf())
        }
        val interactor: FilesInteractor = mock()
        interactor.stub {
            onBlocking { getSavedFiles() }.doReturn(listOf())
            onBlocking { getDeviceFiles() }.doReturn(listOf())
        }
        viewModel.stub {
            onBlocking { loadSavedContent() }.doReturn(savedFiles.convertToUi())
            onBlocking { loadDeviceContent() }.doReturn(listOf())
        }
        viewModel = FilesViewModel(interactor, Dispatchers.Unconfined, Dispatchers.Unconfined)
        val uiSavedFiles = savedFiles.convertToUi()
        viewModel.changedApps.observeForever {
            Assert.assertEquals(savedFiles, uiSavedFiles)
        }
    }

}
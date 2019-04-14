package com.raiden.domain.interactors.info

import com.nhaarman.mockitokotlin2.doReturn
import com.nhaarman.mockitokotlin2.mock
import com.nhaarman.mockitokotlin2.stub
import com.raiden.domain.gateways.ApplicationsGateway
import com.raiden.domain.gateways.FilesGateway
import com.raiden.domain.models.InternalFile
import kotlinx.coroutines.runBlocking
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Test

internal class InfoInteractorCountOfDeletedFiles {
    lateinit var interactor: InfoInteractor
    lateinit var filesGateway: FilesGateway;

    @Before
    fun setUp() {
        filesGateway = mock()
        val appsGateway: ApplicationsGateway = mock()
        interactor = InfoInteractorImpl(appsGateway, filesGateway)
    }

    @Test
    fun `Should return zero deleted files if there is empty database`() = runBlocking {
        filesGateway.stub {
            onBlocking { getSavedFiles() }.doReturn(listOf())
            onBlocking { getFilesFromDevice() }.doReturn(listOf())
        }
        val countOfAddedFiles = interactor.getCountOfDeletedFiles()
        assertEquals(0, countOfAddedFiles)
    }

    @Test
    fun `Should return one deleted files`() = runBlocking {
        filesGateway.stub {
            onBlocking { getSavedFiles() }.doReturn(listOf(InternalFile("asdasd")))
            onBlocking { getFilesFromDevice() }.doReturn(listOf())
        }
        val countOfAddedFiles = interactor.getCountOfDeletedFiles()
        assertEquals(1, countOfAddedFiles)
    }

    @Test
    fun `Should return four deleted files`() = runBlocking {
        filesGateway.stub {
            onBlocking { getSavedFiles() }.doReturn(
                listOf(
                    InternalFile("asdasd"),
                    InternalFile("123"),
                    InternalFile("zxc"),
                    InternalFile("asd44444asd")
                )
            )
            onBlocking { getFilesFromDevice() }.doReturn(listOf())
        }
        val countOfAddedFiles = interactor.getCountOfDeletedFiles()
        assertEquals(4, countOfAddedFiles)
    }

    @Test
    fun `Should return one deleted files if another files exists`() = runBlocking {
        filesGateway.stub {
            onBlocking { getSavedFiles() }.doReturn(
                listOf(
                    InternalFile("asdasd"),
                    InternalFile("123"),
                    InternalFile("zxc"),
                    InternalFile("asd44444asd"),
                    InternalFile("zzxxxv"),
                    InternalFile("213zqdas228")
                )
            )
            onBlocking { getFilesFromDevice() }.doReturn(listOf(
                InternalFile("123"),
                InternalFile("zxc"),
                InternalFile("asd44444asd")
            ))
        }
        val countOfAddedFiles = interactor.getCountOfDeletedFiles()
        assertEquals(3, countOfAddedFiles)
    }

}
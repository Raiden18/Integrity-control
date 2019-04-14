package com.raiden.data.repositories.files

import com.nhaarman.mockitokotlin2.*
import com.raiden.data.datasources.database.dao.FilesDao
import com.raiden.data.datasources.database.entities.FileEntity
import com.raiden.data.datasources.device.files.DeviceFiles
import com.raiden.domain.gateways.FilesGateway
import com.raiden.domain.models.InternalFile
import junit.framework.Assert.assertEquals
import kotlinx.coroutines.runBlocking
import org.junit.Before
import org.junit.Test

internal class FilesRepositoryTest {
    lateinit var filesRepo: FilesGateway
    lateinit var device: DeviceFiles
    lateinit var filesDao: FilesDao

    @Before
    fun setUp() {
        device = mock()
        filesDao = mock()
        filesRepo = FilesRepository(device, filesDao)
    }

    @Test
    fun `Should save all files`() = runBlocking {
        val files = listOf(
            InternalFile("Я на море"),
            InternalFile("Я в качачке"),
            InternalFile("Я у тачки")

        )
        device.stub {
            onBlocking { getFiles() }.thenReturn(files)
        }
        filesRepo.saveFilesFromDevices()
        verify(filesDao, times(files.size)).insert(any())
    }

    @Test
    fun `Should clear files befor insert`() = runBlocking {
        val files = listOf(
            InternalFile("Я на море"),
            InternalFile("Я в качачке"),
            InternalFile("Я у тачки")

        )
        device.stub {
            onBlocking { getFiles() }.thenReturn(files)
        }
        filesRepo.saveFilesFromDevices()
        inOrder(filesDao, device) {
            verify(filesDao).clearTable()
            verify(device).getFiles()
        }
    }


    @Test
    fun `Should return saved files`() = runBlocking {
        val entities = listOf(
            FileEntity("Я на море"),
            FileEntity("Я в качачке"),
            FileEntity("Я у тачки")
        )
        filesDao.stub {
            onBlocking { getFiles() }.thenReturn(entities)
        }

        val saveFiles = filesRepo.getSavedFiles()

        assertEquals(saveFiles, entities.convertToDomain())
    }
}
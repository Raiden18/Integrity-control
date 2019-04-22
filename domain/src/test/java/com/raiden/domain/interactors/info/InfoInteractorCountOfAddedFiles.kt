package com.raiden.domain.interactors.info

import com.nhaarman.mockitokotlin2.doReturn
import com.nhaarman.mockitokotlin2.mock
import com.nhaarman.mockitokotlin2.stub
import com.raiden.domain.gateways.ApplicationsGateway
import com.raiden.domain.gateways.ContactsGateway
import com.raiden.domain.gateways.FilesGateway
import com.raiden.domain.gateways.UpdatedTimeGateway
import com.raiden.domain.models.InternalFile
import junit.framework.Assert.assertEquals
import kotlinx.coroutines.runBlocking
import org.junit.Before
import org.junit.Test

internal class InfoInteractorCountOfAddedFiles {
    lateinit var interactor: InfoInteractor
    lateinit var filesGateway: FilesGateway

    @Before
    fun setUp() {
        filesGateway = mock()
        val appsGateway: ApplicationsGateway = mock()
        val contactsGateway: ContactsGateway = mock()
        val time : UpdatedTimeGateway = mock()
        interactor = InfoInteractorImpl(appsGateway, filesGateway, contactsGateway, time)
    }

    @Test
    fun `Should return zero added of files if database is empty`() = runBlocking {
        filesGateway.stub {
            onBlocking { getSavedFiles() }.doReturn(
                listOf()
            )
        }
        val countOfAddedFiles = interactor.getCountOfAddedFiles()
        assertEquals(0, countOfAddedFiles)
    }

    @Test
    fun `Should return one added file`() = runBlocking {
        filesGateway.stub {
            onBlocking { getSavedFiles() }.doReturn(listOf(
                InternalFile("Я")
            ))
            onBlocking { getFilesFromDevice() }.doReturn(listOf(
                InternalFile("Мой 222 в профиль")
            ))
        }
        val countOfAddedFiles = interactor.getCountOfAddedFiles()
        assertEquals(1, countOfAddedFiles)
    }

    @Test
    fun `Should return 5 added file`() = runBlocking {
        filesGateway.stub {
            onBlocking { getSavedFiles() }.doReturn(listOf(
                InternalFile("123123"),
                InternalFile("123213aaaa"),
                InternalFile("zzxcc"),
                InternalFile("zxczxc"),
                InternalFile("Мой asd в wwwww"),
                InternalFile("Мой xxxx в профиль")
            ))
            onBlocking { getFilesFromDevice() }.doReturn(listOf(
                InternalFile("11223 333 в профиль"),
                InternalFile("Мой ascxz в профиль"),
                InternalFile("Мой zzz в wwwww"),
                InternalFile("Мой 3221` в профиль"),
                InternalFile("Мой asdd в 234"),
                InternalFile("М11233ой zxc в профиль")
            ))
        }
        val countOfAddedFiles = interactor.getCountOfAddedFiles()
        assertEquals(5, countOfAddedFiles)
    }
    @Test
    fun `Should return 0 added file if wasn't updated`() = runBlocking {
        filesGateway.stub {
            onBlocking { getSavedFiles() }.doReturn(listOf(
                InternalFile("123123"),
                InternalFile("123213aaaa"),
                InternalFile("zzxcc"),
                InternalFile("zxczxc"),
                InternalFile("Мой 222 в wwwww"),
                InternalFile("Мой xxxx в профиль")
            ))
            onBlocking { getFilesFromDevice() }.doReturn(listOf(InternalFile("123123"),
                InternalFile("123213aaaa"),
                InternalFile("zzxcc"),
                InternalFile("zxczxc"),
                InternalFile("Мой 122 в wwwww"),
                InternalFile("Мой xxxx в профиль")))
        }
        val countOfAddedFiles = interactor.getCountOfAddedFiles()
        assertEquals(0, countOfAddedFiles)
    }
}
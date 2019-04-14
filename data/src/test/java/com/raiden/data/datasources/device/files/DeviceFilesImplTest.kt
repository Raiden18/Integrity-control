package com.raiden.data.datasources.device.files

import com.nhaarman.mockitokotlin2.mock
import com.raiden.domain.models.InternalFile
import junit.framework.Assert.assertEquals
import kotlinx.coroutines.runBlocking
import org.junit.Before
import org.junit.Test
import org.mockito.Mockito
import org.mockito.Mockito.`when`
import java.io.File

class DeviceFilesImplTest {
    private lateinit var deviceFiles: DeviceFiles
    private lateinit var rootDirectory: File

    @Before
    fun setUp() {
        rootDirectory = mock()
        deviceFiles = DeviceFilesImpl(rootDirectory)
    }

    @Test
    fun `Should return list of files`() = runBlocking {
        val file1 = Mockito.mock(File::class.java)
        `when`(file1.absolutePath).thenReturn("123213123")
        val file2 = Mockito.mock(File::class.java)
        `when`(file2.absolutePath).thenReturn("zdasdasd")
        val file3 = Mockito.mock(File::class.java)
        `when`(file3.absolutePath).thenReturn("asdz")
        `when`(rootDirectory.listFiles()).thenReturn(
            arrayOf(
                file1,
                file2,
                file3
            )
        )
        val internalFile1 = InternalFile("123213123")
        val internalFile2 = InternalFile("zdasdasd")
        val internalFile3 = InternalFile("asdz")

        val internalFiles = listOf(
            internalFile1,
            internalFile2,
            internalFile3
        )
        val files = deviceFiles.getFiles()
        assertEquals(files, internalFiles)
    }
}
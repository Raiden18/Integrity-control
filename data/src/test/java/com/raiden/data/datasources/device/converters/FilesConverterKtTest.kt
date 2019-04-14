package com.raiden.data.datasources.device.converters

import com.raiden.data.datasources.device.files.convertToDomainFile
import com.raiden.data.datasources.device.files.convertToDomainFiles
import com.raiden.domain.models.InternalFile
import org.junit.Assert.assertEquals
import org.junit.Test
import org.mockito.Mockito.`when`
import org.mockito.Mockito.mock
import java.io.File

class FilesConverterKtTest {
    @Test
    fun `Should convert File to domain InternaFile`() {
        val file = mock(File::class.java)
        `when`(file.absolutePath).thenReturn("123213123")
        val convertedInternalFile = file.convertToDomainFile()
        val internalFile = InternalFile("123213123")
        assertEquals(internalFile, convertedInternalFile)
    }

    @Test
    fun `Should convert list of Files to list of domain InternaFile`() {
        val file1 = mock(File::class.java)
        `when`(file1.absolutePath).thenReturn("123213123")
        val file2 = mock(File::class.java)
        `when`(file2.absolutePath).thenReturn("zdasdasd")
        val file3 = mock(File::class.java)
        `when`(file3.absolutePath).thenReturn("asdz")

        val files = listOf(
            file1,
            file2,
            file3
        )
        val convertedFiles = files.convertToDomainFiles()

        val internalFile1 = InternalFile("123213123")
        val internalFile2 = InternalFile("zdasdasd")
        val internalFile3 = InternalFile("asdz")

        val internalFiles = listOf(
            internalFile1,
            internalFile2,
            internalFile3
        )
        assertEquals(convertedFiles, internalFiles)
    }
}
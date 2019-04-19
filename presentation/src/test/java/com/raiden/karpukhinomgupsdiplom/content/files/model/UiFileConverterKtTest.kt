package com.raiden.karpukhinomgupsdiplom.content.files.model

import com.raiden.domain.models.InternalFile
import org.junit.Assert.assertEquals
import org.junit.Test

class UiFileConverterKtTest {
    @Test
    fun `Should convert domain file to uiConverter`() {
        val domainFile = InternalFile("name")
        val uiFile = UiFile("name")

        val convertedFile = domainFile.convertToUi()
        assertEquals(uiFile, convertedFile)
    }

    @Test
    fun `Should convert list of domain files to list of UiFile`() {
        val domainFiles = listOf(
            InternalFile("name"),
            InternalFile("123")
        )
        val uiFiles = listOf(
            UiFile("name"),
            UiFile("123")
        )

        val convertedFile = domainFiles.convertToUi()
        assertEquals(uiFiles, convertedFile)
    }
}
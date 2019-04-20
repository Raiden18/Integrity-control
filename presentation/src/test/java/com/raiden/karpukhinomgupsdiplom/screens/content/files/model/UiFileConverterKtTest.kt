package com.raiden.karpukhinomgupsdiplom.screens.content.files.model

import com.raiden.domain.models.InternalFile
import com.raiden.karpukhinomgupsdiplom.converters.convertToUi
import com.raiden.karpukhinomgupsdiplom.uimodels.UiFile
import org.junit.Assert.assertEquals
import org.junit.Test

class UiFileConverterKtTest {
    @Test
    fun `Should convert domain file to uiConverter`() {
        val domainFile = InternalFile("currentName")
        val uiFile = UiFile("currentName")

        val convertedFile = domainFile.convertToUi()
        assertEquals(uiFile, convertedFile)
    }

    @Test
    fun `Should convert list of domain files to list of UiFile`() {
        val domainFiles = listOf(
            InternalFile("currentName"),
            InternalFile("123")
        )
        val uiFiles = listOf(
            UiFile("currentName"),
            UiFile("123")
        )

        val convertedFile = domainFiles.convertToUi()
        assertEquals(uiFiles, convertedFile)
    }
}
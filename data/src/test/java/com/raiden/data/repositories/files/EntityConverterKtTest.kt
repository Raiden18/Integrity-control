package com.raiden.data.repositories.files

import com.raiden.data.datasources.database.entities.FileEntity
import com.raiden.domain.models.InternalFile
import junit.framework.Assert.assertEquals
import org.junit.Test

class EntityConverterKtTest {

    @Test
    fun `Should convert file entity to domain file`() {
        val fileEntity = FileEntity("123")
        val domainFile = InternalFile("123")
        val convertedFile = fileEntity.convertToDomain()

        assertEquals(domainFile, convertedFile)
    }

    @Test
    fun `Should convert domain file to file entity`() {
        val fileEntity = FileEntity("123")
        val domainFile = InternalFile("123")
        val convertedFile = domainFile.convertToEntity()

        assertEquals(fileEntity, convertedFile)
    }

    @Test
    fun `Should convert list of domain file to list file entity`() {
        val fileEntities = listOf(
            FileEntity("123"),
            FileEntity("312"),
            FileEntity("asd"),
            FileEntity("zxc")
        )
        val domainFiles = listOf(
            InternalFile("123"),
            InternalFile("312"),
            InternalFile("asd"),
            InternalFile("zxc")
        )
        val convertedFile = domainFiles.convertToEntities()

        assertEquals(fileEntities, convertedFile)
    }

    @Test
    fun `Should convert list of entity file to list of domain files`() {
        val fileEntities = listOf(
            FileEntity("123"),
            FileEntity("312"),
            FileEntity("asd"),
            FileEntity("zxc")
        )
        val domainFiles = listOf(
            InternalFile("123"),
            InternalFile("312"),
            InternalFile("asd"),
            InternalFile("zxc")
        )
        val convertedFile = fileEntities.convertToDomain()

        assertEquals(domainFiles, convertedFile)
    }
}
package com.raiden.data.repositories.contacts

import com.raiden.data.datasources.database.entities.ContactsEntity
import com.raiden.domain.models.Contact
import org.junit.Assert.assertEquals
import org.junit.Test

class EntityConverterKtTest {

    @Test
    fun `Should convert Entity contact to domain`() {
        val contactEntity = ContactsEntity("123", "123", "321")
        val contactDomain = Contact("123", "123", "321")

        val contactConverted = contactEntity.convertToDomain()
        assertEquals(contactDomain, contactConverted)
    }

    @Test
    fun `Should convert domain contact to entity`() {
        val contactEntity = ContactsEntity("123", "123", "321")
        val contactDomain = Contact("123", "123", "321")

        val contactConverted = contactDomain.convertToEntity()
        assertEquals(contactEntity, contactConverted)
    }

    @Test
    fun `Should convert list of Entities contact to list of domain`() {
        val contactEntities = listOf(
            ContactsEntity("123", "123", "321"),
            ContactsEntity("123", "222", "333")
        )
        val contactDomains = listOf(
            Contact("123", "123", "321"),
            Contact("123", "222", "333")
        )

        val contactConverted = contactEntities.convertToDomains()
        assertEquals(contactDomains, contactConverted)
    }

    @Test
    fun `Should convert list of domain contact to list of contacts`() {
        val contactEntities = listOf(
            ContactsEntity("123", "123", "321"),
            ContactsEntity("123", "222", "333")
        )
        val contactDomains = listOf(
            Contact("123", "123", "321"),
            Contact("123", "222", "333")
        )

        val contactConverted = contactDomains.convertToEnteties()
        assertEquals(contactEntities, contactConverted)
    }
}
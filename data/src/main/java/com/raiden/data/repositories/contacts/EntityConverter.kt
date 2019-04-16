package com.raiden.data.repositories.contacts

import com.raiden.data.datasources.database.entities.ContactsEntity
import com.raiden.domain.models.Contact

internal fun ContactsEntity.convertToDomain(): Contact {
    return Contact(name, mobileNumber)
}

internal fun Contact.convertToEntity(): ContactsEntity {
    return ContactsEntity(name, mobileNumber)
}

internal fun List<ContactsEntity>.convertToDomains(): List<Contact>{
    return map { it.convertToDomain() }
}

internal fun List<Contact>.convertToEntetis(): List<ContactsEntity>{
    return map { it.convertToEntity() }
}
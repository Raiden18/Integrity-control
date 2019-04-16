package com.raiden.data.datasources.device.contacts

import com.raiden.domain.models.Contact

internal interface DeviceContacts {
    suspend fun getContacts(): List<Contact>
}
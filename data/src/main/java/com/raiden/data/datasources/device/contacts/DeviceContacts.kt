package com.raiden.data.datasources.device.contacts

import com.raiden.domain.models.Contact

interface DeviceContacts {
    fun getContacts(): List<Contact>
}
package com.raiden.karpukhinomgupsdiplom.content.contacts.models

import com.raiden.domain.models.Contact

internal fun Contact.convertToUi(): UiContact {
    return UiContact(id, name, mobileNumber)
}

internal fun List<Contact>.convertToUi(): List<UiContact> {
    return map { it.convertToUi() }
}
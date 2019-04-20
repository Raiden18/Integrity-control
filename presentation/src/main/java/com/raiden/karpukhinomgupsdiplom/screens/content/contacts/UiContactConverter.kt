package com.raiden.karpukhinomgupsdiplom.screens.content.contacts

import com.raiden.domain.models.Contact
import com.raiden.karpukhinomgupsdiplom.uimodels.UiContact

internal fun Contact.convertToUi(): UiContact {
    return UiContact(id, name, mobileNumber)
}

internal fun List<Contact>.convertToUi(): List<UiContact> {
    return map { it.convertToUi() }
}
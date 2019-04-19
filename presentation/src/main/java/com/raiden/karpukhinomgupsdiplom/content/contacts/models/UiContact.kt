package com.raiden.karpukhinomgupsdiplom.content.contacts.models

import com.raiden.karpukhinomgupsdiplom.content.common.models.UiContent

data class UiContact(
    val id: String,
    val name: String,
    val phoneNumber: String
) : UiContent {
    override val primaryKey = id
    override val nameContent = name
    override var isDeleted: Boolean = false
    override var isInstalled: Boolean = false
    override var isUpdated: Boolean = false
}
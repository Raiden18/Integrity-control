package com.raiden.karpukhinomgupsdiplom.content.files.model

import com.raiden.domain.models.InternalFile

internal fun InternalFile.convertToUi(): UiFile {
    return UiFile(fullName)
}

internal fun List<InternalFile>.convertToUi(): List<UiFile> {
    return map { it.convertToUi() }
}
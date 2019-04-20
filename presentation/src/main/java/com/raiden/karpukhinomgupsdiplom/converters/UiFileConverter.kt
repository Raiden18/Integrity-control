package com.raiden.karpukhinomgupsdiplom.converters

import com.raiden.domain.models.InternalFile
import com.raiden.karpukhinomgupsdiplom.uimodels.UiFile

internal fun InternalFile.convertToUi(): UiFile {
    return UiFile(fullName)
}

internal fun List<InternalFile>.convertToUi(): List<UiFile> {
    return map { it.convertToUi() }
}
package com.raiden.data.datasources.device.files

import com.raiden.domain.models.InternalFile
import java.io.File

internal fun List<File>.convertToDomainFiles(): List<InternalFile> {
    return map { it.convertToDomainFile() }
}

internal fun File.convertToDomainFile(): InternalFile {
    return InternalFile(absolutePath)
}
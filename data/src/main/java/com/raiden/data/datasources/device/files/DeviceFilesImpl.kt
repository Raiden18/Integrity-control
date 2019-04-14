package com.raiden.data.datasources.device.files

import com.raiden.domain.models.InternalFile
import java.io.File


internal class DeviceFilesImpl(private val rootDirectory: File) : DeviceFiles {
    override suspend fun getFiles(): Iterable<InternalFile> {
        val files = rootDirectory.listFiles().toList()
        return files.convertToDomainFiles()
    }
}
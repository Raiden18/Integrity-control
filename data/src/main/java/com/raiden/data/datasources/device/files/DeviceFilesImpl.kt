package com.raiden.data.datasources.device.files

import com.raiden.domain.models.InternalFile
import java.io.File


internal class DeviceFilesImpl(private val rootDirectory: File) : DeviceFiles {
    //private val rootDirectory: File = File(Environment.getExternalStorageDirectory().absolutePath)
    val internalFiles = arrayListOf<InternalFile>()

    override suspend fun getFiles(): Iterable<InternalFile> {
        internalFiles.clear()
        val files = rootDirectory.listFiles().toList()

        return listOf()
    }
}
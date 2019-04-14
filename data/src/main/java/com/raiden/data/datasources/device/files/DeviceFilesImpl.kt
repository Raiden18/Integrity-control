package com.raiden.data.datasources.device.files

import com.raiden.domain.models.InternalFile
import java.io.File


internal class DeviceFilesImpl(private val rootDirectory: File) : DeviceFiles {
    override suspend fun getFiles(): Iterable<InternalFile> {
        val files = arrayListOf<File>()
        rootDirectory.walkBottomUp().filter { it.isFile }
            .filter {
                it.name.endsWith(".jpg")
                        || it.name.endsWith(".mp3")
                        || it.name.endsWith(".ogg")
                        || it.name.endsWith(".apk")
                        || it.name.endsWith(".docx")
                        || it.name.endsWith(".pdf")
                        || it.name.endsWith(".xlsx")
                        || it.name.endsWith(".rar")
                        || it.name.endsWith(".zip")
                        || it.name.endsWith(".doc")
                        || it.name.endsWith(".xml")
                        || it.name.endsWith(".txt")
                        || it.name.endsWith(".mp4")
            }
            .forEach { files.add(it) }

        return files.convertToDomainFiles()
    }
}
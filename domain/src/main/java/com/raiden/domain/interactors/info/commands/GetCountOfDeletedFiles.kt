package com.raiden.domain.interactors.info.commands

import com.raiden.domain.gateways.FilesGateway
import com.raiden.domain.models.InternalFile

internal class GetCountOfDeletedFiles(private val gateway: FilesGateway) {
    private var countOfDeletedFiles = 0

    suspend fun getCountOfDeletedFiles(): Int {
        val savedFiles = gateway.getSavedFiles().toList()
        val deviceFiles = gateway.getFilesFromDevice()
        if (!savedFiles.isEmpty()) {
            countOfDeletedFiles(savedFiles, deviceFiles)
        }
        return countOfDeletedFiles
    }

    private fun countOfDeletedFiles(savedFiles: Iterable<InternalFile>, deviceFiles: Iterable<InternalFile>) {
        savedFiles.forEach { savedFile->
            if (!deviceFiles.contains(savedFile)) {
                countOfDeletedFiles++
            }
        }
    }
}
package com.raiden.domain.interactors.info.commands

import com.raiden.domain.gateways.FilesGateway
import com.raiden.domain.models.InternalFile

internal class GetCountOFAddedFiles(private val gateway: FilesGateway) {
    private var countOfAddedFiles = 0

    suspend fun getCountOfAddedFiles(): Int {
        val deviceFiles = gateway.getFilesFromDevice()
        val savedFiles = gateway.getSavedFiles().toList()
        if (!savedFiles.isEmpty()) {
            countOfAddedFiles(savedFiles, deviceFiles)
        }
        return countOfAddedFiles
    }

    private fun countOfAddedFiles(savedFiles: Iterable<InternalFile>, filesFromDevice: Iterable<InternalFile>) {
        filesFromDevice.forEach { deviceFiles ->
            if (!savedFiles.contains(deviceFiles)) {
                countOfAddedFiles++
            }
        }
    }
}
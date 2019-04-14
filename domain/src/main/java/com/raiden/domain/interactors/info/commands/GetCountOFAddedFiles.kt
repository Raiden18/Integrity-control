package com.raiden.domain.interactors.info.commands

import com.raiden.domain.gateways.FilesGateway
import com.raiden.domain.models.InternalFile

class GetCountOFAddedFiles(private val gateway: FilesGateway) {
    private var countOfdeletedApps = 0

    suspend fun getCountOfAddedFiles(): Int {
        countOfdeletedApps = 0
        val deviceFiles = gateway.getFilesFromDevice()
        val savedFiles = gateway.getSavedFiles().toList()
        if (!savedFiles.isEmpty()) {
            countOfAddedFiles(savedFiles, deviceFiles)
        }
        return countOfdeletedApps
    }

    private fun countOfAddedFiles(savedFiles: Iterable<InternalFile>, filesFromDevice: Iterable<InternalFile>) {
        savedFiles.forEach { savedFile ->
            if (!filesFromDevice.isAdded(savedFile)) {
                countOfdeletedApps++
            }
        }
    }

    private fun Iterable<InternalFile>.isAdded(savedFile: InternalFile): Boolean {
        return find { it.fullName == savedFile.fullName } != null
    }
}
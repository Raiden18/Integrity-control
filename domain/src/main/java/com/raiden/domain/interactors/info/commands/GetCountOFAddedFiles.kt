package com.raiden.domain.interactors.info.commands

import com.raiden.domain.gateways.FilesGateway
import com.raiden.domain.models.InternalFile

internal class GetCountOFAddedFiles(private val gateway: FilesGateway) {
    private var countOfdeletedApps = 0

    suspend fun getCountOfAddedFiles(): Int {
        val deviceFiles = gateway.getFilesFromDevice()
        val savedFiles = gateway.getSavedFiles().toList()
        if (!savedFiles.isEmpty()) {
            countOfAddedFiles(savedFiles, deviceFiles)
        }
        return countOfdeletedApps
    }

    private fun countOfAddedFiles(savedFiles: Iterable<InternalFile>, filesFromDevice: Iterable<InternalFile>) {
        filesFromDevice.forEach { deviceFiles ->
            if (!savedFiles.isAdded(deviceFiles)) {
                countOfdeletedApps++
            }
        }
    }

    private fun Iterable<InternalFile>.isAdded(deviceFiles: InternalFile): Boolean {
        return find { it.fullName == deviceFiles.fullName } != null
    }
}
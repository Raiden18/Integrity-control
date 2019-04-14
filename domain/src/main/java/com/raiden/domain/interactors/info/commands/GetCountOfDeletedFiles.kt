package com.raiden.domain.interactors.info.commands

import com.raiden.domain.gateways.FilesGateway
import com.raiden.domain.models.InternalFile

internal class GetCountOfDeletedFiles(private val gateway: FilesGateway) {
    private var countOfdeletedApps = 0

    suspend fun getCountOfDeletedFiles(): Int {
        countOfdeletedApps = 0
        val savedFiles = gateway.getSavedFiles().toList()
        val deviceFiles = gateway.getFilesFromDevice()
        if (!savedFiles.isEmpty()) {
            countOfDeletedFiles(savedFiles, deviceFiles)
        }
        return countOfdeletedApps
    }

    private fun countOfDeletedFiles(savedFiles: Iterable<InternalFile>, deviceFiles: Iterable<InternalFile>) {
        savedFiles.forEach { savedFile->
            if (!deviceFiles.isDeleted(savedFile)){
                countOfdeletedApps++
            }
        }
    }

    private fun Iterable<InternalFile>.isDeleted(savedFile: InternalFile): Boolean{
        return find { it.fullName == savedFile.fullName } != null
    }
}
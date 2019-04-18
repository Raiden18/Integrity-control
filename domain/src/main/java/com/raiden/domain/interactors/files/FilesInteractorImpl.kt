package com.raiden.domain.interactors.files

import com.raiden.domain.gateways.FilesGateway
import com.raiden.domain.models.InternalFile

class FilesInteractorImpl(private val filesGateway: FilesGateway) : FilesInteractor {
    override suspend fun getSavedFiles(): Iterable<InternalFile> {
        return filesGateway.getSavedFiles()
    }

    override suspend fun getDeviceFiles(): Iterable<InternalFile> {
        return filesGateway.getFilesFromDevice()
    }
}
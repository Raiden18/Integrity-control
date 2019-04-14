package com.raiden.domain.gateways

import com.raiden.domain.models.InternalFile

interface FilesGateway {
    suspend fun saveFilesFromDevices()
    suspend fun getSavedFiles(): Iterable<InternalFile>
    suspend fun getFilesFromDevice(): Iterable<InternalFile>
}
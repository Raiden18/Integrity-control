package com.raiden.domain.interactors.files

import com.raiden.domain.models.InternalFile

interface FilesInteractor {
    suspend fun getSavedFiles(): Iterable<InternalFile>
    suspend fun getDeviceFiles(): Iterable<InternalFile>
}
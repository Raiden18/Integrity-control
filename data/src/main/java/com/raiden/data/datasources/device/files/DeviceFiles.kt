package com.raiden.data.datasources.device.files

import com.raiden.domain.models.InternalFile

internal interface DeviceFiles {
    suspend fun getFiles(): Iterable<InternalFile>
}
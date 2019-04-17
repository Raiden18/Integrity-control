package com.raiden.data.repositories.files

import com.raiden.data.datasources.database.dao.FilesDao
import com.raiden.data.datasources.device.files.DeviceFiles
import com.raiden.domain.gateways.FilesGateway
import com.raiden.domain.models.InternalFile

internal class FilesRepository(
    private val device: DeviceFiles,
    private val filesDao: FilesDao
) : FilesGateway {

    override suspend fun saveFilesFromDevices() {
        filesDao.clearTable()
        val deviceFiles = device.getFiles().toList()
        if (!deviceFiles.isEmpty()) {
            saveInDataBase(deviceFiles)
        }
    }

    private suspend fun saveInDataBase(deviceFiles: Iterable<InternalFile>) {
        deviceFiles.forEach {
            filesDao.insert(it.convertToEntity())
        }
    }

    override suspend fun getSavedFiles(): Iterable<InternalFile> {
        if (filesDao.getFiles().isEmpty()){
            saveFilesFromDevices()
        }
        val fileEntities = filesDao.getFiles()
        return fileEntities.convertToDomain()
    }

    override suspend fun getFilesFromDevice(): Iterable<InternalFile> {
        return device.getFiles()
    }
}
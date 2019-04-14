package com.raiden.data.repositories.files

import com.raiden.data.datasources.database.entities.FileEntity
import com.raiden.domain.models.InternalFile

internal fun FileEntity.convertToDomain(): InternalFile{
    return InternalFile(fullName)
}

internal fun InternalFile.convertToEntity(): FileEntity{
    return FileEntity(fullName)
}

internal fun Iterable<InternalFile>.convertToEntities(): Iterable<FileEntity>{
    return map { it.convertToEntity() }
}

internal fun Iterable<FileEntity>.convertToDomain(): Iterable<InternalFile>{
    return map { it.convertToDomain() }
}
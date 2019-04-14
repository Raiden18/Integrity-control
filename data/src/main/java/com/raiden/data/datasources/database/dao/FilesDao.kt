package com.raiden.data.datasources.database.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.raiden.data.DataBase.FILES_TABLE_NAME
import com.raiden.data.datasources.database.entities.FileEntity

@Dao
internal interface FilesDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(fileEntity: FileEntity)

    @Query("SELECT * from $FILES_TABLE_NAME")
    suspend fun getFiles(): List<FileEntity>

    @Query("DELETE FROM $FILES_TABLE_NAME")
    suspend fun clearTable()
}
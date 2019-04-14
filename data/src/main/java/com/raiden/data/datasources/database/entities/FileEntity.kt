package com.raiden.data.datasources.database.entities

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.raiden.data.DataBase.FILES_TABLE_NAME

@Entity(tableName = FILES_TABLE_NAME)
data class FileEntity(
    @PrimaryKey
    @ColumnInfo(name = "fullName")
    val fullName: String
)
package com.raiden.data.sources.database.entities

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.raiden.data.DataBase.APPS_TABLE_NAME

@Entity(tableName = APPS_TABLE_NAME)
data class ApplicationEntity(
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "id")
    val id: Long,

    @ColumnInfo(name = "app_name")
    val name: String,

    @ColumnInfo(name = "app_version_name")
    val versionName: String
)
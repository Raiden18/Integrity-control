package com.raiden.data.datasources.database.entities

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.raiden.data.DataBase.APPS_TABLE_NAME

@Entity(tableName = APPS_TABLE_NAME)
internal data class ApplicationEntity(
    @ColumnInfo(name = "app_name")
    val name: String,

    @ColumnInfo(name = "app_version_name")
    val versionName: String,

    @PrimaryKey
    @ColumnInfo(name = "package_name")
    var packageName: String
)
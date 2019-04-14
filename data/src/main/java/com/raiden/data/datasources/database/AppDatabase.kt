package com.raiden.data.datasources.database

import androidx.room.Database
import androidx.room.RoomDatabase
import com.raiden.data.datasources.database.dao.ApplicationsDao
import com.raiden.data.datasources.database.dao.FilesDao
import com.raiden.data.datasources.database.entities.ApplicationEntity
import com.raiden.data.datasources.database.entities.FileEntity

@Database(entities = [
    ApplicationEntity::class,
    FileEntity::class
], version = 1)
internal abstract class AppDatabase(): RoomDatabase() {
    abstract fun getApplicationsDao(): ApplicationsDao
    abstract fun getFilessDao(): FilesDao
}
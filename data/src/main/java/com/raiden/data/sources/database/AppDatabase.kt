package com.raiden.data.sources.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.raiden.data.sources.database.dao.ApplicationsDao
import com.raiden.data.sources.database.entities.ApplicationEntity

@Database(entities = [
    ApplicationEntity::class
], version = 1)
abstract class AppDatabase(): RoomDatabase() {
    abstract fun getApplicationsDao(): ApplicationsDao
}
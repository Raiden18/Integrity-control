package com.raiden.data.sources.database.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.raiden.data.DataBase.APPS_TABLE_NAME
import com.raiden.data.sources.database.entities.ApplicationEntity

@Dao
interface ApplicationsDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(applicationEntity: ApplicationEntity)

    @Query("SELECT * from $APPS_TABLE_NAME")
    suspend fun getApplications(): List<ApplicationEntity>
}
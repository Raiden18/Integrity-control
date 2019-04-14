package com.raiden.data.datasources.database.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.raiden.data.DataBase.APPS_TABLE_NAME
import com.raiden.data.datasources.database.entities.ApplicationEntity

@Dao
internal interface ApplicationsDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(applicationEntity: ApplicationEntity)

    @Query("SELECT * from $APPS_TABLE_NAME")
    suspend fun getApplications(): List<ApplicationEntity>

    @Query("DELETE FROM $APPS_TABLE_NAME")
    suspend fun clearTable()
}
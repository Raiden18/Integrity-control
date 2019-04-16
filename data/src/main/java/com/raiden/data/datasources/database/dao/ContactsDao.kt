package com.raiden.data.datasources.database.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.raiden.data.DataBase.CONTACTS_TABLE_NAME
import com.raiden.data.datasources.database.entities.ContactsEntity

@Dao
interface ContactsDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(contactsEntity: ContactsEntity)

    @Query("SELECT * from $CONTACTS_TABLE_NAME")
    suspend fun getContacts(): List<ContactsEntity>

    @Query("DELETE FROM $CONTACTS_TABLE_NAME")
    suspend fun clearTable()
}
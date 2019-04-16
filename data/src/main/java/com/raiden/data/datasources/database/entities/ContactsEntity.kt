package com.raiden.data.datasources.database.entities

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.raiden.data.DataBase.CONTACTS_TABLE_NAME

@Entity(tableName = CONTACTS_TABLE_NAME)
data class ContactsEntity(
    @ColumnInfo(name="contact_name")
    val name: String,

    @PrimaryKey
    @ColumnInfo(name="mobile_number")
    val mobileNumber: String
)
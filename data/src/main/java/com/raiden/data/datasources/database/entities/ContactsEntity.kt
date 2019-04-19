package com.raiden.data.datasources.database.entities

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.raiden.data.DataBase.CONTACTS_TABLE_NAME

@Entity(tableName = CONTACTS_TABLE_NAME)
data class ContactsEntity(
    @PrimaryKey
    @ColumnInfo(name = "contact_id")
    val id: String,
    @ColumnInfo(name = "contact_name")
    val name: String,

    @ColumnInfo(name = "mobile_number")
    val mobileNumber: String
)
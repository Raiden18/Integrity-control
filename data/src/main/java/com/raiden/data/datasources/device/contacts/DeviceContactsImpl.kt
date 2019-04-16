package com.raiden.data.datasources.device.contacts

import android.content.Context
import android.provider.ContactsContract
import com.raiden.domain.models.Contact


internal class DeviceContactsImpl(private val context: Context) : DeviceContacts {
    private val contacts = arrayListOf<Contact>()
    private val contentResolver = context.contentResolver
    override suspend fun getContacts(): List<Contact> {
        contacts.clear()
        val cursor = contentResolver.query(
            ContactsContract.Contacts.CONTENT_URI,
            null,
            null,
            null,
            null
        )!!
        if (cursor.count > 0) {
            val id = cursor.getString(cursor.getColumnIndex(ContactsContract.Contacts._ID))
            val hasNumber = cursor.getInt(cursor.getColumnIndex(ContactsContract.Contacts.HAS_PHONE_NUMBER))
            if (hasNumber > 0) {
                val cursorInfo = contentResolver.query(
                    ContactsContract.CommonDataKinds.Phone.CONTENT_URI,
                    null,
                    ContactsContract.CommonDataKinds.Phone.CONTACT_ID + " = ?",
                    arrayOf<String>(id),
                    null
                )!!
                while (cursorInfo.moveToNext()) {
                    val name = cursor.getString(cursor.getColumnIndex(ContactsContract.Contacts.DISPLAY_NAME))
                    val mobileNumber =
                        cursorInfo.getString(cursorInfo.getColumnIndex(ContactsContract.CommonDataKinds.Phone.NUMBER))
                    val contact = Contact(id, name, mobileNumber)
                    contacts.add(contact)
                }
                cursorInfo.close()
            }
        }
        cursor.close()
        return contacts
    }
}
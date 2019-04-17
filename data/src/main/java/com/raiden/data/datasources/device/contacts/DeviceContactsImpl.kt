package com.raiden.data.datasources.device.contacts

import android.Manifest
import android.content.Context
import android.content.pm.PackageManager
import android.database.Cursor
import android.provider.ContactsContract
import androidx.core.content.ContextCompat
import com.raiden.domain.models.Contact


internal class DeviceContactsImpl(private val context: Context) : DeviceContacts {
    private val contacts = arrayListOf<Contact>()

    override suspend fun getContacts(): Iterable<Contact> {
        contacts.clear()
        val checkerPermission = ContextCompat.checkSelfPermission(context, Manifest.permission.READ_CONTACTS)
        if (checkerPermission == PackageManager.PERMISSION_GRANTED) {
            loadContacts()
        }
        return contacts
    }

    private fun loadContacts() {
        val cursor = context.contentResolver.query(
            ContactsContract.Contacts.CONTENT_URI,
            null,
            null,
            null,
            null
        )!!
        if (cursor.count > 0) {
            while (cursor.moveToNext()) {
                loadContactDataAndAddToList(cursor)
            }
        }
        cursor.close()
    }

    private fun loadContactDataAndAddToList(cursor: Cursor){
        val id = cursor.getString(cursor.getColumnIndex(ContactsContract.Contacts._ID))
        val name = cursor.getString(cursor.getColumnIndex(ContactsContract.Contacts.DISPLAY_NAME))
        val hasPhoneNumber = Integer.parseInt(cursor.getString(cursor.getColumnIndex(ContactsContract.Contacts.HAS_PHONE_NUMBER)))
        if (hasPhoneNumber > 0) {
            loadPhoneNumberAndAddContactToList(id, name);
        }
    }

    private fun loadPhoneNumberAndAddContactToList(id: String, name: String) {
        var phone = ""
        val pCur = context.contentResolver.query(
            ContactsContract.CommonDataKinds.Phone.CONTENT_URI,
            null,
            ContactsContract.CommonDataKinds.Phone.CONTACT_ID + " = ?",
            arrayOf(id),
            null
        )!!
        while (pCur.moveToNext()) {
            phone = getPhoneNumber(pCur);
        }
        pCur.close()
        val contact = Contact(name, phone)
        contacts.add(contact)
    }

    private fun getPhoneNumber(cursor: Cursor): String {
        return cursor.getString(cursor.getColumnIndex(ContactsContract.CommonDataKinds.Phone.NUMBER))

    }
}
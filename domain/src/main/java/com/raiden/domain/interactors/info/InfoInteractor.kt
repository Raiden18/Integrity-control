package com.raiden.domain.interactors.info

interface InfoInteractor {
    fun getCountOfUpdatedApps(): Int
    fun getCountOfDeletedApps(): Int
    fun getCountOfInstalledApps(): Int
    fun getCountOfChangedFiles(): Int
    fun getCountOfAddedFiles(): Int
    fun getCountOfDeletedFiles(): Int
    fun isChangedContacts(): Boolean
}
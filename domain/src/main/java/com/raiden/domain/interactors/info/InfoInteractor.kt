package com.raiden.domain.interactors.info

import com.raiden.domain.models.UpdateTime

interface InfoInteractor {
    suspend fun getCountOfUpdatedApps(): Int
    suspend fun getCountOfDeletedApps(): Int
    suspend fun getCountOfInstalledApps(): Int
    suspend fun getCountOfChangedFiles(): Int
    suspend fun getCountOfAddedFiles(): Int
    suspend fun getCountOfDeletedFiles(): Int
    suspend fun isChangedContacts(): Boolean
    suspend fun updateInfo()
    suspend fun getSavedTime(): String
}
package com.raiden.data.repositories.updtime

import com.nhaarman.mockitokotlin2.verify
import com.raiden.data.datasources.sharedpreferences.updatedtime.UpdatedSharedPreferences
import com.raiden.domain.gateways.UpdatedTimeGateway
import com.raiden.domain.models.UpdateTime
import kotlinx.coroutines.runBlocking
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Test
import org.mockito.Mockito.*

class UpdatedTimeRepositoryTest {
    private lateinit var updatedRepo: UpdatedTimeGateway
    private lateinit var updateSharedPreferences: UpdatedSharedPreferences

    @Before
    fun setUp() {
        updateSharedPreferences = mock(UpdatedSharedPreferences::class.java)
        updatedRepo = UpdatedTimeRepository(updateSharedPreferences)
    }

    @Test
    fun `Should save updated time to shared preferences`() = runBlocking {
        val updTime = UpdateTime("123")
        updatedRepo.saveUpdatedTime(updTime)
        verify(updateSharedPreferences).save(updTime)
        verifyNoMoreInteractions(updateSharedPreferences)
    }

    @Test
    fun `Should get saved updated time`() = runBlocking {
        `when`(updateSharedPreferences.get()).thenReturn(UpdateTime("123"))
        val savedTime = updatedRepo.getUpdatedTime()
        assertEquals(updateSharedPreferences.get(), savedTime)
    }
}
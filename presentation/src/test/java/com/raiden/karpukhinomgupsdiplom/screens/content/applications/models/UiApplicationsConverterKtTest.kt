package com.raiden.karpukhinomgupsdiplom.screens.content.applications.models

import com.raiden.domain.models.Application
import com.raiden.karpukhinomgupsdiplom.screens.content.applications.convertToUi
import com.raiden.karpukhinomgupsdiplom.uimodels.UiApplication
import org.junit.Assert.assertEquals
import org.junit.Test

class UiApplicationsConverterKtTest {

    @Test
    fun `Should convert domain application to UiApplication`() {
        val app = Application("123", "das", "packageName")
        val uiApplication = UiApplication("123", "das", "packageName")
        val convertedApp = app.convertToUi()

        assertEquals(uiApplication, convertedApp)
    }

    @Test
    fun `Should convert domain applications to UiApplications`() {
        val apps = listOf(
            Application("123", "das", "packageName"),
            Application("asd", "asdcxz", "packageName"),
            Application("132rtgrh", "asdsfdgh", "packageName")
        )
        val uiApplications = listOf(
            UiApplication("123", "das", "packageName"),
            UiApplication("asd", "asdcxz", "packageName"),
            UiApplication("132rtgrh", "asdsfdgh", "packageName")
        )
        val convertedApps = apps.convertToUi()

        assertEquals(uiApplications, convertedApps)
    }
}
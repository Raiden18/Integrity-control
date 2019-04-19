package com.raiden.karpukhinomgupsdiplom.content.models

import com.raiden.domain.models.Application
import com.raiden.karpukhinomgupsdiplom.content.applications.model.UiApplication
import com.raiden.karpukhinomgupsdiplom.content.applications.model.convertToUi
import org.junit.Assert.assertEquals
import org.junit.Test

class UiApplicationsConverterKtTest {

    @Test
    fun `Should convert domain application to UiApplication`() {
        val app = Application("123", "das")
        val uiApplication = UiApplication("123", "das")
        val convertedApp = app.convertToUi()

        assertEquals(uiApplication, convertedApp)
    }

    @Test
    fun `Should convert domain applications to UiApplications`() {
        val apps = listOf(
            Application("123", "das"),
            Application("asd", "asdcxz"),
            Application("132rtgrh", "asdsfdgh")
        )
        val uiApplications = listOf(
            UiApplication("123", "das"),
            UiApplication("asd", "asdcxz"),
            UiApplication("132rtgrh", "asdsfdgh")
        )
        val convertedApps = apps.convertToUi()

        assertEquals(uiApplications, convertedApps)
    }
}
package com.raiden.data.repositories.converters

import com.raiden.data.datasources.database.entities.ApplicationEntity
import com.raiden.data.repositories.applications.convertToDomainApplication
import com.raiden.data.repositories.applications.convertToDomainApps
import com.raiden.data.repositories.applications.convertToEntites
import com.raiden.data.repositories.applications.convertToEntityApplication
import com.raiden.domain.models.Application
import junitparams.JUnitParamsRunner
import junitparams.Parameters
import org.junit.Assert.assertEquals
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(JUnitParamsRunner::class)
internal class EntityConverterKtTest {

    fun getAppsArguments() = arrayOf(
        arrayOf(
            ApplicationEntity("123", "123", "asdasd"),
            Application("123", "123", "asdasd")
        ),
        arrayOf(
            ApplicationEntity("instagram", "228", "asdasd"),
            Application("instagram", "228", "asdasd")
        )
    )

    @Test
    @Parameters(method = "getAppsArguments")
    fun `Should convert ApplicationEntity to Application`(entityApp: ApplicationEntity, domainApp: Application) {
        val convertApp = entityApp.convertToDomainApplication()
        assertEquals(convertApp, domainApp)
    }

    @Test
    @Parameters(method = "getAppsArguments")
    fun `Should convert Domain application to ApplicationEntity`(entityApp: ApplicationEntity, domainApp: Application) {
        val convertApp = domainApp.convertToEntityApplication()
        assertEquals(convertApp, entityApp)
    }

    @Test
    fun `Should convert list of entity apps to list of domian apps`() {
        val entityApps = listOf(
            ApplicationEntity("instagram", "228", "asdasd"),
            ApplicationEntity("vk", "228", "asdasd"),
            ApplicationEntity("ok", "228", "asdasd"),
            ApplicationEntity("google", "228", "asdasd")
        )

        val domainApps = listOf(
            Application("instagram", "228", "asdasd"),
            Application("vk", "228", "asdasd"),
            Application("ok", "228", "asdasd"),
            Application("google", "228", "asdasd")
        )

        val convertedAPps = entityApps.convertToDomainApps()

        assertEquals(domainApps, convertedAPps)
    }

    @Test
    fun `Should convert list of apps from domain layer to app entity`() {
        val entityApps = listOf(
            ApplicationEntity("instagram", "228", "asdasd"),
            ApplicationEntity("vk", "228", "asdasd"),
            ApplicationEntity("ok", "228", "asdasd"),
            ApplicationEntity("google", "228", "asdasd")
        )

        val domainApps = listOf(
            Application("instagram", "228", "asdasd"),
            Application("vk", "228", "asdasd"),
            Application("ok", "228", "asdasd"),
            Application("google", "228", "asdasd")
        )

        val convertedAPps = domainApps.convertToEntites()
        assertEquals(entityApps, convertedAPps)

    }
}
package com.raiden.data.repositories.converters

import com.nhaarman.mockitokotlin2.verify
import com.raiden.data.datasources.database.entities.ApplicationEntity
import com.raiden.domain.models.Application
import junitparams.JUnitParamsRunner
import junitparams.Parameters
import org.junit.Assert.*
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(JUnitParamsRunner::class)
internal class EntityConverterKtTest{

    fun getAppsArguments() = arrayOf(
        arrayOf(ApplicationEntity("123", "123"), Application("123", "123")),
        arrayOf(ApplicationEntity("instagram", "228"), Application("instagram", "228"))
    )

    @Test
    @Parameters(method = "getAppsArguments")
    fun `Should convert ApplicationEntity to Application`(entityApp:ApplicationEntity,  domainApp: Application){
        val convertApp = entityApp.convertToDomainApplication()
        assertEquals(convertApp, domainApp)
    }

    @Test
    @Parameters(method = "getAppsArguments")
    fun `Should convert Domain application to ApplicationEntity`(entityApp:ApplicationEntity,  domainApp: Application){
        val convertApp = domainApp.convertToEntityApplication()
        assertEquals(convertApp, entityApp)
    }

    @Test
    fun `Should convert list of entity apps to list of domian apps`(){
        val entityApps = listOf(ApplicationEntity("instagram", "228"),
            ApplicationEntity("vk", "228"),
            ApplicationEntity("ok", "228"),
            ApplicationEntity("google", "228"))

        val domainApps = listOf(Application("instagram", "228"),
            Application("vk", "228"),
            Application("ok", "228"),
            Application("google", "228"))

        val convertedAPps = entityApps.convertToDomainApps()

        assertEquals(domainApps, convertedAPps)
    }
}
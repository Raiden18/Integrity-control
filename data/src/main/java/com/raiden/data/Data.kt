package com.raiden.data

import androidx.room.Room
import com.raiden.data.DataBase.DB_NAME
import com.raiden.data.repositories.ApplicationsRepository
import com.raiden.data.datasources.database.AppDatabase
import com.raiden.data.datasources.device.DeviceApplications
import com.raiden.data.datasources.device.DeviceApplicationsImpl
import com.raiden.domain.gateways.ApplicationsGateway
import org.koin.android.ext.koin.androidApplication
import org.koin.dsl.module.module
import org.koin.experimental.builder.singleBy

object DataBase {
    const val DB_NAME = "diplom.db";
    const val APPS_TABLE_NAME = "apps"
}

val data = module {
    single { Room.databaseBuilder(androidApplication(), AppDatabase::class.java, DB_NAME).build() }
    single { get<AppDatabase>().getApplicationsDao() }
    single { androidApplication().packageManager }

    singleBy<ApplicationsGateway, ApplicationsRepository>()
    singleBy<DeviceApplications, DeviceApplicationsImpl>()
}
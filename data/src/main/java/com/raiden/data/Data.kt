package com.raiden.data

import androidx.room.Room
import com.raiden.data.DataBase.DB_NAME
import com.raiden.data.repositories.ApplicationsRepository
import com.raiden.data.sources.database.AppDatabase
import com.raiden.data.sources.device.DeviceApplications
import com.raiden.data.sources.device.DeviceApplicationsImpl
import com.raiden.domain.gateways.ApplicationsGateway
import org.koin.android.ext.koin.androidApplication
import org.koin.dsl.module.module
import org.koin.experimental.builder.singleBy

object DataBase {
    const val DB_NAME = "diplom.db";
    const val APPS_TABLE_NAME = "apps"
}

val data = module {
    singleBy<DeviceApplications, DeviceApplicationsImpl>()
    single { Room.databaseBuilder(androidApplication(), AppDatabase::class.java, DB_NAME).build() }
    single { get<AppDatabase>().getApplicationsDao() }
    singleBy<ApplicationsGateway, ApplicationsRepository>()
}
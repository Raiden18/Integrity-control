package com.raiden.data

import android.os.Environment
import androidx.room.Room
import com.raiden.data.DataBase.DB_NAME
import com.raiden.data.datasources.database.AppDatabase
import com.raiden.data.datasources.device.applications.DeviceApplications
import com.raiden.data.datasources.device.applications.DeviceApplicationsImpl
import com.raiden.data.datasources.device.files.DeviceFiles
import com.raiden.data.datasources.device.files.DeviceFilesImpl
import com.raiden.data.repositories.applications.ApplicationsRepository
import com.raiden.data.repositories.files.FilesRepository
import com.raiden.domain.gateways.ApplicationsGateway
import com.raiden.domain.gateways.FilesGateway
import org.koin.android.ext.koin.androidApplication
import org.koin.dsl.module.module
import org.koin.experimental.builder.singleBy
import java.io.File

object DataBase {
    const val DB_NAME = "diplom.db";
    const val APPS_TABLE_NAME = "apps"
    const val FILES_TABLE_NAME = "files"
}

val data = module {
    single { androidApplication().packageManager }
    single { File(Environment.getExternalStorageDirectory().absolutePath) }

    single { Room.databaseBuilder(androidApplication(), AppDatabase::class.java, DB_NAME).build() }
    single { get<AppDatabase>().getApplicationsDao() }
    single { get<AppDatabase>().getFilessDao() }

    singleBy<ApplicationsGateway, ApplicationsRepository>()
    singleBy<FilesGateway, FilesRepository>()

    singleBy<DeviceApplications, DeviceApplicationsImpl>()
    singleBy<DeviceFiles, DeviceFilesImpl>()
}
package com.gdevs.apptracker.feature_app.data.data_source

import androidx.room.Database
import androidx.room.RoomDatabase
import com.gdevs.apptracker.feature_app.domain.model.AppModel


@Database(
    entities = [AppModel::class],
    version = 1
)
abstract class AppDataBase : RoomDatabase() {

    abstract val appDao: AppDao

    companion object {
        const val DATABASE_NAME = "app_db"
    }
}
package com.gdevs.apptracker.di

import android.app.Application
import androidx.room.Room
import com.gdevs.apptracker.feature_app.data.data_source.AppDataBase
import com.gdevs.apptracker.feature_app.data.repository.AppRepositoryImplementation
import com.gdevs.apptracker.feature_app.domain.repository.AppRepository
import com.gdevs.apptracker.feature_app.domain.use_case.AddApp
import com.gdevs.apptracker.feature_app.domain.use_case.AppUseCase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Provides
    @Singleton
    fun provideAppDataBase(app: Application): AppDataBase {
        return Room.databaseBuilder(
            app,
            AppDataBase::class.java,
            AppDataBase.DATABASE_NAME
        ).build()
    }

    @Provides
    @Singleton
    fun provideAppRepository(db: AppDataBase): AppRepository {
        return AppRepositoryImplementation(db.appDao)
    }

    @Provides
    @Singleton
    fun provideAppUseCases(repository: AppRepository): AppUseCase {
        return AppUseCase(
            addApp = AddApp(repository)
        )
    }
}
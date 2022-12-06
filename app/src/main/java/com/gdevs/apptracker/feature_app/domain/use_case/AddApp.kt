package com.gdevs.apptracker.feature_app.domain.use_case

import com.gdevs.apptracker.feature_app.domain.model.AppModel
import com.gdevs.apptracker.feature_app.domain.model.InvalidAppException
import com.gdevs.apptracker.feature_app.domain.repository.AppRepository

class AddApp(
    private val appRepository: AppRepository
) {

    @Throws(InvalidAppException::class)
    suspend operator fun invoke(app: AppModel) {
        if (app.appName.isBlank()) {
            throw InvalidAppException("The name of the app can not be empty.")
        }

        if (app.firstTimeStamp.isBlank()) {
            throw InvalidAppException("The first timestamp of the app can not be empty.")
        }

        if (app.lastTimeStamp.isBlank()) {
            throw InvalidAppException("The last timestamp of the app can not be empty.")
        }

        if (app.timeOnForeground.isBlank()) {
            throw InvalidAppException("The time on foreground of the app can not be empty.")
        }

        appRepository.insertApp(app)
    }

}
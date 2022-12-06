package com.gdevs.apptracker.feature_app.domain.repository

import com.gdevs.apptracker.feature_app.domain.model.AppModel

interface AppRepository {

    suspend fun insertApp(app: AppModel)

}
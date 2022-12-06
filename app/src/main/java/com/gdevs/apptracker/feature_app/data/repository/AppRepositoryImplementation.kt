package com.gdevs.apptracker.feature_app.data.repository

import com.gdevs.apptracker.feature_app.data.data_source.AppDao
import com.gdevs.apptracker.feature_app.domain.model.AppModel
import com.gdevs.apptracker.feature_app.domain.repository.AppRepository

class AppRepositoryImplementation(
    private val dao: AppDao
) : AppRepository {

    override suspend fun insertApp(app: AppModel) {
        return dao.insertApp(app)
    }

}
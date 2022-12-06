package com.gdevs.apptracker.presentation.app

import com.gdevs.apptracker.feature_app.domain.model.AppModel

data class AppState(

    val apps: List<AppModel> = emptyList()
)
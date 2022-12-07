package com.gdevs.apptracker.presentation.app

import com.gdevs.apptracker.feature_app.domain.model.AppModel

data class AppState(
    val isLoading: Boolean = false,
    val apps: List<AppModel> = emptyList(),
    val error: String = ""
)
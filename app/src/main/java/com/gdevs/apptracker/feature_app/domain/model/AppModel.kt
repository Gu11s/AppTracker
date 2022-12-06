package com.gdevs.apptracker.feature_app.domain.model

import androidx.room.PrimaryKey

data class AppModel(
    val appName: String,
    val firstTimeStamp: String,
    val lastTimeStamp: String,
    val timeOnForeGround: String,
    @PrimaryKey
    val appId: Int? = null
)

class InvalidAppException(message: String) : Exception(message)

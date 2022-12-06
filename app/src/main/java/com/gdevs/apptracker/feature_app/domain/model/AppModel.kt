package com.gdevs.apptracker.feature_app.domain.model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class AppModel(
    val appName: String,
    val firstTimeStamp: String,
    val lastTimeStamp: String,
    val timeOnForeground: String,
    @PrimaryKey
    val appId: Int? = null
)

class InvalidAppException(message: String) : Exception(message)

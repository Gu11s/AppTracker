package com.gdevs.apptracker.presentation.app

import android.app.Application
import android.app.usage.UsageStatsManager
import android.content.Context
import android.content.Context.USAGE_STATS_SERVICE
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import com.gdevs.apptracker.feature_app.domain.use_case.AppUseCase
import com.gdevs.apptracker.presentation.util.GetAppsStats
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Job
import java.text.SimpleDateFormat
import java.util.*
import javax.inject.Inject

@HiltViewModel
class AppViewModel @Inject constructor(
    private val appUseCase: AppUseCase
) : ViewModel() {

    private val _state = mutableStateOf(AppState())
    val state: State<AppState> = _state
    private var getAppsJob: Job? = null

    init {

            getApps()

    }


    private fun getApps(){

        println("TIME FROM VIEW MODEL")
        GetAppsStats().getApps()
    }
}
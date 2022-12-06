package com.gdevs.apptracker.presentation.app

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import com.gdevs.apptracker.feature_app.domain.use_case.AppUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Job
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

    private fun getApps() {

    }
}
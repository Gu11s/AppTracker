package com.gdevs.apptracker.presentation

import android.app.AppOpsManager
import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.os.Process
import android.provider.Settings
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.core.app.AppOpsManagerCompat
import androidx.core.content.ContextCompat.startActivity
import com.gdevs.apptracker.presentation.app.AppListScreen
import com.gdevs.apptracker.ui.theme.AppTrackerTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            AppTrackerTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colors.background
                ) {
                    //Greeting("Android")
                    AppListScreen()
                    requestPermissions(this)
                }
            }
        }
    }
}

@Composable
fun Greeting(name: String) {
    Text(text = "Hello $name!")
}

@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    AppTrackerTheme {
        Greeting("Android")
    }
}

private fun requestPermissions(context: Context): Boolean {
    val appOpsManager: AppOpsManager =
        context.getSystemService(Context.APP_OPS_SERVICE) as AppOpsManager
    //val mode = appOpsManager.unsafeCheckOpNoThrow(OPSTR_GET_USAGE_STATS, Process.myUid(), context.packageName)
    val mode = appOpsManager.checkOpNoThrow(
        AppOpsManager.OPSTR_GET_USAGE_STATS,
        Process.myUid(),
        context.packageName
    )

    if (mode != AppOpsManagerCompat.MODE_ALLOWED) {
        //TODO CREATE DIALOG TO OPEN SETTINGS AND GIVE PERMISSION
        context.startActivity(Intent(Settings.ACTION_USAGE_ACCESS_SETTINGS))
        println("TIME PLEASE GIVE PERMISSION")
    }

    println("TIME GIVEN PERMISSION")
    return mode == AppOpsManagerCompat.MODE_ALLOWED
}
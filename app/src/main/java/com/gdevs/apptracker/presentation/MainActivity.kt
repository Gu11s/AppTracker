package com.gdevs.apptracker.presentation

import android.app.AppOpsManager
import android.app.Application
import android.app.usage.UsageStatsManager
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
import androidx.core.content.getSystemService
import androidx.lifecycle.viewmodel.compose.viewModel
import com.gdevs.apptracker.presentation.app.AppListScreen
import com.gdevs.apptracker.ui.theme.AppTrackerTheme
import dagger.hilt.android.AndroidEntryPoint
import java.lang.StringBuilder
import java.text.SimpleDateFormat
import java.util.*

@AndroidEntryPoint
class MainActivity : ComponentActivity() {

    var firstTimeOpenAppTracker: Long = 0L
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        println("MAIN ACTIVITY CONTEXT ${Context.USAGE_STATS_SERVICE}")
        //println("MAIN ACTIVITY CONTEXT ${getSystemService()}")
        setContent {
            AppTrackerTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colors.background
                ) {
                    //Greeting("Android")

                    val cal = Calendar.getInstance()
                    firstTimeOpenAppTracker = cal.timeInMillis
                    AppListScreen()
                    requestPermissions(this)

                    println("TIME Get apps ${getApps(firstTimeOpenAppTracker)}")

                }
            }
        }
    }

    override fun onResume() {
        super.onResume()
        getApps(firstTimeOpenAppTracker)
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

    private fun getApps(currentTime: Long) {

        println("STATS CURRENT $currentTime")

        val usageStatsManager: UsageStatsManager =
            getSystemService(USAGE_STATS_SERVICE) as UsageStatsManager

        val endTime = System.currentTimeMillis()
        val startTime = endTime - 1000
        val statUsageList =
            usageStatsManager.queryUsageStats(UsageStatsManager.INTERVAL_BEST, startTime, endTime)

        if (statUsageList.isNotEmpty()) {
            for (i in statUsageList) {

                val firstTimeOpen = i.firstTimeStamp
                val lastTimeUsed = i.lastTimeUsed
                println("STATS FIRST LAST $lastTimeUsed")
                val seconds = (i.totalTimeInForeground / 1000) % 60
                val minutes = (i.totalTimeInForeground / (1000 * 60)) % 60
                val hours = (i.totalTimeInForeground / (1000 * 60 * 60))

                var appName: String
                val stringBuilderApps = StringBuilder()

                if (!(hours == 0L && minutes == 0L && seconds == 0L) && (lastTimeUsed > currentTime)) {


                    appName = i.packageName

                    if (appName.contains("com.google.android.")) {
                        appName = appName.substring(19)
                    }

                    if (appName.contains("com.google.")) {
                        appName = appName.substring(11)
                    }

                    if (appName.contains("com.app.")) {
                        appName = appName.substring(8)
                    }

                    if (appName.contains("com.")) {
                        appName = appName.substring(4)
                    }

                    if (appName.contains("apps.")) {
                        appName = appName.substring(5)
                    }

                    stringBuilderApps.append(appName)
                        .append("")
                        .append(hours)
                        .append("")
                        .append(minutes)
                        .append("")
                        .append(seconds)

                    println("TIME STATS COMPLETE STRING $appName")

                    println("STATS LAST $lastTimeUsed")
                    println("STATS LIST ${i.packageName}")
                    println("STATS HOURS $hours Minutes $minutes SECONDS $seconds")
                    if (i.packageName == "com.google.android.deskclock") {
                        println(i.firstTimeStamp)
                        println(i.lastTimeStamp)
                        println(i.lastTimeUsed)
                        println(i.totalTimeInForeground)

                        val secondsFore = (i.totalTimeInForeground / 1000) % 60
                        val minutesFore = (i.totalTimeInForeground / (1000 * 60)) % 60
                        val hoursFore = (i.totalTimeInForeground / (1000 * 60 * 60))
                        println("STATS FORE horas $hoursFore minutos $minutesFore segundos $secondsFore")

                        val firstTime =
                            SimpleDateFormat.getDateTimeInstance().format(i.firstTimeStamp)
                        println("first TIME INSTANCE $firstTime")

                        val lastTime =
                            SimpleDateFormat.getDateTimeInstance().format(i.lastTimeStamp)
                        println("STATS last TIME INSTANCE $lastTime")

                        val lastTimeUsed =
                            SimpleDateFormat.getDateTimeInstance().format(i.lastTimeUsed)
                        println("last TIME INSTANCE $lastTimeUsed")

                        val totalTime = i.lastTimeStamp - i.lastTimeVisible
                        val totalTimeUsed = SimpleDateFormat.getTimeInstance().format(totalTime)
                        println("TOTAL TIME INSTANCE $totalTimeUsed")

                        val seconds = (totalTime / 1000) % 60
                        val minutes = (totalTime / (1000 * 60)) % 60
                        val hours = (totalTime / (1000 * 60 * 60))

                        println("STATS DIF horas $hours minutos $minutes segundos $seconds")

                        val timeInForeGround =
                            SimpleDateFormat.getTimeInstance().format(i.totalTimeInForeground)
                        println("TIME INSTANCE FOREGROUND $timeInForeGround")

                        val lastTimeVisible =
                            SimpleDateFormat.getDateTimeInstance().format(i.lastTimeVisible)
                        println("TIME INSTANCE VISIBLE $lastTimeVisible")
                    }
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
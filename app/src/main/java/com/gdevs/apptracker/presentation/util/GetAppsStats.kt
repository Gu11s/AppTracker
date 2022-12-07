package com.gdevs.apptracker.presentation.util

import android.app.Application
import android.app.usage.UsageStatsManager
import java.lang.StringBuilder
import java.text.SimpleDateFormat
import java.util.*

class GetAppsStats : Application() {

    fun getApps() {

        var firstTimeOpenAppTracker: Long = 0L

        val cal = Calendar.getInstance()

        firstTimeOpenAppTracker = cal.timeInMillis
        println("TIME VIEW MODEL")

        println("STATS CURRENT $firstTimeOpenAppTracker")

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

                if (!(hours == 0L && minutes == 0L && seconds == 0L) && (lastTimeUsed > firstTimeOpenAppTracker)) {
//                if (!(hours == 0L && minutes == 0L && seconds == 0L)) {

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
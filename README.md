# AppTracker
## _Using Usage Stats Manager for tracking apps while using yours_

As mentioned in the official documentation: "Usage stats manager provides access to device usage history and statistics".

- Provides information in years, months, weeks, days and seconds.
- Retrieves app's package name.
- Provides app's total time in foreground.

## Implementation

The permission used in th manifest to help us use the usage stats manager is: "android.permission.PACKAGE_USAGE_STATS"
But this permission is a system level permission so you gonna see the red underline in that permission. It indicates that you have to take care of what you are doing with that permission.
For not get that warning use ' tools:ignore="ProtectedPermissions" ' to indicate that you know what are you doing and, as the tool said, "ignore" that warning.

## Tech

This app uses [JetPackCompose](https://developer.android.com/jetpack/compose)

- Minimum SDK level 31
- [Kotlin](https://kotlinlang.org/) based, [Coroutines](https://github.com/Kotlin/kotlinx.coroutines) + [Flow](https://kotlin.github.io/kotlinx.coroutines/kotlinx-coroutines-core/kotlinx.coroutines.flow/) for asynchronous.
- [JetPackCompose](https://developer.android.com/jetpack/compose)
  - Lifecycle: Observe Android lifecycles and handle UI states upon the lifecycle changes.
  - ViewModel: Manages UI-related data holder and lifecycle aware. Allows data to survive configuration changes such as screen rotations.
  - Room: Constructs Database by providing an abstraction layer over SQLite to allow fluent database access.
  - [Hilt](https://dagger.dev/hilt/): for dependency injection.
- Architecture
  - MVVM Architecture (View - ViewModel - Model)
  - Repository Pattern

## Development
For using the UsageStatsManger inside your app you have to grant permission of AppOpsManagerCompat. This is because is a system level action to perform.
```sh
val appOpsManager: AppOpsManager = 
    context.getSystemService(Context.APP_OPS_SERVICE) as AppOpsManager
```
Grant permission in a manual way, because of the system level permission, and then return to the app and use the UsageStatsManger Api.

> Note: `MODE_ALLOWED` is required for implement UsageStatManager.

UsageStatsManager have many options to retrieve the necessary interval time. A personal recommendation use `INTERVAL_BEST` this is because the api will retrive the BEST interval between start and end times. Use it like this:

```sh
val statUsageList =
            usageStatsManager
            .queryUsageStats(UsageStatsManager.INTERVAL_BEST, startTime, endTime)
```

The code above allows you to have a list of apps used between that interval of time.

## License

**FREE USE**

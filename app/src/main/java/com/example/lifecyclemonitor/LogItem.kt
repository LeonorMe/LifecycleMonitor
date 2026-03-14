package com.example.lifecyclemonitor

import androidx.annotation.ColorRes

enum class LifecycleState(val displayName: String, @ColorRes val colorRes: Int) {
    ON_CREATE("onCreate", R.color.state_create),
    ON_START("onStart", R.color.state_start),
    ON_RESUME("onResume", R.color.state_resume),
    ON_PAUSE("onPause", R.color.state_pause),
    ON_STOP("onStop", R.color.state_stop),
    ON_DESTROY("onDestroy", R.color.state_destroy),
    ON_RESTART("onRestart", R.color.state_restart)
}

sealed class LogItem {
    data class Event(
        val activityName: String,
        val state: LifecycleState,
        val timestamp: Long = System.currentTimeMillis(),
        @ColorRes val activityColorRes: Int
    ) : LogItem()

    data class Separator(
        val timestamp: Long = System.currentTimeMillis()
    ) : LogItem()
}

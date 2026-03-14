package com.example.lifecyclemonitor

import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow

object LifecycleLogger {
    private val _logs = MutableStateFlow<List<LogItem>>(emptyList())
    val logs: StateFlow<List<LogItem>> = _logs.asStateFlow()

    private val _autoScroll = MutableStateFlow(true)
    val autoScroll: StateFlow<Boolean> = _autoScroll.asStateFlow()

    fun logEvent(activityName: String, state: LifecycleState, activityColorRes: Int) {
        val newEvent = LogItem.Event(activityName, state, activityColorRes = activityColorRes)
        _logs.value = _logs.value + newEvent
    }

    fun addSeparator() {
        _logs.value = _logs.value + LogItem.Separator()
    }

    fun clearLogs() {
        _logs.value = emptyList()
    }

    fun toggleAutoScroll() {
        _autoScroll.value = !_autoScroll.value
    }
}

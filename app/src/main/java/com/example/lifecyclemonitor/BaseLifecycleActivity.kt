package com.example.lifecyclemonitor

import android.os.Bundle
import androidx.annotation.ColorRes
import androidx.appcompat.app.AppCompatActivity

abstract class BaseLifecycleActivity : AppCompatActivity() {

    abstract val activityDisplayName: String
    
    @get:ColorRes
    abstract val activityColorRes: Int

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        log(LifecycleState.ON_CREATE)
    }

    override fun onStart() {
        super.onStart()
        log(LifecycleState.ON_START)
    }

    override fun onResume() {
        super.onResume()
        log(LifecycleState.ON_RESUME)
    }

    override fun onPause() {
        super.onPause()
        log(LifecycleState.ON_PAUSE)
    }

    override fun onStop() {
        super.onStop()
        log(LifecycleState.ON_STOP)
    }

    override fun onDestroy() {
        super.onDestroy()
        log(LifecycleState.ON_DESTROY)
    }

    override fun onRestart() {
        super.onRestart()
        log(LifecycleState.ON_RESTART)
    }

    private fun log(state: LifecycleState) {
        LifecycleLogger.logEvent(activityDisplayName, state, activityColorRes)
    }
}

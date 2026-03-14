package com.example.lifecyclemonitor

import android.os.Bundle
import android.view.View

class ThirdActivity : MainActivity() {

    override val activityDisplayName: String = "ThirdActivity"
    override val activityColorRes: Int = R.color.activity_c_bg

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        
        binding.btnNavigate.visibility = View.GONE
    }
}

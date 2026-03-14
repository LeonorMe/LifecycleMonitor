package com.example.lifecyclemonitor

import android.content.Intent
import android.os.Bundle

class SecondActivity : MainActivity() {

    override val activityDisplayName: String = "SecondActivity"
    override val activityColorRes: Int = R.color.activity_b_bg

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        
        binding.btnNavigate.text = "Go to Third"
        binding.btnNavigate.setOnClickListener {
            startActivity(Intent(this, ThirdActivity::class.java))
        }
    }
}

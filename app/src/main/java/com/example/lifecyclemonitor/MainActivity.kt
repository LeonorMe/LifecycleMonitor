package com.example.lifecyclemonitor

import android.content.Intent
import android.os.Bundle
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.lifecyclemonitor.databinding.ActivityMainBinding
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch

open class MainActivity : BaseLifecycleActivity() {

    override val activityDisplayName: String = "MainActivity"
    override val activityColorRes: Int = R.color.activity_a_bg

    protected lateinit var binding: ActivityMainBinding
    private val logAdapter = LogAdapter()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setupUI()
        observeLogs()
    }

    private fun setupUI() {
        binding.tvActivityTitle.text = activityDisplayName
        
        binding.rvLogs.apply {
            layoutManager = LinearLayoutManager(this@MainActivity)
            adapter = logAdapter
        }

        binding.btnNavigate.text = "Go to Second"
        binding.btnNavigate.setOnClickListener {
            startActivity(Intent(this, SecondActivity::class.java))
        }

        binding.btnClear.setOnClickListener {
            LifecycleLogger.clearLogs()
        }

        binding.btnSeparator.setOnClickListener {
            LifecycleLogger.addSeparator()
        }

        binding.chipAutoScroll.setOnCheckedChangeListener { _, isChecked ->
            if (LifecycleLogger.autoScroll.value != isChecked) {
                LifecycleLogger.toggleAutoScroll()
            }
        }
    }

    private fun observeLogs() {
        lifecycleScope.launch {
            LifecycleLogger.logs.collectLatest { logs ->
                logAdapter.submitList(logs) {
                    if (LifecycleLogger.autoScroll.value && logs.isNotEmpty()) {
                        binding.rvLogs.smoothScrollToPosition(logs.size - 1)
                    }
                }
            }
        }

        lifecycleScope.launch {
            LifecycleLogger.autoScroll.collectLatest { isAutoScroll ->
                binding.chipAutoScroll.isChecked = isAutoScroll
            }
        }
    }
}

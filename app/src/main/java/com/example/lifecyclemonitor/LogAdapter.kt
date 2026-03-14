package com.example.lifecyclemonitor

import android.content.res.ColorStateList
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.lifecyclemonitor.databinding.ItemLogBinding
import com.example.lifecyclemonitor.databinding.ItemSeparatorBinding
import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale

class LogAdapter : ListAdapter<LogItem, RecyclerView.ViewHolder>(LogDiffCallback()) {

    private val timeFormat = SimpleDateFormat("HH:mm:ss.SSS", Locale.getDefault())

    companion object {
        private const val TYPE_EVENT = 0
        private const val TYPE_SEPARATOR = 1
    }

    override fun getItemViewType(position: Int): Int {
        return when (getItem(position)) {
            is LogItem.Event -> TYPE_EVENT
            is LogItem.Separator -> TYPE_SEPARATOR
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        return when (viewType) {
            TYPE_EVENT -> {
                val binding = ItemLogBinding.inflate(inflater, parent, false)
                EventViewHolder(binding)
            }
            else -> {
                val binding = ItemSeparatorBinding.inflate(inflater, parent, false)
                SeparatorViewHolder(binding)
            }
        }
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        val item = getItem(position)
        when (holder) {
            is EventViewHolder -> holder.bind(item as LogItem.Event)
            is SeparatorViewHolder -> holder.bind(item as LogItem.Separator)
        }
    }

    inner class EventViewHolder(private val binding: ItemLogBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(event: LogItem.Event) {
            binding.tvActivityName.text = event.activityName
            binding.tvStateName.text = event.state.displayName
            binding.tvTimestamp.text = timeFormat.format(Date(event.timestamp))
            
            val context = binding.root.context
            val stateColor = ContextCompat.getColor(context, event.state.colorRes)
            val activityColor = ContextCompat.getColor(context, event.activityColorRes)

            binding.cardVRoot.setCardBackgroundColor(activityColor)
            binding.ivStateIcon.imageTintList = ColorStateList.valueOf(stateColor)
            binding.tvStateName.setTextColor(stateColor)
        }
    }

    inner class SeparatorViewHolder(private val binding: ItemSeparatorBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(separator: LogItem.Separator) {
            binding.tvSeparatorText.text = "Session Break • ${timeFormat.format(Date(separator.timestamp))}"
        }
    }

    class LogDiffCallback : DiffUtil.ItemCallback<LogItem>() {
        override fun areItemsTheSame(oldItem: LogItem, newItem: LogItem): Boolean {
            return if (oldItem is LogItem.Event && newItem is LogItem.Event) {
                oldItem.timestamp == newItem.timestamp && oldItem.state == newItem.state
            } else if (oldItem is LogItem.Separator && newItem is LogItem.Separator) {
                oldItem.timestamp == newItem.timestamp
            } else false
        }

        override fun areContentsTheSame(oldItem: LogItem, newItem: LogItem): Boolean {
            return oldItem == newItem
        }
    }
}

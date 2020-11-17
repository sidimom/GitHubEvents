package com.example.events.ui.adapter

import android.content.Context
import android.os.Build
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.annotation.RequiresApi
import androidx.recyclerview.widget.RecyclerView
import com.example.events.R
import com.example.events.api.model.EventItem
import com.example.events.databinding.ItemRvEventsBinding
import com.squareup.picasso.Picasso

class EventsAdapter: RecyclerView.Adapter<EventsAdapter.EventsViewHolder>() {

    private var listEvents: List<EventItem> = listOf()
    var listener: ((EventItem) -> Unit)? = null
    private lateinit var context: Context

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): EventsViewHolder {
        context = parent.context
        return EventsViewHolder(
            ItemRvEventsBinding.inflate(
                LayoutInflater.from(context),
                parent,
                false
            )
        )
    }

    @RequiresApi(Build.VERSION_CODES.O)
    override fun onBindViewHolder(holder: EventsViewHolder, position: Int) {
        val event = listEvents[position]
        holder.binding.tvName.text = event.actor.login
        holder.binding.tvEventType.text = event.type
        holder.binding.tvDate.text = event.createdAt
        Picasso.get()
            .load(event.actor.avatarUrl)
            .placeholder(R.color.color_grey)
            .error(R.color.color_grey)
            .into(holder.binding.ivAvatar)
    }

    override fun getItemCount(): Int = listEvents.size

    fun setData(listEvents: List<EventItem>) {
        this.listEvents = listEvents
        notifyDataSetChanged()
    }

    inner class EventsViewHolder(val binding: ItemRvEventsBinding): RecyclerView.ViewHolder(binding.root) {
        init {
            binding.root.setOnClickListener {
                listener?.invoke(listEvents[adapterPosition])
            }
        }
    }
}
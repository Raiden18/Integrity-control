package com.raiden.karpukhinomgupsdiplom.apps.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.raiden.karpukhinomgupsdiplom.apps.models.UiApplication
import com.raiden.karpukhinomgupsdiplom.databinding.RecyclerApplicationItemBinding

class ApplicationAdapter(private val onApplicationClick: OnApplicationClick) :
    RecyclerView.Adapter<ApplicationViewHolder>() {
    private var applications = listOf<UiApplication>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ApplicationViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val itemBinding = RecyclerApplicationItemBinding.inflate(
            inflater,
            parent,
            false
        )
        return ApplicationViewHolder(itemBinding, onApplicationClick)
    }

    override fun getItemCount() = applications.size

    override fun onBindViewHolder(holder: ApplicationViewHolder, position: Int) {
        val item = applications[position]
        holder.bindItem(item)
    }

    fun setApplications(applications: Iterable<UiApplication>) {
        this.applications = applications.toList()
        notifyDataSetChanged()
    }
}

package com.raiden.karpukhinomgupsdiplom.screens.content.common.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.raiden.karpukhinomgupsdiplom.R
import com.raiden.karpukhinomgupsdiplom.uimodels.UiContent

class ContentAdapter(private val onApplicationClick: OnContentClick) :
    RecyclerView.Adapter<ContentViewHolder>() {
    private var applications = listOf<UiContent>()


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ContentViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val itemView = inflater.inflate(R.layout.recycler_content_item, parent, false)
        return ContentViewHolder(itemView, onApplicationClick)
    }

    override fun getItemCount() = applications.size

    override fun onBindViewHolder(holder: ContentViewHolder, position: Int) {
        val item = applications[position]
        holder.bindItem(item)
    }

    fun setApplications(applications: List<UiContent>) {
        this.applications = applications
        notifyDataSetChanged()
    }
}

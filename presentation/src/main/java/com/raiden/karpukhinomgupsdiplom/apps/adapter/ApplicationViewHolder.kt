package com.raiden.karpukhinomgupsdiplom.apps.adapter

import androidx.recyclerview.widget.RecyclerView
import com.raiden.karpukhinomgupsdiplom.apps.models.UiApplication
import com.raiden.karpukhinomgupsdiplom.databinding.RecyclerApplicationItemBinding
import kotlinx.android.synthetic.main.recycler_application_item.view.*

class ApplicationViewHolder(
    private val itemBinding: RecyclerApplicationItemBinding,
    private val onApplicationClick: OnApplicationClick
) : RecyclerView.ViewHolder(itemBinding.root) {

    fun bindItem(application: UiApplication) {
        itemBinding.application = application
        itemView.recycler_application_item.setOnClickListener {
            onApplicationClick(application)
        }
        setColorForApplication(application)
    }

    private fun setColorForApplication(application: UiApplication) {
        application.apply {
            if (isDeleted) {
                setItemTextColor(android.R.color.holo_red_light)
            }
            if (isInstalled) {
                setItemTextColor(android.R.color.holo_blue_dark)
            }
            if (isUpdated) {
                setItemTextColor(android.R.color.holo_orange_dark)
            }
        }
    }

    private fun setItemTextColor(idColor: Int) {
        val color = itemView.context.resources.getColor(idColor)
        itemView.recycler_application_item.setTextColor(color)
    }
}
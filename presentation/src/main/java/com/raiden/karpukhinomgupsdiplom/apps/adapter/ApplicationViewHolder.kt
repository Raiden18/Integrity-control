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
        itemBinding.root.recycler_application_item.setOnClickListener {
            onApplicationClick(application)
        }
    }
}
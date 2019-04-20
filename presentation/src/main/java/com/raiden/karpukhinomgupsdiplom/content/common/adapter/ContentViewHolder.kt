package com.raiden.karpukhinomgupsdiplom.content.common.adapter

import android.view.View
import androidx.recyclerview.widget.RecyclerView
import com.raiden.karpukhinomgupsdiplom.R
import com.raiden.karpukhinomgupsdiplom.content.common.models.UiContent
import kotlinx.android.synthetic.main.recycler_application_item.view.*

class ContentViewHolder(
    viewItem: View,
    private val onContentClick: OnContentClick
) : RecyclerView.ViewHolder(viewItem) {

    fun bindItem(uiContent: UiContent) {
        itemView.recycler_application_item.text = uiContent.nameContent
        itemView.recycler_application_item.setOnClickListener {
            onContentClick(uiContent)
        }
        setColorForContent(uiContent)
    }

    private fun setColorForContent(uiContent: UiContent) {
        uiContent.apply {
            if (isDeleted) {
                setItemTextColor(R.color.deleted_content)
            }
            if (isInstalled) {
                setItemTextColor(R.color.installed_content)
            }
            if (isUpdated) {
                setItemTextColor(R.color.changed_content)
            }
        }
    }

    private fun setItemTextColor(idColor: Int) {
        val color = itemView.context.resources.getColor(idColor)
        itemView.recycler_application_item.setTextColor(color)
    }
}
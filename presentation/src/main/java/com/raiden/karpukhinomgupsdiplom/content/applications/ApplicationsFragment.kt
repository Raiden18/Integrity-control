package com.raiden.karpukhinomgupsdiplom.content.applications

import com.raiden.karpukhinomgupsdiplom.R
import com.raiden.karpukhinomgupsdiplom.content.common.ContentFragment
import com.raiden.karpukhinomgupsdiplom.content.common.models.UiContent
import org.koin.android.ext.android.inject

class ApplicationsFragment : ContentFragment() {
    override val viewModel: ApplicationsViewModel by inject()

    override val emptyContentMessageId = R.string.applications_screen_no_updates

    override fun onItemClick(uiContent: UiContent) {

    }
}
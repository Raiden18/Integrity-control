package com.raiden.karpukhinomgupsdiplom.screens.content.files

import com.raiden.karpukhinomgupsdiplom.R
import com.raiden.karpukhinomgupsdiplom.screens.content.common.ContentFragment
import com.raiden.karpukhinomgupsdiplom.uimodels.UiContent
import org.koin.android.ext.android.inject

class FilesFragment : ContentFragment() {
    override val viewModel: FilesViewModel by inject()

    override val emptyContentMessageId: Int = R.string.files_screen_no_updates

    override val contentInstalled = R.string.content_alert_message_file_installed
    override val contentChanged = R.string.content_alert_message_file_changed
    override val contentDeleted = R.string.content_alert_message_file_deleted

    override fun onItemClick(uiContent: UiContent) {

    }
}
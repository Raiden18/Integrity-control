package com.raiden.karpukhinomgupsdiplom.content.files

import com.raiden.karpukhinomgupsdiplom.R
import com.raiden.karpukhinomgupsdiplom.content.common.ContentFragment
import com.raiden.karpukhinomgupsdiplom.content.common.models.UiContent
import org.koin.android.ext.android.inject

class FilesFragment : ContentFragment() {
    override val viewModel: FilesViewModel by inject()

    override val emptyContentMessageId: Int = R.string.files_screen_no_updates

    override fun onItemClick(uiContent: UiContent) {

    }
}
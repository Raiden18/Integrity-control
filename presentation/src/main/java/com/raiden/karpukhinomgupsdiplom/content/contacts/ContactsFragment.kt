package com.raiden.karpukhinomgupsdiplom.content.contacts

import com.raiden.karpukhinomgupsdiplom.R
import com.raiden.karpukhinomgupsdiplom.content.common.ContentFragment
import com.raiden.karpukhinomgupsdiplom.content.common.models.UiContent
import org.koin.android.ext.android.inject

class ContactsFragment : ContentFragment() {
    override val viewModel: ContactsViewModel by inject()

    override val emptyContentMessageId = R.string.contacts_screen_no_updates

    override fun onItemClick(uiContent: UiContent) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }
}
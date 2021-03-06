package com.raiden.karpukhinomgupsdiplom.screens.content.contacts

import androidx.navigation.fragment.NavHostFragment
import com.raiden.karpukhinomgupsdiplom.R
import com.raiden.karpukhinomgupsdiplom.screens.content.common.ContentFragment
import com.raiden.karpukhinomgupsdiplom.screens.content.contacts.ContactsFragmentDirections.Companion.actionContactsFragmentToSingleContactFragment
import com.raiden.karpukhinomgupsdiplom.uimodels.UiContact
import com.raiden.karpukhinomgupsdiplom.uimodels.UiContent
import org.koin.android.ext.android.inject

class ContactsFragment : ContentFragment() {
    override val viewModel: ContactsViewModel by inject()

    override val emptyContentMessageId = R.string.contacts_screen_no_updates

    override val contentInstalled = R.string.content_alert_message_contact_installed
    override val contentChanged = R.string.content_alert_message_contact_changed
    override val contentDeleted = R.string.content_alert_message_contact_deleted

    override fun onItemClick(uiContent: UiContent) {
        uiContent as UiContact
        val singleContact = actionContactsFragmentToSingleContactFragment(uiContent)
        NavHostFragment.findNavController(this).navigate(singleContact)
    }
}
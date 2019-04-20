package com.raiden.karpukhinomgupsdiplom.screens.content.applications

import androidx.navigation.fragment.NavHostFragment
import com.raiden.karpukhinomgupsdiplom.R
import com.raiden.karpukhinomgupsdiplom.screens.content.applications.ApplicationsFragmentDirections.Companion.actionApplicationsFragmentToSingleApplicationFragment
import com.raiden.karpukhinomgupsdiplom.screens.content.common.ContentFragment
import com.raiden.karpukhinomgupsdiplom.uimodels.UiApplication
import com.raiden.karpukhinomgupsdiplom.uimodels.UiContent
import org.koin.android.ext.android.inject

class ApplicationsFragment : ContentFragment() {
    override val viewModel: ApplicationsViewModel by inject()

    override val emptyContentMessageId = R.string.applications_screen_no_updates

    override val contentInstalled = R.string.content_alert_message_app_installed
    override val contentChanged = R.string.content_alert_message_app_updated
    override val contentDeleted = R.string.content_alert_message_app_deleted

    override fun onItemClick(uiContent: UiContent) {
        uiContent as UiApplication
        val singleAppDirection = actionApplicationsFragmentToSingleApplicationFragment(uiContent)
        NavHostFragment.findNavController(this).navigate(singleAppDirection)
    }
}
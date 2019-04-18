package com.raiden.karpukhinomgupsdiplom.apps

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import com.raiden.karpukhinomgupsdiplom.R
import com.raiden.karpukhinomgupsdiplom.apps.adapter.ApplicationAdapter
import com.raiden.karpukhinomgupsdiplom.apps.models.UiApplication
import kotlinx.android.synthetic.main.fragment_contents.*
import kotlinx.android.synthetic.main.fragment_contents.view.*
import org.koin.android.viewmodel.ext.android.viewModel

class ApplicationsFragment : Fragment() {
    private val viewModel by viewModel<ApplicationsViewModel>()
    private val adapter: ApplicationAdapter by lazy {
        ApplicationAdapter(this::onItemClick)
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        val fragment = inflater.inflate(R.layout.fragment_contents, container, false)
        initRecycler(fragment)
        return fragment
    }

    private fun initRecycler(fragment: View) {
        fragment.applications_recycler_view.apply {
            layoutManager = LinearLayoutManager(this@ApplicationsFragment.context)
            adapter = this@ApplicationsFragment.adapter
        }
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        observeLoading()
        observeChangedApps()
    }

    private fun observeChangedApps() {
        viewModel.changedApps.observe(this, Observer { changedApps ->
            adapter.setApplications(changedApps)
            if (changedApps.isEmpty()) {
                showNoContentMessage()
            } else {
                hideNoContentMessage()
            }
        })
    }

    private fun observeLoading() {
        viewModel.isLoading.observe(this, Observer { isLoading ->
            if (isLoading) {
                showLoading()
            } else {
                showContent()
            }
        })
    }


    private fun onItemClick(uiApplication: UiApplication) {

    }

    private fun showLoading() {
        applications_view_animator.displayedChildId = R.id.applications_rotate_loading
        applications_rotate_loading.start()
    }

    private fun showContent() {
        applications_view_animator.displayedChildId = R.id.application_root_view
        applications_rotate_loading.stop()
    }

    private fun showNoContentMessage() {
        content_lentach.visibility = View.VISIBLE
        content_empty_message.visibility = View.VISIBLE
        content_empty_message.text = getString(R.string.applications_screen_no_updates)
    }

    private fun hideNoContentMessage() {
        content_lentach.visibility = View.GONE
        content_empty_message.visibility = View.GONE
    }
}
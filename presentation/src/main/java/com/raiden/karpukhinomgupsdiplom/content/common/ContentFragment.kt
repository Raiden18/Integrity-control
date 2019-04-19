package com.raiden.karpukhinomgupsdiplom.content.common

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.annotation.StringRes
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import com.raiden.karpukhinomgupsdiplom.R
import com.raiden.karpukhinomgupsdiplom.content.common.adapter.ContentAdapter
import com.raiden.karpukhinomgupsdiplom.content.common.models.UiContent
import kotlinx.android.synthetic.main.fragment_contents.*
import kotlinx.android.synthetic.main.fragment_contents.view.*

abstract class ContentFragment : Fragment() {
    protected abstract val viewModel: UiContentViewModel
    @get:StringRes
    protected abstract val emptyContentMessageId: Int
    private val adapter: ContentAdapter by lazy {
        ContentAdapter(this::onItemClick)
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        val fragment = inflater.inflate(R.layout.fragment_contents, container, false)
        initRecycler(fragment)
        return fragment
    }

    private fun initRecycler(fragment: View) {
        fragment.applications_recycler_view.apply {
            layoutManager = LinearLayoutManager(this@ContentFragment.context)
            adapter = this@ContentFragment.adapter
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


    protected abstract fun onItemClick(uiContent: UiContent)

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
        R.string.applications_screen_no_updates
        content_empty_message.text = getString(emptyContentMessageId)
    }

    private fun hideNoContentMessage() {
        content_lentach.visibility = View.GONE
        content_empty_message.visibility = View.GONE
    }
}
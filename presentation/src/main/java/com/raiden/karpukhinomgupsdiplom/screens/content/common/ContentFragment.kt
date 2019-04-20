package com.raiden.karpukhinomgupsdiplom.screens.content.common

import android.os.Bundle
import android.view.LayoutInflater
import android.view.MenuItem
import android.view.View
import android.view.ViewGroup
import androidx.annotation.StringRes
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.raiden.karpukhinomgupsdiplom.R
import com.raiden.karpukhinomgupsdiplom.screens.content.common.adapter.ContentAdapter
import com.raiden.karpukhinomgupsdiplom.uimodels.UiContent
import kotlinx.android.synthetic.main.fragment_contents.*
import kotlinx.android.synthetic.main.fragment_contents.view.*

abstract class ContentFragment : Fragment() {
    companion object {
        private const val HELP_KEY = "com.raiden.karpukhinomgupsdiplom.screens.content.common.helpmessage"
    }

    protected abstract val viewModel: ContentViewModel
    @get:StringRes
    protected abstract val emptyContentMessageId: Int
    @get:StringRes
    protected abstract val contentInstalled: Int
    @get:StringRes
    protected abstract val contentChanged: Int
    @get:StringRes
    protected abstract val contentDeleted: Int


    protected val colorMessageAlert: ColorInformationAlert by lazy {
        ColorInformationAlert().apply {
            contentInstalledMessage = contentInstalled
            contentChangeddMessage = contentChanged
            contentDeletedMessage = contentDeleted
        }
    }
    private val adapter: ContentAdapter by lazy {
        ContentAdapter(this::onItemClick)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setHasOptionsMenu(true)
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        val fragment = inflater.inflate(R.layout.fragment_contents, container, false)
        initRecycler(fragment)
        return fragment
    }

    private fun initRecycler(fragment: View) {
        val recyclerView = fragment.applications_recycler_view
        val linearLayoutManager = LinearLayoutManager(context)
        val itemDecorator = DividerItemDecoration(context, linearLayoutManager.orientation)
        recyclerView.apply {
            layoutManager = linearLayoutManager
            adapter = this@ContentFragment.adapter
            addItemDecoration(itemDecorator)
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
        content_empty_message.text = getString(emptyContentMessageId)
    }

    private fun hideNoContentMessage() {
        content_lentach.visibility = View.GONE
        content_empty_message.visibility = View.GONE
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.action_info_color -> {
                colorMessageAlert.show(requireActivity().supportFragmentManager, HELP_KEY)
                return true
            }
        }
        return false
    }
}
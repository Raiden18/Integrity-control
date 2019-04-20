package com.raiden.karpukhinomgupsdiplom.screens.singleapplication

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.provider.Settings
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import com.raiden.karpukhinomgupsdiplom.R
import com.raiden.karpukhinomgupsdiplom.databinding.FragmentSingleApplicationBinding
import com.raiden.karpukhinomgupsdiplom.screens.singleapplication.SingleApplicationFragmentArgs.Companion.fromBundle
import kotlinx.android.synthetic.main.fragment_single_application.*
import org.koin.android.viewmodel.ext.android.viewModel


class SingleApplicationFragment : Fragment() {
    private val uiApplication by lazy {
        fromBundle(arguments!!).uiApplication
    }
    private val viewModel by viewModel<SingleApplicationViewModel>()

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        viewModel.uiApplication = uiApplication
        val binding = DataBindingUtil.inflate<FragmentSingleApplicationBinding>(
            inflater,
            com.raiden.karpukhinomgupsdiplom.R.layout.fragment_single_application,
            container,
            false
        )
        binding.lifecycleOwner = this
        binding.viewModel = viewModel
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        single_app_open_in_store.setOnClickListener {
            openAppInGooglePlay()
        }
        single_app_open_in_options.setOnClickListener {
            openApplicationInSystem()
        }
        getIconAppIfIsNotDeleted()
        calculateAction()
    }

    private fun getIconAppIfIsNotDeleted() {
        if (!uiApplication.isDeleted) {
            getAppIcon()
        }
    }

    private fun calculateAction() {
        when {
            uiApplication.isInstalled -> {
                setActionMessage(R.string.single_app_name_installed)
                hideOldHash()
            }
            uiApplication.isDeleted -> {
                setActionMessage(R.string.single_app_name_delete)
                hideOldHash()
            }
            uiApplication.isUpdated -> setActionMessage(R.string.single_app_name_updated)
        }
    }

    private fun setActionMessage(messageId: Int) {
        single_app_action.text = getString(messageId)
    }

    private fun hideOldHash() {
        single_app_old_hash_label.visibility = View.GONE
        single_app_old_hash.visibility = View.GONE
    }

    private fun getAppIcon() {
        val iconApp = requireActivity().packageManager.getApplicationIcon(uiApplication.packageName)
        single_app_icon.background = iconApp
    }

    private fun openAppInGooglePlay() {
        val uri = "https://play.google.com/store/apps/details?id=${uiApplication.packageName}"
        val intent = Intent(Intent.ACTION_VIEW, Uri.parse(uri))
        startActivity(intent)
    }

    private fun openApplicationInSystem() {
        val intent = Intent(Settings.ACTION_APPLICATION_DETAILS_SETTINGS).apply {
            addFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
            data = Uri.fromParts("package", uiApplication.packageName, null)
        }
        startActivity(intent)
    }

}
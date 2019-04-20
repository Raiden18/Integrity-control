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
import com.raiden.karpukhinomgupsdiplom.databinding.FragmentSingleApplicationBinding
import com.raiden.karpukhinomgupsdiplom.screens.singleapplication.SingleApplicationFragmentArgs.Companion.fromBundle
import kotlinx.android.synthetic.main.fragment_single_application.*
import kotlinx.android.synthetic.main.fragment_single_application.view.*
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
        val iconApp = requireActivity().packageManager.getApplicationIcon(uiApplication.packageName)
        binding.root.single_app_icon.background = iconApp
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
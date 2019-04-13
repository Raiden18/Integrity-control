package com.raiden.karpukhinomgupsdiplom.info

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import com.raiden.karpukhinomgupsdiplom.R
import com.raiden.karpukhinomgupsdiplom.databinding.FragmentInfoBinding
import kotlinx.android.synthetic.main.fragment_info.*
import org.koin.android.viewmodel.ext.android.viewModel

class InfoFragment : Fragment() {

    private val viewModel by viewModel<InfoViewModel>()

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val binding = DataBindingUtil.inflate<FragmentInfoBinding>(
            inflater,
            R.layout.fragment_info,
            container,
            false
        )
        binding.lifecycleOwner = this
        binding.viewModel = viewModel
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initClickListeners()
    }

    private fun initClickListeners() {
        info_apps_button.setOnClickListener {
            openAppsScreen()
        }
        info_files_button.setOnClickListener {
            openFilesScreen()
        }
        info_contacts_button.setOnClickListener {
            openContactsScreen()
        }
    }

    private fun openAppsScreen() {

    }

    private fun openFilesScreen() {

    }

    private fun openContactsScreen() {

    }
}
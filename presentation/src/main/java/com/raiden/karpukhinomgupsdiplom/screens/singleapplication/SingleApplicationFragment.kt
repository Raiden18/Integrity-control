package com.raiden.karpukhinomgupsdiplom.screens.singleapplication

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import com.raiden.karpukhinomgupsdiplom.R
import com.raiden.karpukhinomgupsdiplom.databinding.FragmentSingleApplicationBinding
import com.raiden.karpukhinomgupsdiplom.screens.singleapplication.SingleApplicationFragmentArgs.Companion.fromBundle
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
            R.layout.fragment_single_application,
            container,
            false
        )
        binding.lifecycleOwner = this
        binding.viewModel = viewModel
        return binding.root
    }
}
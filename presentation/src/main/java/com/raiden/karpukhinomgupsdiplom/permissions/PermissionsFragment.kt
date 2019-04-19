package com.raiden.karpukhinomgupsdiplom.permissions

import android.Manifest
import android.os.Bundle
import android.view.*
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.navigation.fragment.NavHostFragment.findNavController
import com.livetyping.permission.PermissionBinder
import com.raiden.karpukhinomgupsdiplom.R
import com.raiden.karpukhinomgupsdiplom.databinding.FragmentPermissionsBinding
import com.raiden.karpukhinomgupsdiplom.permissions.PermissionsFragmentDirections.Companion.actionPermissionScreenToInfoFragment
import kotlinx.android.synthetic.main.fragment_permissions.*
import org.koin.android.ext.android.inject
import org.koin.android.viewmodel.ext.android.viewModel

class PermissionsFragment : Fragment() {
    private val viewModel by viewModel<PermissionsViewModel>()
    private val permissionBinder: PermissionBinder by inject()

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val binding = DataBindingUtil.inflate<FragmentPermissionsBinding>(
            inflater,
            R.layout.fragment_permissions,
            container,
            false
        )
        binding.lifecycleOwner = this
        binding.viewModel = viewModel
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setHasOptionsMenu(true)
        checkPermissions()
        viewModel.isAllPermissionsGranted.observe(this, Observer { isGranted ->
            isGranted?.let {
                if (it) {
                    openInfoScreen()
                } else {
                    permission_button.visibility = View.VISIBLE
                }
            }
        })
        permission_button.setOnClickListener {
            checkPermissions()
        }
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        menu.clear()
        super.onCreateOptionsMenu(menu, inflater)
    }
    private fun checkPermissions() {
        val permissions = listOf(Manifest.permission.READ_CONTACTS, Manifest.permission.READ_EXTERNAL_STORAGE)
        val permissionMessage = getString(R.string.permission_screen_message)
        permissionBinder.activePermission(permissions, permissionMessage) { permissionsResult ->
            viewModel.checkPermissions(permissionsResult)
        }
    }

    private fun openInfoScreen() {
        val navDirections = actionPermissionScreenToInfoFragment()
        findNavController(this).navigate(navDirections)
    }
}
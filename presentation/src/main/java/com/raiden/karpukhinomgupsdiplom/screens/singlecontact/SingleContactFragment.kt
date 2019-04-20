package com.raiden.karpukhinomgupsdiplom.screens.singlecontact

import android.content.Context
import android.os.Bundle
import android.view.*
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import com.raiden.karpukhinomgupsdiplom.R
import com.raiden.karpukhinomgupsdiplom.databinding.FragmentSingleContactBinding
import com.raiden.karpukhinomgupsdiplom.screens.singlecontact.SingleContactFragmentArgs.Companion.fromBundle
import kotlinx.android.synthetic.main.fragment_single_contact.*
import org.koin.android.viewmodel.ext.android.viewModel

class SingleContactFragment : Fragment() {
    private val viewModel by viewModel<SingleContactViewModel>()
    private val uiContact by lazy {
        fromBundle(arguments!!).uiContact
    }

    override fun onAttach(context: Context) {
        super.onAttach(context)
        viewModel.uiContact = uiContact
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        setHasOptionsMenu(true)
        val binding = DataBindingUtil.inflate<FragmentSingleContactBinding>(
            inflater,
            R.layout.fragment_single_contact,
            container,
            false
        )
        binding.lifecycleOwner = this
        binding.viewModel = viewModel
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        hideOldNameAndNumberIfDifferent()
        hideOldNameAndPhoneIfNotChanged()
        chooseAction()
    }

    private fun hideOldNameAndNumberIfDifferent() {
        hideOldNameIfDifferent()
        hideOldNumberIfDifferent()
    }

    private fun chooseAction() {
        when {
            uiContact.isDeleted -> setActionMessage(R.string.single_content_name_delete)
            uiContact.isInstalled -> setActionMessage(R.string.single_content_name_installed)
            uiContact.isUpdated -> setActionMessage(R.string.single_content_name_updated)
        }
    }

    private fun setActionMessage(idString: Int) {
        single_contact_action.text = getString(idString)
    }

    private fun hideOldNameAndPhoneIfNotChanged() {
        if (uiContact.isDeleted || uiContact.isInstalled) {
            hideOldName()
            hideOldNumber()
        }
    }

    private fun hideOldNameIfDifferent() {
        if (uiContact.nameContent == uiContact.oldName) {
            hideOldName()
        }
    }

    private fun hideOldNumberIfDifferent() {
        if (uiContact.currentPhoneNumber == uiContact.oldPoneNumber) {
            hideOldNumber()
        }
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        menu.clear()
        super.onCreateOptionsMenu(menu, inflater)
    }

    private fun hideOldNumber() {
        single_contact_changed_name.visibility = View.GONE
        single_contact_changed_name_label.visibility = View.GONE
    }

    private fun hideOldName() {
        single_contact_changed_number_label.visibility = View.GONE
        single_contact_changed_number.visibility = View.GONE
    }
}
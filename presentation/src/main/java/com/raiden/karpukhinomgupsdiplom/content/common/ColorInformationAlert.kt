package com.raiden.karpukhinomgupsdiplom.content.common

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.annotation.StringRes
import androidx.fragment.app.DialogFragment
import com.raiden.karpukhinomgupsdiplom.R
import kotlinx.android.synthetic.main.content_info_alert.view.*

class ColorInformationAlert : DialogFragment() {
    @StringRes
    var contentInstalledMessage: Int = 0
    @StringRes
    var contentChangeddMessage: Int = 0
    @StringRes
    var contentDeletedMessage: Int = 0


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setStyle(DialogFragment.STYLE_NO_TITLE, R.style.AlertDialogColorHint)
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val view = inflater.inflate(R.layout.content_info_alert, container, false)
        view.dialog_installed_message.text = getString(contentInstalledMessage)
        view.dialog_changed_message.text = getString(contentChangeddMessage)
        view.dialog_deleted_message.text = getString(contentDeletedMessage)
        view.dialog_alert_error_ok.setOnClickListener {
            dismiss()
        }
        return view
    }
}
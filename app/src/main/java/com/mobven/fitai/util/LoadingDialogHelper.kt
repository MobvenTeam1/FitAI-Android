package com.mobven.fitai.util

import android.view.LayoutInflater
import androidx.appcompat.app.AlertDialog
import androidx.fragment.app.FragmentActivity
import com.mobven.fitai.R

object LoadingDialogHelper {

    private var loadingDialog: AlertDialog? = null

    fun showLoadingDialog(activity: FragmentActivity) {
        val inflater: LayoutInflater = activity.layoutInflater
        val dialogView = inflater.inflate(R.layout.loading_animation, null)

        val builder = AlertDialog.Builder(activity)
        builder.setView(dialogView)
        builder.setCancelable(false)

        loadingDialog = builder.create()
        loadingDialog?.window?.setBackgroundDrawableResource(android.R.color.transparent)
        loadingDialog?.show()
    }

    fun dismissLoadingDialog() {
        loadingDialog?.dismiss()
        loadingDialog = null
    }
}
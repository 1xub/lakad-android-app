package com.xub.lakad.presentation.common.utils

import android.content.Context
import android.content.DialogInterface
import androidx.appcompat.app.AlertDialog

object DialogFactory {
    fun createConfirmDialog(
        context: Context, titleResource: Int, msgResource: Int,
        yesListener: DialogInterface.OnClickListener
    ): AlertDialog {
        return createConfirmDialog(
            context, context.getString(titleResource),
            context.getString(msgResource), yesListener
        )
    }

    fun createConfirmDialog(
        context: Context, title: String, msg: String,
        yesListener: DialogInterface.OnClickListener
    ): AlertDialog {
        return AlertDialog.Builder(context).setTitle(title)
            .setMessage(msg)
            .setPositiveButton("Yes", yesListener)
            .setNegativeButton("No") { dialog, id -> dialog.cancel() }.create()
    }
}
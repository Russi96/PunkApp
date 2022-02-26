package com.app.punkapp.utils

import android.view.View
import android.widget.TextView
import com.google.android.material.snackbar.BaseTransientBottomBar
import com.google.android.material.snackbar.Snackbar

fun showSnackBar(view: View, message: String) {
    val snackBar = Snackbar.make(
        view,
        message,
        Snackbar.LENGTH_INDEFINITE
    )
    val snackBarView = snackBar.view
    val snackTextView =
        snackBarView.findViewById<TextView>(com.google.android.material.R.id.snackbar_text)

    snackBar.animationMode = BaseTransientBottomBar.ANIMATION_MODE_FADE
    snackTextView.maxLines = 4
    snackBar.setAction("Ok") {}
        .show()
}
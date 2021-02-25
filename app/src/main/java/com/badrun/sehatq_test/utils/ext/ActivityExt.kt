package com.badrun.sehatq_test.utils.ext

import android.app.Activity
import android.content.Intent
import android.view.View
import android.view.inputmethod.InputMethodManager
import androidx.fragment.app.FragmentActivity
import com.google.android.material.snackbar.Snackbar

fun Activity.hideSoftKeyboard() {
    val inputMethodManager =
        this.getSystemService(Activity.INPUT_METHOD_SERVICE) as InputMethodManager
    inputMethodManager.hideSoftInputFromWindow(this.currentFocus?.windowToken, 0)
}

fun Activity.showMessage(view: View, message: String, duration: Int = Snackbar.LENGTH_SHORT) {
    Snackbar.make(view, message, duration).show()
}

fun FragmentActivity.showMessage(
    view: View,
    message: String,
    duration: Int = Snackbar.LENGTH_SHORT
) {
    Snackbar.make(view, message, duration).show()
}

fun FragmentActivity.showMessage(
    view: View,
    message: String,
    duration: Int = Snackbar.LENGTH_SHORT,
    title: String,
    action: () -> Unit
) {
    Snackbar.make(view, message, duration).setAction(title) {
        action.invoke()
    }.show()
}

fun Activity.share(name: String, price: String) {
    val share = Intent.createChooser(
        Intent().apply {
            action = Intent.ACTION_SEND
            val content = "Product name: $name with price: $price"
            putExtra(Intent.EXTRA_TEXT, content)
            type = "text/plain"
            flags = Intent.FLAG_GRANT_READ_URI_PERMISSION
        },
        "Bagikan ke"
    )
    this.startActivity(share)
}

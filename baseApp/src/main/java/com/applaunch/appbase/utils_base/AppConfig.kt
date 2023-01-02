package com.applaunch.appbase.utils_base

import android.app.Activity
import android.content.Context
import android.content.Intent
import android.net.Uri
import androidx.activity.OnBackPressedCallback
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.app.AppCompatDelegate
import androidx.fragment.app.Fragment


fun Activity.changeStatusBarColor(colorId: Int) {
    window.statusBarColor = this.resources.color(colorId)
}

fun Activity.forceLightTheme() {
    AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO)
}

fun AppCompatActivity.onBackPressed(isFinish: Boolean = true, onBackPressed: () -> Unit) {
    onBackPressedDispatcher.addCallback(this,
        object : OnBackPressedCallback(true) {
            override fun handleOnBackPressed() {
                onBackPressed.invoke()
                if (isFinish) finish()
            }
        }
    )
}

fun Fragment.onBackPressed(isFinish: Boolean = true, onBackPressed: () -> Unit) {
    requireActivity().onBackPressedDispatcher.addCallback(this,
        object : OnBackPressedCallback(true) {
            override fun handleOnBackPressed() {
                onBackPressed.invoke()
                if (isFinish) requireActivity().finish()
            }
        }
    )
}

fun Context.shareLink(url: String) {
    val intent =
        Intent(Intent.ACTION_VIEW, Uri.parse(url))
    this.startActivity(intent)
}
package com.example.itunesapp.util

import android.app.Activity
import android.content.Context
import android.content.Intent
import android.graphics.Typeface
import androidx.activity.result.ActivityResultLauncher
import androidx.core.app.ActivityOptionsCompat
import androidx.core.content.ContextCompat
import com.example.itunesapp.R
import com.tapadoo.alerter.Alerter

/**
 * Created by Berk Ç. on 11.11.2021.
 */

inline fun <reified T : Any> newIntent(context: Context): Intent =
    Intent(context, T::class.java)

inline fun <reified T : Any> Activity.launchActivity(
    init: Intent.() -> Unit = {}
) {
    val intent = newIntent<T>(this)
    intent.init()
    startActivity(intent)
}

fun Activity.showAlert(text: String) {
    Alerter.create(this)
        .setText(text)
        .setBackgroundColorInt(ContextCompat.getColor(this, R.color.crimson))
        .setTextTypeface(Typeface.DEFAULT_BOLD)
        .setDuration(1200)
        .show()
}
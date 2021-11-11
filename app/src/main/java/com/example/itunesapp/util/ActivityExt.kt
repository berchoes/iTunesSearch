package com.example.itunesapp.util

import android.app.Activity
import android.content.Context
import android.content.Intent
import androidx.activity.result.ActivityResultLauncher
import androidx.core.app.ActivityOptionsCompat

/**
 * Created by Berk Ç. on 11.11.2021.
 */

inline fun <reified T : Any> newIntent(context: Context): Intent =
    Intent(context, T::class.java)

inline fun <reified T : Any> Activity.launchActivity(
    resultLauncher: ActivityResultLauncher<Intent>? = null,
    activityOptionsCompat: ActivityOptionsCompat? = null,
    noinline init: Intent.() -> Unit = {}
) {
    val intent = newIntent<T>(this)
    intent.init()
    resultLauncher?.launch(intent, activityOptionsCompat) ?: startActivity(intent)
}
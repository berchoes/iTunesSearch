package com.example.itunesapp.widget

import android.app.Dialog
import android.content.Context
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.view.Window
import com.example.itunesapp.R

/**
 * Created by Berk Ç. on 11.11.2021.
 */

class LoadingDialog(context: Context) : Dialog(context, R.style.Theme_ITunesApp_Transparent) {

    init {
        window?.apply {
            requestFeature(Window.FEATURE_NO_TITLE)
            setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))
        }
        setContentView(R.layout.loading_dialog)
        setCancelable(true)
    }
}

package com.example.itunesapp.util.binding

import android.view.View
import androidx.databinding.BindingAdapter
import com.example.itunesapp.util.gone
import com.example.itunesapp.util.visible

/**
 * Created by Berk Ç. on 14.11.2021.
 */


@BindingAdapter("isVisible")
fun isVisible(view: View, isVisible: Boolean){
    if (isVisible) view.visible() else view.gone()
}
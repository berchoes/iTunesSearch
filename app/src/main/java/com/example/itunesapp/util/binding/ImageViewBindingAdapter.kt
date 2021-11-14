package com.example.itunesapp.util.binding

import androidx.appcompat.widget.AppCompatImageView
import androidx.databinding.BindingAdapter
import com.example.itunesapp.util.visible
import com.squareup.picasso.Picasso

/**
 * Created by Berk Ç. on 12.11.2021.
 */


@BindingAdapter("loadImage")
fun loadImageFromUrl(view: AppCompatImageView, url: String?) {
    if (!url.isNullOrEmpty()) {
        Picasso.get()
            .load(url)
            .into(view)

        view.visible()
    }
}
package com.example.itunesapp.util.extensions

import android.view.MenuItem

/**
 * Created by Berk Ç. on 14.11.2021.
 */

fun MenuItem.setOnCollapsedListener(onCollapsed: () -> Unit){
    this.setOnActionExpandListener(object : MenuItem.OnActionExpandListener {
        override fun onMenuItemActionExpand(p0: MenuItem?): Boolean = true

        override fun onMenuItemActionCollapse(p0: MenuItem?): Boolean {
            onCollapsed.invoke()
            return true
        }
    })
}

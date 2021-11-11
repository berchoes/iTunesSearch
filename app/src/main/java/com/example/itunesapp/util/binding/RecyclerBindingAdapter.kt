package com.example.itunesapp.util.binding

import androidx.databinding.BindingAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.itunesapp.model.SearchItem
import com.example.itunesapp.ui.list.SearchListAdapter

/**
 * Created by Berk Ç. on 11.11.2021.
 */

@BindingAdapter(value = ["setAdapter","setList"], requireAll = false)
fun setRecyclerAdapter(
    recyclerView: RecyclerView,
    adapter: SearchListAdapter?,
    items: List<SearchItem>?
) {
    adapter?.let {
        if (recyclerView.adapter != it) {
            recyclerView.adapter = it
        }
        recyclerView.recycledViewPool.clear()
        it.submitList(items)
    }
}
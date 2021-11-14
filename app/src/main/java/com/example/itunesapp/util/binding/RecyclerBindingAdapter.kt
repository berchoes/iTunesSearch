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
    itemsInfo: Triple<List<SearchItem>?,String,Boolean>?,
) {
    adapter?.let {
        if (recyclerView.adapter != it) {
            recyclerView.adapter = it
        }
        recyclerView.recycledViewPool.clear()
        itemsInfo?.third?.let { third -> it.submitList(itemsInfo.first, third) }
    }
}
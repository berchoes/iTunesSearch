package com.example.itunesapp.util.binding

import androidx.databinding.BindingAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.itunesapp.model.NewListInfo
import com.example.itunesapp.model.SearchItem
import com.example.itunesapp.ui.list.SearchListAdapter

/**
 * Created by Berk Ç. on 11.11.2021.
 */

@BindingAdapter(value = ["setAdapter","setList"], requireAll = false)
fun setRecyclerAdapter(
    recyclerView: RecyclerView,
    adapter: SearchListAdapter?,
    itemsInfo: NewListInfo?,
) {
    adapter?.let {
        if (recyclerView.adapter != it) {
            recyclerView.adapter = it
        }
        recyclerView.recycledViewPool.clear()
        itemsInfo?.let { info -> it.submitList(info.list,info.isSearchedList) }
    }
}
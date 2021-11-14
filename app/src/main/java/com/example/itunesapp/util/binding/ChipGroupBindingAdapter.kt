package com.example.itunesapp.util.binding

import androidx.databinding.BindingAdapter
import androidx.databinding.InverseBindingAdapter
import androidx.databinding.InverseBindingListener
import com.example.itunesapp.R
import com.example.itunesapp.common.SearchTypes
import com.google.android.material.chip.ChipGroup

/**
 * Created by Berk Ç. on 12.11.2021.
 */

@InverseBindingAdapter(attribute = "onFilterTypeChange")
fun onFilterTypeChange(view: ChipGroup): SearchTypes = when (view.checkedChipId) {
    R.id.chipEbook-> SearchTypes.EBOOK
    R.id.chipMovies -> SearchTypes.MOVIES
    R.id.chipMusic -> SearchTypes.MUSIC
    R.id.chipPodcast -> SearchTypes.PODCAST
    else -> SearchTypes.ALL
}

@BindingAdapter("onFilterTypeChangeAttrChanged")
fun setFilterTypeChangeListener(view: ChipGroup, attrChange: InverseBindingListener) {
    view.setOnCheckedChangeListener { _, _ ->
        attrChange.onChange()
    }
}

@BindingAdapter("onFilterTypeChange", requireAll = false)
fun onFilterTypeChange(view: ChipGroup, filterType: SearchTypes?){}
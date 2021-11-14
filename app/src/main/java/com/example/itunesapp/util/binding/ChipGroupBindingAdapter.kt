package com.example.itunesapp.util.binding

import androidx.databinding.BindingAdapter
import androidx.databinding.InverseBindingAdapter
import androidx.databinding.InverseBindingListener
import com.example.itunesapp.common.SearchTypes
import com.google.android.material.chip.ChipGroup

/**
 * Created by Berk Ç. on 12.11.2021.
 */

//@InverseBindingAdapter(attribute = "onFilterTypeChange")
//fun onFilterTypeChange(view: ChipGroup): SearchTypes = when (view.checkedChipId) {
//    R.id.chip1 -> SearchTypes.EBOOK
//    R.id.chip2 -> RoomCount.TWO
//    R.id.chip3 -> RoomCount.THREE
//    R.id.chip4 -> RoomCount.FOUR
//    R.id.chip5 -> RoomCount.FIVE
//    else -> {
//        RoomCount.FIVE
//    }
//}

@BindingAdapter("onFilterTypeChangeAttrChanged")
fun setOnRoomCheckedChangeListener(view: ChipGroup, attrChange: InverseBindingListener) {
    view.setOnCheckedChangeListener { _, _ ->
        attrChange.onChange()
    }
}

@BindingAdapter("onFilterTypeChange", requireAll = false)
fun onFilterTypeChange(view: ChipGroup, filterType: SearchTypes?){}
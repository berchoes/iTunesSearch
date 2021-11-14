package com.example.itunesapp.ui.list

/**
 * Created by Berk Ç. on 11.11.2021.
 */
import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.itunesapp.databinding.ItemSearchListBinding
import com.example.itunesapp.model.SearchItem
import com.squareup.picasso.Picasso
import javax.inject.Inject


class SearchListAdapter @Inject constructor() :
    RecyclerView.Adapter<SearchListAdapter.ViewHolder>() {

    private var items = mutableListOf<SearchItem>()
    var onItemClicked: ((SearchItem) -> Unit)? = null


    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): SearchListAdapter.ViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        val itemBinding = ItemSearchListBinding.inflate(layoutInflater, parent, false)
        return ViewHolder(itemBinding)
    }

    override fun onBindViewHolder(holder: SearchListAdapter.ViewHolder, position: Int) {
        holder.bind(items[position])
    }

    override fun getItemCount() = items.size

    override fun getItemId(position: Int): Long {
        return position.toLong()
    }

    override fun getItemViewType(position: Int): Int {
        return position
    }

    inner class ViewHolder(private val binding: ItemSearchListBinding) :
        RecyclerView.ViewHolder(binding.root) {

        @SuppressLint("SetTextI18n")
        fun bind(item: SearchItem) {

            binding.title.text = "${item.artistName} - ${item.collectionName}"

            if (!item.artworkUrl100.isNullOrEmpty()) {
                Picasso.get()
                    .load(item.artworkUrl100)
                    .into(binding.image)
            }

            binding.root.setOnClickListener {
                onItemClicked?.invoke(item)
            }
        }
    }

    @SuppressLint("NotifyDataSetChanged")
    private fun whenListIsNotInitial(newData: List<SearchItem>, fromSearchBar: Boolean){
        if (fromSearchBar) {
            items.clear()
            items.addAll(newData)
            notifyDataSetChanged()
        } else {
            val oldSize = items.size
            items.addAll(newData.drop(oldSize))
            notifyItemRangeInserted(oldSize, newData.size)
        }
    }

    @SuppressLint("NotifyDataSetChanged")
    fun submitList(list: List<SearchItem>?, fromSearchBar: Boolean) {
        list?.let {
            if (items.isEmpty()) {
                items.addAll(it)
                notifyDataSetChanged()
            } else {
                whenListIsNotInitial(it,fromSearchBar)
            }
        }
    }
}
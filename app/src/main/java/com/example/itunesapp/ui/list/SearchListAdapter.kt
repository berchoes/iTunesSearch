package com.example.itunesapp.ui.list

/**
 * Created by Berk Ç. on 11.11.2021.
 */
import android.annotation.SuppressLint
import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.itunesapp.databinding.ItemSearchListBinding
import com.example.itunesapp.model.SearchItem
import dagger.hilt.android.qualifiers.ApplicationContext
import javax.inject.Inject


class SearchListAdapter @Inject constructor(@ApplicationContext private val context: Context) :
    RecyclerView.Adapter<SearchListAdapter.ViewHolder>() {

    private var items = listOf<SearchItem>()
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

    inner class ViewHolder(private val binding: ItemSearchListBinding) :
        RecyclerView.ViewHolder(binding.root) {

        @SuppressLint("SetTextI18n")
        fun bind(item: SearchItem) {

            binding.title.text = "${item.artistName} - ${item.collectionName}"

            if (!item.artworkUrl100.isNullOrEmpty()) {
                Glide.with(context)
                    .load(item.artworkUrl100)
                    .into(binding.image)
            }

            binding.root.setOnClickListener {
                onItemClicked?.invoke(item)
            }
        }
    }

    @SuppressLint("NotifyDataSetChanged")
    fun submitList(list: List<SearchItem>?) {
        list?.let {
            items = it
            notifyDataSetChanged()
        }
    }
}
package com.example.itunesapp.ui.list

import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import androidx.activity.viewModels
import androidx.appcompat.widget.SearchView
import androidx.recyclerview.widget.RecyclerView
import com.example.itunesapp.R
import com.example.itunesapp.base.BaseActivity
import com.example.itunesapp.common.Constants
import com.example.itunesapp.databinding.ActivityListBinding
import com.example.itunesapp.ui.details.DetailsActivity
import com.example.itunesapp.util.*
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class ListActivity : BaseActivity<ActivityListBinding, ListViewModel>() {

    @Inject
    lateinit var adapter: SearchListAdapter

    override fun injectVM(): Lazy<ListViewModel> = viewModels()

    override fun getLayout(): Int = R.layout.activity_list

    override fun onCreateFinished(savedInstance: Bundle?) {
        binding.searchListAdapter = this.adapter
        binding.viewModel = this.viewModel
        collectEvents()
        initListeners()
        viewModel.search()
    }

    private fun initListeners() {
        adapter.onItemClicked = {
            launchActivity<DetailsActivity> {
                putExtra(Constants.SEND_ITEM_TO_DETAIL_PAGE, gson.toJson(it))
            }
        }

        binding.rvList.addOnScrollListener(object : RecyclerView.OnScrollListener() {
            override fun onScrollStateChanged(recyclerView: RecyclerView, newState: Int) {
                super.onScrollStateChanged(recyclerView, newState)

                if (!recyclerView.canScrollVertically(RecyclerView.FOCUS_DOWN) && newState == RecyclerView.SCROLL_STATE_IDLE) {
                    viewModel.loadNewPage()
                }
            }
        })
    }

    private fun collectEvents() {
        collect(viewModel.errorMessage) { showAlert(it) }

        collect(viewModel.searchType){
            viewModel.search(viewModel.latestSearchQuery ?: Constants.SEARCH_ALL,fromSearchBar = true)
        }
        collect(viewModel.resultInfo){
            hideKeyboard()
        }
    }



    override fun onCreateOptionsMenu(menu: Menu): Boolean {

        menuInflater.inflate(R.menu.menu, menu)
        val menuItem = menu.findItem(R.id.search)
        val searchView = menuItem.actionView as SearchView
        searchView.queryHint = getString(R.string.search)

        menuItem.setOnActionExpandListener(object : MenuItem.OnActionExpandListener{
            override fun onMenuItemActionExpand(p0: MenuItem?): Boolean = true

            override fun onMenuItemActionCollapse(p0: MenuItem?): Boolean {
                if(searchView.query.isNullOrEmpty()) viewModel.search(fromSearchBar = true)
                return true
            }
        })

        searchView.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(query: String?): Boolean {
                query?.let { viewModel.search(it, true) }
                return true
            }
            override fun onQueryTextChange(newText: String?): Boolean {
                if(!newText.isNullOrEmpty()) viewModel.latestSearchQuery = newText else viewModel.latestSearchQuery = null
                return true
            }
        })

        return super.onCreateOptionsMenu(menu)
    }
}
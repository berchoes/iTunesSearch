package com.example.itunesapp.ui.list

import android.os.Bundle
import androidx.activity.viewModels
import com.example.itunesapp.R
import com.example.itunesapp.base.BaseActivity
import com.example.itunesapp.databinding.ActivityListBinding
import com.example.itunesapp.util.collect
import com.example.itunesapp.util.showAlert
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class ListActivity : BaseActivity<ActivityListBinding>() {

    @Inject
    lateinit var adapter: SearchListAdapter

    private val viewModel: ListViewModel by viewModels()

    override fun getLayout(): Int = R.layout.activity_list

    override fun onCreateFinished(savedInstance: Bundle?) {
        binding.searchListAdapter = this.adapter
        binding.viewModel = this.viewModel
        collectEvents()
        viewModel.searchAll()
    }

    override fun initListeners() {}

    private fun collectEvents(){
        collect(viewModel.errorMessage){
            showAlert(it)
        }
    }

}
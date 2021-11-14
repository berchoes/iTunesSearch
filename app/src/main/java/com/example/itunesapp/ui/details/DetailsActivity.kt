package com.example.itunesapp.ui.details

import android.os.Bundle
import androidx.activity.viewModels
import androidx.databinding.library.baseAdapters.BR
import com.example.itunesapp.R
import com.example.itunesapp.base.BaseActivity
import com.example.itunesapp.databinding.ActivityDetailsBinding
import com.example.itunesapp.util.collect

class DetailsActivity : BaseActivity<ActivityDetailsBinding, DetailsViewModel>() {

    override fun injectVM(): Lazy<DetailsViewModel> = viewModels()

    override fun getLayout(): Int = R.layout.activity_details

    override fun onCreateFinished(savedInstance: Bundle?) {
        binding.viewModel = this.viewModel
        collectEvents()
    }

    override fun initListeners() {}

    private fun collectEvents(){
        collect(viewModel.currentData){
            supportActionBar?.title = it?.artistName
        }
    }
}
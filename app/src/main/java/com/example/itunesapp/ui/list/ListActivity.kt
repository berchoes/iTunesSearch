package com.example.itunesapp.ui.list

import android.os.Bundle
import androidx.activity.viewModels
import com.example.itunesapp.R
import com.example.itunesapp.base.BaseActivity
import com.example.itunesapp.databinding.ActivityListBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class ListActivity : BaseActivity<ActivityListBinding>() {

    private val viewModel: ListViewModel by viewModels()

    override fun getLayout(): Int = R.layout.activity_list

    override fun onCreateFinished(savedInstance: Bundle?) {
        viewModel.searchAll()
    }

    override fun initListeners() {
    }

}
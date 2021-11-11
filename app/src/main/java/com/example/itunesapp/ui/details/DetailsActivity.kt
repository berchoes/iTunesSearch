package com.example.itunesapp.ui.details

import android.os.Bundle
import com.example.itunesapp.R
import com.example.itunesapp.base.BaseActivity
import com.example.itunesapp.databinding.ActivityDetailsBinding

class DetailsActivity : BaseActivity<ActivityDetailsBinding>() {

    override fun getLayout(): Int = R.layout.activity_details

    override fun onCreateFinished(savedInstance: Bundle?) {

    }

    override fun initListeners() {
    }
}
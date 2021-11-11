package com.example.itunesapp.base

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding

/**
 * Created by Berk Ç. on 10.11.2021.
 */

abstract class BaseActivity<DB: ViewDataBinding> : AppCompatActivity() {

    protected lateinit var binding: DB

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this,getLayout())
        binding.lifecycleOwner = this
        onCreateFinished(savedInstanceState)
        initListeners()
    }

    protected abstract fun getLayout() : Int
    protected abstract fun onCreateFinished(savedInstance: Bundle?)
    protected abstract fun initListeners()

}
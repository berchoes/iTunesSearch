package com.example.itunesapp.base

import android.os.Bundle
import android.view.Menu
import android.widget.SearchView
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.lifecycle.ViewModel
import com.example.itunesapp.R
import com.example.itunesapp.util.collect
import com.example.itunesapp.widget.LoadingDialog
import com.google.gson.Gson
import javax.inject.Inject
import android.app.Activity
import android.content.Context
import android.view.View
import android.view.inputmethod.InputMethodManager


/**
 * Created by Berk Ç. on 10.11.2021.
 */

abstract class BaseActivity<DB : ViewDataBinding, VM : BaseViewModel> : AppCompatActivity() {

    protected lateinit var binding: DB
    protected lateinit var viewModel: VM
    private lateinit var loadingDialog: LoadingDialog

    @Inject
    protected lateinit var gson: Gson

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, getLayout())
        viewModel = injectVM().value
        loadingDialog = LoadingDialog(this)
        binding.lifecycleOwner = this
        collectBaseEvents()
        onCreateFinished(savedInstanceState)
    }

    private fun collectBaseEvents() {

        collect(viewModel.loadingState) {
            if (it) showLoading() else hideLoading()
        }
    }

    private fun showLoading() {
        if (!loadingDialog.isShowing) loadingDialog.show()
    }

    override fun finish() {
        loadingDialog.dismiss()
        super.finish()
    }

    private fun hideLoading() {
        if (loadingDialog.isShowing) loadingDialog.dismiss()
    }

    protected fun hideKeyboard() {
        val inputManager = getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
        inputManager.hideSoftInputFromWindow(
            currentFocus?.windowToken,
            InputMethodManager.HIDE_NOT_ALWAYS
        )
    }

    protected abstract fun injectVM(): Lazy<VM>
    protected abstract fun getLayout(): Int
    protected abstract fun onCreateFinished(savedInstance: Bundle?)

}
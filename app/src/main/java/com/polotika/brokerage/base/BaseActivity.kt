package com.polotika.brokerage.base

import android.app.AlertDialog
import android.app.ProgressDialog
import android.content.DialogInterface
import android.net.ConnectivityManager
import android.net.NetworkInfo
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider

abstract class BaseActivity<DB : ViewDataBinding, VM : BaseViewModel<*>> : AppCompatActivity() {

    lateinit var viewModel: VM
    lateinit var binding: DB
    private var loader: ProgressDialog? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, initializeLayout())
        viewModel = ViewModelProvider(this).get(initializeViewModel())


    }

    abstract fun initializeLayout(): Int

    abstract fun initializeViewModel(): Class<VM>


    fun showMessage(message: String) {
        AlertDialog.Builder(this).setMessage(message).setCancelable(false)
            .setPositiveButton("Ok", DialogInterface.OnClickListener { dialog, which ->
                dialog.dismiss()
            }).show()
    }

    fun showDialog(
        title: String,
        message: String,
        posBtnText: String,
        posBtnClickListener: DialogInterface.OnClickListener,
        negBtnText: String
    ) {
        AlertDialog.Builder(this)
            .setTitle(title)
            .setMessage(message)
            .setCancelable(false)
            .setPositiveButton(posBtnText, posBtnClickListener)
            .setNegativeButton(negBtnText) { dialog, _ ->
                dialog.dismiss()
            }.show()
    }

    fun showLoader() {
        loader = ProgressDialog(this)
        loader?.apply { ->
            setMessage("Loading...")
            setCancelable(false)
            show()
        }

    }

    fun hideLoader() {
        if (loader != null && loader?.isShowing!!) {
            loader?.dismiss()
        }
    }


    private fun observers() {
        viewModel.message.observe(this, Observer {
            showMessage(it)
        })
        viewModel.loader.observe(this, Observer {
            when (it) {
                true -> showLoader()
                false -> hideLoader()
            }
        })
    }

    fun isInternetAvailable(): Boolean {

        val cm = getSystemService(CONNECTIVITY_SERVICE) as ConnectivityManager
        val activeNetwork: NetworkInfo? = cm.activeNetworkInfo
        return activeNetwork?.isConnectedOrConnecting == true
    }
}
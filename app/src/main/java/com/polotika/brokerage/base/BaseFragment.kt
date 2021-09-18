package com.polotika.brokerage.base

import android.app.AlertDialog
import android.app.ProgressDialog
import android.content.DialogInterface
import android.net.ConnectivityManager
import android.net.NetworkInfo
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider

abstract class BaseFragment<DB:ViewDataBinding,VM:BaseViewModel<*>>:Fragment() {
    lateinit var binding:DB
    lateinit var viewModel:VM
    private var loader: ProgressDialog?=null

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = DataBindingUtil.inflate(inflater,initializeLayout(),container,false)
        viewModel = ViewModelProvider(requireActivity()).get(initializeViewModel())
        return binding.root
    }
    abstract fun initializeLayout():Int

    abstract fun initializeViewModel():Class<VM>


    fun showMessage(message: String) {
        AlertDialog.Builder(requireContext()).setMessage(message).setCancelable(false)
            .setPositiveButton("Ok", DialogInterface.OnClickListener { dialog, which ->
                dialog.dismiss()
            }).show()
    }

    fun showDialog(
        title: String,
        message: String,
        posBtnText: String,
        posBtnClickListener: DialogInterface.OnClickListener,
        negBtnText:String
    ) {
        AlertDialog.Builder(requireContext())
            .setTitle(title)
            .setMessage(message)
            .setCancelable(false)
            .setPositiveButton(posBtnText, posBtnClickListener)
            .setNegativeButton(negBtnText) { dialog, _ ->
                dialog.dismiss()
            }.show()
    }

    fun showLoader() {
        loader = ProgressDialog(requireContext())
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

    fun isInternetAvailable():Boolean{

        val cm = requireActivity().getSystemService(AppCompatActivity.CONNECTIVITY_SERVICE) as ConnectivityManager
        val activeNetwork: NetworkInfo? = cm.activeNetworkInfo
        return activeNetwork?.isConnectedOrConnecting == true
    }

}
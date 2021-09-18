package com.polotika.brokerage.ui.services

import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.lifecycle.Observer
import androidx.lifecycle.lifecycleScope
import com.polotika.brokerage.R
import com.polotika.brokerage.base.BaseFragment
import com.polotika.brokerage.databinding.FragmentServicesBinding
import com.polotika.brokerage.pojo.adapters.ServicesRecyclerViewAdapter
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

@AndroidEntryPoint
class ServicesFragment : BaseFragment<FragmentServicesBinding, ServicesViewModel>() {

    private val TAG = "ServicesFragment"
    private val STATE_KEY = "STATE"
    private val adapter = ServicesRecyclerViewAdapter()


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        Log.d(TAG, "onViewCreated: start")
        if (savedInstanceState == null && viewModel.savedInstanceState) {
            Log.d(TAG, "onViewCreated: $savedInstanceState")
            showLoader()
            lifecycleScope.launch {
                delay(1000)
                viewModel.getServicesList()
                hideLoader()
            }
        }
        binding.servicesRv.adapter = adapter

        viewModel.servicesList.observe(viewLifecycleOwner, Observer {
            adapter.changeData(it)
        })

    }

    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        outState.putBoolean(STATE_KEY, true)
    }

    override fun initializeLayout() = R.layout.fragment_services

    override fun initializeViewModel() = ServicesViewModel::class.java
}
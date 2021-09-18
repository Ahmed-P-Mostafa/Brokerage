package com.polotika.brokerage.ui.services

import android.util.Log
import androidx.lifecycle.MutableLiveData
import com.polotika.brokerage.base.BaseNavigator
import com.polotika.brokerage.base.BaseViewModel
import com.polotika.brokerage.pojo.models.ServiceItem
import com.polotika.brokerage.pojo.repository.main.ServicesListUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class ServicesViewModel @Inject constructor(private val userCase: ServicesListUseCase) :BaseViewModel<BaseNavigator>() {

    private val TAG = "ServicesViewModel"
    var savedInstanceState: Boolean = true
    val servicesList = MutableLiveData<List<ServiceItem>>()


    fun getServicesList(){
        Log.d(TAG, "getServicesList: ")
        savedInstanceState = false
        servicesList.value =  userCase.execute()
    }
}
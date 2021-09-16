package com.polotika.brokerage.base

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

open class BaseViewModel<N> :ViewModel() {
    val message= MutableLiveData<String>()
    val loader = MutableLiveData<Boolean>()
    var navigator :N?=null


}
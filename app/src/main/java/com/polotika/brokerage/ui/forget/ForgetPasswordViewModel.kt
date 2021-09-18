package com.polotika.brokerage.ui.forget

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.polotika.brokerage.base.BaseNavigator
import com.polotika.brokerage.base.BaseViewModel
import com.polotika.brokerage.pojo.models.Event
import com.polotika.brokerage.pojo.repository.login.ForgetUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Deferred
import kotlinx.coroutines.async
import kotlinx.coroutines.cancel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ForgetPasswordViewModel @Inject constructor(private val useCase: ForgetUseCase) :
    BaseViewModel<BaseNavigator>() {

    val email = MutableLiveData<String>()

    fun sendRequest(email: String):Boolean {
        this@ForgetPasswordViewModel.email.value = ""

        var result :Boolean = false
        viewModelScope.launch {
                result =  useCase.execute(email)
        }
        return result
    }

}
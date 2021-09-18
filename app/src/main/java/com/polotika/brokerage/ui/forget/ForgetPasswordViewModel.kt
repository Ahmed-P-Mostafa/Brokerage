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
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ForgetPasswordViewModel @Inject constructor(private val useCase: ForgetUseCase) :
    BaseViewModel<BaseNavigator>() {

    private val TAG = "ForgetPasswordViewModel"
    val requestResult = MutableLiveData<Event>()
    val email = MutableLiveData<String>()

    fun sendRequest(email: String) {
        Log.d(TAG, "sendRequest: ")
        viewModelScope.launch {
            val result: Deferred<Event> = async {
                useCase.execute(email)

            }
            requestResult.value = result.await()
            result.cancel()
        }

    }

}